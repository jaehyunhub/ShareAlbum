// src/interfaces/MemberInfoDto.ts
interface JwtToken {
    accessToken: string;
    refreshToken: string;
  }
  
  interface MyGroupInfo {
    groupId: number;
    groupTitle: string;
    // other fields
  }
  
  interface AlbumInfo {
    albumId: number;
    albumName: string;
    // other fields
  }
  
  export interface MemberInfo {
    name: string;
    id:number;
    loginId: string;
    nickname: string;
    myGroupList: MyGroupInfo[];
    myAlbum: Record<number, AlbumInfo[]>;
    jwtToken: JwtToken;
  }
  