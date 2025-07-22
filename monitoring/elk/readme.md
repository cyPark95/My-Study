# ELK Stack

ELK(Elasticsearch, Logstash, Kibana) 프로젝트

- Spring Boot + Logback + logstash-logback-encoder
- Docker(Elasticsearch, Logstash, Kibana)

### Elasticsearch

- 실시간 분산 검색 및 분석 엔진
- JSON 문서 저장 및 검색
- Apache Lucene 기반의 강력한 검색 기능 제공

### Logstash

- 다양한 로그 소스 수집 및 처리 (파일, TCP 등)
- 필터를 통한 데이터 정제 및 변환
- Elasticsearch로 로그 전송

### Kibana

- 웹 기반 데이터 시각화 도구
- 대시보드, 차트, 그래프 등을 통해 로그 분석 및 모니터링 가능

| 구분 | PUSH 방식                     | PULL 방식                    |
|----|-----------------------------|----------------------------|
| 정의 | 클라이언트가 주기적으로 데이터를 서버로 전송    | 서버가 주기적으로 클라이언트로부터 데이터를 수집 |
| 장점 | 보안에 유리 (서버가 클라이언트에 접근하지 않음) | 클라이언트 추가 시 서버만 설정하면 됨      |
| 단점 | 클라이언트마다 수집 설정 필요            | 보안 취약 및 응답 지연 가능성 있음       |

## 파일 구성

- `docker-compose.yml` : ELK 서비스 정의
- `config/` : Elasticsearch, Kibana, Logstash 설정 파일
- `logstash/pipeline/logstash.conf` : Logstash 파이프라인

## 참고 자료

- [Elastic 공식 문서](https://www.elastic.co/guide/index.html)
