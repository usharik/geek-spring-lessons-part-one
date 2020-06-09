package ru.geekbrains.camera;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class JavaConfig {

    @Bean
    public CameraRoll cameraRoll() {
        return new BlackAndWhiteCameraRoll();
    }

    @Bean
    public Camera camera() {
        return new Camera();
    }

    @Bean
    public UnproducableCameraRollBeanFactoryPostProcessor unproducableCameraRollBeanFactoryPostProcessor() {
        return new UnproducableCameraRollBeanFactoryPostProcessor();
    }

    @Bean
    public UnproducableCameraRollBeanPostProcessor unproducableCameraRollBeanPostProcessor() {
        return new UnproducableCameraRollBeanPostProcessor();
    }
}
