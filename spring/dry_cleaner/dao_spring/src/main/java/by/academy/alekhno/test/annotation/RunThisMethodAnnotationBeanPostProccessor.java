package by.academy.alekhno.test.annotation;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

import org.springframework.beans.BeansException;
import org.springframework.beans.factory.config.BeanPostProcessor;

public class RunThisMethodAnnotationBeanPostProccessor implements BeanPostProcessor {

	@Override
	public Object postProcessAfterInitialization(Object o, String arg1) throws BeansException {
		Method[] methodes = o.getClass().getMethods();
		for (Method method : methodes) {
			if (method.isAnnotationPresent(RunThisMethod.class)) {
				try {
					method.invoke(o);
				} catch (IllegalAccessException e) {
					e.printStackTrace();
				} catch (IllegalArgumentException e) {
					e.printStackTrace();
				} catch (InvocationTargetException e) {
					e.printStackTrace();
				}
			}
		}
		return o;
	}

	@Override
	public Object postProcessBeforeInitialization(Object o, String arg1) throws BeansException {
		return o;
	}

}
