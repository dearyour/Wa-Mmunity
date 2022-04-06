# Git Readme

---

![스크린샷 2022-04-06 오후 2.09.39.png](Git%20Readme%20d9964/%E1%84%89%E1%85%B3%E1%84%8F%E1%85%B3%E1%84%85%E1%85%B5%E1%86%AB%E1%84%89%E1%85%A3%E1%86%BA_2022-04-06_%E1%84%8B%E1%85%A9%E1%84%92%E1%85%AE_2.09.39.png)

---

# 🍷  와인 추천 서비스 WAMMUNITY 🍷

> 선물용 와인을 고르는 A양 <br>
> 맥주와 소주만 마시다 와인에 관심에 생긴 B군 <br>
> 특별한 날 분위기 있게 마실 와인을 찾는 C양 <br>
> </br>
> Wammunity의 와인 설문을 통해 당신의 취향에 맞는 와인을 추천받고 </br>
> 다른 사람들이 남긴 리뷰와 평점을 확인해보세요 !~! </br>
> </br>
> Wammunity를 통해 취향 저격 와인을 찾아보세요!! </br>

---

## 📎   프로젝트 목차

---

- 🍷  와인 추천 서비스 WAMMUNITY 🍷
- 1️⃣  프로젝트 소개
- 2️⃣  기술 스택
- 3️⃣  파일 구조
- 4️⃣  산출물
- 5️⃣  프로젝트 빌드

---

### 1️⃣  프로젝트 소개

📆  일정 : 22.02.28 - 22.04.08 (총 6주)

- - - - - - - - - - - - - - - - - - - - - - - - - - - - - -

👨‍👨‍👦‍  인원 (총 5인)

🧑 강동원 : 팀장, WineData 분석, Frontend

🧑 김명섭 : Frontend

🧑 김용희 : Frontend

👩 이민정 : Backend, Server

🧑 정원식 : Backend 

- - - - - - - - - - - - - - - - - - - - - - - - - - - - - -

🎨  컨셉 : 식욕을 자극하는 적색계열이면서 레드 와인이 연상되는 버건디를 메인컬러로 사용

- 메인컬러1 : 861610 (로고, 글씨색 등)

- 메인컬러2: 590805 (버튼 등)

- 컬러1 : FFFFFF (배경 등)

- 컬러2 : F7F3F0  (예비, 카드 배경 등)

- 컬러3 : D4C5BB (후터 등)

- - - - - - - - - - - - - - - - - - - - - - - - - - - - - -

‼️  기획 배경 

- 홈술&혼술 유행

- 와인 판매량 증가

- BUT 진입장벽으로 인한 문제점

- - - - - - - - - - - - - - - - - - - - - - - - - - - - - -

⚙️  주 기능

- 전체 와인 리스트

- 와인상세 정보 제공

- 마셔본 와인 평가 및 리뷰

- 사용자 리뷰 기반 와인 추천

- 설문을 통해 취향에 맞는 와인 추천

- - - - - - - - - - - - - - - - - - - - - - - - - - - - - -

🧐  추천 알고리즘 

- 와인추천페이지 - 리뷰기반 와인 추천
  - 유저가 남긴 평가 바탕으로 와인 추천
  - 숨겨진 구매 패턴을 모델에 포함시키기 위해 협업 필터링 모델 사용
  - Matrix Facorization 이용한 CF 모델
  - Stochastic Descent Method 사용 

</br>

- 와인 상세 페이지 - 비슷한 와인 추천
  - 선택된 와인과 맛이나 향 등이 비슷한 와인을 관련상품으로 추천
  - 리뷰데이터에서 빈도가 높은 단어를 추출해 키워드로 저장
  - 설문 결과와 키워드 목록을 tfidf로 비교해 일치율이 높은 와인 추천

---

### 2️⃣  기술 스택

1. 이슈관리 : Jira
2. 형상관리 : Gitlab
3. 커뮤니케이션 : Mattermost, Discord, Notion
4. 개발 환경
   - OS
     - Windows10
     - Mac
   - IDE
     - Eclipse
     - IntelliJ
     - Visual Studio Code
   - Frontend
     - React
     - Next.js
   - Backend
     - Java 11
     - SpringBoot 2.6.x
     - Swagger 3.x.x
     - QueryDsl-JPA 5.x.x
     - JWT 0.9.1
     - JUnit 4.12
     - Lombok
     - Security 2.5.5
     - Validation 2.6.2
     - AWS 2.2.6
   - BigData
     - Python 3.9.6
     - Flask 2.1.1
     - Numpy 1.22.3
     - Pandas 1.4.2
     - Scikit-learn 1.0.2
     - Scipy 1.8.0
     - Sklearn 0.0
   - Database
     - MySQL Workbench 8.0.x
     - AWS S3
     - AWS RDS
   - CI/CD
     - AWS EC2
     - Jenkins
     - Docker
     - Ngnix

---

### 3️⃣  파일 구조

- Frontend

  ```java
  .
  ├── components
  │   ├── Home
  │   │   ├── FilterPanel
  │   │   ├── List
  │   │   └── SearchBar
  │   ├── common
  │   │   ├── CheckboxProton
  │   │   ├── CheckboxProtons
  │   │   ├── EmptyView
  │   │   ├── FilterListToggle
  │   │   └── SliderProton
  │   ├── layout
  │   ├── member
  │   ├── recomm
  │   └── wine
  ├── constants
  ├── dist
  │   ├── cache
  │   │   ├── eslint
  │   │   └── webpack
  │   │       ├── client-development
  │   │       ├── client-production
  │   │       ├── server-development
  │   │       └── server-production
  │   ├── server
  │   │   ├── chunks
  │   │   └── pages
  │   │       └── wine
  │   └── static
  │       ├── chunks
  │       │   └── pages
  │       │       └── wine
  │       ├── css
  │       ├── dWTxxrtttLjU5AWIYZb40
  │       └── media
  ├── pages
  │   ├── recommend
  │   └── wine
  ├── public
  │   ├── assets
  │   ├── fonts
  │   ├── images
  │   ├── imagez
  │   │   ├── dishes
  │   │   ├── gif
  │   │   └── places
  │   └── img
  ├── store
  │   ├── api
  │   ├── hooks
  │   ├── interfaces
  │   ├── module
  │   ├── sagas
  │   └── slice
  ├── styles
  │   ├── wineListCSS
  │   └── wineMainCSS
  └── types
  ```

- Backend

  ```java
  .
  ├── bin
  │   ├── default
  │   ├── main
  │   │   ├── com
  │   │   │   └── web
  │   │   │       └── wam
  │   │   │           ├── config
  │   │   │           │   └── security
  │   │   │           ├── controller
  │   │   │           ├── exception
  │   │   │           │   └── member
  │   │   │           └── model
  │   │   │               ├── dto
  │   │   │               │   ├── freeboard
  │   │   │               │   ├── member
  │   │   │               │   ├── resellboard
  │   │   │               │   └── wine
  │   │   │               ├── entity
  │   │   │               │   ├── freeboard
  │   │   │               │   └── resellboard
  │   │   │               ├── repository
  │   │   │               │   ├── freeboard
  │   │   │               │   ├── resellboard
  │   │   │               │   └── wine
  │   │   │               └── service
  │   │   └── templates
  │   ├── querydsl
  │   │   └── com
  │   │       └── web
  │   │           └── wam
  │   │               └── model
  │   │                   └── entity
  │   │                       ├── freeboard
  │   │                       └── resellboard
  │   └── test
  │       └── com
  │           └── web
  │               └── wam
  │                   ├── controller
  │                   └── model
  │                       ├── repository
  │                       └── service
  ├── build
  │   └── tmp
  │       └── compileQuerydsl
  ├── gradle
  │   └── wrapper
  ├── lib
  └── src
      ├── main
      │   ├── generated
      │   │   └── com
      │   │       └── web
      │   │           └── wam
      │   │               └── model
      │   │                   └── entity
      │   │                       ├── freeboard
      │   │                       └── resellboard
      │   ├── java
      │   │   └── com
      │   │       └── web
      │   │           └── wam
      │   │               ├── config
      │   │               │   └── security
      │   │               ├── controller
      │   │               ├── exception
      │   │               │   └── member
      │   │               └── model
      │   │                   ├── dto
      │   │                   │   ├── freeboard
      │   │                   │   ├── member
      │   │                   │   ├── resellboard
      │   │                   │   └── wine
      │   │                   ├── entity
      │   │                   │   ├── freeboard
      │   │                   │   └── resellboard
      │   │                   ├── repository
      │   │                   │   ├── freeboard
      │   │                   │   ├── resellboard
      │   │                   │   └── wine
      │   │                   └── service
      │   └── resources
      │       ├── static
      │       └── templates
      └── test
          └── java
              └── com
                  └── web
                      └── wam
                          ├── controller
                          └── model
                              ├── repository
                              └── service
  ```

- Bigdata

  ```java
  .
  ├── __pycache__
  ├── data
  │   ├── input
  │   ├── output
  │   └── related_wine
  └── models
      └── __pycache__
  ```

---

### 4️⃣  산출물

- 프로젝트 관리 : [Notion](https://www.notion.so/SSAFY-PJT-b02a8015d76d4fe0ab044dd162025893)
- 기획서
- 와이어프레임
- ERD
- 시스템 아키텍처
- 화면 정의서

---

### 5️⃣  프로젝트 빌드

- 포팅 메뉴얼
