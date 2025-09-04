# 1장 IoC 컨테이너와 DI

- 스프링은 유연하고 강력한 IoC 컨테이너와 DI 기술을 제공한다.
- 스프링의 다양한 DI 설정 방식과 IoC 컨테이너 활용 방법에 대해 알아본다.

## 1.1 IoC 컨테이너: 빈 팩토리와 애플리케이션 컨텍스트

- 스프링 애플리케이션에서는 독립된 컨테이너가 객체의 제어권을 가지고 관리하며, 이를 IoC라 한다.
- 스프링의 IoC 컨테이너는 일반적으로 애플리케이션 컨텍스트를 의미하며, 빈 팩토리 역할뿐 아니라 그 이상의 기능을 제공한다.
- 스프링의 빈 팩토리와 애플리케이션 컨텍스트는 각각 기능을 대표하는 `ApplicationContext`와 `BeanFactory` 두 개의 인터페이스로 정의되어 있다.
- 빈 팩토리와 애플리케이션 컨텍스트는 각각 `BeanFactory`와 `ApplicationContext` 인터페이스로 정의되며, `ApplicationContext`는 `BeanFactory`를 상속한 형태다.
- 일반적으로 스프링 컨테이너 또는 IoC 컨테이너라 할 때는 `ApplicationContext` 인터페이스를 구현한 객체를 가리킨다.

### 1.1.1 IoC 컨테이너를 이용해 애플리케이션 만들기

- `ApplicationContext` 구현 클래스의 인스턴스를 생성하면 IoC 컨테이너를 만들 수 있다.
- IoC 컨테이너는 POJO 클래스와 설정 메타 정보를 기반으로 동작한다.
- POJO 클래스
    - 애플리케이션의 핵심 코드를 담고 있으며 특정 기술이나 스펙에 종속되지 않는다.
    - 의존관계에 있는 다른 POJO와는 인터페이스를 통해 느슨한 결합을 유지해야 한다.
- 설정 메타정보
    - IoC 컨테이너가 관리하는 빈을 어떻게 생성하고 동작시킬지를 정의하는 정보다.
    - 스프링의 설정 메타정보는 특정 파일 포맷에 제한되지 않으며, `BeanDefinition` 인터페이스로 표현되는 추상 정보다.
        - `BeanDefinitionReader` 인터페이스를 통해 다양한 형식의 설정을 `BeanDefinition` 객체로 변환할 수 있다.
    - `BeanDefinition` 인터페이스로 정의되는 주요 메타정보
        - 빈 아이디, 이름, 별칭: 빈 객체를 구분할 수 있는 식별자
        - 클래스 또는 클래스 이름: 빈으로 만들 POJO 클래스 또는 서비스 클래스 정보
        - 스코프: 싱글톤, 프로토타입과 같은 빈의 생성 방식과 존재 범위
        - 프로퍼티 값 또는 참조: DI에 사용할 프로퍼티 이름과 값 또는 참조하는 빈의 이름
        - 생성자 파라미터 값 또는 참조: DI에 사용할 생성자 파라미터 이름과 값 또는 참조할 빈의 이름
        - 지연된 로딩 여부, 우선 빈 여부, 자동와이어링 여부, 부모 빈 정보, 빈 팩토리 이름 등
    - 스프링 IoC 컨테이너는 이 메타정보를 바탕으로 빈을 생성하고 의존성을 주입을 통해 애플리케이션을 구성한다.
      <img alt="Image" src="https://github.com/user-attachments/assets/b5189cb5-3721-47e1-8631-f42bef978cd3" />
    - 스프링 애플리케이션은 POJO 클래스와 설정 메타정보를 이용해 IoC 컨테이너가 만들어내는 객체 조합이라고 할 수 있다.
    - IoC 컨테이너는 빈을 객체 단위로 관리하므로, 필요에 따라 하나의 클래스를 여러 개의 빈으로 등록할 수도 있다.

### 1.1.2 IoC 컨테이너의 종류와 사용 방법

- 스프링은 다양한 용도의 `ApplicationContext` 구현 클래스를 제공한다.
- `StaticApplicationContext`
    - 코드를 통해 빈 메타정보를 등록할 때 사용한다.
    - 스태틱 애플리케이션 컨텍스트는 테스트 목적으로만 사용하고, 실전에서는 사용하면 안 된다.
- `GenericApplicationContext`
    - 실무에서 사용 가능한 모든 기능을 갖춘 가장 범용적인 애플리케이션 컨텍스트다.
    - 컨테이너의 주요 기능을 DI 방식으로 확장할 수 있도록 설계되어 있다.
    - 외부 리소스에 담긴 빈 설정 메타정보를 `BeanDefinitionReader` 구현체를 통해 읽어와 메타정보로 변환해 사용한다.
        - 여러 개의 빈 설정 리더를 동시에 사용해 다양한 형태의 리소스에서 메타정보를 읽어올 수도 있다.
    ```java
    public void genericApplicationContext() {
        GenericApplicationContext applicationContext = new GenericApplicationContext();
        XmlBeanDefinitionReader beanDefinitionReader = new XmlBeanDefinitionReader(applicationContext);
        beanDefinitionReader.loadBeanDefinitions("classpath:ioc-context.xml");
        applicationContext.refresh();

        // ...
    }
    ```
    - 일반적으로 개발자가 직접 `GenericApplicationContext`를 생성하거나 초기화할 일은 많지 않지만, 내부적으로 자주 활용된다.
- `GenericXmlApplicationContext`
    - `GenericApplicationContext`와 `XmlBeanDefinitionReader` 클래스가 결합된 형태의 애플리케이션 컨텍스트다.
- `WebApplicationContext`
    - `ApplicationContext`를 확장한 인터페이스로, 스프링 애플리케이션에서 가장 많이 사용되는 애플리케이션 컨텍스트다.
    - 웹 환경에서 필요한 기능이 추가된 애플리케이션 컨텍스트다.
    - 스프링 IoC 컨테이너를 적용했을 때 웹 애플리케이션 기동 과정
      <img alt="Image" src="https://github.com/user-attachments/assets/b5530b42-ef18-461a-bee3-369ec4a7a319" />
        - 스프링 IoC 컨테이너는 빈 설정 메타정보를 바탕으로 빈 객체를 생성하고 DI를 수행한다.
        - 서블릿 컨테이너는 클라이언트의 HTTP 요청을 받아 해당 요청에 매핑된 서블릿을 실행한다.
        - 서블릿은 애플리케이션 실행 시, 미리 준비된 웹 애플리케이션 컨텍스트에서 애플리케이션 실행에 필요한 빈을 요청하여 받아둔다.
        - 이후 지정된 메서드를 호출하면, 스프링 컨테이너가 DI 방식으로 구성한 애플리케이션의 기능이 시작된다.
    - 스프링은 이를 위해 `DispatcherServlet`이라는 서블릿을 제공한다.
    - `web.xml`에 `DispatcherServlet`을 등록하는 것만으로도 스프링 컨테이너가 웹 환경에서 자동으로 생성되며, 애플리케이션 실행에 필요한 대부분의 준비가 완료된다.

### 1.1.3 IoC 컨테이너 계층구조

- 일반적으로 애플리케이션마다 하나의 IoC 컨테이너만 사용해도 충분하지만, 필요에 따라 트리 형태의 계층구조를 만들 수도 있다.
- 부모 컨텍스트를 이용한 계층구조 효과
    - 각 컨텍스트는 독립적인 설정 정보를 바탕으로 빈을 관리한다.
    - DI를 위해 빈을 검색할 때, 먼저 자신의 컨텍스트뿐만 아니라 부모 컨텍스트까지 모두 검색한다.
        - 부모는 자식 컨텍스트의 빈을 검색하지 않는다.
    - 검색 순서는 자신 -> 직계 부모 순서로 진행된다.
    - 계층구조를 활용하면 여러 애플리케이션 컨텍스트가 공유하는 설정을 만들 수 있다.
    - 하지만 계층 구조를 제대로 이해하지 못하면 예상치 못한 방식으로 동작할 수 있으므로 주의가 필요하다.
- 컨텍스트 계층구조 테스트(소스 참고)
    - 부모/자식 구조의 계층구조는 스프링 애플리케이션에서 자주 활용되는 방법이다.
    - 하지만 반드시 일정한 규칙과 제어 원칙을 지키면서, 불필요한 중복 빈 정의는 최대한 피하는 것이 바람직하다.
