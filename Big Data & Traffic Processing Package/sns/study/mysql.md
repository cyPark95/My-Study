## MySQL

> 데이터베이스: 파일을 관리하는 서버

- 오픈소스 관계형 데이터베이스
    - [Git-Repository](https://github.com/mysql/mysql-server)
    - [Mysql-Document](https://dev.mysql.com/doc/internals/en/guided-tour.html)
    - [Source-Level-Document](https://dev.mysql.com/doc/dev/mysql-server/latest/)
- 높은 접근성과 낮은 비용
- SQL 안식 표준을 지키고 있다.


- MySQL 서버
    - MySQL 엔진
        - 판단과 명령을 내리는 두뇌
            - 쿼리 파서(Query Parser)
                - SQL을 파싱 하여 Syntax Tree를 만든다.
                - 이 과정에서 문법 오류 검사가 이뤄진다.
            - 전처리기(Preprocessor)
                - 쿼리파서에서 만든 Tree를 바탕으로 전처리기 시작
                - 테이블이나 컬럼 존재 여부, 접근 권한 등 Semantic 오류 검사
          > - 쿼리 파서와 전처리기의 컴파일 과정은 유사하다.
          > - 하지만 SQL은 컴파일 시점에 검증할 수 없어, 매번 구문 평가를 진행한다.
            - 옵티마이저(Optimizer)
                - 쿼리를 처리하기 위한 여러 방법들을 만들고, 각 방법들의 비용 정보와 테이블의 통계 정보를 이용해 비용 산정
                - 테이블 순서, 불필요한 조건 제거, 통계정보 등을 바탕으로 전략을 결정한다.(실행 계획 수립 단계)
                - 옵티마이저의 전략에 따라 성능이 달라지고, 개발자가 힌트를 통해 도움을 줄 수 있다.
            - 엔진 실행기(Engine Executor)
                - 옵티마이저가 결정한 계획대로 스토리지 엔진에 요청(핸들링)
    - 스토리지 엔진
        - 디스크에서 데이터를 가져오거나 저장하는 역할
        - 플러그인 형태로 Handler API만 지킨다면 직접 구현해서 사용할 수 있다.
        - 8.0부터는 InnoDB 엔진은 기본으로 사용한다.
            - Clustered Index, Redo - Undo, Buffer pool
    - 운영체제

### 쿼리 캐시

- 같은 SQL에 대해 캐시해둔 데이터를 반환
- 8.0부터 폐기
    - 테이블의 데이터가 변경되면 캐시된 데이터도 변경
    - 캐시 데이터 관리에 더 많은 비용이 들어간다.

```markdown
- 소프트 파싱
    - SQL, 실행계획을 캐시에서 찾아 옵티마이저 과정을 생략하고 실행 단계로 넘어간다.
- 하드 파싱
    - SQL, 실행계획을 캐시에서 찾이 못해 옵티머이저 과정을 거치고 실행 단계로 넘어간다.
```
