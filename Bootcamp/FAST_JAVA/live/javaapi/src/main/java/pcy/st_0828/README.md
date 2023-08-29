## Q. DAO객체의 역할은 무엇인가.

DAO(Data Access Object)는 DB의 데이터에 접근하기 위한 객체

## Q. 예외처리에서 예외와 상관없이 무조건 처리되는 블럭은 무엇인가.

finally

## Q. CRUD와 대응되는 SQL쿼리를 작성하시오.

C(Create): `INSERT INTO table(...) VALUES(...)`

R(Read): `SELECT ... FROM table`

U(Update): `UPDATE tbale SET ...`

D(Delete): `DELETE FROM table`

## Q. select SQL을 실행 후 결과를 받는 객체는 무엇인가.

ResultSet

## Q. 파라미터가 있는 SQL을 전송 할 때 사용하는 객체는 무엇인가.

PrepareStatement

## Q. SQL 결과집합에서 커서를 이동시키는 메서드는 무엇인가.

ResultSet.next()

## Q. JDBC 프로그램의 단점을 기술하시오.

1. 로직이 복잡하다.
    - DB 연결, 자원 얻기/닫기 등 
2. SQL에 의존적이다.
    - SQL을 명시적으로 작성 
3. 직접 예외처리를 해주어야 한다.
    - 개발자 책임
4. DB에 종속적이다.
    - DB 변경에 따른 코드 수정 필요

## Q. SQL을 전송 할 때 insert, update, delete SQL문장을 실행하는 메서드를 쓰시오.

Statement.executeUpdate()

## Q. SQL을 전송 할 때 select SQL문장을 실행하는 메서드를 쓰시오.

Statement.executeQuery()

## Q. JDBC Connection 객체를 만들기 위해서 필요한 정보는 무엇인가.

DB URL

Driver Class

사용자 이름(Username)

비밀번호(Password
)
