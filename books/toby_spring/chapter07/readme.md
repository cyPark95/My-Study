# 7장 스프링 핵심 기술의 응용

- 스프링은 객체지향 기술을 가장 가치를 두고 있다.
- 스프링의 모든 기술은 객체지향 언어의 장점을 활용해서 코드를 작성할 수 있도록 도와준다.
- 스프링이 다양한 분야에 핵심 기술을 적용했듯, 개발자도 세 가지 주요 기술을 상황에 맞게 응용할 수 있어야 한다.
- 7장에서는 스프링의 개발 철학과 추구하는 가치, 그리고 사용자에게 요구되는 자세를 살펴본다.

## 7.1 SQL과 DAO의 분리

- DAO는 순수한 데이터 액세스 코드만 존재한다.
    - 반복적인 JDBC 작업 흐름은 템플릿으로 제거했다.
    - 트랜잭션 처리와 예외 처리는 서비스 추상화나 AOP 등을 통해 분리했다.
    - 서로 다른 책임은 분리하고, 인터페이스와 DI를 활용해 다이나믹하게 관계를 설정할 수 있도록 설계했다.
- 그러나 여전히 DB 테이블과 필드 정보를 담고 있는 SQL 문장이 DAO 내부에 남아 있다.
    - DAO는 데이터를 조회하고 조작하는 인터페이스 역할을 한다.
    - SQL은 테이블 구조나 필드명 변경 변경 작업은 빈번히 발생한다.
    - SQL만 바뀌더라도 DAO 코드를 수정하고 컴파일해야 하는 것은 비효율적이고 위험하다.
- 따라서 DAO 코드와 SQL을 분리하는 것이 좋다.

### 7.1.1 XML 설정을 이용한 분리

- SQL을 XML 파일로 분리하는 방법을 생각해 볼 수 있다.
- 스프링은 설정을 통해 빈에 값을 주입할 수 있다.
- SQL은 문자열이므로, 프로퍼티 값으로 정의해 DAO에 주입할 수 있다.
- 이를 통해 SQL을 코드와 독립적으로 관리할 수 있다.
- 개별 SQL 프로퍼티 방식
    ```xml
    <bean id="userDao" class="study.user.dao.UserDaoJdbc">
        <property name="dataSource" ref="dataSource"/>
        <property name="sqlAdd" value="insert into users(id, name, password, level, login, recommend, email) values(?, ?, ?, ?, ?, ?, ?)"/>
    </bean>
    ```
- SQL 맵 프로퍼티 방식
    ```xml
        <bean id="userDao" class="study.user.dao.UserDaoJdbc">
        <property name="dataSource" ref="dataSource"/>
        <property name="sqlMap">
            <map>
                <entry key="add" value="insert into users(id, name, password, level, login, recommend, email) values(?, ?, ?, ?, ?, ?, ?)"/>
            </map>
        </property>
    </bean>
    ```
    - 이 방식을 사용하면 SQL을 일일이 설정에 추가하는 번거로움을 줄일 수 있다.
    - 하지만 문자열 기반 키를 사용하므로, 메서드 실행 전까지 오류를 확인하기 어렵다.
    - `UserDaoTest` 같은 포괄적인 테스트를 통해 미리 검증하는 것이 좋다.

### 7.1.2 SQL 제공 서비스

- XML을 통해 SQL을 관리하면, SQL을 코드에서 간단히 분리할 수 있다.
- 문제점
    - SQL은 데이터 액세스 로직의 일부인데, DI 설정과 섞이면 관리가 어렵다.
    - SQL을 독립적으로 관리해야 리뷰나 성능 튜닝이 편하다.
    - XML 설정은 애플리케이션을 재시작하지 않으면 변경 내용을 적용하기 어렵다.
- 해결 방법
    - DAO가 사용할 SQL을 제공하는 기능을 독립시킨다.
    - 외부에서 SQL을 불러오는 SQL 정보 소스를 사용한다.
    - 운영 중에도 동적으로 갱신 가능한 확장성 있는 SQL 서비스가 필요하다.
- SQL 서비스 인터페이스(소스 참고)
    - 키 값을 전달하면 해당 SQL을 반환하는 기능을 제공한다.
    - SQL이 어디에 저장되어 있는지, 어떻게 검색되는지는 DAO의 관심사가 아니다.
- 스프링 설정을 사용하는 단순 SQL 서비스(소스 참고)
    - 구체적인 구현 방법과 기술에 상관 없이 `SqlService` 인터페이스 타입으로 DI 받아 사용할 수 있다.

## 7.2 인터페이스의 분리와 자기참조 빈

- 인터페이스는 내부에 숨겨진 방대한 서브 시스템을 외부에 단순하게 노출하는 관문에 불과하다.
- 구현 방식이나 확장 가능성에 따라, 인터페이스를 유연하게 재구성할 수 있도록 설계하는 것이 중요하다.

### 7.2.1 XML 파일 매핑

- XML 설정 파일에서 `<bean>` 태그 안에 SQL 정보를 넣어 사용하는 방식은 권장되지 않는다.
- SQL 전용 포맷을 가진 독립된 XML 파일을 사용하는 것이 바람직하다.
- 검색용 키와 SQL 문장을 담은 간단한 XML 문서를 설계하고, 이 파일에서 SQL을 읽어 DAO에 제공하는 SQL 서비스 구현 클래스를 만들 수 있다.
- JAXB(Java Architecture for XML Binding)
    - XML에 담긴 정보를 파일에서 읽어오는 방법이다.
    - 장점
        - XML 문서 정보를 동일한 구조의 객체로 매핑해준다.
        - XML 문서의 구조를 정의한 스키마를 이용해서 매핑할 객체의 클래스까지 자동으로 만들어주는 컴파일러도 제공한다.
            - 자동 생성된 객체에는 매핑정보가 어노테이션으로 담겨 있다.
- SQL 맵을 위한 스키마 작성과 컴파일(소스 참고)
    ```shell
    xjc -p {패키지이름} {변환할 스키마 파일} -d {생성된 파일이 저장될 위치}
    ```
- 언마샬링
    - XML 문서를 읽어서 자바의 객체로 변환하는 것을 JAXB에서는 언마살링(Unmarshalling)이라고 한다.
    - 바인딩 오브젝트를 XML 문서로 변환하는 것은 마살링(Marshalling)이라고 한다.
    - 자바의 직렬화과 비슷하다.

### 7.2.2 XML 파일을 이용하는 SQL 서비스

- SQL 맵 XML 파일(소스 참고)
- XML SQL 서비스(소스 참고)

### 7.2.3 빈의 초기화 작업

- `XmlSqlService`의 생성자에서 예외가 발생할 수 있는 복잡한 로직을 처리하는 것은 바람직하지 않다.
    - 문제점
        - 생성자에서 예외가 발생하면 처리하기 어렵다.
        - 상속이 제한적이다.
        - 보안 측면에서 문제가 생길 수 있다.
    - 객체를 단순한 초기 상태로 생성한 뒤, 별도의 초기화 메서드를 사용하는 방식이 권장된다.
    - SQL을 담은 XML 파일의 경로와 이름이 코드에 고정되지 않도록 DI로 설정할 수 있도록 개선 한다.

```java
XmlSqlService sqlProvider = new XmlSqlService();
sqlProvider.setSqlmapFile("sqlmap.xml");
sqlProvider.loadSql();
```

- `XmlSqlService` 객체가 스프링 빈으로, 객체 생성과 초기화의 제어권은 스프링에 있다.
- 스프링이 제공하는 빈 후처리기을 활용할 수 있다.
    - `@PostConstruct` 어노테이션을 사용하면, 빈 객체가 생성되고 DI까지 완료된 후 호출될 메서드를 지정할 수 있다.

### 7.2.4 변화를 위한 준비: 인터페이스 분리

- 현재 SQL을 가져오는 방식이 특정 기술에 고정되어 있다.
    - XML 파일에서 SQL을 읽어오는 방식
    - `HashMap`을 사용하는 저장/검색 방식
- 변경 이유가 두 가지로, 단일 책임 원칙을 위반한다.
- 변하는 시기와 성질이 다른 요소를 분리하는 것이 바람직한 설계 구조다.
- 책임에 따른 인터페이스 정의
    - 서로 독립적으로 변경 가능한 책임을 분리해 각각의 인터페이스로 정의한다.
        - 외부 리소스에서 SQL 정보를 읽어오는 기능
        - 읽어온 SQL을 저장하고, 필요한 시점에 제공하는 기능
        - 애플리케이션 서버를 재시작하지 않고 SQL을 수정할 수 있는 기능
    - 전략 패턴을 활용해 이 기능들을 별도의 객체로 분리한다.
    - 복잡한 정보를 전달하기 위해 일정한 포맷으로 강제 변환하지 않아도 되는 구조
        - `SqlReader`가 SQL 정보를 읽고, 이를 직접 `SqlRegistry`에 저장하게 하면 된다.
        - 자바 객체는 자신의 데이터를 알고 있고, 그 데이터에 대한 작업도 스스로 수행할 수 있다.
            - 불필요하게 데이터를 외부로 노출할 필요가 없다.
        - `SqlReader`는 내부에 갖고 있는 SQL 정보를 형식을 갖춰 돌려주는 대신, 의존 객체인 `SqlRegistry`에게 필요에 따라 등록을 요청할 때만 활용하면 된다.
        - `SqlReader`는 SQL 정보를 반환하기보다, 의존하고 있는 `SqlRegistry`에 직접 등록하는 방식으로 협력할 수 있다.
- `SqlRegistry` 인터페이스(소스 참고)
    - 예외 복구 가능성을 고려해, 런타임 예외라도 메소드에 명시적으로 선언해두는 것을 권장한다.
- `SqlReader` 인터페이스(소스 참고)

### 7.2.5 자기참조 빈으로 시작하기

- 다중 인터페이스 구현과 간접 참조
    - 자바는 인터페이스의 다중 상속을 허용한다.
    - 인터페이스 구현은 타입을 상속하는 것으로 다형성을 활용할 수 있다.
    - 동일한 클래스 내에 포함된 코드라도 서로 다른 책임을 인터페이스로 명확히 분리하면, 코드의 역할을 보다 명확히 드러낼 수 있다.
- 인터페이스를 이용한 분리(소스 참고)
- 자기참조 빈 설정
    ```xml
    <bean id="sqlService" class="study.user.sqlservice.XmlSqlService">
        <property name="sqlmapFile" value="/sql/sqlmap.xml"/>
        <property name="sqlReader" ref="sqlService"/>
        <property name="sqlRegistry" ref="sqlService"/>
    </bean>
    ```
    - 자기 참조 방법은 흔하게 사용하는 방법은 아니지만 책임과 관심사가 복잡하게 얽혀 있어 얽혀 있을 때 구조를 정리하고 유연성을 확보하기 위한 초기 단계로 유용하다.

### 7.2.6 디폴트 의존관계

- 확장 가능한 기반 클래스(소스 참고)
    ```xml
    <bean id="sqlService" class="study.user.sqlservice.BaseSqlService">
        <property name="sqlReader" ref="sqlReader"/>
        <property name="sqlRegistry" ref="sqlRegistry"/>
    </bean>

    <bean id="sqlReader" class="study.user.sqlservice.sqlreader.JaxbXmlSqlReader">
        <property name="sqlmapFile" value="/sql/sqlmap.xml"/>
    </bean>

    <bean id="sqlRegistry" class="study.user.sqlservice.sqlregistry.HashMapSqlRegistry"/> 
    ```
- 디폴트 의존관계를 갖는 빈 만들기
    - 기능을 분리하고 인터페이스, 전략 패턴, DI를 적용하면 확장을 고려한 구조를 만들 수 있지만, 클래스와 인터페이스가 늘어나고 의존관계 설정이 복잡해지는 부담은 감수해야 한다.
    - 특정 의존 객체가 대부분의 상황에서 기본적으로 사용된다면, 디폴트 의존관계를 갖도록 설계하면 설정을 단순화할 수 있다.
    - 디폴트 의존관계란 외부에서 DI 받지 않는 경우 기본적으로 적용되는 의존 객체를 말한다.
    - 자신이 사용할 디폴트 의존 객체를 스스로 DI 하면, 특별한 설정 없이 편리하게 사용할 수 있다.
    - 디폴트 의존 객체를 직접 DI 하는 경우 프로퍼티를 외부에서 지정할 수 없다.
        - 해결 방법
            - `sqlmapFile`을 `DefaultSqlService`의 프로퍼티로 정의한다.
                - 디폴트란 명시적인 설정이 없는 경우에만 사용하는 것이기 때문에 적절하지 않다.
            - `JaxbXmlSqlReader`에 관용적인 이름의 디폴트 `sqlmapFile` 파일 이름을 지정해서 가지고 있는다.
    - `DefaultSqlService`는 `SqlService`를 직접 구현한 것이 아니라, `BaseSqlService`를 상속한 구조로 되어 있다.
        - `SqlReader`와 `SqlRegistry` 프로퍼티를 가지고 있으므로, DI를 통해 필요한 의존 객체로 변경할 수 있다.
    - 단점
        - 디폴트 의존 객체를 생성자에서 만들면 외부에서 설정한 빈 객체로 대체되더라도 내부에서 생성된 객체가 만들어진다.
        - 이런 경우 `@PostConstruct` 초기화 메서드에서 프로퍼티 설정 여부를 확인한 뒤, 없는 경우에만 디폴트 객체를 생성하도록 개선할 수 있다.
    - 디폴트 의존 객체는 설정을 최소화하면서도, 필요한 부분은 DI를 통해 확장할 수 있도록 하는 유연한 구조를 제공한다.

## 7.3 서비스 추상화 적용

- `JaxbXmlSqlReader` 개선 사항
    - 필요에 따라 JAXB 외에 다른 기술로 손쉽게 변경할 수 있어야 한다.
    - XML 파일을 더 다양한 소스에서 가져올 수 있어야 한다.

### 7.3.1 OXM 서비스 추상화

- OXM(Object-XML Mapping)은 XML과 자바 객체를 매핑하여 상호 변환하는 기술이다.
- 서비스 추상화를 적용하면, JAXB뿐 아니라 다양한 OXM 프레임워크와 기술에 대해 독립적인 코드를 작성할 수 있다.
- 스프링은 트랜잭션, 메일 전송과 마찬가지로 OXM에 대해서도 서비스 추상화 기능을 제공한다.
- OXM 서비스 인터페이스
    - 대표적으로 `Marshaller`와 `Unmarshaller`가 있고, `SqlReader`는 `Unmarshaller`를 사용한다.
    ```java
    public interface Unmarshaller {

    	boolean supports(Class<?> clazz);
    
    	Object unmarshal(Source source) throws IOException, XmlMappingException;
    }
    ```
    - `supports(Class<?>)`: 주어진 클래스 타입이 언마샬링 대상인지 확인한다.
    - `unmarshal(Source)`: XML 데이터를 자바 객체로 변환해 반환한다.
- JAXB 구현 테스트(소스 참고)
- Castor 구현 테스트(Deprecated)
    ```xml
    <bean id="unmarshaller" class="org.springframework.oxm.castor.CastorMarshaller">
        <property name="mappingLocation" value="/mapping.xml" />
    </bean> 
    ```

### 7.3.2 OXM 서비스 추상화 적용

- `SqlReader`는 스프링의 OXM 언마샬러를 이용하도록 제한해서 사용성을 극대화 한다.
- 구체적인 OXM 기술은 `SqlReader`의 구현체에 위임하고, `OxmSqlService`는 `SqlReader` 인터페이스에만 의존하도록 설계하면 기술의 변경이나 확장에 유연하게 대응할 수 있다.
- 멤버 클래스를 참조하는 통합 클래스(소스 참고)
    - `OxmSqlService`는 `SqlReader` 타입의 의존 객체를 사용하지만, 이를 static 멤버 클래스로 내장하여 외부에서 접근할 수 없도록 한다.
        - 내부 전용 구현체를 사용함으로써 외부 의존을 줄이고, 스스로 최적화된 구조를 구성할 수 있다.
        - 자바의 static 멤버 클래스는 구조적으로는 강하게 결합되어 있지만, 논리적으로 분리되는 구조 설계에 적합하다.
    - 설정 간소화
        - 스프링 OXM 추상화를 사용하면 언마샬러와 같은 빈을 등록해야 하기 때문에 설정이 복잡해질 수 있다.
        - `OxmSqlService`는 `SqlReader` 구현을 내부 멤버 클래스로 포함하고, 직접 설정 정보를 주입하는 구조를 사용하여 설정 편의성과 내부 응집도를 높일 수 있다.
        - `OxmSqlReader`는 외부로 노출되지 않기 때문에 스스로 빈으로 등록될 수 없다.
            - `OxmSqlService`의 공개된 프로퍼티를 통해 간접적으로 DI 받아야 한다.
            - `OxmSqlReader`의 경우 필요한 두 개의 프로퍼티는 `OxmSqlService`에서 DI 받아서 넘겨주어야 한다.
            ```java
            public class OxmSqlService implements SqlService {

                private final OxmSqlReader oxmSqlReader = new OxmSqlReader();
                
                public void setUnmarshaller(Unmarshaller unmarshaller) {
                    oxmSqlReader.setUnmarshaller(unmarshaller);
                }
                
                public void setSqlmapFile(String sqlmapFile) {
                    oxmSqlReader.setSqlmapFile(sqlmapFile);
                }
          
                // ...
            }
            ````
        - `OxmSqlService`와 JAXB 언마샬러 두 개의 빈으로 `SqlService`를 빈으로 등록할 수 있다.
- 위임을 이용한 `BaseSqlService`의 재사용
    - `OxmSqlService`는 `SqlReader` 구현을 static 멤버 클래스로 고정해 설정을 단순화하고 의도되지 않은 방식으로 확장될 위험이 없다.
    - 하지만 핵심 기능의 구현이 `BaseSqlService`와 중복되며, 단순한 로직이라면 괜찮지만 복잡해질 경우 심각한 문제가 될 수 있다.
    - 중복된 코드 제거(소스 참고)
        - `OxmSqlService`를 어댑터처럼 구성하고, 핵심 로직은 내부에서 `BaseSqlService`에 위임하도록 설계한다.
            - `SqlService` 역할은 BaseSqlService가 수행하므로 중복 없이 재사용 가능.
            - 프록시의 경우 각각을 별도 빈으로 등록해야 하지만, 한 번만 사용할 것이므로 DI 방식 대신 하나의 클래스로 통합해 관리할 수 있다.

### 7.3.3 리소스 추상화

- `OxmSqlReader`와 `XmlSqlReader`는 `UserDao` 클래스 패스에 존재하는 XML 파일로 제한된다는 문제점이 있다.
- 자바 자체에는 리소스 위치와 형식에 관계없이 일관된 방식으로 접근할 수 있는 인터페이스가 없다.
    - `ClassLoader`, `URL`, `ServletContext` 등 목적에 따라 변경이 필요하다.
- 리소스
    - 스프링은 `Resource` 인터페이스 기반의 추상화 리소스 접근 API를 제공한다.
    - 하지만 스프링에서 `Resource`는 빈이 아니라 값으로 취급하기 때문에 DI 대상이 아니다.
- 리소스 로더
    - 스프링은 `ResourceLoader`를 통해 문자열 접두어를 이용해 `Resource` 객체로 변환하는 기능을 제공한다.
        - 접두어 종류
            - `file:`: 파일 시스템에서 리소스
            - `classpath:`: 클래스 패스의 루트에 존재하는 리소스
            - 없음: `ResourceLoader` 구현에 따라 리소스 결정
            - `http:`: HTTP 프로토콜을 사용해 접근할 수 있는 웹 상의 리소스(`ftp:`도 사용 가능)
        - 스프링의 `ApplicationContext`는 `ResourceLoader`를 구현하며, `<property>`의 `value`로 전달된 문자열을 `Resource` 객체로 자동 변환해준다.
- `Resource`를 이용해 XML 파일 가져오기(소스 참고)
    - `OxmSqlService`에 `Resource` 타입을 적용하면 SQL 매핑 정보를 담은 XML 파일을 다양한 위치에서 불러올 수 있다.
    - `Resource`는 추상화된 리소스 접근 핸들러로, 객체가 생성되었다고 해서 실제 리소스가 반드시 존재하는 것은 아니다.

## 7.4 인터페이스 상속을 통한 안전한 기능확장 

- 기존 기능을 확장하거나 개선할 때, 스프링 답게 접근 방식을 살펴본다.

### 7.4.1 DI와 기능의 확장

- 지금까지 DI를 디자인 패턴이나 프로그래밍 모델의 관점에서 이해해왔다.
- DI의 진정한 가치를 얻기 위해서는 DI에 적합한 객체 설계가 필요하다.
- DI를 의식하는 설계
    - 인터페이스를 활용한 DI는 객체 간의 의존성을 유연하게 만들어, 각각의 객체가 독립적으로 발전할 수 있는 구조를 제공한다.
    - 효과적인 DI 설계를 위해서는 하나의 거대한 객체보다는, 명확한 책임을 가진 여러 객체로 분리된 구조가 필요하다.
    - DI는 런타임에 의존 객체를 다이나믹하게 주입함으로써 유연한 확장을 가능하게 하는 것이 목적이므로, 의존 객체는 자유롭게 확장될 수 있다는 전제를 두도 객체 사이의 관계를 설계해야 한다.
    - 이렇게 설계한다면, 객체지향이 지향하는 유연한 확장성과 재사용성이 가능한 객체지향 설계를 구현하는 데 도움이 된다.
    - 지금 당장에는 문제가 없더라도, 미래에 발생할 변화와 예상해야 올바른 설계가 가능하다.
- DI와 인터페이스 프로그래밍
    - DI를 제대로 활용하려면, 두 객체가 인터페이스를 통해 느슨하게 연결되어야 한다.
    - 인터페이스를 사용하는 이유
        - 다형성을 활용할 수 있다.
            - 하나의 인터페이스를 기반으로 다양한 구현체를 바꿔가며 사용할 수 있다.
        - 인터페이스 분리 원칙을 통해 클라이언트와 의존 객체 사이의 관계를 명확하게 해줄 수 있다.
            - 하나의 객체가 여러 인터페이스를 구현하고 있을 때, 클라이언트는 자신이 필요로 하는 기능에 맞는 인터페이스만을 의존하게 된다.
            - 하나의 객체를 사용하는 여러 클라이언트가 서로 다른 관심사와 목적을 가지고 해당 객체에 의존하고 있다는 의미다.
            - 객체가 응집도 작은 높은 단위로 설계되어 있더라도, 클라이언트의 목적에 따라 인터페이스를 분리해 줄 필요가 있다.
            - 이를 인터페이스 분리 원칙(ISP: Interface Segregation Principle)이라고 한다.
    - DI는 특별한 이유가 없다면 항상 인터페이스를 사용하는 것을 권장한다.

### 7.4.2 인터페이스 상속

- 모든 클라이언트는 자신의 관심사에 맞는 방식으로 객체에 접근할 수 있으며, 불필요한 간섭 없이 유지할 수 있다.
    - 기존 클라이언트에 영향을 주지 않고 객체의 기능을 확장하거나 수정할 수 있다.
        - 새로운 인터페이스를 추가로 정의하고 객체가 이를 추가로 구현하도록 하면 된다.
        - 기존 인터페이스를 상속하여 기능을 확장한 후, 그 인터페이스를 구현함으로써 기능을 확장할 수 있다.
        <img alt="Image" style="margin: 10px;" src="https://github.com/user-attachments/assets/4f8ed1a8-5a53-43ce-8f76-0bc18bd75917" />
    - 제3의 클라이언트를 위한 인터페이스를 제공할 수 있다.
        - 기존 인터페이스를 확장한 인터페이스를 구현하도록 하면 기존 클라이언트에 영향을 주지 않고, 제3의 클라이언트를 위한 기능을 추가적으로 제공할 수 있다.
        - 기존 인터페이스를 확장한 새로운 인터페이스를 구현하게 하면, 기존 클라이언트에 영향 없이 제3의 클라이언트를 위한 기능을 제공할 수 있다.
        ```xml
        <bean id="sqlService" class="study.user.sqlservice.BaseSqlService">
            <property name="sqlRegistry" ref="sqlRegistry"/>
        </bean>
        
        <bean id="sqlRegistry" class="study.user.sqlservice.sqlregistry.UpdatableSqlRegistry"/>
        
        <bean id="sqlAdminService" class="study.user.sqlservice.SqlAdminService">
            <property name="updatableSqlRegistry" ref="sqlRegistry"/>
        </bean>
        ```

## 7.5 DI를 이용해 다양한 구현 방법 적용하기

- 운영 중인 시스템에서 정보를 변경하는 기능을 구현할 때는 동시성 문제를 고려해야 한다.

### 7.5.1 `ConcurrentHashMap`을 이용한 수정 가능 SQL 레지스트리

- `HashMapRegistry`는 JDK의 `HashMap`을 사용하지만, `HashMap`은 멀티 스레드 환경에서 동시성 문제가 발생할 수 있다.
  - `Collections.synchronizedMap()` 등의 방법으로 외부에서 동기화를 적용할 수 있지만, 성능 저하가 발생할 수 있다.
- 데이터 조작에 최적화된 `ConcurrentHashMap` 사용을 권장한다.
  - 데이터 조작 시 전체가 아닌 특정 영역에만 락을 적용한다.
  - 조회 작업에는 락을 적용하지 않는다.
  - 따라서 안전성과 성능을 보장할 수 있다.
- 수정 가능 SQL 레지스트리 테스트(소스 참고)
- 수정 가능 SQL 레지스트리 구현(소스 참고)

### 7.5.2 내장형 데이터베이스를 이용한 SQL 레지스트리 만들기

- `ConcurrentHashMap`은 데이터가 많아지고, 잦은 조회와 변경이 일어나는 환경에서 한계가 있다.
- DB는 인덱스를 활용한 최적화된 검색, 안정적인 동시 처리, 트랜잭션을 통한 데이터 무결성 보장 등 다양한 장점을 제공한다.
- 단순히 SQL을 저장하고 관리할 목적으로 별도의 DB를 구성하는 것은 부담이 될 수 있다.
- 이런 경우 내장형 DB를 사용할 수 있다.
    - 애플리케이션에 포함되어 함께 시작되고 종료되기 때문에 별도 설정이 필요 없다.
    - 데이터는 메모리에 저장하기 때문에 I/O 부하가 적고 성능이 우수하다.
    - 최적화된 락킹, 격리수준, 트랜잭션 기능도 적용할 수 있다.
- 관계형 DB는 복잡한 데이터를 효과적으로 분석하고 조작해야 할 경우 효과적이다.
- 스프링의 내장형 DB 지원 기능
    - 자바에서 사용 가능한 대표적인 내장형 DB로는 Derby, HSQL, H2가 있다.
    - 모두 JDBC 드라이버를 제공하며, 표준 DB와 호환되므로 기존 JDBC 프로그래밍 모델을 그대로 사용할 수 있다.
    - 하지만 내장형 DB는 애플리케이션과 생명주기를 함께하기 때문에, 애플리케이션 내에서 DB를 구동하고 초기화하는 작업이 별도로 필요하다.
    - 이로 인해, 기존의 `DataSource`와 DAO 모델을 그대로 사용하는 방식은 적절하지 않다.
    - 스프링은 내장형 DB를 편리하게 사용할 수 있도록 추상화 서비스 기능을 제공한다.
        - 다른 스프링의 추상화 서비스 기능과 달리, 별도의 레이어나 인터페이스를 제공하지는 않는다.
        - 초기화 작업을 지원하는 내장형 DB 빌더를 제공한다.
    - 내장형 DB 빌더
        - 내장형 DB 사용 시 필요한 URL, 드라이버 등 초기화 및 테이블 생성, 초기 데이터를 삽입 SQL 실행해주는 기능이 있다.
        - 모든 준비가 완료되면, 내장형 DB에 대한 `DataSource` 객체를 반환한다.
    - 스프링은 내장형 DB는 애플리케이션 내부에서 종료를 제어할 수 있도록 `DataSource`를 상속한 `EmbeddedDatabase` 인터페이스를 제공한다.
        - `shutdown()` 메서드를 통해 내장형 DB 종료 요청을 할 수 있다.
- 내장형 DB 빌더 학습 테스트(소스 참고)
- 내장형 DB를 이용한 `SqlRegistry` 만들기(소스 참고)
    - 스프링에서 내장형 DB를 사용하려면 `EmbeddedDatabaseBuilder`를 사용하면 된다.
    - `EmbeddedDatabaseBuilder`는 단순히 빈으로 등록해서 사용할 수 없고, DB 초기화 작업이 필요하기 때문에 팩토리 빈으로 등록하는 것이 적절하다.
    - 스프링은 팩토리 빈을 등록할 수 있도록 전용 XML 태그를 제공한다.
        - 전용 태그는 `jdbc` 네임스페이스에 정의되어 있다.
    - 인터페이스 분리 원칙을 지키기 위해 `EmbeddedDatabase`가 아닌 `DataSource` 타입으로 DI 받도록 한다.
- `UpdatableSqlRegistry` 테스트 코드의 재사용(소스 참고)
    - `ConcurrentHashMapSqlRegistry`와 `EmbeddedDbSqlRegistry`는 동일한 인터페이스를 구현하기 때문에 테스트 내용이 중복될 가능성이 높다.
    - 이런 경우 공통 테스트 코드를 추상 클래스로 분리하고, 상속을 통해 테스트 코드를 공유할 수 있다.
- XML 설정을 통한 내장형 DB의 생성과 적용
    - `<jdbc:embedded-database>` 태그에 의해 만들어지는 `EmbeddedDatabase` 타입 빈은 스프링 컨테이너가 종료될 때 자동으로 `shutdown()` 메서드가 호출된다.
    ```xml
    <jdbc:embedded-database id="embeddedDatabase" type="H2">
        <jdbc:script location="classpath:schema.sql"/>
    </jdbc:embedded-database>
    ```

### 7.5.3 트랜잭션 적용

- `EmbeddedDbSqlRegistry`에서 SQL을 `Map` 형태로 한 번에 전달받아 수정할 때, 트랜잭션이 적용되어 있지 않다.
- 여러 개의 SQL 수정 작업은 반드시 하나의 트랜잭션 내에서 처리되어야 한다.
- `HashMap`과 같은 컬렉션은 트랜잭션과 원자성을 보장하려면 복잡한 로직이 필요하기 때문에 적용이 어렵다.
- 하지만 DB 자체적으로 트랜잭션 기반으로 설계되어 있기 때문에 트랜잭션 적용이 상대적으로 수월하다.
- 스프링에서 트랜잭션을 적용할 때 트랜잭션의 경계가 DAO 외부이고 범위가 넓다면 AOP 방식이 적합하다.
- 하지만 SQL 레지스트리라는 제한된 객체 내에서 간단한 트랜잭션이 필요한 경우, AOP보다 트랜잭션 추상화 API를 직접 사용하는 방식이 더 적합하다.
- 다중 SQL 수정에 대한 트랜잭션 테스트(소스 참고)
- 코드를 이용한 트랜잭션 적용(소스 참고)
    - 여러 AOP 트랜잭션 프록시가 하나의 `PlatformTransactionManager`를 공유할 수 있도록, 트랜잭션 매니저는 싱글톤 빈으로 등록해 사용하는 것이 일반적이다.
    - 하지만 `EmbeddedDbSqlRegistry`처럼 특정 목적에만 사용된다면, `TransactionTemplate`를 내부에서 직접 만들어 사용하는 방식이 더 적합하다.
    - 트랜잭션 작업이 빈번하게 발생하는 환경에서는 격리수준(Isolation Level)도 함께 고려해야 한다.

> 내장형 DB의 트랜잭션 격리수준 지원
> 
> - HSQL의 경우 1.8 이하의 버전에서는 `READ_UNCOMMITTED`만을 지원한다.
> - `READ_UNCOMMITTED` 트랜잭션 격리수준은 한 트랜잭션이 종료되기 전의 작업 내용이 다른 트랜잭션이 읽을 위험성이 있다.
> - 이런 문제를 피하려면 HSQL 1.9이상, H2, Derby와 같은 DB를 사용해야 한다.

## 7.6 스프링 3.1의 DI

- 스프링은 여러 차례 변화와 발전을 거쳤지만, 객체지향적인 설계 원칙을 지켜온 덕분에 기본 철학을 유지하면서도 유연성과 확장성을 지속할 수 있었다.
- 자바 언어의 변화와 스프링
    - 어노테이션의 메타정보 활용
        - 초기의 리플렉션 API는 주로 자바 코드나 컴포넌트를 작성하는 툴을 개발할 때 사용되었다.
        - 그러나 점차 본래의 목적보다는, 자바 코드의 메타정보를 활용하는 스타일의 프로그래밍 방식에 더 많이 사용되기 시작했다.
        - 이러한 흐름의 정점에 있는 것이 바로 어노테이션이다.
        - 어노테이션은 클래스의 타입이나 동작에 직접적인 영향을 주지 않으며, 일반 코드에서 활욜될 수 없다.
            - 객체지향 프로그래밍 스타일의 코드나 패턴 등에 적용할 수 없다.
            - 어노테이션은 핵심 로직을 담은 자바 코드, 이를 지원하는 IoC 기반 프레임워크, 프레임워크가 참조할 수 있는 메타정보라는 구조에 잘 어울린다.
        - XML 방식
            - 작성이 편리하고, 빌드과정 없이 수정이 가능하다.
            - 유연하게 빈 생성 및 관계를 설정할 수 있다.
        - 어노테이션 방식
            - 타입 정보에 기반한 메타정보를 활용할 수 있다.
            - 리팩토링 시 IDE나 컴파일러의 도움을 받아 안전하고 일관된 수정이 가능하다.
            - 하지만 자바 코드 내에 존재하기 때문에 변경 시 컴파일이 필요하다.
        - 스프링 3.1부터는 어노테이션 기반의 메타정보 작성이 스프링 프레임워크 전반에 적용되었으며, 이제는 XML 없이도 순수 자바 코드 기반의 스프링 애플리케이션 구성이 가능해졌다.
    - 정책과 관례를 이용한 프로그래밍
        - 어노테이션 기반 프로그래밍은 코드로 동작을 명시하기보다, 정해진 규칙과 관례에 따라 자동으로 동작하도록 만드는 프로그래밍 방식을 적극 활용한다.
        - 이러한 방식은 작성해야하는 내용을 줄이고, 자주 반복되는 패턴을 관례화 하면 많은 내용의 코드를 생략할 수 있다.
        - 하지만 미리 정의된 규칙과 관례를 기억하고 이해해야 하며, 실제 동작을 파악하기 어려울 수 있다.
        - 스프링은 점차 어노테이션을 통한 메타정보 활용 방식을 적극 도입하고 있다.

### 7.6.1 자바 코드를 이용한 빈 설정

- 애노테이션과 자바 코드로 XML을 대체해 본다.
- 테스트 컨텍스트의 변경
    - 스프링 3.1부터는 자바 기반 설정 클래스와 XML 설정을 함께 사용할 수 있는 방법을 제공한다.
    - `@ImportResource` 어노테이션을 사용하면 클래스 내에서 XML 설정을 불러올 수 있다.
- `<context:annotation-config/>` 제거
    - `@Configuration`이 붙은 자바 설정 클래스를 사용하는 경우, 스프링이 직접 필요한 빈 후처리기들을 등록한다.
    - 따라서 `<context:annotation-config/>`는 더 이상 필요하지 않다.
- `<bean>`의 전환
    - XML의 `<bean>`은 `@Bean` 어노테이션이 붙은 `public` 메서드로 전환할 수 있다.
    - 메서드의 이름은 `<bean>`의 `id`와 동일하게 사용된다.
    - 메서드의 반환 타입은 구현 클래스보다는 인터페이스 타입으로 지정하는 것을 권장한다.
    - XML에서 정의된 빈을 자바 설정 코드에서 사용하려면 `@Autowired` 어노테이션을 붙인 필드를 통해 주입받을 수 있다.
- 전용 태그 전환
    - `EmbeddedDatabaseBuilder`를 사용하면 `<jdbc:embedded-database>` 태그에 해당하는 빈 객체를 생성할 수 있다.
    - 스프링 3.1부터 XML 설정에서 자주 사용되던 전용 태그들을 자바 기반 설정에서 대체할 수 있도록, `@Enable..`로 시작하는 다양한 어노테이션을 제공한다.
- 자바 코드를 이용한 빈 설정 전체 코드
```java
@Configuration
@EnableTransactionManagement
public class TestApplicationContext {

    @Bean
    public DataSource dataSource() {
        return new EmbeddedDatabaseBuilder()
                .setName("embeddedDatabase")
                .setType(EmbeddedDatabaseType.H2)
                .addScript("sql/schema.sql")
                .build();
    }

    @Bean
    public PlatformTransactionManager transactionManager() {
        DataSourceTransactionManager transactionManager = new DataSourceTransactionManager();
        transactionManager.setDataSource(dataSource());
        return transactionManager;
    }

    @Bean
    public UserDao userDao() {
        UserDaoJdbc userDao = new UserDaoJdbc();
        userDao.setDataSource(dataSource());
        userDao.setSqlService(sqlService());
        return userDao;
    }

    @Bean
    public UserService userService() {
        UserServiceImpl userService = new UserServiceImpl();
        userService.setUserDao(userDao());
        userService.setMailSender(mailSender());
        return userService;
    }

    @Bean
    public UserService testUserService() {
        UserServiceTest.TestUserService testService = new UserServiceTest.TestUserService();
        testService.setUserDao(userDao());
        testService.setMailSender(mailSender());
        return testService;
    }

    @Bean
    public MailSender mailSender() {
        return new DummyMailSender();
    }

    @Bean
    public SqlService sqlService() {
        OxmSqlService sqlService = new OxmSqlService();
        sqlService.setUnmarshaller(unmarshaller());
        sqlService.setSqlRegistry(sqlRegistry());
        return sqlService;
    }

    @Bean
    public Unmarshaller unmarshaller() {
        Jaxb2Marshaller marshaller = new Jaxb2Marshaller();
        marshaller.setContextPath("study.user.sqlservice.jaxb");
        return marshaller;
    }

    @Bean
    public SqlRegistry sqlRegistry() {
        EmbeddedDbSqlRegistry sqlRegistry = new EmbeddedDbSqlRegistry();
        sqlRegistry.setDataSource(embeddedDataSource());
        return sqlRegistry;
    }
}
```

### 7.6.2 빈 스캐닝과 자동와이어링

- `@Autowired`를 이용한 자동와이어링
    - `@Autowired`는 스프링 컨테이너가 생성한 빈을 자동으로 주입받기 위해 사용된다.
    - 자동 와이어링 기법을 사용하면, 스프링이 빈의 타입이나 이름을 기준으로 의존 객체를 찾아 주입하므로, 의존 객체를 설정하는 작업을 줄일 수 있다.
    - `@Autowired`가 붙은 수정자 메서드가 존재하면 스프링은 다음 순서로 주입 가능한 빈을 찾는다.
        - 주입 가능한 타입의 빈이 하나일 경우, 해당 빈을 주입한다.
        - 둘 이상일 경우, 메서드 이름과 일치하는 이름의 빈을 찾아 주입한다.
        - 주입 가능한 빈이 없으면 예외가 발생한다.
    - `@Autowired`를 필드에 직접 사용할 수도 있으며, 이 경우 스프링은 리플렉션을 사용해 `private` 필드에도 값을 주입할 수 있다.
    ```java
    public class UserDaoJdbc implements UserDao {
    
        private JdbcTemplate jdbcTemplate;
    
        @Autowired
        private SqlService sqlService;
    
        @Autowired
        public void setDataSource(DataSource dataSource) {
            this.jdbcTemplate = new JdbcTemplate();
            this.jdbcTemplate.setDataSource(dataSource);
        }
    
        // ...
    }
    ```
    - `UserDaoJdbc.setDataSource`와 같이 추가적인 작업이 필요한 경우는 수정자 메서드에 `@Autowired`를 사용하는 것이 적합하다.
    - `@Autowired`를 활용하면 DI 설정이 간결해지지만, 빈 간의 의존 관계를 설정 코드에서 명시적으로 파악하기 어려워지는 단점이 있다.
- `@Component`를 이용한 자동 빈 등록(소스 참고)
    - `@Component` 또는 이를 메타 어노테이션으로 갖는 어노테이션이 붙은 클래스는 빈 스캐너를 통해 자동으로 빈으로 등록된다.
    - `@ComponentScan` 어노테이션은 `@Component`가 붙은 클래스들을 스캔해 자동으로 빈으로 등록하는 역할을 한다.
        - `basePackages` 속성을 통해 스캔할 기준 패키지를 지정할 수 있다.
        - 지정된 패키지의 모든 하위 패키지까지 포함해 클래스를 검색한다.
        - 별도로 이름을 지정하지 않으면, 클래스 이름의 첫 글자를 소문자로 바꾼 값이 빈의 ID로 사용한다.
            - `@Component("userDao")`과 같이 직접 빈 이름을 지정할 수 있다.
