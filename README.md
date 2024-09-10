<h1>
<div style="position: relative; display: inline-block;">
  <img src="https://github.com/user-attachments/assets/5bd1761a-dd3e-45c5-bdab-9e88d2fbb489" alt="아이콘" width="150" height="150">
  <span style="position: absolute; bottom: 0; right: 0; background-color: white; padding: 2px 5px; font-weight: bold;">
    ShareAlbum(앨범 공유)
  </span>
</div>
</h1>

## 1. 목표와 기능

### 목표
- 효율적인 개발 환경 구축 : 실무에서 개발 환경 설정에 많은 시간이 소요된다는 점을 느끼고, 이를 개선하기 위해 이 프로젝트에서 Docker를 활용한 컨테이너화를 통해 일관된 개발 환경을 구축하고 설정 시간을 단축하고자 합니다.
- 프론트엔드와 백엔드 간의 상호작용 최적화 : 프론트엔드와 백엔드 간의 원활한 상호작용이 사용자 경험에 미치는 영향을 깨닫고, 이 프로젝트에서 React와 Next.js를 활용해 프론트엔드와의 협업을 최적화하고 상호작용을 강화하는 방법을 적용하고자 합니다.
- 효율적인 쿼리 작성과 성능 최적화 : 실무에서 복잡한 SQL 쿼리 작성에 많은 시간을 소비하면서 비즈니스 로직에 집중하기 어려웠던 경험을 바탕으로, 이 프로젝트에서 JPA와 QueryDSL을 사용하여 더 간결하고 효율적인 쿼리를 작성하고 성능을 최적화하는 방안을 학습하고자 합니다.

### 학습 및 적용 계획
- Docker를 활용한 개발 환경 자동화 : 프로젝트 초기 단계에서 Docker를 사용하여 개발 환경을 컨테이너화하고, 팀원 간의 환경 설정 차이를 줄여 프로젝트의 효율성을 높일 계획입니다.
- React와 Next.js로 프론트엔드 역량 강화 : 프론트엔드와 백엔드 간의 상호작용을 이해하고 최적화하기 위해 React와 Next.js를 학습하고, 프로젝트에 적용해 프론트엔드 개발 역량을 강화할 것입니다.
- JPA와 QueryDSL을 통한 데이터 처리 성능 향상 : JPA와 QueryDSL을 활용해 데이터 조회 성능을 최적화하고, 복잡한 쿼리를 간결하게 작성하는 방법을 프로젝트에 적용하여 실무에 필요한 기술 역량을 강화할 것입니다.

## 🔧 Stack
**Frontend(Web)**
- **Language** : JavaScript, TypeScript
- **Library & Framework** : React, Next.Js
<br/>

**Backend**
- **Language** : Java 
- **Library & Framework** : Spring Boot
- **Database** : PostgrelSql
- **ORM** : JPA
- **Deploy**: AWS(EC2, RDS), Redis(Cache),Docker
<br/>


## 🔨 프로젝트 구조
📦 shareAlbum  <br>
 ┣ 📂Backend   <br>
 ┃ ┣ 📂domian  <br>
 ┃ ┃ ┣ 📂album <br>
 ┃ ┃ ┣ ┃ 📂controller <br>
 ┃ ┃ ┣ ┃ 📂dto <br>
 ┃ ┃ ┣ ┃ 📂entity <br>
 ┃ ┃ ┣ ┃ 📂repository <br>
 ┃ ┃ ┣ ┃ 📂service <br>
 ┃ ┃ ┣ 📂chat <br>
 ┃ ┃ ┣ ┃ 📂controller <br>
 ┃ ┃ ┣ ┃ 📂dto <br>
 ┃ ┃ ┣ ┃ 📂entity <br>
 ┃ ┃ ┣ ┃ 📂repository <br>
 ┃ ┃ ┣ ┃ 📂service <br>
 ┃ ┃ ┣ 📂group <br>
 ┃ ┃ ┣ ┃ 📂controller <br>
 ┃ ┃ ┣ ┃ 📂dto <br>
 ┃ ┃ ┣ ┃ 📂entity <br>
 ┃ ┃ ┣ ┃ 📂repository <br>
 ┃ ┃ ┣ ┃ 📂service <br>
 ┃ ┃ ┣ 📂member <br>
 ┃ ┃ ┣ ┃ 📂controller <br>
 ┃ ┃ ┣ ┃ 📂dto <br>
 ┃ ┃ ┣ ┃ 📂entity <br>
 ┃ ┃ ┣ ┃ 📂repository <br>
 ┃ ┃ ┣ ┃ 📂service <br>
 ┃ ┃ ┣ 📂global <br>
 ┃ ┃ ┣ ┃ 📂baseEntity <br>
 ┃ ┃ ┣ ┃ 📂config <br>
 ┃ ┃ ┣ ┃ 📂jwt <br>
 ┃ ┃ ┣ ┃ 📂redis <br>
 ┣ 📂frontend <br>
 ┃ ┣ 📂(member)  <br>
 ┃ ┃ ┣ 📂[nickname] <br>
 ┃ ┃ ┣ ┃ 📜layout.tsx <br>
 ┃ ┃ ┣ ┗ 📜page.tsx <br>
 ┃ ┃ ┣ 📂login <br>
 ┃ ┃ ┣ ┃ 📜layout.tsx <br>
 ┃ ┃ ┣ ┗ 📜page.tsx <br>
 ┃ ┃ ┣ 📂searchResults <br>
 ┃ ┃ ┣ ┃ 📂[nickname] <br>
 ┃ ┃ ┃ ┣ ┃ 📜layout.tsx <br>
 ┃ ┃ ┃ ┣ ┗ 📜page.tsx <br>
 ┃ ┃ ┣ 📂signup <br>
 ┃ ┃ ┣ ┃ 📜page.tsx <br>
 ┃ ┃ ┣ 📂components <br>
 ┃ ┃ ┣ ┃ 📂Button <br> 
 ┃ ┃ ┃ ┣ ┗ 📜ImageButton.tsx <br>
 ┃ ┃ ┣ ┃ 📂Header <br>
 ┃ ┃ ┃ ┣ ┃ 📜Header.tsx <br>
 ┃ ┃ ┃ ┣ ┗ 📜SearchResultsHeader.tsx <br>
 ┃ ┃ ┣ ┃ 📂Modal <br>
 ┃ ┃ ┃ ┣ ┃ 📜AcceptGroupModal.tsx <br>
 ┃ ┃ ┃ ┣ ┃ 📜AddNewGroupModal.tsx <br>
 ┃ ┃ ┃ ┣ ┃ 📜AddNewPostModal.tsx <br>
 ┃ ┃ ┃ ┣ ┗ 📜InviteGroupModal.tsx <br>
 ┃ ┃ ┣ 📂context <br>
 ┃ ┃ ┃ ┗ 📜AuthContext.tsx <br>
 ┃ ┃ ┣ 📂interfaces <br>
 ┗ ┗ ┗ ┗📜MemberInfo.txt <br>  

## 데이터베이스 모델링(ERD)
## 아키텍처
<br/

## ⭐ 주요 기능
<table>
    <tbody>
        <tr>
            <td>메인</td>
            <td>로그인</td>
        </tr>
        <tr>
            <td>
		<img src="ui1.png" width="100%">
            </td>
            <td>
                <img src="ui2.png" width="100%">
            </td>
        </tr>
        <tr>
            <td>회원가입</td>
            <td>정보수정</td>
        </tr>
        <tr>
            <td>
                <img src="ui3.png" width="100%">
            </td>
            <td>
                <img src="ui3.png" width="100%">
            </td>
        </tr>
        <tr>
            <td>검색</td>
            <td>번역</td>
        </tr>
        <tr>
            <td>
                <img src="ui3.png" width="100%">
            </td>
            <td>
                <img src="ui3.png" width="100%">
            </td>
        </tr>
        <tr>
            <td>선택삭제</td>
            <td>글쓰기</td>
        </tr>
        <tr>
            <td>
	        <img src="ui3.png" width="100%">
            </td>
            <td>
                <img src="ui3.png" width="100%">
            </td>
        </tr>
        <tr>
            <td>글 상세보기</td>
            <td>댓글</td>
        </tr>
        <tr>
            <td>
                <img src="ui3.png" width="100%">
            </td>
            <td>
                <img src="ui3.png" width="100%">
            </td>
        </tr>
    </tbody>
</table>
- **회원가입** :
- **로그인** : 
- **메인페이지** 
- **회원 검색**  
- **그룹 추가** : 
- **그룹 초대** : 
- **그룹 승인** : 
- **앨범 추가** :  
<br/>

## 🔨 프로젝트 구조
<br/>



## 💡 개발하며 느낀점

## 💡 Version



