
# community
Spring boot & jpa 커뮤니티 게시판

## Details
게시글 목록                            
![list](https://user-images.githubusercontent.com/34118304/183297622-2ce742e2-2c99-4c89-b9f8-9a4a7b26fee7.PNG)

게시글 상세                        
![detail](https://user-images.githubusercontent.com/34118304/183297667-149a81dc-09a1-4f18-929c-17fdd2267ead.PNG)

글 작성                            
![write](https://user-images.githubusercontent.com/34118304/182396261-08cb54f1-fac3-4353-a0e0-43966c51a00f.PNG)

## Features
- 사용자
  - Email, PW, Nickname 회원가입
  - 일반 로그인
  - 회원정보 수정 (미완성)

- 게시판
  - 게시글 및 댓글 기본 CRUD
  - 키워드, 작성자 검색
  - 페이징
  - 좋아요 (미완성)

## Tech Stack
- Java 11 / Spring Boot v2.6.7
- Maven
- Postgresql (DB)

## Dependencies
- Spring Boot
- Spring Web
- Spring Data JPA
- Validation
- Spring Boot DevTools
- Spring Security
- Thymeleaf
- Lombok
- Postgresql Driver



<img width="715" alt="image" src="https://user-images.githubusercontent.com/34118304/182553577-09fae51c-17c9-4221-9f61-cc60c3010ab9.png">
<img width="715" alt="image" src="https://user-images.githubusercontent.com/34118304/182532610-fe690335-5c21-4823-a3a7-b35afff7cb0e.png">
<img width="715" alt="image" src="https://user-images.githubusercontent.com/34118304/182532612-64564b1d-eca3-4678-8d24-17a04e1f1f8f.png">


## ERD
![erd](https://user-images.githubusercontent.com/34118304/182532615-8d9ee19a-98ce-4966-837a-73623afdec05.PNG)

## Validation
- 회원가입
  - 이메일 : 이메일 패턴에 맞게, 중복X
  - 비밀번호 : 5~15자, 영문 대소문자, 숫자, 특수문자 포함
  - 닉네임 : 2~15자, 영문 대소문자, 숫자, 한글(가-힣), 중복 X
  

- 로그인
  - 로그인 실패 시 하단에 로그인 실패 원인 출력
    - `이메일 또는 비밀번호가 일치하지 않습니다.`
    - `존재하지 않는 아이디입니다.`
    - `서버 오류가 발생했습니다. 잠시 후 다시 시도해주세요.`
    - `알 수 없는 이유로 오류가 발생했습니다.`
  - 로그인 성공 시 로그인 이전 페이지로 redirect
  - 회원가입 → 로그인 경로로 로그인 시 index page로 redirect

- 게시글 작성 / 수정
  - 제목 : 공백X
  - 내용 : 공백X

- 댓글 작성 / 수정
  - 내용 : 공백X

## Business Logic
- 회원가입 - 일반
  - Validation 확인은 Backend 단에서
  - error 여부에 따라 Form 테두리 빨간색, 하단에 error message 출력
  - 이메일, 닉네임 중복 시 하단에 “중복된 OO입니다.” 메시지 출력
  - 가입 성공 시 로그인 페이지로 redirect

- 게시글
  - 작성 날짜 오늘일 경우 “HH:mm” format 적용
  - 작성 날짜 오늘이 아닐 경우 “MM-dd” format 적용
  - 본인이 작성한 게시글만 수정 / 삭제 버튼 보여줌
  - (페이징) Controller → Model & View 넘겨줄 땐 무조건 페이지 값(0부터 시작) 기준으로 넘겨줌.
    - View에서 page+1 처리

- 좋아요
  - 로그인 사용자에 한해 게시글마다 좋아요 선택 가능
  
- 댓글
  - 본인이 작성한 댓글만 수정 / 삭제 버튼 보여줌

## 새롭게 알게된 점
JPA 관련 학습이 조금 더 필요함.
js 비동기 api fetch (async, await)

