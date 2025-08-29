package study.learningtest.ioc;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

public class IoCTest {

    @Test
    public void registerBean() {
        StaticApplicationContext applicationContext = new StaticApplicationContext();
        applicationContext.registerSingleton("hello1", study.learningtest.ioc.Hello.class);

        study.learningtest.ioc.Hello hello1 = applicationContext.getBean("hello1", study.learningtest.ioc.Hello.class);
        assertNotNull(hello1);

        RootBeanDefinition beanDefinition = new RootBeanDefinition(study.learningtest.ioc.Hello.class);
        beanDefinition.getPropertyValues().addPropertyValue("name", "spring");
        applicationContext.registerBeanDefinition("hello2", beanDefinition);

        study.learningtest.ioc.Hello hello2 = applicationContext.getBean("hello2", study.learningtest.ioc.Hello.class);
        assertNotEquals(hello1, hello2);
        assertEquals(2, applicationContext.getBeanFactory().getBeanDefinitionCount());
    }

    @Test
    public void registerBeanWithDependency() {
        StaticApplicationContext applicationContext = new StaticApplicationContext();
        RootBeanDefinition printerBeanDefinition = new RootBeanDefinition(study.learningtest.ioc.StringPrinter.class);
        applicationContext.registerBeanDefinition("printer", printerBeanDefinition);

        RootBeanDefinition helloBeanDefinition = new RootBeanDefinition(study.learningtest.ioc.Hello.class);
        helloBeanDefinition.getPropertyValues().addPropertyValue("name", "Spring");
        helloBeanDefinition.getPropertyValues().addPropertyValue("printer", new RuntimeBeanReference("printer"));
        applicationContext.registerBeanDefinition("hello", helloBeanDefinition);

        study.learningtest.ioc.Hello hello = applicationContext.getBean("hello", study.learningtest.ioc.Hello.class);
        hello.print();

        assertEquals("Hello Spring", applicationContext.getBean("printer").toString());
    }

    @Test
    public void genericApplicationContext() {
        GenericApplicationContext applicationContext = new GenericXmlApplicationContext("classpath:ioc-context.xml");

        study.learningtest.ioc.Hello hello = applicationContext.getBean("hello", study.learningtest.ioc.Hello.class);
        hello.print();

        assertEquals("Hello Spring", applicationContext.getBean("printer").toString());
    }
}
