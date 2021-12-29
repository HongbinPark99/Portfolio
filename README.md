# Coin Community

관심이 있고 화제가 되고 있는 코인 커뮤니티를 Java eclipse로 만들어 보았습니다.

# 구동 화면

## Main
<img src="https://user-images.githubusercontent.com/89118596/147677451-703b44e7-2fa6-4262-ab3f-793e0a5e0356.png" width="600" height="400"/>
메인화면으로 관련 기사를 볼 수 있습니다.

## Menu
<img src="https://user-images.githubusercontent.com/89118596/147677453-cdf200f2-c294-4db5-9c83-f403c193744d.png" width="600" height="400"/>
우측 상단에 메뉴를 누르면 로그인과 회원가입 버튼이 나옵니다.

## Login
<img src="https://user-images.githubusercontent.com/89118596/147677461-a19fac2e-4a70-4082-8cb9-2d56b99b2022.png" width="300" height="200"/>
로그인 버튼을 누르면 나오는 로그인 화면으로 회원가입한 계정으로 로그인 할 수 있습니다.

## Sign Up
<img src="https://user-images.githubusercontent.com/89118596/147677463-f39db8dc-ce4a-42bb-83ec-23a674cf8424.png" width="400" height="300"/>
회원가입 버튼을 누르면 나오는 회원가입 화면으로 ID, 비밀번호, 이름, 이메일, 성별을 입력하여 계정을 생성할 수 있습니다.

## Bulletin Board
<img src="https://user-images.githubusercontent.com/89118596/147682073-12f26548-b6e3-4790-8a6a-196016531cf2.png">
좌측 상단에 게시판 버튼을 누르면 나오는 게시판 화면입니다. 

## Article Content 1
<img src="https://user-images.githubusercontent.com/89118596/147682075-dbad4d2a-d72c-47be-861b-42e10c884717.png">
게시글을 쓴 사용자와 계정이 일치하지 않은 사람이 볼 수 있는 화면으로 목록만 볼 수 있습니다.

## Article Content 2
<img src="https://user-images.githubusercontent.com/89118596/147682076-fcc00165-eed0-4121-b5fe-80853461ccd5.png">
게시글을 쓴 사용자와 계정이 일치한 사람이 볼 수 있는 화면으로 게시글을 수정, 삭제 할 수 있습니다.

# DB 1
<img src="https://user-images.githubusercontent.com/89118596/147682069-0ba652db-5db8-4441-aed3-3215f36f1766.png" width="600" height="400"/>
My SQL에서 저장된 데이터베이스 user 회원 정보입니다.

# DB 2
<img src="https://user-images.githubusercontent.com/89118596/147682072-836438ed-61ab-4cf7-80fb-873d483f22f4.png" width="600" height="400"/>
My SQL에서 저장된 데이터베이스 bbs 게시글 내용입니다.

# 프로젝트 설명

Tomcat을 설치하여 Tomcat 웹 서버 상에서 프로젝트가 실행할 수 있게 하였고 JDBC Driver를 프로젝트에 추가해서 데이터베이스 프로그래밍을 하였습니다.
MySQL을 사용해 DB에 저장된 회원 정보를 이용하여 JSP를 만들었습니다. DB에서 UserID는 ‘PRIMARY KEY’로 설정하여 중복되지 않게 하나만 존재할 수 있게 만들었고 BBS에 자바빈즈로 getters/setters까지 생성하여 DB 구축을 완료하였습니다.
그 후 실제로 DB에 접근해서 데이터를 빼내주는 역할을 해주는 데이터 접근 객체 DAO파일을 만들었습니다.
게시판 화면에서 ‘if’를 사용하여 로그인 한 상태의 게시글을 쓴 ID와 동일해야만 수정 및 삭제를 할 수 있게 하였고 'replaceAll'을 사용하여 특수문자가 나타나지 않는 버그를 방지하였습니다. 
