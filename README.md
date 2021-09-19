# 참참참

|구분|내용|
|---|-------|
|한줄 소개|	각박한 세상속에서 살아가는 현대인들이 '참을 인'을 기록하는 iOS 앱|
|진행 기간|	2021.05.29 - 2021.05.30|
|주요 기술|	Java Spring MySQL iOS|
|팀원 구성|	4명 (Server 개발 2명, Client 개발 1명, 디자이너 1명)|
|전담 역할|	Server 개발 (Spring)|
|🏆 수상	|MAKEUS 7TH 해커톤 대회 최우수상|
|Github|	https://github.com/heoseungyeon/Team_4_UQ_Ban-Server|

## 프로젝트 개요

- MakeUs 7th 해커톤 대회에서 진행했던 프로젝트 입니다.
- iOS을 이용하여 앱을 제작하였으며 Spring 프레임워크로 서버를 구현하였습니다.
- 각박한 세상속에 사는 현대인들이 '참을 인'을 기록하여 유쾌하게 스트레스를 해소시키기 위한 서비스 입니다.

## 프로젝트 사용 기술

### ✔Language

- Java

### ✔GUI

- iOS

### ✔ 협업

- GitHub

### ✔Data Base

- MySQL

### ✔Server

- Spring

## 주요 기능

- 홈 화면 상단에 인내와 관련된 명언을 랜덤하게 확인할 수 있습니다.
- 사용자는 스트레스 해소 대상(ex, 상사, 애인, 친구)을 설정할 수 있습니다.
- 3초 간 '참을 인' 버튼을 꾹 눌러 해당 대상의 '참을 인'을 기록할 수 있습니다.
- 대상 별 '참을 인'이 3회 누적 시 전체 참은 횟수의 카운트가 1씩 증가합니다.
- 랭킹화면에서 모든 대상의 참은 횟수 카운트가 가장 높은 순으로 순위를 확인할 수 있습니다.

## 나의 역할

- Server 개발
    - Spring framework 사용
    - home API 개발
        - GET /home/quotes : 명언 전체 조회 API
        - POST /home/count/{categoryId}} : 카운트 추가 API
        - GET home/count/{categoryId} : 카운트 조회 API
        - GET home/category : 카테고리 전체 조회 API
    - user api 중 정렬된 참을 인 카운트 전체 조회 API 개발
