import React, { ChangeEvent, useRef, useState } from "react";
import { PiInstagramLogoThin } from "react-icons/pi";
import { FaTimes } from "react-icons/fa";
import { useAuthState } from "@/app/context/\bAuthContext";
import axios from "axios";

const AddNewPostModal = (props: any) => {
  // 전달받은 state 함수
  const { clickModal } = props;
  const { user: memberInfo } = useAuthState();
  const modalRef = useRef<HTMLDivElement>(null);
  const [preview, setPreview] = useState<string | null>(null);
  const [image, setImage] = useState<File | null>(null);
  const [content, setContent] = useState<string>("");
  const [groupId, setGroupId] = useState<number>();

  const handleClickOutSide = (event: MouseEvent) => {
    if (modalRef.current && !modalRef.current.contains(event.target as Node)) {
      clickModal();
    }
  };

  const handleGroupChange = (event: ChangeEvent<HTMLSelectElement>) => {
    const selectedGroupId = Number(event.target.value);
    setGroupId(selectedGroupId);
    // const selectedGroup = memberInfo?.myGroupList.find(
    //   (group) => group.groupTitle === selectedGroupTitle
    // );
    // if (selectedGroup) {
    //   setGroupId(selectedGroup.id);
    //   console.log("-------------")
    //   console.log(groupId);
    // }
  };

  const handleFileChange = (event: React.ChangeEvent<HTMLInputElement>) => {
    const image = event.target.files?.[0];
    if (image) {
      const reader = new FileReader();
      reader.onloadend = () => {
        setPreview(reader.result as string);
      };
      reader.readAsDataURL(image);
      setImage(image);
      console.log("Fil selected",image);
    }else{
      console.log("No file selected");
    }
  };

  const handleSubmit = async (event: React.FormEvent) => {
    event.preventDefault();
    if (!image) {
      console.error("No file selected");
      return;
    }
    if(!groupId){
      console.log("그룹ID가 없습니다")
      return;
    }
    if(!memberInfo){
      console.log("memberInfo가 없습니다")
      return;
    }

    const formData = new FormData();    
    formData.append("image", image);
    formData.append("content", content);
    formData.append("groupId", groupId.toString());
    formData.append("loginId", memberInfo.loginId);

    try {
      const res = await axios.post(
        "http://localhost:8080/uploadAlbum",
        formData,
        {
          headers: {
            "Content-Type": "multipart/form-data",
          },
        }
      );
      console.log("업로드 되었습니당", res.data);
      clickModal(); // 업로드 후 모달 닫기
    } catch (error) {
      console.error("사진 업로드 중 에러 발생", error);
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
      <div
        ref={modalRef}
        className="relative flex flex-col h-auto w-[500px] bg-white rounded-lg shadow-lg"
      >
        <button
          className="absolute top-3 right-3 text-gray-600 hover:text-gray-800"
          onClick={clickModal}
        >
          <FaTimes size={24} />
        </button>
        <div className="w-full p-5">
          <h1 className="text-center text-xl">새 게시물 만들기</h1>
          <div className="h-[0.8px] w-full bg-slate-400 mt-5"></div>
          <div className="flex flex-col h-[400px] items-center justify-center mt-5">
            {preview ? (
              <div className="flex flex-col h-[400px] items-center justify-center mt-5">
                <img
                  src={preview}
                  alt="Preview"
                  className="h-[350px] w-[350px] object-cover"
                />
                <label className="font-semibold bg-[#0099e6] text-white rounded-md mt-4 cursor-pointer">
                  <span className="text-[15px] ml-2 mr-2">다시 선택하기</span>
                  <input
                    type="file"
                    className="hidden"
                    onChange={handleFileChange}
                  />
                </label>
              </div>
            ) : (
              <div className="flex flex-col h-[400px] items-center justify-center mt-5">
                <PiInstagramLogoThin className="h-[200px] w-[200px]" />
                <span className="mt-5">
                  사진과 동영상을 여기에 끌어다 놓으세요
                </span>
                <label className="font-semibold bg-[#0099e6] text-white rounded-md mt-4 cursor-pointer">
                  <span className="text-[15px] ml-2 mr-2">컴퓨터에서 선택</span>
                  <input
                    type="file"
                    className="hidden"
                    onChange={handleFileChange}
                  />
                </label>  
              </div>
            )}
          </div>

          {preview && (
            <div className="mt-7">
              <div className="mt-7">
                <label className="block mb-2 text-sm font-medium text-gray-700">
                  그룹 선택
                </label>
                <select
                  value={groupId}
                  onChange={handleGroupChange}
                  className="w-full p-2 border border-gray-300 rounded-md"
                >
                  <option value="groupCategory">그룹을 선택하세요</option>
                  {memberInfo?.myGroupList.map((group) => (
                    <option key={group.id} value={group.id}>
                      {group.groupTitle}
                    </option>
                  ))}
                </select>
              </div>

              <div className="mt-5">
                <label className="block mb-2 text-sm font-medium text-gray-700">
                  글 내용
                </label>
                <textarea
                  value={content}
                  onChange={(e) => setContent(e.target.value)}
                  className="w-full p-2 border border-gray-300 rounded-md"
                />
              </div>
              <div className="flex items-center justify-center mt-5">
                <button
                  className="font-semibold h-10 w-20 bg-[#0099e6] text-white rounded-md mt-4 cursor-pointer"
                  onClick={handleSubmit}
                >
                  <span className="text-[17px] ml-2 mr-2">업로드</span>
                </button>
              </div>
            </div>
          )}
        </div>
      </div>
    </div>
  );
};

export default AddNewPostModal;
