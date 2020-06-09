package ru.geekbrains.camera;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class Camera {

    @Autowired
    private CameraRoll cameraRoll;

    public void doPhotograph() {
        System.out.println("Щелк!");
        cameraRoll.processing();
    }
}

