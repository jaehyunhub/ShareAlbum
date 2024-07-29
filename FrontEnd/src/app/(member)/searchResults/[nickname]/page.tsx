"use client";
import React, { useEffect, useState } from "react";
import { useRouter, usePathname } from "next/navigation";
import axios from "axios";
import { SearchResultsMemberInfo } from "@/app/interfaces/MemberInfo";
import { useAuthState } from "@/app/context/\bAuthContext";
import { MdGroups } from "react-icons/md";
import InviteGroupModal from "@/app/components/Modal/InviteGroupModal"; // 모달 컴포넌트를 임포트합니다.

const ProfilePage: React.FC = () => {
  const router = useRouter();
  const pathname = usePathname();
  const nickname = pathname.split('/').pop();
  const { user: memberInfo } = useAuthState();
  const [activeTab, setActiveTab] = useState<string>("");
  const [searchResultsMemberInfo, setSearchResultsMemberInfo] = useState<SearchResultsMemberInfo | null>(null);
  const [showInviteModal, setShowInviteModal] = useState(false); // 모달을 제어하는 상태입니다.

  const clickModal = () => setShowInviteModal(!showInviteModal);

  useEffect(() => {
    const fetchProfileData = async () => {
      if (nickname) {
        try {
          const response = await axios.get<SearchResultsMemberInfo>(`http://localhost:8080/searchResults/${nickname}`);
          setSearchResultsMemberInfo(response.data);
        } catch (error) {
          console.log(error);
        }
      }
    };

    fetchProfileData();
  }, [nickname]);

  if (!searchResultsMemberInfo) {
    return <div>Loading...</div>;
  }

  const handleTabClick = (tab: string) => {
    setActiveTab(tab);
  };

  const handleInvite = () => {
    setShowInviteModal(true);
  };

  const renderGridItems = () => {
    const albumGroupId = searchResultsMemberInfo.myGroupList.find(group => group.groupTitle === activeTab)?.id;
    if (albumGroupId !== undefined && searchResultsMemberInfo.myAlbum[albumGroupId]) {
      return searchResultsMemberInfo.myAlbum[albumGroupId].map((album) => (
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
            <div className="flex items-center">
              <div className="text-black text-2xl font-bold">{searchResultsMemberInfo.nickname}</div>
              {memberInfo && memberInfo.nickname !== searchResultsMemberInfo.nickname && (
                <button
                  className="ml-5 py-2 px-4 bg-white text-black rounded border-2 border-black text-lg"
                  onClick={handleInvite}
                >
                  <MdGroups size={24} />
                </button>
              )}
            </div>
            <div className="text-black mt-2">게시물 455 팔로워 1730만 팔로우 0</div>
            <div className="text-black mt-2">{searchResultsMemberInfo.name}</div>
          </div>
        </div>
      </div>

      {/* 탭 섹션 */}
      <div className="w-full max-w-4xl p-4">
        <div className="flex justify-center items-center border-b">
          {searchResultsMemberInfo.myGroupList.map((group) => (
            <button
              key={group.id}
              onClick={() => handleTabClick(group.groupTitle)}
              className={`py-2 ml-5 mr-5 ${activeTab === group.groupTitle ? "border-b-2 border-black" : ""}`}
            >
              {group.groupTitle}
            </button>
          ))}
        </div>
      </div>

      {/* 그리드 섹션 */}
      <div className="w-full max-w-4xl grid grid-cols-3 gap-4 mt-4">
        {renderGridItems()}
      </div>

      {/* 초대 모달 */}
      {showInviteModal && 
      <InviteGroupModal inviteeNickname={searchResultsMemberInfo.nickname} clickModal={clickModal} />}
    </div>
  );
};

export default ProfilePage;
