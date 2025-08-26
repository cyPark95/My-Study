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

## Spring Batch 구조

Spring Batch는 **Job → Step** 구조로 이루어진다.

### Job

- 배치 작업의 최상위 단위 (전체적인 실행 흐름 정의)
- 구성 요소
    - 이름
    - Step 정의 및 순서
    - 재시작 여부

### Step

- Job을 구성하는 실행 단위 (독립적이며 순차적으로 실행)
- 두 가지 실행 방식
    - Tasklet 기반
        - 단일 작업을 실행할 때 사용
    - Chunk 기반
        - 대량 데이터 처리에 최적화된 방식
        - 지정된 chunk 단위마다 읽고(`Reader`), 처리하고( `Processor`), 저장(`Writer`)

### 설계 원칙

- **역할과 관심사 분리**: Reader / Processor / Writer
- **Template - Callback 패턴**:
    - Job은 잘 변하지 않는다. (흐름 정의)
    - Business 로직은 자주 변한다. → Step/Tasklet으로 캡슐화하여 유연하게 확장

## 주요 용어

| 용어                                  | 설명                          | 저장 테이블                                                        |
|-------------------------------------|-----------------------------|---------------------------------------------------------------|
| **Job**                             | 전체 배치 프로세스를 캡슐화             | -                                                             |
| **JobInstance**                     | Job의 논리적 실행 단위              | `BATCH_JOB_INSTANCE`                                          |
| **JobParameter**                    | Job 실행 시 사용하는 파라미터          | `BATCH_JOB_EXECUTION_PARAMS`                                  |
| **JobExecution**                    | Job의 단일 실행 단위               | `BATCH_JOB_EXECUTION`                                         |
| **Step**                            | 독립적 실행 단위                   | -                                                             |
| **StepExecution**                   | Step의 단일 실행 단위              | `BATCH_STEP_EXECUTION`                                        |
| **ExecutionContext**                | Job/Step의 상태 저장 (Key-Value) | `BATCH_JOB_EXECUTION_CONTEXT`, `BATCH_STEP_EXECUTION_CONTEXT` |
| **JobRepository**                   | 위의 모든 메타데이터 저장 및 관리         | DB                                                            |
| **JobLauncher**                     | Job 실행 담당                   | -                                                             |
| **ItemReader / Processor / Writer** | 입력 / 처리 / 출력 역할             | -                                                             |

## 실행 흐름

1. JobLauncher가 Job에 JobParameter를 넣어서 실행
2. JobInstance 생성
3. JobExecution 생성 / 실행
4. StepExecution
    - Step 1 -> Step 2 -> ... 순차 실행

## 세부 기능

### Job 관련

- 재시작 정책
    - 기본: 실패 시 재시작 가능
    - `preventRestart()` 설정 시 재시작 불가
- JobParametersIncrementer
    - 파라미터 변경 없이 Job 반복 실행 가능
    - `RunIdIncrementer`
- JobParametersValidator
    - 입력된 JobParameter 검증
    - DefaultJobParametersValidator
- JobExecutionListener
    - Job 실행 전/후 로직 추가 가능
    - 성공/실패 여부와 무관하게 실행

### Step 관련

#### TaskletStep

- 단순 단일 작업 처리
- 단점: 데이터가 많을 경우 하나씩 커밋 → 성능 저하

#### Chunk-Oriented Processing

- 일반적인 Spring Batch 처리 방식
- `Reader` → `Processor` → `Writer` 구조
- Chunk size = commit interval

#### 주요 기능

- Commit Interval: 일정 개수 단위로 트랜잭션 커밋
- Skip: 특정 에러 발생 시 Step 중단 대신 건너뛰기
- Retry: 특정 예외 발생 시 재시도
- Rollback: 실패 시 Chunk 단위로 롤백 (예외적으로 `noRollback` 설정 가능)
- Listener: 다양한 이벤트 지점에 비즈니스 로직 추가
- Late Binding: 실행 시점에 파라미터 바인딩 (`@JobScope`, `@StepScope`)
- Flow 제어
    - Sequential Flow: Step1 → Step2 → Step3 순차 실행
    - Conditional Flow: 성공/실패 여부에 따른 분기 처리
    - Flow 속성: `on`, `to`, `from` 키워드 활용

#### Flow 종료 상태

- `Completed`: 정상 종료
- `Failed`: 실패 종료
- `Stopped`: 중간 정지

## 정리

Spring Batch는 대규모 데이터를 안정적이고 유연하게 처리하기 위한 프레임워크이다.

- Job → Step → Chunk 구조로 동작
- Reader, Processor, Writer로 역할 분리
- 재시작, 스킵, 리트라이, 롤백 등 강력한 오류 복구 기능 제공
- 다양한 Flow 제어 및 Listener로 확장성 확보
