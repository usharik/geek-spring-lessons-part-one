package ru.geekbrains.camera;

import org.springframework.stereotype.Component;

@Component
@UnproducableCameraRoll(usingCameraRollClass = ColorCameraRoll.class)
public class BlackAndWhiteCameraRoll implements CameraRoll {
    public void processing(){
        System.out.println("-1 черно-белый кадр");
    }
}
