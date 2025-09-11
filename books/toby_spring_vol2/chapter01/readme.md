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

### 1.1.4 웹 애플리케이션의 IoC 컨테이너 구성

- 웹 애플리케이션에서 스프링 IoC 컨테이너를 사용하는 방법은 웹 모듈 내부에 두는 두 가지 방식과 엔터프라이즈 애플리케이션 레벨에 두는 방식이 있다.
- 스프링 기반 자바 서버는 일반적으로 독립적으로 배치 가능한 웹 모듈(WAR) 형태로 배포된다.
- 대표 서블릿이 공통 작업을 처리하고, 이후 개별 기능을 담당하는 핸들러를 호출하는 프론트 컨트롤러 패턴이 사용된다.
- 웹 애플리케이션 안에서 동작하는 IoC 컨테이너
    1. 스프링 애플리케이션의 요청을 처리하는 서블릿 내부에 생성되는 컨테이너
    2. 웹 애플리케이션 레벨에서 생성되는 컨테이너

    - 스프링 애플리케이션은 두 개의 컨테이너, WebApplicationContext 객쳋가 만들어진다.
- 웹 애플리케이션의 컨텍스트 계층구조
    - 웹 애플리케이션 레벨 컨테이너는 계층구조의 최상단에 위치하며, 서블릿 레벨 컨테이너들의 부모 컨테이너 역할을 한다.
    - 하나의 웹 애플리케이션 안에는 여러 개의 스프링 프론트 컨트롤러 서블릿을 등록할 수 있으며, 각각은 독립적인 애플리케이션 컨텍스트를 가진다.
        - 공통적으로 사용되는 빈은 웹 애플리케이션 레벨 컨텍스트에 등록해 공유한다.
    - 일반적으로 하나의 서블릿만 등록해서 사용하지만, 웹 기술 의존적인 부분과 독립적인 부분을 분리하기 위해 여러 개의 스프링용 서블릿을 등록할 수도 있다.
    - 스프링은 서블릿 컨텍스트를 통해 루트 애플리케이션 컨텍스트에 접근할 수 있는 방법을 제공한다.
      ````java
      WebApplicationContextUtils.getWebApplicationContext(ServletContext servletContext);
      ````
    - `ServletContext`는 웹 애플리케이션마다 하나씩 만들어지며, 서블릿의 런타임 환경 정보를 담고 있다.
      <img alt="Image" src="https://github.com/user-attachments/assets/4779ac68-1a13-40c6-87a8-22a882598ffb" />
    - 애플리케이션을 계층구조로 구성하면 웹 기술을 쉽게 확장, 변경, 조합할 수 있어 권장된다.
    - 계층구조를 사용할 때 주의사항
        - 루트 컨텍스트에서 하위 컨텍스트의 빈은 참조할 수 없다.
        - 루트 컨텍스트에 정의된 빈은 이름이 같은 서블릿 컨텍스트의 빈이 있으면 무시된다.
        - 하나의 컨텍스트에 정의된 AOP 설정은 다른 컨텍스트의 빈에는 적용되지 않는다.
    - 스프링 XML 설정파일도 성격과 관리 주체에 따라 여러 파일로 분리해서 사용하면 유용하다.
- 웹 애플리케이션의 컨텍스트 구성 방법
    - 서블릿 컨텍스트와 루트 애플리케이션 컨텍스트 계층구조
        - 가장 많이 사용되는 기본적인 방식이다.
        - 웹 관련 빈은 서블릿 컨텍스트에 두고, 그 외의 공통 빈은 루트 애플리케이션 컨텍스트에 등록한다.
    - 루트 애플리케이션 컨텍스트 단일구조
        - 스프링 웹 기술을 사용하지 않는 경우, 서블릿을 둘 필요가 없으므로 루트 애플리케이션 컨텍스트만 등록한다.
    - 서블릿 컨텍스트 단일구조
        - 스프링 웹 기술만 사용하는 경우, 루트 애플리케이션 컨텍스트 없이 서블릿 컨텍스트에 모든 빈을 등록한다.
- 루트 애플리케이션 컨텍스트 등록
    - 웹 애플리케이션 레벨에 루트 애플리케이션 컨텍스트를 등록하는 가장 간단하 방법은 서블릿 이벤트 리스너(Event Listener)를 이용하는 것이다.
    - `ServletContextListener`를 사용하면 웹 애플리케이션의 시작과 종료 시점에 발생하는 이벤트를 처리할 수 있다.
    - 스프링은 이를 위해 `ContextLoaderListener`를 제공한다.
        - `web.xml` 파일에 리스너를 선언하면 자동으로 루트 애플리케이션 컨텍스트가 생성된다.
          ```xml
          <listener>
              <listener-class>org.springframework.web.context.ContextLoaderListener</listener-class>
          </listener>
          ```
    - `<context-param>`을 사용해 필요한 설정을 지정할 수 있다.
        - `contextConfigLocation`
            - XML 설정 파일의 위치를 지정한다.
        - `contextClass`
            - 사용할 애플리케이션 컨텍스트 구현 클래스를 지정한다.
- 서블릿 애플리케이션 컨텍스트 등록
    - 스프링의 웹 기능을 지원하는 프론트 컨트롤러 서블릿은 `DispatcherServlet`이다.
    - `DispatcherServlet`을 등록하려면 `web.xml`에 서블릿을 선언하면 된다.
        ```xml
        <servlet>
            <servlet-name>spring</servlet-name>
            <servlet-class>org.springframework.web.servlet.DispatcherServlet</servlet-class>
            <load-on-startup>1</load-on-startup>
        </servlet>
        ```
        - `<servlet-name>`
            - `DispatcherServlet`이 생성하는 애플리케이션 컨텍스트는 모두 독립적인 네임스페이스를 가진다.
            - 네임스페이스를 통해 여러 개의 `DispatcherServlet`을 구분할 수 있고, 각 네임스페이스별로 디폴트 설정 파일을 가질 수 있다.
        - `<load-on-startup>`
            - 서블릿 컨테이너가 해당 서블릿을 언제 생성하고 초기화할지, 그리고 그 순서를 결정하는 정수 값이다.
            - `DispatcherServlet`은 초기화 과정에서 스프링 컨텍스트를 생성하므로, 웹 애플리케이션 시작 시 최대한 빨리 초기화되도록 설정하는 것을 권장한다.
                - 컨텍스트 및 빈 초기화 과정에서 발생하는 문제를 애플리케이션 기동 초기에 빠르게 확인할 수 있다.
            - `<init-param>`을 사용하면 `contextConfigLocation`과 `contextClass`를 지정할 수 있다.

## 1.2 IoC/DI를 위한 빈 설정 메타정보 작성

- IoC 컨테이너는 애플리케이션을 구성하는 POJO로 만들어진 객체를 생성하고 관리하는 역할을 한다.
- IoC 컨테이너는 빈 설정 메타정보를 통해 `BeanDefinition` 타입의 객체로 변환해서 활용한다.
