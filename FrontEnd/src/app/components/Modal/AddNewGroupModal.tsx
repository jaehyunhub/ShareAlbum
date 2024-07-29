import React, { useState, useRef } from "react";
import { FaTimes } from "react-icons/fa";
import axios from "axios";
import { useAuthState } from "@/app/context/\bAuthContext";

const AddNewGroupModal = (props: any) => {
  const { clickModal } = props;
  const [groupTitle, setGroupTitle] = useState("");
  const [groupCategory, setGroupCategory] = useState("FAMILY");
  const { user: memberInfo } = useAuthState();
  const modalRef = useRef<HTMLDivElement>(null);

  const handleSubmit = async (event: React.FormEvent) => {
    event.preventDefault();

    if (!memberInfo || !memberInfo.loginId) {
      console.error("로그인 정보가 없습니다.");
      return;
    }

    try {
      const response = await axios.post("http://localhost:8080/createGroup", {
        groupTitle,
        groupCategory: groupCategory.toUpperCase(),
        loginId: memberInfo.loginId
      });
      console.log("그룹이 추가되었습니다:", response.data);
      clickModal(); // 모달 닫기
    } catch (error) {
      console.error("그룹 추가 중 오류 발생:", error);
    }
  };
  const handleClickOutSide = (event: MouseEvent) => {
    if (modalRef.current && !modalRef.current.contains(event.target as Node)) {
      clickModal();
    }
  };

  React.useEffect(() => {
    document.addEventListener("mousedown", handleClickOutSide);
    return () => {
      document.removeEventListener("mousedown", handleClickOutSide);
    };
  }, []);

  return (
    <div className="fixed inset-0 z-50 flex items-center justify-center bg-black bg-opacity-50">
      <div ref={modalRef} className="relative flex flex-col h-auto w-[500px] bg-white rounded-lg shadow-lg">
        <button className="absolute top-3 right-3 text-gray-600 hover:text-gray-800" onClick={clickModal}>
          <FaTimes size={24} />
        </button>
        <div className="w-full p-5">
          <h1 className="text-center text-xl">새 그룹 만들기</h1>
          <div className="h-[0.8px] w-full bg-slate-400 mt-5"></div>
          <form onSubmit={handleSubmit} className="mt-5">
            <label className="block mb-2 text-sm font-medium text-gray-700">
              그룹 제목
              <input
                type="text"
                value={groupTitle}
                onChange={(e) => setGroupTitle(e.target.value)}
                className="w-full p-2 border border-gray-300 rounded-md mt-1"
                required
              />
            </label>
            <label className="block mb-2 text-sm font-medium text-gray-700">
              카테고리
              <select
                value={groupCategory}
                onChange={(e) => setGroupCategory(e.target.value)}
                className="w-full p-2 border border-gray-300 rounded-md mt-1"
              >
                <option value="FAMILY">Family</option>
                <option value="COMPANY">Company</option>
                <option value="FRIEND">Friend</option>
              </select>
            </label>
            <button
              type="submit"
              className="w-full mt-4 py-2 bg-blue-500 text-white font-semibold rounded-md"
            >
              추가
            </button>
          </form>
        </div>
      </div>
    </div>
  );
};

export default AddNewGroupModal;
