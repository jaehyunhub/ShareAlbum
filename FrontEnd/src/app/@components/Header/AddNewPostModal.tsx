import React from "react";
import { PiInstagramLogoThin } from "react-icons/pi";

const addNewPostModal = (props: any) => {
  // 전달받은 state 함수
  const { clickModal } = props;
  return (
    <div className="fixed inset-0 z-50 flex items-center justify-center bg-black  bg-opacity-50">
      <div className="flex h-[500px] w-[500px] justify-center bg-white rounded-lg shadow-lg">
        <div className="w-full">
          <h1 className="text-center pt-5 text-xl">새 게시물 만들기</h1>
          <div className="h-[0.8px] w-full bg-slate-400 mt-5"></div>
          <div className="flex items-center justify-center mt-5">
            <PiInstagramLogoThin className="h-[200px] w-[200px]"></PiInstagramLogoThin>
          </div>
          <div className="flex items-center justify-center mt-5"><span>사진과 동영상을 여기에 끌어다 놓으세요</span></div>
          <div className="flex items-center justify-center mt-5">
            <button className="font-semibold bg-[#0099e6;] text-white rounded-md mt-4">
              <span className="text-[15px] ml-2 mr-2">컴퓨터에서 선택</span>
            </button>
          </div>
        </div>
      </div>
    </div>
  );
};

export default addNewPostModal;
