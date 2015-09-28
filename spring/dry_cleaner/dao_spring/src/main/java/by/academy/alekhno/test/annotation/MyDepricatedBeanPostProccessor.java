package by.academy.alekhno.test.annotation;

import org.springframework.beans.BeansException;
import org.springframework.beans.factory.config.BeanPostProcessor;

public class MyDepricatedBeanPostProccessor implements BeanPostProcessor {

	@Override
	public Object postProcessAfterInitialization(Object o, String arg1) throws BeansException {
		return o;
	}

	@Override
	public Object postProcessBeforeInitialization(Object o, String arg1) throws BeansException {
		return o;
	}

}
