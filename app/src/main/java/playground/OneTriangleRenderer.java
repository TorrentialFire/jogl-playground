package playground;

import com.jogamp.opengl.GL;
import com.jogamp.opengl.GL2;
import com.jogamp.opengl.glu.GLU;

public class OneTriangleRenderer implements ISimpleRenderer {

    @Override
    public void render(GL2 gl2, int width, int height) {
        gl2.glClear(GL.GL_COLOR_BUFFER_BIT);
        
        float fwidth = (float)width;
        float half_width = fwidth / 2.0f;
        float fheight = (float)height;

        gl2.glLoadIdentity();

        gl2.glBegin(GL.GL_TRIANGLES);
        gl2.glColor3f(1.0f, 0.0f, 0.0f);
        gl2.glVertex2f(0.0f, 0.0f);
        gl2.glColor3f(0.0f, 1.0f, 0.0f);
        gl2.glVertex2f(fwidth, 0.0f);
        gl2.glColor3f(0.0f, 0.0f, 1.0f);
        gl2.glVertex2f(half_width, fheight);
        gl2.glEnd();

        gl2.glFlush();
    }

    @Override
    public void setup(GL2 gl2, int width, int height) {
        gl2.glMatrixMode(GL2.GL_PROJECTION);
        gl2.glLoadIdentity();

        // coordinate system origin at lower left with width and height the same as window
        GLU glu = new GLU();
        glu.gluOrtho2D(0.0f, width, 0.0f, height);
        
        gl2.glMatrixMode(GL2.GL_MODELVIEW);
        gl2.glLoadIdentity();

        gl2.glViewport(0, 0, width, height);
    }
    
}
