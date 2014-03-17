package common.spring

import org.springframework.beans.BeansException
import org.springframework.beans.factory.config.BeanPostProcessor

import java.lang.reflect.Method

import static java.util.Arrays.asList
import static org.springframework.util.ReflectionUtils.doWithMethods

public abstract class TypeSafeBeanPostProcessor<T> implements BeanPostProcessor {

    static Collection<String> methodNames =
            asList("safelyPostProcessBeforeInitialization", "safelyPostProcessAfterInitialization");

    final Class<?> expectedType;

    protected TypeSafeBeanPostProcessor() {
        Class<?> inferedType = null
        doWithMethods(getClass(),
            { Method method -> inferedType = method.getParameterTypes()[0] },
            { Method method ->
                !method.isSynthetic() &&
                methodNames.contains(method.getName()) &&
                (inferedType == null || inferedType.isAssignableFrom(method.getParameterTypes()[0]))
            }
        )
        expectedType = inferedType
    }

    @Override
    @SuppressWarnings("unchecked")
    public final Object postProcessBeforeInitialization(Object bean, String beanName) throws BeansException {
        if (expectedType.isInstance(bean)) {
            return safelyPostProcessBeforeInitialization((T) bean, beanName);
        } else {
            return bean;
        }
    }

    public T safelyPostProcessBeforeInitialization(T bean, String beanName) throws BeansException { return bean }

    @Override
    @SuppressWarnings("unchecked")
    public final Object postProcessAfterInitialization(Object bean, String beanName) throws BeansException {
        if (expectedType.isInstance(bean)) {
            return safelyPostProcessAfterInitialization((T) bean, beanName);
        } else {
            return bean;
        }
    }

    public T safelyPostProcessAfterInitialization(T bean, String beanName) throws BeansException { return bean }
}
