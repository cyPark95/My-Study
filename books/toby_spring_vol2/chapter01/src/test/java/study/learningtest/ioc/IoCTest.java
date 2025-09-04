package study.learningtest.ioc;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.config.RuntimeBeanReference;
import org.springframework.beans.factory.support.RootBeanDefinition;
import org.springframework.beans.factory.xml.XmlBeanDefinitionReader;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.GenericApplicationContext;
import org.springframework.context.support.GenericXmlApplicationContext;
import org.springframework.context.support.StaticApplicationContext;
import study.learningtest.ioc.bean.Hello;
import study.learningtest.ioc.bean.Printer;
import study.learningtest.ioc.bean.StringPrinter;

import static org.junit.jupiter.api.Assertions.*;

public class IoCTest {

    @Test
    public void registerBean() {
        StaticApplicationContext applicationContext = new StaticApplicationContext();
        applicationContext.registerSingleton("hello1", Hello.class);

        Hello hello1 = applicationContext.getBean("hello1", Hello.class);
        assertNotNull(hello1);

        RootBeanDefinition beanDefinition = new RootBeanDefinition(Hello.class);
        beanDefinition.getPropertyValues().addPropertyValue("name", "spring");
        applicationContext.registerBeanDefinition("hello2", beanDefinition);

        Hello hello2 = applicationContext.getBean("hello2", Hello.class);
        assertNotEquals(hello1, hello2);
        assertEquals(2, applicationContext.getBeanFactory().getBeanDefinitionCount());
    }

    @Test
    public void registerBeanWithDependency() {
        StaticApplicationContext applicationContext = new StaticApplicationContext();
        RootBeanDefinition printerBeanDefinition = new RootBeanDefinition(StringPrinter.class);
        applicationContext.registerBeanDefinition("printer", printerBeanDefinition);

        RootBeanDefinition helloBeanDefinition = new RootBeanDefinition(Hello.class);
        helloBeanDefinition.getPropertyValues().addPropertyValue("name", "Spring");
        helloBeanDefinition.getPropertyValues().addPropertyValue("printer", new RuntimeBeanReference("printer"));
        applicationContext.registerBeanDefinition("hello", helloBeanDefinition);

        Hello hello = applicationContext.getBean("hello", Hello.class);
        hello.print();

        assertEquals("Hello Spring", applicationContext.getBean("printer").toString());
    }

    @Test
    public void genericApplicationContext() {
        GenericApplicationContext applicationContext = new GenericXmlApplicationContext("classpath:ioc-context.xml");

        Hello hello = applicationContext.getBean("hello", Hello.class);
        hello.print();

        assertEquals("Hello Spring", applicationContext.getBean("printer").toString());
    }

    @Test
    public void childContextUseParentBean() {
        ApplicationContext parent = new GenericXmlApplicationContext("classpath:parentContext.xml");
        GenericApplicationContext child = new GenericApplicationContext(parent);
        XmlBeanDefinitionReader reader = new XmlBeanDefinitionReader(child);
        reader.loadBeanDefinitions("classpath:childContext.xml");
        child.refresh();

        Printer printer = child.getBean("printer", Printer.class);
        assertNotNull(printer);

        Hello hello = child.getBean("hello", Hello.class);
        assertNotNull(hello);

        hello.print();
        assertEquals("Hello Child", printer.toString());
    }
}
