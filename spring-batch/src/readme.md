# Spring Batch

## 배치(Batch)란?

즉시성이 필요하지 않은 데이터 처리에서, 일정량 또는 일정 기간 동안 데이터를 모아 **일괄 처리**하는 방식

### 특징

- **자원 효율성**: 시스템 자원을 효율적으로 활용
- **대용량 처리**: 한 번에 많은 데이터를 처리 가능
- **자동 실행**: 주로 정해진 시간에 반복적으로 동작
- **비대화형**: 사용자와 상호작용 없이 실행

> 단점
> - 실시간 응답에는 부적합
> - 오류 발생 시 대규모 장애로 이어질 수 있음

### 사용 시나리오

1. **데이터 수집(Reads)**: DB, 파일, 메시지 큐 등에서 대량 데이터 조회
2. **데이터 처리(Processes)**: 데이터 가공/변환
3. **데이터 저장(Writes)**: 변환/수정된 데이터 저장

## Spring Batch란?

Spring 기반의 가볍고 강력한 **배치 프레임워크**로, 엔터프라이즈 환경에서 견고한 배치 애플리케이션을 쉽게 개발할 수 있도록 지원한다.

### 주요 특징

1. **Spring 기반**
    - DI, IoC, AOP, 서비스 추상화 등 Spring의 핵심 기능 활용
    - Spring에 익숙하다면 쉽게 적용 가능
2. **대용량 데이터 처리**
    - **Chunk 기반 처리**: 실패해도 전체가 아닌 일부만 롤백
    - **성능 최적화**: 파티셔닝, 멀티스레드, 병렬 처리 지원
3. **견고성**
    - 예외 재시도, 스킵 정책 등 방어 코드 제공
4. **신뢰성**
    - 실패 시 로깅/추적, 작업 통계, 트랜잭션 관리 제공

## Batch 구조

Spring Batch는 **Job → Step** 구조로 이루어진다.

- **Job**: 배치 작업의 최상위 단위 (전체적인 실행 흐름 정의)
- **Step**: Job을 구성하는 실행 단위 (독립적이며 순차적으로 실행)
    - Tasklet 기반
        - Step 구현 방식 중 하나
        - 단일 작업을 실행할 때 사용
    - Chunk 기반
        - 대량 데이터 처리에 최적화된 방식
        - Step이 `Reader` → `Processor` → `Writer` 구조로 동작
        - 지정된 chunk 단위마다 읽고, 처리하고, 저장

### 설계 원칙

- **역할과 관심사 분리**: Reader / Processor / Writer
- **Template - Callback 패턴**:
    - Job은 잘 변하지 않는다. (흐름 정의)
    - Business 로직은 자주 변한다. → Step/Tasklet으로 캡슐화하여 유연하게 확장

### 주요 용어

- Job
    - 전체 배치 프로세스를 캡슐화한 도메인
    - 단순히 Step 인스턴스를 위한 컨테이너
    - 구성
        - Job의 이름
        - Step 정의 및 순서
        - 작업을 다시 시작할 수 있는지 여부
- JobInstance
    - Job의 논리적 실행 단위를 나타내는 도메인
    - 하나의 Job이 여러개의 JobInstance를 가진다.
    - 구성
        - Job 이름
        - 식별 파라미터
    - BATCH_JOB_INSTANCE
- JobParameter
    - Job을 실행할 때 함께 사용되는 파라미터 도메인
    - 하나의 Job에 존재할 수 있는 여러 개의 JobInstance를 구분
    - BATCH_JOB_EXECUTION_PARAMS
- JobExecution
    - Job의 단일 실행에 대한 도메인
    - Job 실행 중에 실제로 일어난 일에 대한 기본 저장 메커니즘
    - BATCH_JOB_EXECUTION 저장
- Step
    - 배치 작업의 독립적이고, 순차적인 단계를 캡슐화한 도메인
    - 하나의 Job은 한 개 이상의 Step을 가진다.
    - 입력 자원을 설정하고 어떤 방법으로 어떤 과정을 통해 처리할지 그리고 어떻게 출력자원을 만들 것인지에 대한 모든 설정
- StepExecution
    - Step의 단일 실행에 대한 도메인
    - Step 실행 중에 실제로 일어난 일에 대한 기본 저장 메커니즘
    - BATCH_STEP_EXECUTION 저장
- ExecutionContext
    - Batch의 세션 역할을 하는 도메인
    - Job, Step의 상태를 가진다.
    - 단순한 Key-Value 구조
    - BATCH_JOB_EXECUTION_CONTEXT, STEP_JOB_EXECUTION_CONTEXT 저장
- JobRepository
    - 앞서 언급한 도메인들을 저장
        - JobInstance
        - JobParameter
        - JobExecution
        - StepExecution
        - (Job) ExecutionContext
        - (Step) ExecutionContext
    - 배치의 상태를 DB에 저장함으로서 다양한 기능에 활용할 수 있다.
        - 오류 복구
        - 재시작 여부
        - 모니터링
- JobLauncher
    - Job을 실행시키는 도메인
- ItemReader / ItemProcessor / ItemWriter
    - ItemReader: 배치의 입력 도메인
    - ItemProcessor: 배치의 비즈니스를 처리하는 도메인
    - ItemWriter: 배치의 출력 도메인

### 정리

1. JobLauncher가 Job에 JobParameter를 넣어서 실행
2. JobInstance 생성
3. JobExecution 생성 / 실행
4. StepExecution
    1. Step 1 실행
    2. Step 2 실행

Job

- 기본 설정으로 Job은 실패하면 재시작할 수 있다.
- `SimpleJobBuilder.preventRestart()`를 설정하면 재시작할 수 없다.

JopParametersIncrementer

- 시퀀스에서 다음 JobParameters 객체를 얻기 위한 인터페이스
- 잡 파라미터 변경 없이 Job을 반복해서 실행하기 위해 사용한다.
    - `RunIdIncrementer` 스프링 배치에서 제공

JobParametersValidator

- 입력 받은 잡 파라미터 검증
- 스프링 배치에서 손쉽게 검증할 수 있도록 제공한다.
  - `DefaultJobParametersValidator` 제공

JobExecutionListener

- 스프링 배치 생명 주기 중 Job 실행 전/후 로직을 추가할 수 있는 기능 제공
- 성공 여부와 상관 없이 실행
  - 원하는 경우 상태를 통해 조건 부 실행 


Step

- Template Method Pattern(Abstract Step)

TaskletStep

- 단점
  - 처리해야할 개수는 많은데 하나씩 커밋하니까 너무 느려... 해결할 수 없을까?

Chunk-oriented Processing

- Spring Batch는 일반적으로 Chunk-oriented 스타일을 사용
- Chunk-oriented Processing는 데이터를 하 번에 하나씩 읽고 트랜잭션 경계 내에 기록되는 Chunk를 생성하는 것을 의미
- 읽은 항목의 수가 커밋 간격과 같으면 ItemWriter가 전체 청크를 기록한 다음 트랜잭션을 커밋
- ChunkOrientedTasklet
- Commit Interval
  - Chunk Size는 커밋 간격을 의미
  - 성능적 이점
  - Chunk 내에서 롤백
  - Step Restart
    - allowStartIfComplete: Step이 성공해도 재시작 허용
    - startLimit: Step 시작 제한 수
  - Skip
    - 한 번의 실패가 Job, Step을 멈추게 함
    - 비즈니스에 따라 특정 에러는 Skip할 수 있는 기능 제공
    - 그 외 기능도 제공
      - noSkip 
      - SkipPolicy
  - Retry
    - 특정 예외의 경우 다시 시도하면 성공할 수 있음
    - 재시도를 통해 회복 탄력성을 갖는다.
    - retryPolicy
  - Rollback
    - Chunk 안에서 작업을 수행하다 실패하면 Rollback 발생
    - 비즈니스에 따라 rollback을 원치 않는 경우 noRollback
  - Intercepting Step
    - Step은 다양한 Listener 제공
    - 무수히 많은 비즈니스에 대응하기위해 적재적소에서 이벤트 구현 가능
  - Late Binding
    - 애플리케이션 구동 시점이 아닌 빈의 실행 시점에 적용
    - 병렬 처리 시 개별의 Scope 빈이 할당되기에 Thread-safe
    - 주로 잡 파라미터에서 값을 활용할 때 사용
    - @JobScope: Step
    - @StepScope: Tasklet, Item 3총사
  - Sequential Flow
    - Step 1 -> Step 2 -> Step 3 순으로 진행되는 흐름
  - Conditional Flow
    - Step 1 성공 -> Step 2
    - Step 1 실패 -> Step 3
  - Flow 속성
    - on(String): ExitStatus 의 반환물과 Match
      - '*': 0개 이상의 문자열과 일치
      - '?': 정확하게 문자열 일치
    - to(Step): on 조건에 만족하면 해당 Step으로 이동
    - from: 이전에 등록한 단계로 돌아가서 새 경로를 시작 
  - Flow Stop
    - Completed: 정상 종료
    - Failed: 실패 처리
    - Stopped
