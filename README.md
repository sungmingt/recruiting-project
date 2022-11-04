
# 🏙️ 기업 채용 웹 서비스 API 입니다.

<br>

원티드의 지난 채용연계 코스 과제를 개인학습 겸 수행해봤습니다. <br>
명시된 요구사항들을 토대로 API를 구현 완료했고, 이후 여건이 된다면 완성도를 높여갈 생각입니다.

<br>

## 과제 요구사항

### API

- 채용공고 등록
- 수정
- 삭제
- 조회 (전체, 개별)
- 검색
- 지원

<br>

### 기타 사항
- 필수 domain : 회사, 사용자, 채용공고, 지원내역(선택사항)
- 회사, 사용자 등록 절차는 생략 (DB에 임의로 생성하여 진행)
- 로그인 등 사용자 인증절차(토큰 등)는 생략
- 사용자는 채용공고에 1회만 지원 가능하다


<br><br><br>


## 📘 실행 방법 (h2)

```c
./gradlew clean bootRun
```
- 애플리케이션 실행 시 ```data.sql``` 쿼리 실행 -> 바로 채용 API 요청 가능 

- h2 접속 확인
http://localhost:8080/h2


<details>
<summary><h3>Http Request Example</h3></summary>
<div markdown="1">

<br>

<h4>채용 공고 등록</h4>

```http request
POST http://localhost:8080/recruit
Content-Type : application/json

{
    "companyId" : 1,
    "position" : "백엔드",
    "reward" : 150000,
    "content" : "백엔드 java 개발자 채용",
    "tool" : "java, spring, mysql"
}
```
</div>
</details>


<br><br>



## 📘 ERD

<img width="558" alt="recruit erd 최종" src="https://user-images.githubusercontent.com/95558880/197238493-a0198727-68fe-48dd-947f-22dfda8d2cd0.png">

요구사항 : **사용자는 1회만 지원 가능하다** -> 회원과 채용공고 N:1 관계

<br><br>


## 📘 API Docs (Swagger)

http://localhost:8080/swagger-ui/index.html#/

<img width="1085" alt="recruiting api" src="https://user-images.githubusercontent.com/95558880/197230360-ea06bcb0-ab91-4f8e-bb96-dead1f4d90cd.png">

<br><br>

## 📘 테스트

### slice test + dynamic test

<img width="353" alt="happy 2" src="https://user-images.githubusercontent.com/95558880/197231062-85bbc00a-4b29-4935-8809-dcec9bda51a7.png">


<br>



