"use client";
import React, { FormEvent, useState } from "react";
import Link from "next/link";
import { SiKakaotalk } from "react-icons/si";

const SignUp = () => {
  const [id, setId] = useState("");
  const [name, setName] = useState("");
  const [nickname,setNickName] = useState("");
  const [password, setPassword] = useState("");



  const handleSubmit = () => {};

  return (
    <div className="w-screen h-screen mt-20 flex itmes-center justify-center m-auto">
      <div>
        <div className="w-full border border-gray-300 p-10">
          {/* 여기 */}
          <div>
            <div className="flex justify-center my-2 text-5xl tracking-wider">
              ShareAlbum
            </div>
            <div className=" text-center text-s pb-5 tracking-wider">
              가족, 친구들과의 추억을 평생 남기세요
            </div>
            <button className=" bg-[#0099e6;] text-white active:scale-95 transform transition w-full h-9 disabled:bg-opacity-50 disabled:scale-100 rounded-lg text-sm font-semibold">
              <SiKakaotalk className="inline-block mr-2 text-2xl text-yellow-400" />
              <span className="text-sm font-semibold mt-10 mb-6">
                KakaoTalk으로 로그인
              </span>
            </button>

            <div className="flex items-center  justify-center flex-row w-full mt-5 space-x-2">
              <div className="h-[0.8px] w-full bg-slate-400" />
              <div className="text-sm font-semibold text-gray-400 whitespace-nowrap">또는</div>
              <div className="h-[0.8px] w-full bg-slate-400" />
            </div>

            <form
              onSubmit={handleSubmit}
              className="relative flex flex-col w-full mt-5 space-y-5 bg-white "
            >
              <input
                type="id"
                name="id"
                id="id"
                value={id}
                onChange={(e) => setId(e.target.value)}
                className="w-full px-2 py-1 bg-gray-100 border rounded-sm outline-none hover:bg-transparent focus:bg-transparent placeholder:text-sm focus:border-gray-400"
                placeholder="휴대폰 번호 또는 이메일 주소"
              />
              <input
                type="password"
                name="password"
                id="password"
                onChange={(e) => setPassword(e.target.value)}
                className="w-full px-2 py-1 transition bg-gray-100 border rounded-sm outline-none hover:bg-transparent focus:bg-transparent placeholder:text-sm focus:border-gray-400"
                placeholder="성명"
              />
              <input
                type="name"
                name="name"
                id="name"
                onChange={(e) => setPassword(e.target.value)}
                className="w-full px-2 py-1 transition bg-gray-100 border rounded-sm outline-none hover:bg-transparent focus:bg-transparent placeholder:text-sm focus:border-gray-400"
                placeholder="사용자 이름"
              />
              <input
                type="password"
                name="password"
                id="password"
                onChange={(e) => setPassword(e.target.value)}
                className="w-full px-2 py-1 transition bg-gray-100 border rounded-sm outline-none hover:bg-transparent focus:bg-transparent placeholder:text-sm focus:border-gray-400"
                placeholder="비밀번호"
              />
              <Link href="">
              <button className=" bg-[#0099e6;] text-white active:scale-95 transform transition w-full h-9 disabled:bg-opacity-50 disabled:scale-100 rounded-lg text-sm font-semibold">
                가입
              </button>
              </Link>
            </form>
          </div>
          {/* 여기끝 */}
        </div>


        <div className="w-full py-5 space-y-5 text-sm text-center bg-white border border-gray-300 mt-2">
          계정이 있으신가요?
          <Link href="/login">
            <button className="ml-2 font-semibold text-blue-500">
              <a href="/signup">로그인</a>
            </button>
          </Link>
        </div>
      </div>
    </div>
  );
};

export default SignUp;
