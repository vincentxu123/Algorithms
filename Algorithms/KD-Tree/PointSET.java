
import java.util.ArrayList;
import java.util.Iterator;

import edu.princeton.cs.algs4.Point2D;
import edu.princeton.cs.algs4.RectHV;
import edu.princeton.cs.algs4.SET;

public class PointSET {
	
	private SET<Point2D> setPoints;
	
	
	public PointSET()  {
		// construct an empty set of points
		setPoints = new SET<Point2D>();
	}
	public  boolean isEmpty()  {
		// is the set empty? 
		return setPoints.size() == 0;
	}
	public  int size()  {
		// number of points in the set 
		return setPoints.size();
	}
	public void insert(Point2D p) {
		// add the point to the set (if it is not already in the set)
		setPoints.add(p);
	}
	public boolean contains(Point2D p) {
		// does the set contain point p? 
		return setPoints.contains(p);
	}
	public void draw() {
		// draw all points to standard draw
		Iterator<Point2D> itr = setPoints.iterator();
		while (itr.hasNext()) {
			itr.next().draw();
		}
	}
	public Iterable<Point2D> range(RectHV rect) {
		// all points that are inside the rectangle (or on the boundary)
		Iterator<Point2D> itr = setPoints.iterator();
		ArrayList<Point2D> arr = new ArrayList<Point2D>();
		while (itr.hasNext()) {
			Point2D point = itr.next();
			if (rect.contains(point)) {
				arr.add(point);
			}
		}
		return arr;
	}
	public Point2D nearest(Point2D p) {
		// a nearest neighbor in the set to point p; null if the set is empty 
		Iterator<Point2D> itr = setPoints.iterator();
		if (setPoints.isEmpty()) {
			return null;
		}
		Point2D temp = itr.next();
		double nearestPoint = temp.distanceTo(p);
		while (itr.hasNext()) {
			Point2D point = itr.next();
			if (point.distanceTo(p) < nearestPoint) {
				nearestPoint = point.distanceTo(p);
				temp = point;
			}
		}
		return temp;
	}

	public static void main(String[] args) {
		PointSET set = new PointSET();
		set.insert(new Point2D(1,1));
		set.draw();
	}
}
