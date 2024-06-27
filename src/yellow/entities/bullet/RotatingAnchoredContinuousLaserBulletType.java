package yellow.entities.bullet;

import arc.math.geom.*;
import mindustry.gen.*;

public class RotatingAnchoredContinuousLaserBulletType extends RotatingContinuousLaserBulletType{

    public RotatingAnchoredContinuousLaserBulletType(float damage){
        super(damage);
    }

    public RotatingAnchoredContinuousLaserBulletType(){
        super();
    }

    @Override
    public void update(Bullet b){
        super.update(b);
        if(b.owner instanceof Position p) b.set(p);
    }
}
