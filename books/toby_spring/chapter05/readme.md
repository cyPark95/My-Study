# 5장 서비스 추상화

- 자바에는 사용 방법과 형식은 다르지만 기능과 목적이 유사한 기술이 존재한다.
- 5장에서는 지금까지 만든 DAO에 트랜잭션을 적용해보면서 스프링이 어떻게 성격이 비슷한 여러 종류의 기술을 추상화하고 이를 일관된 방법으로 사용할 수 있도록 지원하는지 살펴본다.

## 5.1 사용자 레벨 관리 기능 추가

- 지금까지 UserDao는 기초적인 CRUD(Create-Read-Update-Delete) 작업만 가능하다.
- 사용자 관리 기능을 추가한다.
    - 사용자의 레벨은 `BASIC`, `SILVER`, `GOLD` 세 가지 중 하나다.
    - 시용자가 처음 가입하면 `BASIC` 레벨이 되며, 이후 활동에 따라서 한 단계씩 업그레이드될 수 있다.
    - 가입 후 50회 이상 로그인을 하면 `BASIC`에서 `SILVER` 레벨이 된다.
    - SILVER 레벨이면서 30번 이상 추천을 받으면 `GOLD` 레벨이 된다.
    - 사용자 레벨의 변경 작업은 일정한 주기를 가지고 일괄적으로 진행된다. 변경 작업 전에는 조건을 충족하더라도 레벨의 변경이 일어나지 않는다.

### 5.1.1 필드 추가

- 사용자의 레벨을 저장하기 위한 필드로 각 레벨을 코드화해서 숫자로 저장하면 DB 용량도 덜 차지하고, 가볍운 장점이 있다.
    - User에 추가할 Level 타입을 정수로 지정한다면 다음과 같은 문제들이 발생할 수 있다.
      ```java
        public class User {
        
          private static final int BASIC = 1;
          private static final int SILVER = 2;
          private static final int GOLD = 3;
        
          private int level;
        
          public void setLevel(int level) {
              this.level = level;
          }
        }
      ```
        - 다른 종류의 정보를 넣는 실수를 해도 컴파일러가 체크해주지 못한다.
            ```java
              user.setLevel(other.getSum());
            ```
        - 범위를 벗어나는 값을 넣어도 컴파일러가 체크해주지 못한다.
            ```java
              user.setLevel(1000);
            ```
    - Java의 Enum을 이용하면 안전하고 편리하다.
      ```java
        public enum Level {
            
            BASIC(1),
            SILVER(2),
            GOLD(3),
            ;
            
            private final int value;
            
            Level(int value) {
                this.value = value;
            }
            
            public int getValue() {
                return value;
            }
            
            public static Level valueOf(int value) {
                return switch (value) {
                    case 1 -> BASIC;
                    case 2 -> SILVER;
                    case 3 -> GOLD;
                    default -> throw new AssertionError("Unknown value: " + value);
                };
            }
        }
      ```
        - Level Enum 내부에는 DB에 저장할 정수 타입의 값을 가지고 있지만, 객체이기 때문에 타입이 일치하지 않으면 컴파일러가 체크해서 걸러준다.
