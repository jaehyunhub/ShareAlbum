// InviteGroupModal.tsx
import React, { useRef, useEffect, useState } from "react";
import { FaTimes } from "react-icons/fa";
import axios from "axios";
import { useAuthState } from "@/app/context/\bAuthContext";
import { group } from "console";

interface InviteGroupModalProps {
  clickModal: () => void;
  inviteeNickname: string;
}

const InviteGroupModal: React.FC<InviteGroupModalProps> = ({ clickModal, inviteeNickname }) => {
  const [selectedGroup, setSelectedGroup] = useState("");
  const { user: memberInfo } = useAuthState();
  const modalRef = useRef<HTMLDivElement>(null);

  const handleClickOutside = (event: MouseEvent) => {
    if (modalRef.current && !modalRef.current.contains(event.target as Node)) {
      clickModal();
    }
  };

  const handleSubmit = async (event: React.FormEvent) => {
    event.preventDefault();

    if (!memberInfo || !memberInfo.nickname) {
      console.error("로그인 정보가 없습니다.");
      return;
    }
    try {
      const response = await axios.post("http://localhost:8080/invite", {
        inviterNickname: memberInfo.nickname,
        inviteeNickname,
        groupId: selectedGroup
      });
      console.log("초대가 성공적으로 전송되었습니다:", response.data);
      clickModal(); // 모달 닫기
    } catch (error) {
      console.error("초대 전송 중 오류 발생:", error);
    }
  };

  useEffect(() => {
    document.addEventListener("mousedown", handleClickOutside);
    return () => {
      document.removeEventListener("mousedown", handleClickOutside);
    };
  }, []);

  return (
    <div className="fixed inset-0 z-50 flex items-center justify-center bg-black bg-opacity-50" onClick={clickModal}>
      <div ref={modalRef} className="relative flex flex-col h-auto w-[500px] bg-white rounded-lg shadow-lg" onClick={(e) => e.stopPropagation()}>
        <button className="absolute top-3 right-3 text-gray-600 hover:text-gray-800" onClick={clickModal}>
          <FaTimes size={24} />
        </button>
        <div className="w-full p-5">
          <h1 className="text-center text-xl">그룹 초대</h1>
          <div className="h-[0.8px] w-full bg-slate-400 mt-5"></div>
          <form onSubmit={handleSubmit} className="mt-5">
            <label className="block mb-2 text-sm font-medium text-gray-700">
              그룹 선택
              <select
                value={selectedGroup}
                onChange={(e) => setSelectedGroup(e.target.value)}
                className="w-full p-2 border border-gray-300 rounded-md mt-1"
                required
              >
                <option value="">그룹을 선택하세요</option>
                {memberInfo?.myGroupList.map((group) => (
                  <option key={group.id} value={group.id}>
                    {group.groupTitle}
                  </option>
                ))}
              </select>
            </label>
            <button
              type="submit"
              className="w-full mt-4 py-2 bg-blue-500 text-white font-semibold rounded-md"
            >
              초대
            </button>
          </form>
        </div>
      </div>
    </div>
  );
};

export default InviteGroupModal;
