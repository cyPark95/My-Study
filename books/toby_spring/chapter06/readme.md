# 6장 AOP

- AOP(Aspect-Oriented Programming)는 IoC/DI, 서비스 추상화와 함께 스프링의 3대 핵심 기반 기술 중 하나
- AOP는 용어와 개념이 다소 어려워 충분한 이해가 뒷받침될 때 효과적으로 활용할 수 있다. 
- 스프링에서 대표적으로 AOP가 적용된 사례는 선언적 트랜잭션 관리이다.

## 6.1 트랜잭션 코드의 분리

- UserService는 트랜잭션 기술에 독립적이고, 메일 발송 기술과 환경에 종속되지 않도록 다듬어 왔다.
- 하지만 아직까지 트랜잭션 경계 설정을 위한 코드가 존재한다.
