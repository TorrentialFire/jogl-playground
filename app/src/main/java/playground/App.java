/*
 * This Java source file was generated by the Gradle 'init' task.
 */
package playground;

import com.jogamp.opengl.GLAutoDrawable;
import com.jogamp.opengl.GLEventListener;
import com.jogamp.opengl.GLProfile;
import com.jogamp.opengl.GLCapabilities;
import com.jogamp.opengl.awt.GLCanvas;
import javax.swing.JFrame;
import java.awt.Toolkit;
import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

public class App {

    public static void main(String[] args) {
        GLProfile gl_Profile = GLProfile.getDefault();
        GLCapabilities gl_Capabilities = new GLCapabilities(gl_Profile);
        
        gl_Capabilities.setSampleBuffers(true); // MSAA
        gl_Capabilities.setNumSamples(8);   // MSAA x8

        final GLCanvas gl_Canvas = new GLCanvas(gl_Capabilities);

        // ISimpleRenderer renderer = new OneTriangleRenderer();
        // ISimpleRenderer renderer = new BigDipperRenderer();
        ISimpleRenderer renderer = new SierpinskiGasketRenderer();
        
        gl_Canvas.addGLEventListener( new GLEventListener() {
            @Override
            public void reshape(GLAutoDrawable drawable, int x, int y, int width, int height) {
                renderer.setup(drawable.getGL().getGL2(), width, height);
            }           

            @Override
            public void init(GLAutoDrawable drawable) { /* NOOP */ }

            @Override
            public void dispose(GLAutoDrawable drawable) { /* NOOP */}

            @Override
            public void display(GLAutoDrawable drawable) {
                renderer.render(drawable.getGL().getGL2(), drawable.getSurfaceWidth(), drawable.getSurfaceHeight());
            }
        });

        final JFrame jframe = new JFrame("JOGL Playground");
        jframe.addWindowListener(new WindowAdapter() {
            @Override
            public void windowClosing(WindowEvent e) {
                jframe.dispose();
                System.exit(0);
            }
        });

        jframe.getContentPane().add(gl_Canvas, BorderLayout.CENTER);

        int frame_width = 1920;
        int frame_height = 1080;
        jframe.setSize(frame_width, frame_height);

        /* Calculate a position which centers the window on the screen, clamped to (0,0) at a minimum. */
        Dimension screen_size = Toolkit.getDefaultToolkit().getScreenSize();
        int window_loc_x = Math.max((screen_size.width - frame_width) / 2, 0);
        int window_loc_y = Math.max((screen_size.height - frame_height) / 2, 0);

        jframe.setLocation(window_loc_x, window_loc_y);
        jframe.setVisible(true);
    }
}