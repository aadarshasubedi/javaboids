package VectorMath;

public class Vector3D {
	
	double mX = 0.0;
	double mY = 0.0;
	double mZ = 0.0;

	public Vector3D(double x, double y, double z)
	{
		mX = x; mY = y; mZ = z;
	}
	
	public Vector3D(Vector3D copyVector)
	{
		this.mX = copyVector.getX();
		this.mY = copyVector.getY();
		this.mZ = copyVector.getZ();
	}
	
	public Vector3D()
	{
		reset();
	}
	
	public void reset()
	{
		mX = 0.0; mY = 0.0; mZ = 0.0;
	}
	
	public double getX() {
		return mX;
	}
	public void setX(double x) {
		mX = x;
	}
	public double getY() {
		return mY;
	}
	public void setY(double y) {
		mY = y;
	}
	public double getZ() {
		return mZ;
	}
	public void setZ(double z) {
		mZ = z;
	}	
	
	public Vector3D add(Vector3D obj)
	{
		this.mX = this.mX + obj.mX;
		this.mY = this.mY + obj.mY;
		this.mZ = this.mZ + obj.mZ;
		
		return new Vector3D(this);
	}
	
	public Vector3D subtract(Vector3D obj)
	{
		this.mX = this.mX - obj.mX;
		this.mY = this.mY - obj.mY;
		this.mZ = this.mZ - obj.mZ;
		
		return new Vector3D(this);
	}
	
	public Vector3D scalarMultiply(double scalar)
	{
		this.mX = this.mX * scalar;
		this.mY = this.mY * scalar;
		this.mZ = this.mZ * scalar;
		
		return new Vector3D(this);
	}
	
	public Vector3D multiply(Vector3D obj)
	{
		this.mX = this.mX * obj.mX;
		this.mY = this.mY * obj.mY;
		this.mZ = this.mZ * obj.mZ;
		
		return new Vector3D(this);
	}
	
	public Vector3D dotProduct(Vector3D obj)
	{
		return new Vector3D();
	}
	
	public double length()
	{
		return Math.sqrt((mX*mX)+(mY*mY)+(mZ*mZ));
	}
	
	public Vector3D normalize()
	{
		double vecLen = length();	
		
		this.mX = this.mX / vecLen;
		this.mY = this.mY / vecLen;
		this.mZ = this.mZ / vecLen;
		
		return new Vector3D (this);
	}
}
