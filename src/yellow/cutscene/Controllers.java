package yellow.cutscene;

import arc.func.*;
import arc.math.*;
import arc.math.geom.*;
import arc.util.pooling.*;

public class Controllers{

    /** Returns a new or pooled controller of the specified type. */
    public static <T extends CutsceneController<?>> T controller(Class<T> type, Prov<T> sup){
        T control = Pools.obtain(type, sup);
        control.setPool(Pools.get(type, sup));
        return control;
    }

    /** @return A controller that moves the camera to the target coordinates. */
    public static CameraMoveController moveCameraTo(float x, float y, float time, Interp interp){
        CameraMoveController camera = controller(CameraMoveController.class, CameraMoveController::new);
        camera.x = x;
        camera.y = y;
        camera.time = time;
        camera.interp = interp;
        camera.moveMode = CameraMoveController.MoveMode.to;
        return camera;
    }

    /** @return A controller that moves the camera to the target position. */
    public static CameraMoveController moveCameraTo(Position pos, float time, Interp interp){
        CameraMoveController camera = controller(CameraMoveController.class, CameraMoveController::new);
        camera.target = pos;
        camera.time = time;
        camera.interp = interp;
        camera.moveMode = CameraMoveController.MoveMode.to;
        return camera;
    }

    /** @return A controller that moves the camera by the specified amount. */
    public static CameraMoveController moveCameraBy(float x, float y, float time, Interp interp){
        CameraMoveController camera = controller(CameraMoveController.class, CameraMoveController::new);
        camera.x = x;
        camera.y = y;
        camera.time = time;
        camera.interp = interp;
        camera.moveMode = CameraMoveController.MoveMode.by;
        return camera;
    }

    /** @return A controller that changes the camera zoom to the specified distance. */
    public static CameraZoomController changeZoom(float zoom, float time, Interp interp){
        CameraZoomController camera = controller(CameraZoomController.class, CameraZoomController::new);
        camera.zoom = zoom;
        camera.time = time;
        camera.interp = interp;
        return camera;
    }

    public static ParallelController parallel(CutsceneController<?>... controllers){
        ParallelController p = controller(ParallelController.class, ParallelController::new);
        p.controllers.add(controllers);
        return p;
    }
}
