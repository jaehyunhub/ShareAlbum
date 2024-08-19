import React, { createContext, useContext, useReducer, useEffect, ReactNode } from 'react';
import axios from 'axios';
import useSWR from 'swr';
import { MemberInfo } from '@/app/interfaces/MemberInfo';

interface State {
  authenticated: boolean;
  user: MemberInfo | null;
  loading: boolean;
}

const initialState: State = {
  authenticated: false,
  user: null,
  loading: true,
};

const StateContext = createContext<State>(initialState);
const DispatchContext = createContext<React.Dispatch<Action>>(() => null);


interface Action {
  type: 'LOGIN' | 'LOGOUT' | 'STOP_LOADING';
  payload?: any;
}

const reducer = (state: State, action: Action): State => {
  switch (action.type) {
    case 'LOGIN':
      return {
        ...state,
        authenticated: true,
        user: action.payload,
        loading: false,
      };
    case 'LOGOUT':
      return {
        ...state,
        authenticated: false,
        user: null,
        loading: false,
      };
    case 'STOP_LOADING':
      return {
        ...state,
        loading: false,
      };
    default:
      throw new Error(`Unknown action type: ${action.type}`);
  }
};

const fetcher = (url: string) => 
  axios.get(url).then((res) => res.data);

export const AuthProvider = ({ children }: { children: ReactNode }) => {
  const [state, dispatch] = useReducer(reducer, initialState);

  const { data: user, error } = useSWR(
    state.authenticated ? `http://localhost:8080/redis/${state.user?.nickname}` : null, fetcher  // fetcher를 적절하게 호출
  );

  useEffect(() => {
    const savedNickname = localStorage.getItem('nickname');

    const loadUser = async () => {
      if (savedNickname && !state.authenticated) {
        // 로컬 저장소에 있는 닉네임으로 요청
        try {
          const fetchedUser = await fetcher(`http://localhost:8080/redis/${savedNickname}`);
          dispatch({ type: 'LOGIN', payload: fetchedUser });
        } catch (error) {
          console.log("Failed to fetch user data:", error);
        } finally {
          dispatch({ type: 'STOP_LOADING' });
        }
      }
    };

    // 로그인 상태일 때 로컬 저장소에 닉네임 저장
    if (state.user?.nickname) {
      localStorage.setItem('nickname', state.user.nickname);
    }

    loadUser();
  }, [state.authenticated]);

  return (
    <DispatchContext.Provider value={dispatch}>
      <StateContext.Provider value={state}>{children}</StateContext.Provider>
    </DispatchContext.Provider>
  );
};

export const useAuthState = () => useContext(StateContext);
export const useAuthDispatch = () => useContext(DispatchContext);
