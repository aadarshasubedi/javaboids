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
		return new Point3D(this.mX + pnt.getX(), 
				 this.mY + pnt.getY(), 
				 this.mZ  + pnt.getZ());
	}
	
	public Point3D divide(double divisor)
	{
		return new Point3D(mX / divisor, mY / divisor, mZ / divisor);
	}
	
	public double distance(Point3D point)
	{
		double xd = point.getX() - this.getX();
		double yd = point.getY() - this.getY();
		double zd = point.getZ() - this.getZ();
		
		double squaredSum = (xd * xd) + (yd * yd) + (zd * zd);
		
		return Math.sqrt(squaredSum);
	}
	
	public Vector3D vector(Point3D pnt2)
	{
		return new Vector3D(pnt2.getX() - this.getX(),
				pnt2.getY() - this.getY(),
				pnt2.getZ() - this.getZ());
	}
	
	public void print()
	{
		System.out.print("(" + this.getX() + ","
				+ this.getY() + ","
				+ this.getZ() + ")\n");
	}
}
