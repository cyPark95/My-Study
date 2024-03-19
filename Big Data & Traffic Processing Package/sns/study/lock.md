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
