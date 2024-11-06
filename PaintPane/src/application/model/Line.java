package model;

public class Line extends Element {
	private static final double dThreshold = 10;
	
	public Line(double x1, double y1, double x2, double y2, Color color) {
		super();
		if(x1 > x2) {
			this.x1 = x2;
			this.y1 = y2;
			this.x2 = x1;
			this.y2 = y1;
		} else {
			this.x1 = x1;
			this.y1 = y1;
			this.x2 = x2;
			this.y2 = y2;
		} // resize 편의성을 위해 (x1, y1)이 왼쪽 점을 가리키도록 강제
		
		this.setColor(color);
	}

	public Color getColor() {
		return color;
	}
	
	@Override
	public void resize(double width, double height) {
		double originalWidth = x2 - x1;
		double dy = y2 - y1;
		double originalHeight = Math.abs(dy);
		double scaleX = width / originalWidth;
		double scaleY = height / originalHeight;
		
		scale(Math.max(scaleX, scaleY)); // lines are not allowed to change orientation
		notifyObservers();
	}
	
	private void scale(double weight) {
		x2 = x1 + weight * (x2 - x1);
		y2 = y1 + weight * (y2 - y1);
	}
	
	@Override
	public boolean isInHere(double x, double y) {
		// Calculate the perpendicular distance from point to line
	    double numerator = Math.abs((y2 - y1) * x - (x2 - x1) * y + x2 * y1 - y2 * x1);
	    double denominator = Math.sqrt((y2 - y1) * (y2 - y1) + (x2 - x1) * (x2 - x1));
	    double distance = numerator / denominator;

	    // Check if the distance is within the threshold
	    if (distance > dThreshold) {
	        return false;
	    }

	    // Ensure the point is within the line segment bounds
	    return (x >= Math.min(x1, x2) && x <= Math.max(x1, x2)) &&
	                           (y >= Math.min(y1, y2) && y <= Math.max(y1, y2));
	}
	
	@Override
	public boolean intersects(double x1, double y1, double x2, double y2) {
        // Check intersection with each edge of the rectangle
		Line topEdge = new Line(x1, y1, x2, y1, null);
		Line bottomEdge = new Line(x1, y1, x2, y1, null);
		Line leftEdge = new Line(x1, y1, x2, y1, null);
		Line rightEdge = new Line(x1, y1, x2, y1, null);

        return this.lineIntersects(topEdge) || this.lineIntersects(bottomEdge) ||
        		this.lineIntersects(leftEdge) || this.lineIntersects(rightEdge);
    }
	
	private boolean lineIntersects(Line other) {
		return orientation(this.x1, this.y1, this.x2, this.y2, other.x1, other.y1) != 
               orientation(this.x1, this.y1, this.x2, this.y2, other.x2, other.y2) &&
               orientation(other.x1, other.y1, other.x2, other.y2, this.x1, this.y1) != 
               orientation(other.x1, other.y1, other.x2, other.y2, this.x2, this.y2);
	}
	
	private int orientation(double pX, double pY, double qX, double qY, double rX, double rY) {
        double val = (qY - pY) * (rX - qX) - (qX - pX) * (rY - qY);
        if (val == 0) return 0; // collinear
        return (val > 0) ? 1 : 2; // clock or counterclockwise
    }
}
