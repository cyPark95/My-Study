# Redis(REmote DIctionary Server)

- 오픈소스 인메모리 데이터 구조 저장소로, Key-Value 형태로 데이터를 관리한다.
- 문자열, 해시(Hash), 집합(Set), 정렬된 집합(Sorted Set) 등 다양한 자료구조를 지원한다.

### 특징

1. 지속성(Persistence)
    - 메모리 기반 DB이지만, 데이터를 디스크에 저장하여 재시작 후에도 복구 가능
2. 데이터 복제(Replication)
    - Master-Slave(Primary-Replica) 구조 지원
    - 읽기 부하 분산(Read Scaling)과 Failover 구성 가능
3. 트랜잭션(Transaction)
    - `MULTI`, `EXEC`, `WATCH` 등을 통해 여러 명령을 원자적으로 실행
    - 롤백은 지원하지 않지만, `WATCH`를 통해 낙관적 락 구현 가능
4. Pub/Sub
    - 발행-구독(Publish-Subscribe) 모델 지원
5. 빠른 속도
    - 메모리 기반으로, HDD 기반 DB 보다 빠른 처리 속도 제공
6. 싱글 스레드 기반
    - 요청 처리를 단일 스레드로 수행하여 Race Condition 방지
    - 내부적으로 I/O 멀티플렉싱(비동기 이벤트 루프) 사용
7. 클러스터링(Clustering)
    - Redis Cluster로 데이터 샤딩(Sharding) 및 수평 확장 가능
    - 자동 파티셔닝과 장애 복구 지원
