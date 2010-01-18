package Boids;

import java.util.Vector;
import java.util.Iterator;

import VectorMath.Point3D;
import VectorMath.Vector3D;

public class CBoidScene {

	private Vector<CSceneObject> allObjectsInScene = new Vector<CSceneObject>();

	public Vector<CSceneObject> getSceneObjects() {
		return allObjectsInScene;
	}

	public CBoidScene() {
		super();

		// allObjectsInScene.add(new CBoidObject(this, new Point3D(0,0,0)));
		for (int i = 0; i < 1; ++i) {
			allObjectsInScene.add(new CBoidObject(this, new Point3D((Math
					.random() - 1) * 10.0, (Math.random() - 1) * 10.0, 0)));
			allObjectsInScene.add(new CBoidObject(this, new Point3D(1, (Math
					.random() - 1) * 10.0, 0)));
			allObjectsInScene.add(new CBoidObject(this, new Point3D(0, -1, 0)));
			allObjectsInScene.add(new CBoidObject(this, new Point3D((Math
					.random() - 1) * 10.0, 1, 0)));
		}

//		CBoidObject bd = new CBoidObject(this, new Point3D(-4, 0, 0));
//		bd.setHeading(new Vector3D(1, 1, 1));
//
//		allObjectsInScene.add(bd);
		// allObjectsInScene.add(new CBoidObject(this, new Point3D(8,8,0)));
		 allObjectsInScene.add(new CBoidObject(this, new Point3D(0, 0, 4)));
		allObjectsInScene.add(new CBoidObject(this, new Point3D(0,0,0)));

		// allObjectsInScene.add(new CBoidObject(this, new Point3D(0,1,0)));
	}

	public void update() {
		// Has to be done in two steps, results in very strange behaviors
		// of the rules when directly applying results of rule evaluation
		// to new position of boid.
		if (allObjectsInScene.size() != 0) {
			Iterator<CSceneObject> it = allObjectsInScene.iterator();

			while (it.hasNext()) {
				CBoidObject obj = (CBoidObject) it.next();
				obj.calculate();

				// if (obj == allObjectsInScene.firstElement())
				// {
				if (obj == allObjectsInScene.get(0)) {
					Point3D pos = obj.getLocationInScene();
					// obj.setPositionInScene(new Point3D(pos.getX()-0.2,
					// pos.getY(), pos.getZ()));
				}

				if (obj == allObjectsInScene.get(1)) {
					Point3D pos = obj.getLocationInScene();
					// obj.setPositionInScene(new Point3D(pos.getX()+0.2,
					// pos.getY(), pos.getZ()));
				}
				// }
			}
		}

		if (allObjectsInScene.size() != 0) {
			Iterator<CSceneObject> it = allObjectsInScene.iterator();

			while (it.hasNext()) {
				it.next().update(System.currentTimeMillis());
			}
		}
	}
}
