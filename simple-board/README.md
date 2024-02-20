# 간단한 게시판 프로젝트

## ERD

![ERD](https://github.com/cyPark95/My-Study/assets/139435149/b2deb35d-0784-4ba8-856e-f86b84f570bb)

## REST API

#### BOARD

- 게시판 생성

```http request
POST /api/boards
Authentication: TRUE
```

#### POST

- 게시글 생성

```http request
POST /api/posts
```

- 게시글 상세조회

```http request
POST /api/posts/details
```

- 게시글 목록조회

```http request
GET /api/posts
```

- 게시글 삭제

```http request
POST /api/posts/details
Authentication: TRUE
```

#### REPLY

- 댓글 생성

```http request
POST /api/replies
```

- 댓글 삭제

```http request
POST /api/replies/delete
Authentication: TRUE
```

## Filter

![study](https://github.com/cyPark95/My-Study/assets/139435149/a4c2a6e7-a842-4c48-a5de-df93cda0fc94)

- `void init(FilterConfig filterConfig)`
    - 서블릿 컨테이너가 필터를 처음으로 로드할 때, 호출

- `void destroy()`
    - 서블릿 컨테이너가 필터를 언로드할 때, 호출

- `void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain)`
    - 필터에서 구현해야 하는 로직 작성
    - HTTP Request와 HTTP Response를 가로채어 작업을 수행할 수 있다.
    - HTTP Request, HTTP Response Body 로깅 기능 구현
        - `ContentCachingRequestWrapper`와 `ContentCachingResponseWrapper`를 통해 요청과 응답의 본문을 캐싱하면, 재사용할 수 있다.

## Interceptor

![study](https://github.com/cyPark95/My-Study/assets/139435149/a4c2a6e7-a842-4c48-a5de-df93cda0fc94)

- `boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler)`
    - 핸들러가 호출되기 전 실행
    - 반환 값이`true`인 경우, 실제 핸들러 메서드가 호출된다.
    - `@Authenticated` 어노테이션을 통해 HTTP Header 인증정보 포함 여부를 확인하는 기능 구현

- `void postHandle(HttpServletRequest request, HttpServletResponse response, Object handler, ModelAndView modelAndView)`
    - 핸들러 메서드가 실행된 후, 뷰가 렌더링되기 전 실행

- `void afterCompletion(HttpServletRequest request, HttpServletResponse response, Object handler, Exception ex)`
    - 뷰 렌더링이 완료된 후 실행
    - 예외 발생 여부와 관계 없이 항상 호출된다.

```markdown
`WebMvcConfigurer` 클래스의 `void addInterceptors(InterceptorRegistry registry)` 메서드를 통해 인터셉터를 등록할 수 있다.
```

## AOP (Aspect Oriented Programming)

#### 관점지향 프로그램

![study](https://github.com/cyPark95/My-Study/assets/139435149/844f6676-2fb4-4417-9528-81e29db9c769)

- Aspect
    - 공통의 관심사를 모듈화한 것

- Advice
    - `@Before`
        - 대상 메서드 실행 전 호출

    - `@After`
        - 대상 메서드 실행 후 호출
        - 예외 발생 여부와 관계 없이 호출된다.

    - `@AfterReturning`
        - 대상 메서드 정상 실행 후 호출
        - `returning` 속성을 통해 반환 값을 받을 수 있다.

    - `@AfterThrowing`
        - 대상 메서드 예외 발생시 호출
        - `throwing` 속성을 통해 예외를 받을 수 있다.

    - `@Around`
        - 대상 메서드의 실행 전과 후, 예외 발생 시 호출
        - `StapWatch`를 통해 메서드 실행에 걸리는 시간을 구하는 기능 구현

- JoinPoint
    - Advice가 실행될 수 있는 지점

- PointCut
    - AOP를 적용할 메서드를 지정하는데 사용되는 지점

  | 포인트컷 지시자    | 의미                             |
  |-------------|--------------------------------|
  | execution   | 반환타입 , 타입 , 메소드 , 파라미터 기준으로 지정 |
  | within      | 특정 경로의 타입을 기준으로 지정             |
  | this        | 특정 타입의 객체를 기준으로 지정             |
  | target      | 특정 타입의 객체를 기준으로 지정             |
  | args        | 특정 타입의 파라미터를 가지는 메소드를 기준으로 지정  |
  | @target     | 특정 어노테이션을 가지는 객체를 기준으로 지정      |
  | @args       | 특정 어노테이션의 파라미터를 가지는 메소드를 기준    |
  | @within     | 특정 클래스의 경로의 어노테이션을 기준          |
  | @annotation | 특정 메소드의 어노테이션을 기준              |
  | bean        | 스프링빈을 기준으로 지정                  |

## 실행 순서

1. Filter init()
2. Filter doFilter() Start
3. Interceptor preHandle()
4. AOP Around Start
5. AOP Before

***핸들러 로직 실행***

6. AOP AfterReturning / AfterThrowing
7. AOP After
8. AOP Around End
9. Interceptor postHandle()
10. Interceptor afterCompletion()
11. Filter doFilter() End
12. Filter destroy()
