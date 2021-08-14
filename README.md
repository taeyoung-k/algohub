# AlgoHub 

인원 : 컴퓨터공학과 4학년 4명

기간 : 2021.03.08 ~ 2021.06.14

개발IDE 및 언어 : 프론트엔드 : react(javascript) / 백엔드 : springboot(java)

Persistence lib : mybatis(SQL Mapper)

DB : mysql

```
본인 역할 : 백엔드 / DB 설계, 알고리즘 문제 및 풀이 등록/조회, WebRTC 시그널링 서버 구현
```

## 프로젝트 개발동기
 요즘 많은 IT 기업들이 개발자 채용과정에 코딩테스트를 도입해 높은 비중으로 다루고 있다. 
 
 창의적 문제해결력과 구현능력 등 개발자의 역량을 평가하여 좋은 개발자를 영입하기 위함이다. 그뿐만 아니라 알고리즘 교육을 창의적·논리적 문제해결력을 키우기 위해 다양한 기관에서 활용하고 있다. 알고리즘 풀이에 관한 관심이 높아짐에 따라 백준, 프로그래머스, 알고스팟 등 다양한 알고리즘 문제 풀이 사이트를 많은 사람이 활용하고 있다.
 
 **현재 하나의 문제에 대한 풀이를 확인하기 위해서는 문제 풀이 사이트, 개인 블로그나 GitHub의 Code를 일일이 탐색해야 하는 불편함이 따른다. 또한, 제공하는 문제가 한정되어 있어 다양한 문제를 찾기 위해서는 다른 알고리즘 문제 풀이 사이트를 접속하여야 한다.**
 
 이에 AlgoHub는 기존의 알고리즘 문제 풀이 사이트의 풀이 검색 단계를 단축하고 알고리즘 유형에 따른 다양한 문제와 풀이를 제공하고자 한다. 또한, 학습능력을 향상하기 위한 랭킹 제도와 멘토-멘티 관계를 통해 흥미와 학습 동기를 유발하며, 나아가 온라인 알고리즘 학습 커뮤니티를 구축하고자 한다.
 

## Database 설계

#### ERD
![ERD_수정본](https://user-images.githubusercontent.com/87019615/124610205-44348a80-deab-11eb-9108-7ea0a14d54f8.jpg)

#### 물리적 설계
![물리_DB_추가_정리](https://user-images.githubusercontent.com/87019615/124610399-6dedb180-deab-11eb-9d62-0000ee457b50.jpg)


## API 설계

### 회원
1. 로그인 `POST /api/auth/login`
2. 사용자 인증 `GET /api/auth/me`
3. 회원가입 `POST /api/signup`
4. 프로필 조회 `GET /api/profile/{m_name}`

### 알고리즘
1. 알고리즘 분류 목록 `GET /api/categories`
2. 알고리즘 문제 조회 `GET /api/algorithms/{id}`
3. 알고리즘 문제 등록 `POST /api/algorithms/writing`
4. 문제 검색 `GET /api/algorithms/{search}`
5. 문제 해설 조회 `GET /api/solution/{p_title}/language/{language}`
6. 문제 풀이 등록 `POST /api/solution/writing`
7. 풀이 댓글 조회 `GET /api/solution/comments/{s_id}`
8. 풀이 댓글 등록 `POST /api/solution/comments`
9. 풀이 댓글 수정 `PUT /api/solution/comments/{s_cm_id}`
10. 풀이 댓글 삭제 `DELETE /api/solution/comments/{s_cm_id}`

### 멘토/멘티 추가
1. 멘토 신청 `PUT /api/mentor-request`
2. 멘토 목록 조회 `GET /api/mentors`
3. 멘토 조회 `GET /api/mentors/{m_name}`
4. 멘토 페이지 조회 `GET /api/mentors-room/{m_name}`
5. 멘토 구독 `POST /api/mentoring/subscribe`
6. 멘토게시판 게시글 등록 `POST /api/mentors/writing`
7. 멘토게시판 게시글 수정 `PUT /api/mentor-board/{mb_id}`
8. 멘토게시판 게시글 삭제 `DELETE /api/mentor-board/{mb_id}`
9. 멘토 후기 조회 `GET /api/mentors/{m_name}/review`
10. 멘토 후기 등록 `POST /api/mentors/review`
11. 멘토 후기 수정 `PUT /api/mentor/{m_name}/review/{mr_r_id}`
12. 멘토 후기 삭제 `DELETE /api/mentor/{m_name}/review/{mr_r_id}`
13. 구독한 멘토 목록 조회 `GET /api/subscription-info/user/{m_id}`
14. 채팅방 생성 `POST /api/mentors/createRoom`
15. 채팅방 입장 `GET /api/mentors/joinRoom/{m_name}`
16. 채팅방 퇴장 `POST /api/mentors/exitRoom/{chat_id}`
17. 채팅방 조회 `GET /api/mentors/chatting/{m_name}`

## 참고

- WebRTC signaling server https://github.com/Benkoff/WebRTC-SS

# 수정중 ...
