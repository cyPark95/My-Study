## MongoDB

> 유연하고 확장성 높은 오픈소스 Document 지향 데이터베이스

- 특징
    - 데이터가 문서 형식(JSON)으로 저장되어, 데이터 접근성과 가시성이 좋다.
    - 중복 데이터가 발생할 수 있지만, 스키마가 유연해서 애플리케이션의 요구사항에 맞는 데이터 모델을 쉽게 수용할 수 있다.
    - HA(High Availability)와 Scale-Out 솔루션을 내장하고 있어, 쉽게 확장 가능하다.
    - Primary Key 외에도 다양한 Secondary Index를 지원하여 데이터 검색 효율을 높일 수 있다.
    - 배우기 쉽고 개발 과정이 간편하게 빠른 개발이 가능하다.


- 구조
  - Database -> Collection -> Document -> Field
    - Collection
      - 동적 스키마를 지원하며, 필드 추가/수정/삭제로 스키마 변경이 가능하다.
      - BSON(Binary JSON) 형태로 데이터를 저장한다.
      - 스키마가 없다고 생각하면 될 듯
      - 인덱스 생성, Shard 분할 등이 컬렉션 단위로 이루어진다.
      - 스키마는 자유롭지만, 효율적인 데이터 관리 및 검색을 위해 일정 수준의 구조가 필요하다.
    - Document
      - JSON 형태로 표현되며, BSON 형태로 저장된다.
      - 모든 문서는 고유한 "_id" 필드를 가지고, 생성 시 자동으로 ObjectId 타입의 값을 할당한다.
      - 생성 시, 상위 구조인 데이터베이스나 Collection이 없다면 먼저 생성하고, Document를 생성한다.
      - 문서의 최대 크기는 16MB이다.

- 관리용 데이터베이스
  - admin
    - 인증과 권한 부여 역할
  - local
    - 인스턴스 복제 및 진단 정보(oplog, startup_log 등)를 저장
    - local 데이터베이스는 복제되지 않는다.
  - config
    - Sharded Cluster 설정에 사용
    - 각 Shard 정보를 저장한다.
