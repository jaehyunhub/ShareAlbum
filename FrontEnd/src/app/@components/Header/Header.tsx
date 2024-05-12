"use client"
import React,{useState} from "react";
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
import AddNewPostModal from "./AddNewPostModal";
export { FaTimesCircle as Cross } from "react-icons/fa";
export { FiCompass as Compass } from "react-icons/fi";
export { RiMessengerLine as Messenger } from "react-icons/ri";
export { IoPersonCircleOutline as Profile } from "react-icons/io5";
export {
  AiOutlineHome as Home,
  AiOutlinePlusCircle as Add,
  AiOutlineHeart as Heart,
} from "react-icons/ai";

const Header = () => {
    const [showModal, setShowModal] = useState(false)
    
	// 버튼 클릭시 모달 버튼 클릭 유무를 설정하는 state 함수
    const clickModal = () => setShowModal(!showModal)

  return (
    <div className="fixed top-0 z-10 flex items-center justify-around w-full h-16 bg-white shadow-md">
      <div className="flex items-center space-x-40  md:justify-center">
        <Link href="/">
          <div className="text-xl font-semibold tracking-wider cursor-pointer select-none">
            ShareAlbum
          </div>
        </Link>
        <div className="flex items-center px-2 space-x-4 bg-gray-100 border border-gray-400 rounded-lg group group-focus:border-gray-400">
          <label htmlFor="search">
            <BsSearch className="text-lg text-gray-400"></BsSearch>
          </label>
          <input
            type="search"
            name="search"
            className="w-full px-2 py-1 transition bg-gray-100 rounded-sm outline-none   hover:bg-yellow-500  focus:bg-yellow-900 placeholder:text-sm"
          />
        </div>
        <div className="flex items-center text-xl">
          <div className="flex mr-3">
            <AiOutlineHome></AiOutlineHome>
          </div>
          <div className="flex mr-3">
            <RiMessageLine></RiMessageLine>
          </div>
          <div className="flex mr-3">
            <button onClick={clickModal}>
                <AiOutlinePlusCircle/>
            </button>
          </div>
          {showModal && <AddNewPostModal clickModal={clickModal} />}
          <div className="flex mr-3">
            <FiCompass></FiCompass>
          </div>
          <div className="flex mr-3">
            <AiOutlineHeart></AiOutlineHeart>
          </div>
          <div className="flex mr-3">
            <IoPersonCircleOutline></IoPersonCircleOutline>
          </div>
          <button className="bg-[#0095F6] py-1 h-4/5 text-white active:scale-95 transform transition disabled:bg-op px-6 disabled:scale-100 rounded text-sm font-semibold">
            Logout
          </button>
        </div>
      </div>
    </div>
  );
};
export default Header;
