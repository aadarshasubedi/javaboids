package VectorMath;

public class Point3D {
	
	private double mX = 0.0;
	private double mY = 0.0;
	private double mZ = 0.0;
	 
	public Point3D(double x, double y, double z)
	{
		mX = x; mY= y; mZ = z;
	}
	
	public void reset()
	{
		mX = 0.0; mY = 0.0; mZ = 0.0;
	}
	
	public double getX() {
		return mX;
	}
	public void setX(double x) {
		this.mX = x;
	}
	public double getY() {
		return mY;
	}
	public void setY(double y) {
		this.mY = y;
	}
	public double getZ() {
		return mZ;
	}
	public void setZ(double z) {
		this.mZ = z;
	}
	
	public Point3D add(Point3D pnt)
	{
		this.mX = this.mX + pnt.getX();
		this.mY = this.mY + pnt.getY();
		this.mZ = this.mZ + pnt.getZ();
		
		return this;
	}
	
	public double distance(Point3D point)
	{
		double xd = point.getX() - this.getX();
		double yd = point.getY() - this.getY();
		double zd = point.getZ() - this.getZ();
		
		double squaredSum = (xd * xd) + (yd * yd) + (zd * zd);
		
		return Math.sqrt(squaredSum);
	}
}
