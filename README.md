## RestaurantRecommend
2022-1 자바프로그래밍 팀 프로젝트 &lt;골목식당>

### 배경
- 경북대생을 위한 경북대 근처 맛집 추천/리뷰 시스템

### 주요 기능
- 학교 주변 위치별 / 메뉴별 식당 검색
    - 정문, 쪽문, 북문, 동문&텍문, 학식
    - 한식, 양식, 일식, 중식, 패스트푸드, 기타
- 구체적인 식당 정보 제공
- 추가 기능
    - 랜덤 식당 추천 (위치만 고르면 메뉴 결정!)
    - 날씨에 따른 메뉴 추천 기능
    - 리뷰 작성 및 열람 기능

### 구현 과정
- 메인 페이지 mainPage.java
    - 첫 화면, 위치를 선택하세요
    - ImageIcon, Jbutton, JLabel
- 식당 선택 페이지 RestaurantChoice.java
    - 각 위치당 메뉴 선택 → 식당별 버튼
    - MackRestBtn(), RestaurantButton()
- 식당 정보 페이지 Store.java
    - 구체적인 식당 정보 제공
    - 리뷰 열람 및 작성 페이지
    - getInfo(), createImageMap(), MenuClick(), InfoClick(), ReviewClick()
 
### 역할
- 정문, 쪽문, 북문 식당 자료조사 및 정리
- 메인 페이지 및 GUI 제작
- 최종 발표 피피티 제작 및 최종 발표

### Tool
![Java](https://img.shields.io/badge/Java-ED8B00?style=for-the-badge&logo=openjdk&logoColor=white)
