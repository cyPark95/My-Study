package study.learningtest.factorybean;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import static org.junit.jupiter.api.Assertions.assertEquals;

@ExtendWith(SpringExtension.class)
@ContextConfiguration(locations = "classpath:message.xml")
public class FactoryBeanTest {

    @Autowired
    private ApplicationContext context;

    @Test
    void getMessageFormFactoryBean() {
        Object message = context.getBean("message");
        assertEquals(Message.class, message.getClass());
        assertEquals("Factory Bean", ((Message) message).getText());
    }

    @Test
    void getFactoryBean() {
        Object factory = context.getBean("&message");
        assertEquals(MessageFactoryBean.class, factory.getClass());
    }
}
