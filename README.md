<img src="https://capsule-render.vercel.app/api?type=wave&color=auto&height=300&section=header&text=Daily&fontSize=90" />

## 📌 프로젝트 목적
- 요구사항 정의된 백엔드 기능을 모두 구현하기
- 기능 구현에 필요한 개념을 모두 습득하고 이해하기
- 프로젝트 진행중에 생긴 오류들 이해하고 해결하기
- 코드 리뷰를 통해서 개선된 사항들 이해하고 내 것으로 만들기
- 테스트 코드 완벽하게 이해하기
  
  <br>
  
## 📍 프로젝트 요구사항
- 권한
  <br>
  회원 : 로그인, 회원가입, 소모임 신청, 회원정보 수정
  <br>
  관리자 : 소모임 관리(등록, 수정, 삭제), 카테고리 관리(등록, 수정, 삭제), 회워 관리(관리자 권한 생성, 삭제)
- 기능
  <br>
  관리자가 소모임을 등록하면 회원이 소모임에 신청한다.

<br>

## 💿 설치 및 설정
- Spring 3.0.6
- JDK17
- Tomcat
- Jar

<br>

## 🔧 기술 스택
- [MariaDB](https://github.com/f-lab-edu/Daily/wiki/DB-:-MariaDB)
- [Gradle](https://github.com/f-lab-edu/Daily/wiki/Build-Tool-:-Gradle)
- AWS
- MyBatis

<br>

## 🖼 화면 구성
[Daily 델리 화면 구성 링크입니다.](https://ovenapp.io/view/CgBLAqU04G0hykV5P9lgNLVb5RXT5QVu/ElvAn)

<br>

## 📃 API
[기능별 API 페이지로 이동합니다.](https://github.com/f-lab-edu/Daily/wiki/%EA%B8%B0%EB%8A%A5%EB%B3%84-API-%EC%A0%95%EB%A6%AC)

<br>

## 📝 ERD
![Daily](https://github.com/f-lab-edu/Daily/assets/9190171/2921bbb0-f0f1-4224-ae7e-53f499ab1cf2)

- category
    - 모임의 카테고리에 대한 정보를 가지고 있는 테이블
    - 하나의 카테고리에는 여러개의 meeting이 등록될 수 있다.
        
        
        | Name | Description  | Constraints |
        | --- | --- | --- |
        | category_id | 카테고리의 고유한 아이디  | PK, AUTO INCREMENT |
        | category_name | 카테고리의 이름 (중복 허용 X) | UNIQUE |
        | created_by | 카테고리를 등록한 사용자 |  |
        | updated_by | 카테고리를 수정한 사용자 |  |
        | created_date | 카테고리를 등록한 시간 |  |
        | updated_date | 카테고리를 수정한 시간 |  |

- meeting
    - 모임에 대한 정보를 나타내는 테이블
    - 하나의 모임은 여러개의 member_meeting 을 가질 수있다.
        
        
        | Name | Description  | Constraints |
        | --- | --- | --- |
        | meeting_id | 모임의 고유한 아이디 | PK, AUTO INCREMENT |
        | category_id | 카테고리의 고유한 아이디  (category 테이블의 category_id) | FK |
        | meeting_name | 모임의 이름 |  |
        | meeting_description | 모임에 대한 설명 |  |
        | meeting_data | 모임 날짜 |  |
        | meeting_place | 모임 장소 |  |
        | current_people | 모임 등록 자 수 |  |
        | meeting_people | 모임 총 인원 |  |
        | meeting_image | 모임 이미지 |  |
        | created_by | 모임을 등록한 사용자 |  |
        | created_date | 모임을  등록한 시간 |  |
        | updated_date | 모임을 수정한 시간 |  |

- member_meeting
    - 모임과과 모임에 참석할 멤버의 정보를 가지고 있는 테이블
    - member_metting은 여러 metting과 여러 member를 가질 수 있다.
        
        
        | Name | Description  | Constraints |
        | --- | --- | --- |
        | group_id | 모임의 고유한 아이디 | PK, AUTO INCREMENT |
        | meeting_id | meeting 테이블 meeting_id | FK |
        | member_id | member 테이블  member_id | FK |
        | created_by | 사용자가 모임에 참석신청을 한 시간 |  |
        
- member
    - 멤버에 대한 정보를 가지고 있는 테이블
    - 하명의 member는 여러 member_meeting에 등록될 수 있다.
        
        
        | Name | Description  | Constraints |
        | --- | --- | --- |
        | member_id | 멤버의 고유한 아이디 | PK, AUTO INCREMENT |
        | email | 멤버의 이메일 (종복 허용 X) | UNIQUE |
        | password | 멤버의 비밀번호 |  |
        | nickname | 델리에서 사용할 이름 |  |
        | member_type | 멤버의 유형 (어드민인지, 사용자 구분) |  |
        | created_date | 계정을 생성 시간 |  |
        | update_date | 계정 정보를 변경 시간 |  |

