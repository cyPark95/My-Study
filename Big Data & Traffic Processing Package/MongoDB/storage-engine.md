## MongoDB Storage Engine

- 데이터가 메모리와 디스크에 어떻게 저장하고 읽을지 관리하는 컴포넌트
- MySQL과 동일하게 Plugin 형태로, MongoDB도 다양한 Storage Engine을 사용할 수 있다.
- MongoDB 3.2 버전부터 기본 Storage Engine은 Wired Tiger이다.
    - 3.2 이전 버전은 MMAPv1 사용
- Wired Tiger 도입으로 MongoDB의 성능이 크게 향상됐다.

|                               | MMAPv1                                 | Wired Tiger    |
|:-----------------------------:|----------------------------------------|----------------|
| Data Compression<br/>(데이터 압축) | 지원하지 않는다.                              | 지원한다.          |
|             Lock              | 버전에 따라 Database 혹은 Collection 레벨의 Lock | Document 레벨의 락 |
