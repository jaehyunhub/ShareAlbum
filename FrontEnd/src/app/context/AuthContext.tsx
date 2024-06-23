import React, { createContext, useContext, useReducer, useEffect, ReactNode } from 'react';
import axios from 'axios';
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

export const AuthProvider = ({ children }: { children: ReactNode }) => {
  const [state, dispatch] = useReducer(reducer, initialState);

  useEffect(() => {
    const loadUser = async () => {
      try {
        const res = await axios.get("http://localhost:8080/auth/me",{
        });
        dispatch({ type: 'LOGIN', payload: res.data });
      } catch (error) {
        console.log(error);
      } finally {
        dispatch({ type: 'STOP_LOADING' });
      }
    };
    loadUser();
  }, []);

  return (
    <DispatchContext.Provider value={dispatch}>
      <StateContext.Provider value={state}>{children}</StateContext.Provider>
    </DispatchContext.Provider>
  );
};

export const useAuthState = () => useContext(StateContext);
export const useAuthDispatch = () => useContext(DispatchContext);
