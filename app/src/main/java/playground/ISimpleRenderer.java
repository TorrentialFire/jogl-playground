package playground;

import com.jogamp.opengl.GL2;

public interface ISimpleRenderer {
    void setup(GL2 gl2, int width, int height);
    void render(GL2 gl2, int width, int height);
}
