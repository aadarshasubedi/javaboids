package Interface;

import VectorMath.Point3D;
import VectorMath.Vector3D;

public interface ISceneObject {

	public Point3D getLocationInScene();	
	public Vector3D getDirection();
	public double getSpeed();
	public int   getColor();

	public void calculate();
	public void update(double deltaTime);
}
