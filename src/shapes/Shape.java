package shapes;

import java.awt.*;
import java.io.Serializable;

public abstract class Shape implements Serializable {
    public abstract void drawShape(Graphics2D g);
}
