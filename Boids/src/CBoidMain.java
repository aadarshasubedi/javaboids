import Boids.CBoidScene;
import Boids.ParticleRenderer;


import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

import javax.media.opengl.GL;
import javax.media.opengl.GLAutoDrawable;
import javax.media.opengl.GLCanvas;
import javax.media.opengl.GLCapabilities;
import javax.media.opengl.GLEventListener;
import javax.media.opengl.glu.GLU;
import javax.swing.JFrame;

import com.sun.opengl.util.FPSAnimator;
import com.sun.opengl.util.GLUT;

public class CBoidMain extends JFrame implements GLEventListener,
		KeyListener, MouseListener {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	static private CBoidScene scene = new CBoidScene();
	static private ParticleRenderer particleRenderEngine = new ParticleRenderer();

	public static void main(String[] args) {
		new CBoidMain().run();
	}
	
	private FPSAnimator anim = null;
	private GLU glu;
	private GLUT glut;
	private GLCapabilities caps;
	private GLCanvas canvas;

	private GL gl = null;
	
	
	public CBoidMain() {
		super("Java Boids");

		caps = new GLCapabilities();
		canvas = new GLCanvas(caps);
		canvas.addGLEventListener(this);
		canvas.addKeyListener(this);
		canvas.addMouseListener(this);

		getContentPane().add(canvas);
	}

	public void display(GLAutoDrawable drawable) {
		
		scene.update();
		particleRenderEngine.render();		
	}

	public void displayChanged(GLAutoDrawable drawable, boolean modeChanged,
			boolean deviceChanged) {
	}

	public void init(GLAutoDrawable drawable) {
		gl = drawable.getGL();

		glu = new GLU();
		glut = new GLUT();

		gl.glViewport(0, 0, 640, 480);
		gl.glMatrixMode(GL.GL_PROJECTION);
		gl.glLoadIdentity();
		glu.gluPerspective(180.0, 1.0, 1.0, 50.0);

		gl.glEnable(GL.GL_DEPTH_TEST);
		gl.glDisable(GL.GL_CULL_FACE);
	
		particleRenderEngine.setGLContext(gl, glu, glut);
		particleRenderEngine.setScene(scene);
		
		// Create the Animator and start it
		anim = new FPSAnimator(drawable, 5);
		anim.start();
	}

	public void keyPressed(KeyEvent key) {

		switch (key.getKeyCode()) {
		
		// Toggle Camera position
//		case KeyEvent.VK_C: {
//			
//			if (otherPos < 2)
//			{
//				otherPos ++;
//			}
//			else
//			{
//				otherPos = 0;
//			}
//		}
//			break;
//			
//		// Rotate complete scene
//		case KeyEvent.VK_S: {
//			sceneRotation = !sceneRotation;
//		}
//		break;
//		
//		case KeyEvent.VK_T:
//		{
//			moveTail = !moveTail;
//		}
//		break;
//		
//		//Toggle head step rotation
//		case KeyEvent.VK_H: {			
//			headRotation = !headRotation;
//		}
//		break;
//		
//		// Toggle head animated rotation
//		case KeyEvent.VK_A: {			
//			animate = !animate;
//		}
//		break;
//		
//		case KeyEvent.VK_K: {
//			kneelDown = !kneelDown;
//		}
//		break;
//				
//		// Rotate light source through scene
//		case KeyEvent.VK_L: {
//			lightRotate = !lightRotate;
//		}
//		break;

		case KeyEvent.VK_ESCAPE:
			System.exit(0);
			break;

		default:
			break;
		}
	}

 	public void keyReleased(KeyEvent key) {
	}

	public void keyTyped(KeyEvent key) {
	}

	public void mouseClicked(MouseEvent mouse) {
	}

	public void mouseEntered(MouseEvent mouse) {
	}

	public void mouseExited(MouseEvent mouse) {
	}

	public void mousePressed(MouseEvent mouse) {
	}

	public void mouseReleased(MouseEvent mouse) {
	}

	public void reshape(GLAutoDrawable drawable, int x, int y, int w, int h) {

		gl.glViewport(0, 0, w, h);
		gl.glMatrixMode(GL.GL_PROJECTION);
		gl.glLoadIdentity();

		glu.gluPerspective(30.0, (float) w / (float) h, 1.0, 20.0);
		gl.glMatrixMode(GL.GL_MODELVIEW);
		gl.glLoadIdentity();
	}

	public void run() {
		setSize(500, 500);
		setLocationRelativeTo(null);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setVisible(true);
		canvas.requestFocusInWindow();
	}
	
	}