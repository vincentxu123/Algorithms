import java.util.ArrayList;
import java.util.Iterator;

import edu.princeton.cs.algs4.Point2D;
import edu.princeton.cs.algs4.RectHV;
import edu.princeton.cs.algs4.SET;


public class KdTree {
	
	private Node root;
	private int count = 0;
	private ArrayList<Point2D> list = new ArrayList<Point2D>();
	private Point2D answer;
	
	public KdTree()  {
		// construct an empty set of points
	}
	
	private class Node implements Comparable<Node> {
		
		int num = 0;
		private Point2D point;
		private Node left;
		private Node right;
		public Node(Point2D p) {
			point = p;
		}
		@Override
		public int compareTo(Node o) {
			// TODO Auto-generated method stub
			if (point.x() == o.point.x() && point.y() == o.point.y()) {
				return 0;
			}
			if (num % 2 == 0) {
				num++;
				if (o.point.x() > point.x()) {
					return -1;
				}
				return 1;
			} else {
				num++;
				if (o.point.y() > point.y()) {
					return -1;
				}
				return 1;
			}
		}
		
		public String toString() {
			return "(" + point.x() + ", " + point.y() + ")";
		}
	}
	
	public  boolean isEmpty()  {
		// is the set empty? 
		return root == null;
	}
	public  int size()  {
		// number of points in the set
		return count;
	}
	public void insert(Point2D p) {
		// add the point to the set (if it is not already in the set)
		Node newNode = new Node(p);
		root = put(root, p, newNode);
	}
	
	private Node put(Node x, Point2D p, Node temp) {
		if (x == null) {
			return new Node(p);
		} 
		int compare = temp.compareTo(x);
		if (compare < 0) {
			x.left = put(x.left, p, temp);
			//System.out.println(x.left + " is to the left of " + x);
		} else if (compare > 0) {
			x.right = put(x.right, p, temp);
			//System.out.println(x.right + " is to the right of " + x);
		} else {
			x.point = p;
		}
		count++;
		return x;
	}
	
	public boolean contains(Point2D p) {
		// does the set contain point p? 
		boolean hasPoint = false;
		int compare = 1;
		Node x = root;
		Node temp = new Node(p);
		while (x != null && !hasPoint) {
			compare = temp.compareTo(x);
			if (compare < 0) {
				x = x.left;
			} else if (compare > 0) {
				x = x.right;
			} else {
				hasPoint = true;
			}
		}
		return hasPoint;
	}
	public void draw() {
		// draw all points to standard draw
		Node x = root;
		drawImage(x);
	}
	
	private void drawImage(Node x) {
		if (x != null) {
			x.point.draw();
			drawImage(x.left);
			drawImage(x.right);
		}
	}
	public Iterable<Point2D> range(RectHV rect) {
		// all points that are inside the rectangle (or on the boundary)
		Node x = root;
		checkRect(x, rect);
		return list;
	}
	
	private void checkRect(Node x, RectHV rect) {
		if (x != null) {
			if (x.point.x() > rect.xmin() && x.point.x() < rect.xmax()) {
				if (x.point.y() > rect.ymin() && x.point.y() < rect.ymax()) {
					list.add(x.point);
				}
			}
			checkRect(x.left, rect);
			checkRect(x.right, rect);
		}
	}
	public Point2D nearest(Point2D p) {
		// a nearest neighbor in the set to point p; null if the set is empty
		Node x = root;
		double diff = Integer.MAX_VALUE;
		findPoint(x, p, diff);
		return answer;
	}
	
	private void findPoint(Node x, Point2D p, double min) {
		if (x != null) {
			Node temp = new Node(p);
			int compare = temp.compareTo(x);
			double diff = x.point.distanceTo(p);
			if (diff < min) {
				min = diff;
				answer = x.point;
				
			}
			if (compare < 0) {
				findPoint(x.left, p, min);
			} else if (compare > 0) {
				findPoint(x.right, p, min);
			} 
		}
	}

	public static void main(String[] args) {
		KdTree kd = new KdTree();
		kd.insert(new Point2D(7,2));
		kd.insert(new Point2D(5,4));
		kd.insert(new Point2D(2,3));
		kd.insert(new Point2D(4,7));
		kd.insert(new Point2D(9,6));
		ArrayList<Point2D> list = (ArrayList<Point2D>) kd.range(new RectHV(0, 0, 6, 6));
		//System.out.println(list);
		//System.out.println(kd.nearest(new Point2D(10, 7)));
	}
}
