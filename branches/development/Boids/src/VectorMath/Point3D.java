package VectorMath;



public class Point3D {
	
	private float mX = 0.0f;
	private float mY = 0.0f;
	private float mZ = 0.0f;
	 
	public Point3D(float x, float y, float z)
	{
		mX = x; mY= y; mZ = z;
	}
	
	public float getX() {
		return mX;
	}
	public void setX(float x) {
		this.mX = x;
	}
	public float getY() {
		return mY;
	}
	public void setY(float y) {
		this.mY = y;
	}
	public float getZ() {
		return mZ;
	}
	public void setZ(float z) {
		this.mZ = z;
	}
}
