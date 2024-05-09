package yellow.entities.aftermath;

import arc.math.geom.Position;

public interface InflictionField extends Position{

    float lifetime();

    void lifetime(float lifetime);

    void update();
}
