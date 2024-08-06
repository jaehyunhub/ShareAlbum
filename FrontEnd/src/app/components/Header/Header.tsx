"use client";
import React, { useState, useEffect, useRef } from "react";
import { useRouter } from "next/navigation";
import Link from "next/link";
import { BsSearch } from "react-icons/bs";
import { RiMessageLine } from "react-icons/ri";
import { FiCompass } from "react-icons/fi";
import {
  AiOutlineHeart,
  AiOutlineHome,
  AiOutlinePlusCircle,
} from "react-icons/ai";
import { IoPersonCircleOutline } from "react-icons/io5";
import { HiOutlineBellAlert } from "react-icons/hi2";

import AddNewPostModal from "../Modal/AddNewPostModal";
import AcceptGroupModal from "../Modal/AcceptGroupModal";

import axios from "axios";
import { useAuthDispatch, useAuthState } from "@/app/context/\bAuthContext";
import { SearchResultsNickname } from "@/app/interfaces/MemberInfo";

const Header = () => {
  const router = useRouter();
  const [showAddPostModal, setShowAddPostModal] = useState(false);
  const [showAcceptGroupModal, setShowAcceptGroupModal] = useState(false);
  const dispatch = useAuthDispatch();
  const { user: memberInfo } = useAuthState();
  const [searchNickName, setSearchNickName] = useState("");
  const [searchResults, setSearchResults] = useState<SearchResultsNickname[]>([]);
  const [searchPlaceholder, setSearchPlaceholder] = useState("검색");

  const searchInputRef = useRef<HTMLInputElement>(null);

  const toggleAddPostModal = () => setShowAddPostModal(!showAddPostModal);
  const toggleAcceptGroupModal = () => setShowAcceptGroupModal(!showAcceptGroupModal);

  const logout = () => {
    if (window.confirm("정말 로그아웃하시겠습니까?")) {
      console.log(memberInfo?.nickname);
      axios.post(`http://localhost:8080/logout/${memberInfo?.nickname}`)
        .then(() => {
          dispatch({ type: 'LOGOUT' });
          router.push('/');
        })
        .catch((error) => {
          console.log(error);
        });
    }
  };

  const handleSearchChange = (e: React.ChangeEvent<HTMLInputElement>) => {
    setSearchNickName(e.target.value);
    setSearchPlaceholder("검색");
  };

  useEffect(() => {
    const fetchData = async () => {
      if (searchNickName.length > 0) {
        try {
          const response = await axios.get<SearchResultsNickname[]>(
            `http://localhost:8080/search/${searchNickName}`,
            {
              params: { data: memberInfo?.nickname }
            }
          );
          const searchResults = response.data;
          console.log(searchResults)
          setSearchResults(searchResults);
        } catch (error) {
          console.log(error);
        }
      } else {
        setSearchResults([]);
      }
    };

    fetchData();
  }, [searchNickName]);

  const handleSearchIconClick = () => {
    if (searchNickName.length === 0) {
      setSearchPlaceholder("검색어를 작성해주세요");
      searchInputRef.current?.focus();
    }
  };

  return (
    <div className="fixed top-0 z-10 flex items-center justify-around w-full h-16 bg-white shadow-md">
      <div className="flex items-center space-x-40 md:justify-center">
        <Link href="/">
          <div className="text-xl font-semibold tracking-wider cursor-pointer select-none">
            ShareAlbum
          </div>
        </Link>
        <div className="relative flex items-center px-2 space-x-4 bg-gray-100 border border-gray-400 rounded-lg group group-focus:border-gray-400">
          <label htmlFor="search" onClick={handleSearchIconClick}>
            <BsSearch className="text-lg text-gray-400 cursor-pointer" />
          </label>
          <input
            ref={searchInputRef}
            type="search"
            name="search"
            value={searchNickName}
            onChange={handleSearchChange}
            placeholder={searchPlaceholder}
            className="w-full px-2 py-1 transition bg-gray-100 rounded-sm outline-none placeholder:text-sm"
          />
          {searchResults.length > 0 && (
            <div className="absolute top-12 left-0 w-full bg-white border border-gray-300 rounded-lg shadow-lg z-10">
              {searchResults.map((result) => (
                <div key={result.id} className="p-2 hover:bg-gray-200">
                  <Link href={`/searchResults/${result.nickname}`}>{result.nickname}</Link>
                </div>
              ))}
            </div>
          )}
        </div>
        <div className="flex items-center text-xl">
          <div className="flex mr-3">
            <AiOutlineHome />
          </div>
          
          <div className="flex mr-3">
            <button onClick={toggleAcceptGroupModal}> 
              <HiOutlineBellAlert />
            </button>
          </div>
          {showAcceptGroupModal && <AcceptGroupModal clickModal={toggleAcceptGroupModal} />}
          
          <div className="flex mr-3">
            <button onClick={toggleAddPostModal}>
              <AiOutlinePlusCircle />
            </button>
          </div>
          {showAddPostModal && <AddNewPostModal clickModal={toggleAddPostModal} />}
          
          <div className="flex mr-3">
            <FiCompass />
          </div>
          <div className="flex mr-3">
            <AiOutlineHeart />
          </div>
          <div className="flex mr-3">
            <IoPersonCircleOutline />
          </div>
          <button
            className="bg-[#0095F6] py-1 h-4/5 text-white active:scale-95 transform transition disabled:bg-op px-6 disabled:scale-100 rounded text-sm font-semibold"
            onClick={logout}>
            Logout
          </button>
        </div>
      </div>
    </div>
  );
};
export default Header;
