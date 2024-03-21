## MongoDB 배포 형태

- Replica Set
    - 고가용성(HA)을 보장하기 위한 솔루션
    - 동일한 데이터 셋를 유지하는 3개 이상의 MongoDB 인스턴스로 구성된다.
    - 멤버는 Primary와 Secondary 상태를 가질 수 있고, 데이터 복제를 통해 일관성을 유지한다.
        - Primary
            - 읽기와 쓰기(Read/Write) 요청 모두를 처리할 수 있는 유일한 멤버
            - Replica Set 내에서 하나만 존재할 수 있다.
            - Primary에 문제가 발생하면, Secondary 중 하나가 새로운 Primary로 선출된다.
                - 멤버 간 Heartbeat 메시지를 통해 서로의 상태를 확인하고, 과반수 이상의 동의를 얻어 새 Primary를 선출합니다.
        - Arbiter
            - 데이터를 저장하지 않고, 투표 과정에만 참여하는 멤버
            - Primary-Secondary-Arbiter(PSA) 구성에서는 Arbiter가 홀수의 투표 참여자를 유지하여 투표의 교착 상태를 방지하는 역할을 한다.
            - Arbiter는 리소스 비용을 절감하면서도 고가용성을 유지하고자 할 때 유용합니다.
            - PSA 구성에서 Secondary 노드에 장애가 발생할 경우, 읽기와 쓰기 요청이 모두 Primary 노드로 집중되고, 시스템 전체의 성능 저하로 이어질 수 있다.
        - Secondary
            - 주로 읽기(Read) 요청을 처리하며, Primary로부터 데이터를 복제하여 동일한 데이터 셋를 유지한다.
            - Replica Set 내에 여러 개가 존재할 수 있다.
            - 선출을 통해 Primary가 될 수 있다.
    - 하나의 서버가 다운되어도, 나머지 멤버들을 통해 서비스의 지속적인 운영이 가능하다.
    - Oplog
        - Replica Set의 모든 멤버가 동일한 데이터 셋를 유지할 수 있도록 한다.
        - 쓰기 요청이 Primary에 들어오면, 해당 요청은 먼저 Primary에 적용되고, 변경 사항은 Local 데이터베이스의 Oplog Collection에 기록된다.
        - 각 Secondary는 Oplog를 비동기적으로 복사하여 Primary와 동일한 변경을 수행하여 데이터 일관성을 유지한다.


- Sharded Cluster
    - 분산(Distribution)처리를 위한 데이터베이스 솔루션
    - 수평적 확장(Scale-Out)을 통해 데이터의 용량 한계를 극복하고, 데이터와 트래픽을 여러 서버(Shard)에 분산시켜 고가용성을 보장한다.
    - 구성 요소
        - Shard
            - 데이터를 실제로 저장하는 Replica Set 컴포넌트
        - Router(Mongos)
            - 클라이언트와 통신하며, 적절한 Shard로 요청을 라우팅하고, 결과를 다시 합쳐서 반환한다.
        - Config Server
            - Cluster의 메타데이터(Shard에 저장된 데이터 범위, 클러스터 구성 등)를 관리하는 컴포넌트
    - 분산의 기준을 Shard Key라고 한다.
    - 종류
        - Ranged Sharding
            - Shard Key의 범위를 기준으로 데이터를 분할하는 방식
            - 연속된 값들이 같은 Shard에 분포되기 때문에 타겟 쿼리에 유리하다.
            - 타겟쿼리를 통해서 효율적이고 빠르게 결과를 조회할 수 있다.
            - 하지만 데이터가 불균형하게 분산될 가능성이 있다.
        - Hashed Sharding
            - Shard Key의 해시 값을 사용해 데이터를 균등하게 분산하는 방식
            - 데이터 불균형 문제를 줄일 수 있다.
            - 데이터가 연속적이지 안기 때문에 브로드 캐스트 쿼리(모든 Shard에 쿼리)를 통해 필터링이 작업이 필요할 수 있다.
            - 범위 쿼리에는 비효율적일 수 있다.
        - Zone Sharding
            - 특정 조건을 만족하는 데이터를 지정된 Shard에 할당하는 방식
            - Ranged Sharding과 Hashed Sharding을 함께 사용한다.
    - Sharded Cluster의 목적에 맞게 가능하면 균등한 분산을 위해 Hashed Sharding 방식을 사용한다.
    - 장점
        - 하드웨어 한계를 극복하고, 성능과 용량을 늘릴 수 있다.
        - Replica Set 구조를 통해 고가용성을 보장한다.
    - 단점
        - 구성과 관리가 복잡하며, 쿼리 성능에 영향을 줄 수 있다.
        - 데이터가 여러 Shard에 걸쳐 있을 경우, 쿼리 처리에 추가 시간이 소요될 수 있다.
    - 고려사항
        - Sharded Cluster 구성 시, Shard Key 선택은 성능에 큰 영향을 미친다.
        - Shard Key는 데이터 분산의 균형, 쿼리 성능 최적화, 확장성을 고려하여 결정해야 한다.

```markdown
Sharding vs Partitioning

- Sharding
    - 하나의 큰 데이터를 여러 서브셋으로 나누고
    - 여러 인스턴스에 저장하는 기술

- Partitioning
    - 하나의 큰 데이터를 여러 서브셋으로 나누고
    - 하나의 인스턴스 내에서 여러 테이블로 나누어 저장하는 기술
```

```markdown
Replica Set vs Sharded Cluster

- Replica Set
    - Replica Set은 모든 멤버가 동일한 데이터 셋을 갖는다.

- Sharded Cluster
    - Sharded Cluster는 각 Shard가 다른 데이터의 서브셋을 갖는다.
```
