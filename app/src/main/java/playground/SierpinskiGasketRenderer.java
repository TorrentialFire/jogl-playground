package playground;

import java.util.Random;

import com.jogamp.opengl.GL;
import com.jogamp.opengl.GL2;
import com.jogamp.opengl.glu.GLU;

public class SierpinskiGasketRenderer implements ISimpleRenderer {

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

        gl2.glPointSize(1.0f);
        gl2.glDisable(GL.GL_MULTISAMPLE);
    }

    void drawDot(GL2 gl2, int x, int y) {
        gl2.glVertex2i(x, y);
    }

    @Override
    public void render(GL2 gl2, int width, int height) {
        int[] p0 = {0, 0};
        int[] p1 = {width, 0};
        int[] p2 = {width / 2, height};
        int[][] points = {p0, p1, p2};

        Random rand = new Random(System.currentTimeMillis());
        int idx = rand.nextInt(3);
        int[] prev_p = points[idx];

        gl2.glBegin(GL.GL_POINTS);
        for (int i = 0; i < 55000; i++) {       
            idx = rand.nextInt(3);
            int[] corner = points[idx];
            int[] new_p = {(corner[0] + prev_p[0]) / 2, (corner[1] + prev_p[1]) / 2};
            
            // t-values for color lerp
            float tx = (float)new_p[0] / (float)width;
            float ty = (float)new_p[1] / (float)height;
            
            // Perform two successive linear interpolations to determine the point's color
            float green = (1.0f - tx);
            float blue = tx;
            green = (1.0f - ty) * (green);
            blue = (1.0f - ty) * (blue);
            float red = ty;
            
            // Draw the point
            gl2.glColor3f(red, green, blue);
            gl2.glVertex2i(new_p[0], new_p[1]);
            prev_p = new_p;
        }
        gl2.glEnd();

        gl2.glFlush();
    }
    
}
