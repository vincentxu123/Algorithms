
import java.util.ArrayList;
import java.util.Arrays;



public class FastCollinearPoints {
	
	private boolean collinear;
	private double slope = 0;
	private ArrayList<Point> collinearPoints;
	private double[] arr;
	private Point[] pointList;
	private LineSegment[] lines;
	private ArrayList<LineSegment> lineList;
	
	public FastCollinearPoints(Point[] points)  {
		// finds all line segments containing 4 or more points
		if (points == null) {
			throw new IllegalArgumentException();
		}
		for (int i = 0; i< points.length - 1; i++) {
			if (points[i] == null) {
				throw new IllegalArgumentException("No");
			}
			for (int j = i + 1; j < points.length; j++) {
				if (points[j] == null) {
					throw new IllegalArgumentException("No");
				}
				if (points[i].compareTo(points[j]) == 0) {
					throw new IllegalArgumentException("No");
				}
			}
		}
		pointList = points;
		Arrays.sort(pointList);
		lineList = new ArrayList<LineSegment>();
	}
	public int numberOfSegments() {
		// the number of line segments
		return lineList.size();
	}
	
	public LineSegment[] segments() {
		// the line segments
		getSlopes();
		lines = new LineSegment[lineList.size()];
		for (int i = 0; i < lineList.size(); i++) {
			lines[i] = lineList.get(i);
		}
		return lines;
	}
	private void getSlopes() {
		int counter = 0;
		for (int i = 0; i < pointList.length - 1; i++) {
			arr = new double[pointList.length - 1 - i];
			collinearPoints = new ArrayList<Point>();
			for (int j = i + 1; j < pointList.length; j++) {
				arr[counter] = pointList[i].slopeTo(pointList[j]);
				counter++;
			}
			findMatch(arr);
			if (collinear) {
				for (int k = i + 1; k < pointList.length; k++) {
					if (pointList[i].slopeTo(pointList[k]) == slope) {
						collinearPoints.add(pointList[k]);
					}
				}
			}
			if (collinearPoints.size() > 0) {
				lineList.add(new LineSegment(pointList[i], collinearPoints.get(2)));
			} else {
				//break;
			}
			counter = 0;
			collinear = false;
		}
		
	}
	private void findMatch(double[] arr) {
		Arrays.sort(arr);
		int count = 0;
		//printArr(arr);
		
		for (int i = 0; i < arr.length - 1; i++) {
			int temp = i;
			while (temp + 1 < arr.length && arr[temp] == arr[temp+1]) {
				count++;
				temp++;
			}
			if (count == 2) {
				slope = arr[temp];
				collinear = true;
				break;
			}
			count = 0;
		}
	}
	
	private void printArr(double[] arr) {
		for (int i = 0; i < arr.length; i++) {
			System.out.print(arr[i] + " ");
		}
		System.out.println();
	}
		
}
