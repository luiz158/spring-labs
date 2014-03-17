package common.spring

import org.springframework.beans.BeansException
import spock.lang.Specification

class TypeSafeBeanPostProcessorTest extends Specification {

    def "Should infer type from safelyPostProcessBeforeInitialization" () {
        when:
            TestBeforeTypeSafeBeanPostProcessor processor = new TestBeforeTypeSafeBeanPostProcessor()
        then:
            processor.expectedType == MyBean.class
    }

    def "Should infer and process `beforeInitialization` the Groovy way too"() {
        given:
            MyBean bean = new MyBean()
            def processor = [
                safelyPostProcessBeforeInitialization: { MyBean b, String name -> b.process(name) }
            ] as TypeSafeBeanPostProcessor<MyBean>
        when:
            processor.postProcessBeforeInitialization(bean, "before")
        then:
            bean.prop == "before"
    }

    def "Should infer type from safelyPostProcessAfterInitialization" () {
        when:
            TestAfterTypeSafeBeanPostProcessor processor = new TestAfterTypeSafeBeanPostProcessor()
        then:
            processor.expectedType == MyBean.class
    }

    static class MyBean {
        String prop

        MyBean process(String val) {
            prop = val
            return this
        }
    }

    static class TestBeforeTypeSafeBeanPostProcessor extends TypeSafeBeanPostProcessor<MyBean> {
        @Override
        MyBean safelyPostProcessBeforeInitialization(MyBean bean, String beanName) throws BeansException {
            return bean.process(beanName)
        }
    }

    static class TestAfterTypeSafeBeanPostProcessor extends TypeSafeBeanPostProcessor<MyBean> {
        @Override
        MyBean safelyPostProcessAfterInitialization(MyBean bean, String beanName) throws BeansException {
            return bean.process(beanName)
        }
    }
}
