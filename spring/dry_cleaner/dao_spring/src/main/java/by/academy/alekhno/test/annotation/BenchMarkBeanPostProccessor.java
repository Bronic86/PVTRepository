package by.academy.alekhno.test.annotation;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.lang.reflect.Proxy;

import org.springframework.beans.BeansException;
import org.springframework.beans.factory.config.BeanPostProcessor;

public class BenchMarkBeanPostProccessor implements BeanPostProcessor {

	@Override
	public Object postProcessAfterInitialization(final Object o, String arg1) throws BeansException {
		Class<?> clazz = o.getClass();
		if (clazz.isAnnotationPresent(BenchMark.class)) {
			Object proxy = Proxy.newProxyInstance(clazz.getClassLoader(), clazz.getInterfaces(),
					new InvocationHandler() {

						@Override
						public Object invoke(Object proxy, Method method, Object[] args)
								throws Throwable {
							long before = System.nanoTime();
							Object objReturn = method.invoke(o, args);
							long after = System.nanoTime();
							System.out.println(after - before);
							return objReturn;
						}
					});
			return proxy;
		} else {
			return o;
		}

	}

	@Override
	public Object postProcessBeforeInitialization(Object o, String arg1) throws BeansException {

		return o;
	}

}
