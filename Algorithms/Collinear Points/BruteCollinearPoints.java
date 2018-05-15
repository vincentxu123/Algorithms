
public class BruteCollinearPoints {
	
	private Point[] pointList;
	private LineSegment[] segmentList;
	private LineSegment[] tempList;
	private int pointer; //tracks templist
			
	public BruteCollinearPoints(Point[] points) {
		// finds all line segments containing 4 points
		if (points == null) {
			throw new IllegalArgumentException("No");
		}
		for (int i = 0; i< points.length - 1; i++) {
			if (points[i] == null) {
				throw new IllegalArgumentException("No");
			}
			for (int j = i + 1; j < points.length; j++) {
				if (points[j] == null) {
					throw new IllegalArgumentException("No");
				}
				if (points[j].compareTo(points[i]) == 0) {
					throw new IllegalArgumentException("No");
				}
			}
		}
		pointList = points;
		tempList = new LineSegment[pointList.length];
		calculations();
		
	}
    public int numberOfSegments() {
    	// the number of line segments
    	return pointer;
    }
    public LineSegment[] segments()  {
	   // the line segments
    	//calculations();
    	segmentList = new LineSegment[pointer];
    	int i = 0;
    	while (i < pointer) {
    		segmentList[i] = tempList[i];
    		i++;
    	}
    	return segmentList;
    }
    
    private void calculations() {
		Point min = null;
		Point max = null;
		int counter = 0;
		double slope = 0;
		
		for (int i = 0; i < pointList.length - 2; i++) {
			for (int j = i + 1; j < pointList.length - 1; j++) {
				if (pointList[i].compareTo(pointList[j]) > 0) {
					max = pointList[i];
					min = pointList[j];
				} else {
					max = pointList[j];
					min = pointList[i];
				}
				slope = pointList[i].slopeTo(pointList[j]);
				for (int k = j + 1; k < pointList.length; k++) {
					if (slope == pointList[j].slopeTo(pointList[k])) {
						counter++;
						max = compareMax(max, pointList[k]);
						min = compareMin(min, pointList[k]);
					}
				}
				if (counter == 2 && pointer < tempList.length) {
					tempList[pointer] = new LineSegment(min, max);
					pointer++;
				}
				counter = 0;
			}
		}
    }
    
    private Point compareMax(Point one, Point two) {
    	if (one.compareTo(two) > 0) {
    		return one;
    	} 
    	return two;
    }
    
    private Point compareMin(Point one, Point two) {
    	if (one.compareTo(two) < 0) {
    		return one;
    	}
    	return two;
    }
    
}