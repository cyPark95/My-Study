# MSA(Micro Service Architecture)

- 모놀리식과 SOA는 요구사항 변화에 따른 빠른 대처에 어려움이 있다.
- Cloud 환경과 Cloud 기술 스택(컨테이너 가상화, 오케스트레이션 ..)의 발전으로 MSA가 일반적일 수 있는 상황이 만들어젔다.

## 핵심 원칙

- Componentization via Services
    - 서비스 단위로 구성하여 기능을 독립적으로 배포하고 관리
- Organized around **Business Capabilities**
    - 조직의 비즈니스 역량에 맞춰 구조화
    - 비즈니스 변화에 빠르게 대응할 수 있도록 설계해야 한다.
      ![image](https://github.com/user-attachments/assets/edc504b0-0f2f-4b0d-b3b9-385de5e225d4)
- Products not Projects
    - 개발자와 운영자가 동일 팀에서 지속적으로 책임진다.
      ![image](https://github.com/user-attachments/assets/942f6f00-18ab-40a6-ad17-3b1cbfb3f69d)
- Smart endpoints and dumb pipes
    - 통신은 단순한 프로토콜을 사용하며, 주로 RESTful API를 사용
    - 관심의 분리 - Separation of Concerns
- Decentralized Data Management / Governance
    - 데이터베이스를 서비스별로 분리하여 독립성을 높인다.
    - 데이터의 유연성, 탄력성의 확보
- Infrastructure Automation
    - CI/CD와 같은 인프라 자동화의 중요성
    - 지속적인 통합 / 지속적인 배포
- Design for Failure
    - 시스템은 언제든지 문제가 생길 수 있음을 인정한다.
    - 장애에 대비하여 복원력을 갖춘 시스템을 설계해야 한다.
      ![image](https://github.com/user-attachments/assets/73eea4d4-acc6-41ed-9089-37b1f4ad506b)
- Evolutionary Design
    - 반복적 개발과 빠른 피드백을 통해 시스템을 지속적으로 발전시킨다.

[reference](https://martinfowler.com/articles/microservices.html#OrganizedAroundBusinessCapabilities)