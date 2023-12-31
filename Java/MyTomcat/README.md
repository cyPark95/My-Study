# Web Server(웹 서버) 란?

- 웹 서버는 HTTP 요청에 대한 정적인 웹 페이지, 이미지, 동영상 등의 정적 리소스를 제공하는 소프트웨어

### 동작 방식

1. 클라이언트로부터 HTTP 요청을 받는다.
2. 요청된 리소스의 경로를 분석하여 해당 파일을 찾는다.
3. 요청된 파일을 클라이언트에게 응답으로 반환한다.

### 기능

1. 정적 콘텐츠 제공
  - WAS를 거치지 않고 바로 리소스 제공
2. 동적 콘텐츠 제공
  - 요청을 WAS에 보내고, WAS가 처리한 결과를 클라이언트에게 응답 

# WAS(Web Application Server: 웹 애플리케이션 서버) 란?

- 웹 서버의 기능을 확장하여 동적인 콘텐츠를 처리하는 소프트웨어
- `웹 컨테이너` 혹은 `서블릿 컨테이너`라고 부른다.

## 기능

1. 동적인 콘텐츠 처리
   - 웹 애플리케이션을 실행한다.
   - 비즈니스 로직을 수행하고, 데이터베이스와 상호작용을 통해 동적인 콘텐츠를 생성한다.

2. 트랜잭션 관리
   - 데이터베이스를 사용하는 작업들은 트랜잭션으로 관리되어야 한다. 
   - WAS는 트랜잭션 관리를 지원하여 데이터의 일관성과 무결성을 보장한다.

3. 스레드 관리
   - 여러 사용자의 요청을 동시에 처리하기위해 각각의 요청을 별도의 스레드에서 처리되어야 한다.
   - WAS는 스레드 풀을 사용하여 효율적으로 스레드를 관리할 수 있다.

4. 커넥션 풀링
   - 데이터베이스와의 연결은 비용이 큰 작업이다.
   - WAS는 미리 여러 개의 데이터베이스 연결을 생성하고 유지하여 이를 커넥션 풀에 저장하기 때문에 새로운 연결을 생성하는 오버헤드를 줄이고 성능을 향상시킨다.

5. 보안 기능
   - WAS는 사용자 인증, 권한 관리, 데이터 보호 등 다양한 보안 기능을 제공한다.

6. 클러스터링과 부하 분산
   - WAS는 여러 대의 서버를 클러스터로 구성하여 부하 분산을 지원하기 때문에 서버의 가용성과 확장성을 높일 수 있다.

# MyWAS 동작

| URL    | 설명                                                                                                   |
|--------|------------------------------------------------------------------------------------------------------|
| /join  | 회원가입 페이지 표시                                                                                          |
| /login | 로그인 페이지 표시 + 쿠키 생성                                                                                   |
| /      | 요청 정보 출력(Method, HOST, Path [, QueryString, Cookie, Session, ContentLength/ContentType, RequestBody] |
| 그 외..  | 잘못된 URL 요청 표시                                                                                        |

### `/join`

![image](https://github.com/cyPark9510/Spring-Study/assets/50781066/26b62189-b60d-4539-963c-3c0e8fd89ba2)

### `/login`

![image](https://github.com/cyPark9510/Spring-Study/assets/50781066/3caf84a5-c782-4f98-ab6f-922a0b2dc0ca)

![image](https://github.com/cyPark9510/Spring-Study/assets/50781066/b54385fd-2f75-4ea3-ada9-7a4e4343b6e7)

### `/?name=cyPark`

![image](https://github.com/cyPark9510/Spring-Study/assets/50781066/e1a0e5a2-74aa-421d-8397-bbed1c2c16d9)

### 그 외(`/url`)

![image](https://github.com/cyPark9510/Spring-Study/assets/50781066/4577cd87-c689-4d4e-ad5c-665dad46238b)