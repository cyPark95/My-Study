## 쓰기락과 읽기락

- 락을 통해 동시성을 제어할 때는, 락의 범위를 최소화 해야한다.
    - 락의 범위가 클수록 여러 트랜잭션이 대기하는 시간이 늘어난다.
    - 최악에는 하나의 쓰레드를 사용하는 것과 동일한 효과를 가져올 수 있다.
    - 이로 인해 커넥션 풀을 점유하는 시간이 길어지고, 최악의 경우 커넥션 풀이 고갈되는 문제가 발생할 수 있다.


- MySQL에서는 트랜잭션의 커밋 또는 롤백 시점에 잠금이 풀린다.
    - 트랜잭션의 범위가 곧 락의 범위이다.
- MySQL은 읽기락과 쓰기락 두 가지 종류의 잠금을 제공한다.
    - 읽기락
        - SELECT ... FOR SHARE
    - 쓰기락
        - SELECT ... FOR UPDATE 또는 DELETE

|                     | 읽기락(Shared Lock) | 쓰기락(Exclusive Lock) |
|---------------------|:----------------:|:-------------------:|
| 읽기락(Shared Lock)    |        O         |         대기          |
| 쓰기락(Exclusive Lock) |        대기        |         대기          |

- 매번 잠금이 발생할 경우, 성능저하를 피할 수 없다.
    - MySQL에서는 일반 조회는 Nonblocking Consistent Read로 동작한다.
    - 원본 데이터 변경 시점에 Undo Log를 통해 변경 전 데이터를 관리한다.

- 레코드 락
    - MySQL에서 잠금은 레코드가 아닌 인덱스를 잠근다.
    - 인덱스가 없는 조건으로 Locking Read를 할 경우 불필요한 데이터가 잠길 수 있다.

```sql
// 락 상태 레코드 확인
SELECT * FROM performance_schema.data_locks

// 트랜잭션 상태 확인
SELECT * FROM information_schema.innodb_trx
```

## 비관적 락과 낙관적 락

- 비관적 락(Pessimistic Locking)
    - 락을 통해 데이터를 보호하고, 동시에 다른 트랜잭션이 데이터를 변경하지 못하도록 줄 세우는 방법
    - 동시성 제어를 위해 락을 획득하고 대기하는 시간이 발생할 수 있다.
    - 대규모 시스템에서는 락의 경합으로 성능 저하가 발생할 수 있습니다다.


- 낙관적 락(Optimistic Locking)
    - 동시성 이슈가 발생할 것을 기대하지 않고, 데이터를 변경하기 전에 충돌 여부를 확인하는 방법
    - 어플리케이션에서 제어한다.
    - CAS(Compare And Set)연산을 사용하여 동시 업데이트를 방지한다.
        - 데이터를 업데이트하기 전에 현재 상태를 비교하고, 일치하는 경우에만 업데이트를 수행한다.
    - 일반적으로는 버전 관리를 통해 구현하고, 업데이트 시 버전 번호를 비교하여 충돌을 감지한다.
    - 충돌이 발생한 경우, 실패에 대한 처리를 직접 구현해야 한다.
