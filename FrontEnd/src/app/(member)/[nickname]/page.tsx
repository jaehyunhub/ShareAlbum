"use client";
import { useAuthState } from "@/app/context/\bAuthContext";
import React, { useState, useRef, useEffect } from "react";
import { FaPlus, FaChevronLeft, FaChevronRight } from "react-icons/fa";
import AddNewGroupModal from "@/app/components/Modal/AddNewGroupModal";
import { group } from "console";

const UserMainPage: React.FC = () => {
  const { user: memberInfo } = useAuthState();
  const [activeTab, setActiveTab] = useState<string>("");
  const [showModal, setShowModal] = useState(false);
  const [visibleStartIndex, setVisibleStartIndex] = useState(0); // 현재 보여지는 그룹 시작 인덱스
  const MAX_VISIBLE_GROUPS = 4; // 한번에 보여지는 최대 그룹 수
  const clickModal = () => setShowModal(!showModal);


  // `onUploadComplete`가 호출되면 해당 그룹을 활성화
  const handleUploadComplete = (groupId: number) => {
    const group = memberInfo?.myGroupList.find((group) => group.groupId === groupId);
    if (group && memberInfo) {
      setActiveTab(memberInfo.myGroupList[0].groupTitle);
      setVisibleStartIndex(0); // visibleStartIndex도 초기화
      const groupIndex = memberInfo.myGroupList.findIndex((g) => g.groupId === groupId);
      setVisibleStartIndex(Math.max(0, Math.min(groupIndex, memberInfo.myGroupList.length - MAX_VISIBLE_GROUPS)));
    }
  };

  useEffect(() => {
    if (memberInfo && memberInfo.myGroupList.length > 0) {
      const savedTab = sessionStorage.getItem("activeTab");
      const startIndex = Number(sessionStorage.getItem("startIndex"));
      const myGroupLength = memberInfo.myGroupList.length;
      const previousGroupLength = Number(sessionStorage.getItem("groupLength"));      

      if(savedTab === null){
        console.log("============일루 들어오나용??============")
        console.log("============!activeTab============")
        setActiveTab(memberInfo.myGroupList[0].groupTitle);
        setVisibleStartIndex(0); // visibleStartIndex도 초기화
        sessionStorage.setItem("activeTab", activeTab);
        sessionStorage.setItem("startIndex", "0");
        sessionStorage.setItem("groupLength", memberInfo.myGroupList.length.toString());
        return  
      }

      if(savedTab !== null && startIndex !== null && myGroupLength === previousGroupLength){
        console.log("============일루 들어오나용??============")
        console.log("============savedTab && startIndex && myGroupLength == previousGroupLength============")
        setVisibleStartIndex(startIndex);
        setActiveTab(savedTab);
        return
      }
      
      if(myGroupLength > previousGroupLength){
        console.log("============일루 들어오나용??============")
        console.log("============myGroupLength > previousGroupLength============")
        let startIndex = 0;
          if (myGroupLength > MAX_VISIBLE_GROUPS) {
            if (myGroupLength % MAX_VISIBLE_GROUPS === 0) {
              startIndex = myGroupLength - MAX_VISIBLE_GROUPS;
            } else {
              startIndex = myGroupLength - (myGroupLength % MAX_VISIBLE_GROUPS);
            }
          }
          setVisibleStartIndex(startIndex);
          setActiveTab(memberInfo.myGroupList[myGroupLength - 1].groupTitle);
          sessionStorage.setItem("activeTab", memberInfo.myGroupList[myGroupLength - 1].groupTitle);
          sessionStorage.setItem("startIndex", startIndex.toString());
          sessionStorage.setItem("groupLength", myGroupLength.toString());
          return  
      }
    }
  }, [memberInfo]);


  if (!memberInfo) {
    return <div>Loading...</div>;
  }

  const handleTabClick = (tab: string) => {
    setActiveTab(tab);
    sessionStorage.setItem("activeTab",tab);
  };

  const handleNext = () => {
    if (
      visibleStartIndex + MAX_VISIBLE_GROUPS <
      memberInfo.myGroupList.length
    ) {
      setVisibleStartIndex(visibleStartIndex + 4);
      sessionStorage.setItem("startIndex",(visibleStartIndex + 4).toString());
    }
  };

  const handlePrevious = () => {
    if (visibleStartIndex > 0) {
      if (visibleStartIndex - 4 < 0) {
        setVisibleStartIndex(visibleStartIndex - 1);
        sessionStorage.setItem("startIndex",(visibleStartIndex - 1).toString());
      } else {
        setVisibleStartIndex(visibleStartIndex - 4);
        sessionStorage.setItem("startIndex",(visibleStartIndex - 4).toString());
      }
    }
  };

  const visibleGroups = memberInfo.myGroupList.slice(
    visibleStartIndex,
    visibleStartIndex + MAX_VISIBLE_GROUPS
  );

  const renderGridItems = () => {
    const albumGroupId = memberInfo.myGroupList.find(
      (group) => group.groupTitle === activeTab
    )?.groupId;
    if (albumGroupId !== undefined && memberInfo.myAlbum[albumGroupId]) {
      return memberInfo.myAlbum[albumGroupId].map((album) => (
        <div
          key={album.id}
          className="w-full h-72 mr-5 bg-gray-300 flex items-center justify-center"
        >
          {album.imagePath ? (
            <img
              src={`http://localhost:8080${album.imagePath}`}
              alt={album.content}
              className="w-full h-full object-cover"
            />
          ) : (
            <span>{album.id}</span>
          )}
        </div>
      ));
    } else {
      return <div className="flex items-center ">사진이 없습니다.</div>;
    }
  };

  return (
    <div className="bg-white min-h-screen flex flex-col items-center">
      <div className="w-full max-w-4xl mt-20 flex flex-col items-center bg-[#FAFAFA]">
        <div className="flex w-full items-center p-4">
          <div className="w-1/3 flex flex-col items-center justify-center">
            <div className="w-32 h-32 bg-gray-200 rounded-full mb-4 flex items-center justify-center">
              <img
                src="/images/profile2.webp"
                className="w-full h-full rounded-full"
              />
            </div>
          </div>
          <div className="w-2/3 pl-4 flex flex-col justify-center">
            <div className="text-black text-2xl font-bold">
              {memberInfo.nickname}
            </div>
            <div className="text-black mt-2">
              팔로워 0만 팔로우 0
            </div>
            <div className="text-black mt-2">{memberInfo.name}</div>
          </div>
        </div>
      </div>

      <div className="w-full max-w-4xl p-4">
        <div className="space-x-4 flex justify-center items-center border-b">
          {visibleStartIndex > 0 && (
            <button onClick={handlePrevious} className="mr-2">
              <FaChevronLeft />
            </button>
          )}
          {visibleGroups.map((group) => (
            <button
              key={group.id}
              onClick={() => handleTabClick(group.groupTitle)}
              className={`py-2 ml-2 mr-2 ${
                activeTab === group.groupTitle ? "border-b-2 border-black" : ""
              }`}
            >
              {group.groupTitle}
            </button>
          ))}
          {visibleStartIndex + MAX_VISIBLE_GROUPS <
            memberInfo.myGroupList.length && (
            <button onClick={handleNext} className="ml-2">
              <FaChevronRight />
            </button>
          )}
          <button
            className="py-2 px-4 border rounded-full flex items-center justify-center ml-2"
            onClick={clickModal}
          >
            <FaPlus size={16} />
          </button>
          {showModal && <AddNewGroupModal clickModal={clickModal}  onUploadComplete={handleUploadComplete}/>}
        </div>
      </div>

      <div className="w-full max-w-4xl grid grid-cols-3 gap-4 mt-4">
        {renderGridItems()}
      </div>
    </div>
  );
};

export default UserMainPage;
