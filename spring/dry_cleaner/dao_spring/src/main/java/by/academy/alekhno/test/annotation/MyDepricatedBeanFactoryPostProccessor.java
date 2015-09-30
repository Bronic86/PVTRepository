package by.academy.alekhno.test.annotation;

import org.springframework.beans.BeansException;
import org.springframework.beans.factory.config.BeanFactoryPostProcessor;
import org.springframework.beans.factory.config.ConfigurableListableBeanFactory;
import org.springframework.beans.factory.support.AbstractBeanDefinition;

public class MyDepricatedBeanFactoryPostProccessor implements BeanFactoryPostProcessor {

	@Override
	public void postProcessBeanFactory(ConfigurableListableBeanFactory beanFactory)
			throws BeansException {
		String[] beanDefinishionNames = beanFactory.getBeanDefinitionNames();
		for (String beanDefinishionName : beanDefinishionNames) {
			AbstractBeanDefinition beanDefinition = (AbstractBeanDefinition) beanFactory
					.getBeanDefinition(beanDefinishionName);
			String originalClassName = beanDefinition.getBeanClassName();
			try {
				Class<?> originalClass = Class.forName(originalClassName);
				MyDepricated annotation = originalClass.getAnnotation(MyDepricated.class);
				if (annotation != null) {
					Class newClass = annotation.newClass();
					beanDefinition.setBeanClass(newClass);
				}
			} catch (ClassNotFoundException e) {
				e.printStackTrace();
			}
		}
	}
}
