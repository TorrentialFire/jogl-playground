package playground;

import com.jogamp.opengl.GL;
import com.jogamp.opengl.GL2;
import com.jogamp.opengl.glu.GLU;

public class BigDipperRenderer implements ISimpleRenderer {

    @Override
    public void setup(GL2 gl2, int width, int height) {
        gl2.glMatrixMode(GL2.GL_PROJECTION);
        gl2.glLoadIdentity();

        // coordinate system origin at lower left with width and height the same as window
        GLU glu = new GLU();
        glu.gluOrtho2D(-10.0f, 330.0f, 0.0f, 200);
        
        gl2.glMatrixMode(GL2.GL_MODELVIEW);
        gl2.glLoadIdentity();

        gl2.glViewport(0, 0, width, height);

        gl2.glPointSize(14.0f);
        gl2.glColor3f(1.0f, 1.0f, 1.0f);
    }

    @Override
    public void render(GL2 gl2, int width, int height) {
        gl2.glClear(GL.GL_COLOR_BUFFER_BIT);

        gl2.glBegin(GL.GL_POINTS);
        gl2.glVertex2i(289, 190);
        gl2.glVertex2i(320, 128);
        gl2.glVertex2i(239, 67);
        gl2.glVertex2i(194, 101);
        gl2.glVertex2i(129, 83);
        gl2.glVertex2i(75, 73);
        gl2.glVertex2i(74, 74);
        gl2.glVertex2i(20, 10);
        gl2.glEnd();

        gl2.glFlush();
    }
    
}
