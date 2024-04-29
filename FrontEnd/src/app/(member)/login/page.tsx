
    "use client"
    import { useRouter } from 'next/navigation';
    import React, {FormEvent, useState} from 'react'
    import Lottie from 'react-lottie-player'
    import lottieJson from '../../../../public/assets/animations/auth-page-animation.json';
    import { SiKakaotalk } from 'react-icons/si';
    import axios from 'axios';
    import Link from 'next/link'
    import Head from 'next/head'


    const Login = () =>{    
    let router = useRouter();
    const [id, setId] = useState("");
    const [password, setPassword] = useState("");

    const handleSubmit = async (event: FormEvent) =>{
        event.preventDefault();
        try{
            const res = await axios.post("/login",{id,password})
            router.push("")
        }catch(error: any){
            console.log(error);
        }
    }
    return (
        <div className="w-screen h-screen flex items-center justify-center bg-[#FAFAFA]">
            <Head>
                <title>shareAlbum • Login</title>
                <meta name="description" content="shareAlbum" />
                 <link rel="icon" href="/instagram.png" />
            </Head>
            <div className="flex h-4/5 w-4/5" >
                {/* 부모 div에 flex설정이 되어 있으면 자식에 w-full를 하면 부모 w를 반반으로 나눠서 갖는다*/}
                <div className ="w-full h-full">
                <Lottie
                    loop
                    animationData={lottieJson}
                    play
                    className="w-full h-full"
                />
                </div>
                
                <div>
                    <div className ="w-full bg-white border border-gray-300 p-10">
                        <div>
                        <form onSubmit={handleSubmit} 
                            className="relative flex flex-col w-full p-10 space-y-5 bg-white ">
                        <div className="flex justify-center my-5 text-5xl tracking-wider">ShareAlbum</div>
                        <input 
                            type="id" 
                            name="id" 
                            id="id" 
                            value={id} 
                            onChange={ (e) => setId(e.target.value)}
                            className="w-full px-2 py-1 bg-gray-100 border rounded-sm outline-none hover:bg-transparent focus:bg-transparent placeholder:text-sm focus:border-gray-400"
                            placeholder="id"/>
                        <input 
                            type="password" 
                            name="password" 
                            id="password" 
                            onChange={ (e) => setPassword(e.target.value)}
                            className="w-full px-2 py-1 transition bg-gray-100 border rounded-sm outline-none hover:bg-transparent focus:bg-transparent placeholder:text-sm focus:border-gray-400"
                            placeholder="Password"/>
                        <button 
                                className="bg-[#0095F6] py-1 text-white active:scale-95 transform transition w-full disabled:bg-opacity-50 disabled:scale-100 rounded-lg text-sm font-semibold">Login </button>
                        </form>

                        <div className="flex items-center justify-center w-full px-10 my-1 space-x-2">
                            <div className="h-[0.8px] w-full bg-slate-400" />
                            <div className="text-sm font-semibold text-center text-gray-400">OR</div>
                            <div className="h-[0.8px] w-full bg-slate-400" />
                        
                        </div>

                        <div className="flex items-center justify-center w-full text-center text-indigo-900">
                            <div className="pt-5">
                                <SiKakaotalk className="inline-block mr-2 text-2xl"/>
                                <span className="text-sm font-semibold mt-10 mb-6">
                                    KakaoTalk으로 로그인
                                </span>
                            </div>                            
                        </div>
                        <div className="w-full text-center text-xs text-indigo-900 pt-5">비밀번호를 잊으셨나요?</div>
                        </div>
                    </div>

                    <div className="w-full py-5 space-y-5 text-sm text-center bg-white border border-gray-300 mt-2">
                        계정이 없으신가요?
                        <Link href="/signup">
                        <button className="ml-2 font-semibold text-blue-500"><a href="/signup">가입하기</a></button>
                        </Link>
                    </div>
            
                </div>
            
                
               
                                
            </div>
        </div>
    )
    }
    export default Login;