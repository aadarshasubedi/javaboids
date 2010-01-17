package Boids;

import java.util.Vector;
import java.util.Iterator;

import javax.media.opengl.GL;
import javax.media.opengl.glu.GLU;

import com.sun.opengl.util.GLUT;

import VectorMath.Point3D;
import VectorMath.Vector3D;

/**
 * @author pbeer
 * 
 */
public class ParticleRenderer {

	private GL gl = null;
	private GLU glu = null;
	private GLUT glut = null;

	private CBoidScene mScene = null;

	public void setScene(CBoidScene scene) {
		mScene = scene;
	};

	public void setGLContext(GL glContext, GLU gluIn, GLUT glutIn) {
		gl = glContext;
		glu = gluIn;
		glut = glutIn;
	};

	public void render() {
		gl.glClear(GL.GL_COLOR_BUFFER_BIT | GL.GL_DEPTH_BUFFER_BIT);
		gl.glClearColor(0.0f, 0.0f, 0.0f, 1.0f);

		gl.glDisable(GL.GL_CULL_FACE);
		gl.glShadeModel(GL.GL_SMOOTH);
		
		setupCamera();
		
		gl.glMatrixMode(GL.GL_MODELVIEW);
		gl.glLoadIdentity();

		setupLight();

		Vector<CSceneObject> objectsInScene = mScene.getSceneObjects();

		if (objectsInScene.size() != 0) {
			objectsInScene.firstElement();
			Iterator<CSceneObject> it = objectsInScene.iterator();

			while (it.hasNext()) {
				CBoidObject particle = (CBoidObject) it.next();
				renderBoid(particle);
			}
		}

		gl.glFlush();
	};

	private void renderBoid(CBoidObject boid) {
		gl.glPushMatrix();
		
		TranslateToPosition(boid);
		TransformBoidSpace(boid);

		SetMaterial();
		DrawBoid();

		gl.glPopMatrix();
	}

	// Brings particle in position in scene space
	private void TranslateToPosition(CBoidObject boid) {
		Point3D point = boid.getLocationInScene();
		gl.glTranslated(point.getX(), point.getY(), point.getZ());
	}

	// Rotates to get local coordinate system setup
	private void TransformBoidSpace(CBoidObject boid) {
		// Rotate around z, to requested pitch
		Vector3D x_axis = new Vector3D(1,0,0);
		Vector3D xdisp = new Point3D(0,0,0).vector(new Point3D(boid.getHeading().getX(), 0, 0));
		double angle_x = Math.acos(xdisp.dotProduct(x_axis));
		
		//gl.glRotated(boid.getPitch(), 1.0f, 0.0f, 0.0f);
		gl.glRotated(angle_x, 1.0f, 0.0f, 0.0f);

		Vector3D y_axis = new Vector3D(0,1,0);
		Vector3D ydisp = new Point3D(0,0,0).vector(new Point3D(0, boid.getHeading().getY(), 0));
		double angle_y = Math.acos(ydisp.dotProduct(y_axis));
		// rotate around y, to requested (not defined)
		gl.glRotated(angle_y, 0.0f, 1.0f, 0.0f);

		// rotate round x, to requested roll
		//gl.glRotated(25.0f, 1.0f, 0.0f, 0.0f);
	}

	// Draw the boid as a simple triangle
	private void DrawBoid() {
		
		gl.glPushMatrix();		
		gl.glColor3f(1.0f,0.0f,0.0f);
		//glut.glutSolidCube(0.25f);
		gl.glRotatef(90.0f, 0, 1, 0);
		glut.glutSolidCone(0.25f, 1.0f, 10, 10);
		gl.glPopMatrix();
		
//		gl.glBegin(GL.GL_TRIANGLES);
//		
//		gl.glColor3f(1.0f, 0.0f, 0.0f);
//		gl.glVertex3f(-0.50f, 0.0f, -0.50f);
//		gl.glColor3f(0.0f, 0.0f, 1.0f);
//		gl.glVertex3f(0.50f, 0.0f, -0.50f);
//		gl.glColor3f(0.0f, 1.0f, 0.0f);
//		gl.glVertex3f(0.0f, 0.0f, 0.0f);
//		
//		gl.glColor3f(1.0f, 0.0f, 0.0f);
//		gl.glVertex3f(-0.50f, 0.0f, 0.50f);
//		gl.glColor3f(0.0f, 0.0f, 1.0f);
//		gl.glVertex3f(0.50f, 0.0f, 0.50f);
//		gl.glColor3f(0.0f, 1.0f, 0.0f);
//		gl.glVertex3f(0.0f, 0.0f, 0.0f);
//		
//		gl.glEnd();
		
		gl.glColor3f(1.0f,0.0f,0.0f);
		//glut.glutSolidCube(0.25f);
	}

	protected void SetMaterial() {
		float material_Ka[] = { 1.00f, 0.72f, 0.11f, 1.00f };
		float material_Kd[] = { 0.94f, 0.68f, 0.54f, 1.00f };
		float material_Ks[] = { 0.33f, 0.33f, 1.00f, 1.00f };
		float material_Ke[] = { 0.00f, 0.54f, 0.00f, 0.00f };
		float material_Se[] = { 56.0f };

		gl.glMaterialfv(GL.GL_FRONT, GL.GL_AMBIENT, material_Ka, 0);
		gl.glMaterialfv(GL.GL_FRONT, GL.GL_DIFFUSE, material_Kd, 0);
		gl.glMaterialfv(GL.GL_FRONT, GL.GL_SPECULAR, material_Ks, 0);
		gl.glMaterialfv(GL.GL_FRONT, GL.GL_EMISSION, material_Ke, 0);
		gl.glMaterialfv(GL.GL_FRONT, GL.GL_SHININESS, material_Se, 0);
	}

	private void setupLight() {

		float position0[] = { 1.0f, 0.0f, 0.0f, 1.0f };
		float position1[] = { 0.0f, 1.0f, 0.0f, 1.0f };

		// http://www.falloutsoftware.com/tutorials/gl/gl8.htm#defining_a_light_source
		gl.glEnable(GL.GL_LIGHTING);
		gl.glEnable(GL.GL_LIGHT0);
		gl.glEnable(GL.GL_LIGHT1);

		gl.glLightfv(GL.GL_LIGHT1, GL.GL_POSITION, position1, 0);

		// gl.glPushMatrix();
		// gl.glRotatef(lightPosition, 0.0f, 1.0f, 0.0f);
		// gl.glLightfv(GL.GL_LIGHT0, GL.GL_POSITION, position0, 0);
		// gl.glPopMatrix();
	}

	public void setupCamera() {
		
		gl.glMatrixMode(GL.GL_PROJECTION);
		gl.glLoadIdentity();
		glu.gluPerspective(55.0, 1.0, 1.0, 50.0);

		
		// if (otherPos == 0) {
		glu.gluLookAt(0, 0, 8.0, 0.0, 0.0, 0.0, 0.0, 1.0, 0.0);
		// }
		// else if (otherPos == 1)
		// {
		// glu.gluLookAt(-4.5, -2.5, 6.0, 0.0, 0.0, 0.0, 0.0, 1.0, 0.0);
		// }
		// else
		{
			
		//	glu.gluLookAt(4.5, 3.5, -6.0, 0.0, 0.0, 0.0, 0.0, 1.0, 0.0);
		}
	}

}
