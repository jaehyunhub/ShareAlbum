"use client";
import { useAuthState } from "@/app/context/\bAuthContext";
import React, { useState, useEffect } from "react";
import { FaPlus } from "react-icons/fa";
import axios from "axios";
import AddNewGroupModal from "@/app/components/Modal/AddNewGroupModal";

const UserMainPage: React.FC = () => {
  const { user: memberInfo } = useAuthState();
  const [activeTab, setActiveTab] = useState<string>("");
  const [showModal, setShowModal] = useState(false);

  // 버튼 클릭시 모달 버튼 클릭 유무를 설정하는 state 함수
  const clickModal = () => setShowModal(!showModal);

  useEffect(() => {
    if (memberInfo) {
      if (memberInfo.myGroupList.length > 0) {
        setActiveTab(memberInfo.myGroupList[0].groupTitle); // 첫 번째 탭을 기본 활성 탭으로 설정
      }
    }
  }, [memberInfo]);

  if (!memberInfo) {
    return <div>Loading...</div>;
  }

  const handleTabClick = (tab: string) => {
    setActiveTab(tab);
  };

  const renderGridItems = () => {
    console.log("Active Tab:", activeTab);
    const albumGroupId = memberInfo.myGroupList.find(group => group.groupTitle === activeTab)?.id;
    console.log("Album Group ID:", albumGroupId);
    console.log("Member Albums:", memberInfo.myAlbum);
    if (albumGroupId !== undefined && memberInfo.myAlbum[albumGroupId]) {
      return memberInfo.myAlbum[albumGroupId].map((album) => (
        <div key={album.id} className="w-full h-72 bg-gray-300 flex items-center justify-center">
          {album.imagePath ? (
            <img 
              src={`http://localhost:8080${album.imagePath}`} 
              alt={album.content} 
              className="w-full h-full object-cover"
              onError={(e) => { console.error('Image load error:', e); }}
              onLoad={() => { console.log('Image loaded:', `http://localhost:8080${album.imagePath}`); }}
            />
          ) : (
            <span>{album.id}</span>
          )}
        </div>
      ));
    } else {
      return <div>사진이 없습니다.</div>;
    }
  };

  return (
    <div className="bg-white min-h-screen flex flex-col items-center">
      {/* 프로필 섹션 */}
      <div className="w-full max-w-4xl mt-20 flex flex-col items-center bg-[#FAFAFA]">
        <div className="flex w-full items-center p-4">
          <div className="w-1/3 flex flex-col items-center justify-center">
            <div className="w-32 h-32 bg-gray-200 rounded-full mb-4 flex items-center justify-center">
              <img src="/path/to/profile-pic.jpg" alt="프로필" className="w-full h-full rounded-full object-cover" />
            </div>
          </div>
          <div className="w-2/3 pl-4 flex flex-col justify-center">
            <div className="text-black text-2xl font-bold">{memberInfo.nickname}</div>
            <div className="text-black mt-2">게시물 455 팔로워 1730만 팔로우 0</div>
            <div className="text-black mt-2">{memberInfo.name}</div>
          </div>
        </div>
      </div>

      {/* 탭 섹션 */}
      <div className="w-full max-w-4xl p-4">
        <div className="flex justify-center items-center border-b">
          {memberInfo.myGroupList.map((group) => (
            <button
              key={group.id}
              onClick={() => handleTabClick(group.groupTitle)}
              className={`py-2 ml-5 mr-5 ${activeTab === group.groupTitle ? "border-b-2 border-black" : ""}`}
            >
              {group.groupTitle}
            </button>
          ))}
          <button
            className="py-2 px-4 border rounded-full flex items-center justify-center"
            onClick={clickModal}
          >
            <FaPlus size={16} />
          </button>
          {showModal && <AddNewGroupModal clickModal={clickModal} />}
        </div>
      </div>

      {/* 그리드 섹션 */}
      <div className="w-full max-w-4xl grid grid-cols-3 gap-4 mt-4">
        {renderGridItems()}
      </div>
    </div>
  );
};

export default UserMainPage;
