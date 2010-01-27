package Interface;

import VectorMath.*;
import Boids.*;

public interface IBoidNavigatorRule {
	
	public void 		init(CBoidObject actor, double weightFactor);	
	
	public void 		evaluate(CBoidObject object);
	public Vector3D 	result();

}