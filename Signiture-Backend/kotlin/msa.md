# Micro Service Architecture

- 소프트웨어 개발 방법론 중 하나로, 큰 서비스를 작은 기능 단위로 나눠 개발하고 배포하는 방식
- 각각의 작은 서비스들은 독립적으로 운영되며, 서로 통신하면서 전체 시스템의 기능을 수행

## 1. 독립적인 서비스
- 각각의 마이크로 서비스는 독립적으로 확장할 수 있으며, 이는 서비스 간의 영향을 최소화하여 각 서비스의 업데이트나 변경이 다른 서비스에 영향을 미치지 않는다.
- 각 서비스는 독립적으로 배포 가능하며, 장애 발생 시에도 다른 서비스에 영향을 주지 않도록 설계된다.

## 2. 분산 개발
- 각각의 마이크로 서비스는 다른 팀에 의해 자율적으로 독립적으로 개발될 수 있다.
- 이를 통해 팀은 자율적으로 각자의 서비스에 집중하고, 자신들의 서비스에 가장 적합한 기술 스택을 선택할 수 있다.

## 3. 결합도와 응집도
- 마이크로 서비스는 낮은 결합도와 높은 응집도를 갖는다.
   - **낮은 결합도**: 서비스 간의 의존성이 적어, 하나의 서비스 변경이 다른 서비스에 영향을 미치지 않도록 한다.
   - **높은 응집도**: 특정 비즈니스 기능을 수행하는 데 필요한 작업이 하나의 서비스 안에 잘 모여 있어, 서비스가 독립적인 기능을 명확하게 수행한다.

## 4. 서비스 간 통신
- 마이크로 서비스는 API를 통해 서로 통신한다.
- 이 통신은 일반적으로 HTTP/REST 또는 비동기 메시징을 통해 이뤄진다. 메시지 큐나 이벤트 기반 시스템을 활용할 수 있다.

## 5. 새로운 도전 과제
- **서비스 간의 통신**: 각 서비스가 독립적으로 배포되기 때문에 서비스 간의 통신을 관리하는 것이 중요한 이슈가 된다.
- **데이터 일관성**: 각 서비스가 별도의 데이터베이스를 가질 수 있기 때문에, 트랜잭션 처리와 데이터 일관성을 유지하는 것이 복잡해질 수 있다.
- **서비스 디스커버리**: 마이크로 서비스는 동적으로 생성되거나 삭제될 수 있어, 이러한 서비스의 위치를 관리하고 자동으로 감지하는 '서비스 디스커버리' 기능이 필요하다. 보통 `Service Registry`와 함께 사용된다.

---

마이크로 서비스 아키텍처는 복잡성을 관리하고, 서비스를 빠르게 배포하고, 대규모 서비스를 효과적으로 운영하는 데 도움을 준다. 
그러나 서비스 간의 통신, 데이터 일관성, 서비스 디스커버리 등의 새로운 도전 과제를 해결해야 한다.


[참고(Microsoft)](https://learn.microsoft.com/ko-kr/azure/architecture/guide/architecture-styles/microservices)