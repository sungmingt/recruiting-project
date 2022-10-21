## 기업의 채용을 위한 웹 서비스 API

<br>

원티드의 프리온보딩 코스 지원에 필요한 과제를 학습 겸 수행해본 내용입니다.
명시된 요구사항들을 토대로 API를 구현하고, 이후 여건이 된다면 웹 서비스 API로써의 완성도를 높여볼 생각입니다.

<br><br>

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
   
<br>

data.sql
사진

## API Docs (Swagger)

<img width="1085" alt="recruiting api" src="https://user-images.githubusercontent.com/95558880/197230360-ea06bcb0-ab91-4f8e-bb96-dead1f4d90cd.png">




<br><br>



## 테스트

### slice test + dynamic test 구현

<img width="353" alt="happy 2" src="https://user-images.githubusercontent.com/95558880/197231062-85bbc00a-4b29-4935-8809-dcec9bda51a7.png">

단위 테스트(slice test)와 더불어 실제 사용자의 유스케이스 테스트를 위해 dynamic test 추가적으로 실행






