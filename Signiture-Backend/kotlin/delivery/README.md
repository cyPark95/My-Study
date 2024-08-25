# 배달 플랫폼

## Package Structure

```markdown
Domain
├─ Controller: 클라이언트 요청 처리 및 응답
├─ Business: 작업의 흐름 정의
├─ Converter: 데이터 변화 처리
└─ Service: 비즈니스 로직 정의
```

## API 모듈

- 비즈니스 로직 정의
- 애플리케이션을 실행하기 위한 jar 파일만 필요

#### Filter

- HTTP Request, Response 로깅

#### Interceptor

- 요청 JWT 인증

#### MethodArgumentResolver

- `@UserSession` 어노테이션이 붙은 메서드 파라미터에 인증 사용자 정보 바인딩

#### ExceptionHandler

- `@RestControllerAdvice` 어노테이션을 활용하여 공통 작업 처리
- `@ExceptionHandler` 어노테이션과 함께 사용하여 예외 처리

#### Message Queue

- `Rabbit MQ` 사용
- 사용자 메뉴 주문 시, Message Queue에 주문정보 전송

## Store Admin 모듈

- 가맹점 비즈니스 로직 정의
- 애플리케이션을 실행하기 위한 jar 파일만 필요

#### Spring Security

- 기본 Form 로그인 방식 사용

#### SSE(Server-Sent-Event)

- 클라이언트는 데이터를 받을 수만 있는 `단방향 통신`
- 주문 접수 알람 이벤트를 위해 SSE 연결

#### Message Queue

- `Rabbit MQ` 사용해서
- Message Queue에 주문정보가 수신되면 이벤트 발생
- 주문 정보를 기반으로 연결된 SSE를 통해 데이터 전송

## DB 모듈

- 엔티티 정의
- 다른 모듈에서 참조하기 위한 라이브러리
- plain.jar 파일 필요

```markdown
Gradle Build Task

- bootJar - `{project-name}.jar`
    - Spring Boot 애플리케이션을 실행 가능한 jar 파일 생성
    - `BOOT-INF`와 `META-INF` 디렉토리를 포함하는 jar 파일 생성
    - jar 파일 실행 시 `loader` 디렉토리 생성

- jar - `{project-name}-plain.jar`
    - 라이브러리 등으로 배포할 때 사용될 수 있는 jar 파일 생성
    - `META-INF`와 클래스 파일만 포함하는 jar 파일 생성
```
