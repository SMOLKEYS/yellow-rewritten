package yellow.entities.aftermath;

import arc.math.geom.*;

public interface InflictionField extends Position{

    float lifetime();

    void lifetime(float lifetime);

    void update();
}
