# 배달 플랫폼


## API 모듈

- 비즈니스 로직 정의
- 애플리케이션을 실행하기 위한 jar 파일만 필요

### Filter

- HTTP Request, Response 로그

### Interceptor

- 요청 JWT 인증

### MethodArgumentResolver

- `@UserSession` 어노테이션이 붙은 메서드 파라미터에 인증 사용자 정보 바인딩

## DB 모듈

- 엔티티 정의
- 다른 모듈에서 참조하기 위한 라이브러리
- plain.jar 파일 필요

```markdown
Gradle Build Task

- bootJar(`{project-name}.jar`)
    - Spring Boot 애플리케이션을 실행 가능한 jar 파일 생성
    - `BOOT-INF`와 `META-INF` 디렉토리를 포함하는 jar 파일 생성
    - jar 파일 실행 시  `loader` 디렉토리 생성

- jar(`{project-name}-plain.jar`)
    - 라이브러리 등으로 배포할 때 사용될 수 있는 jar 파일 생성
    - `META-INF`와 클래스 파일만 포함하는 jar 파일 생성
```
