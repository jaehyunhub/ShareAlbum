// src/interfaces/MemberInfoDto.ts
interface JwtToken {
    accessToken: string;
    refreshToken: string;
  }
  
  interface MyGroupInfo {
    id: number;
    groupTitle: string;
    // other fields
  }
  
  interface AlbumInfo {
    id: number;
    content:string;
    imagePath:string;
    groupListId:number;
    // other fields
  }

  interface GroupInvitationList{
    invitationId:number;
    inviterId: string;
    receiverId: string;
    groupTitle:string;
  }

  export interface SearchResultsNickname{
    id: number;
    nickname:string;
  }

  export interface SearchResultsMemberInfo{
    name: string;
    id:number;
    loginId: string;
    nickname: string;
    myGroupList: MyGroupInfo[];
    myAlbum: Record<number, AlbumInfo[]>;
  }
  
  export interface MemberInfo {
    name: string;
    id:number;
    loginId: string;
    nickname: string;
    myGroupList: MyGroupInfo[];
    myAlbum: Record<number, AlbumInfo[]>;
    groupInvitationList : GroupInvitationList[];
    jwtToken: JwtToken;
  }
  