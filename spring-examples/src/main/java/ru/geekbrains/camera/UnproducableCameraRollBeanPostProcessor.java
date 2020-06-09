package ru.geekbrains.camera;

import org.springframework.beans.BeansException;
import org.springframework.beans.factory.config.BeanPostProcessor;

public class UnproducableCameraRollBeanPostProcessor implements BeanPostProcessor {

    @Override
    public Object postProcessAfterInitialization(Object bean, String beanName) throws BeansException {
        UnproducableCameraRoll annotation = bean.getClass().getAnnotation(UnproducableCameraRoll.class);

        if (annotation != null) {
            try {
                return annotation.usingCameraRollClass().newInstance();
            } catch (InstantiationException e) {
                e.printStackTrace();
            } catch (IllegalAccessException e) {
                e.printStackTrace();
            }
        }
        return bean;
    }
}
