package model;

import common.Color;
import common.Point;
import common.PropertyDTO;

public class Line extends Element {
	private static final double dThreshold = 20;
	
	public Line(String id, Point p, Point q, Color color) {
		super(id, p, q);
		this.color = color;
	}

	public Color getColor() {
		return color;
	}
	
	@Override
	public boolean isInHere(Point r) {
		double x1 = p.getX(), x2 = q.getX(), x = r.getX();
		double y1 = p.getY(), y2 = q.getY(), y = r.getY();
		
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
	public PropertyDTO toDTO() {
		PropertyDTO.Builder builder = new PropertyDTO.Builder(id, "line", p, q);
		PropertyDTO dto = builder
			.setColor(color)
			.build();
		return dto;
	}

	@Override
	public double getWidth() {
		return Point.distance(p, q);
	}

	@Override
	public void setWidth(double width) {
		// Calculate the direction vector (normalized)
	    double dx = q.getX() - p.getX();
	    double dy = q.getY() - p.getY();
	    double currentLength = Point.distance(p, q);
	    
	    if (currentLength == 0) {
	        throw new IllegalStateException("Cannot set width for a zero-length line.");
	    }

	    // Normalize the direction vector
	    double directionX = dx / currentLength;
	    double directionY = dy / currentLength;

	    // Update the position of q to achieve the desired width
	    q = new Point(
	        p.getX() + directionX * width,
	        p.getY() + directionY * width
	    );
	    updateMatchingNode();
	}

	@Override
	public double getHeight() {
		return 2;
	}

	@Override
	public void setHeight(double height) {
		// Do nothing
	}
	
	@Override
	public boolean intersects(Point rectP1, Point rectP2) {
	    double rectX1 = Math.min(rectP1.getX(), rectP2.getX());
	    double rectY1 = Math.min(rectP1.getY(), rectP2.getY());
	    double rectX2 = Math.max(rectP1.getX(), rectP2.getX());
	    double rectY2 = Math.max(rectP1.getY(), rectP2.getY());

	    // Check if either endpoint of the line is inside the rectangle
	    if (isInHere(rectP1) || isInHere(rectP2)) {
	        return true;
	    }

	    // Define the four edges of the rectangle as lines
	    Point topLeft = new Point(rectX1, rectY1);
	    Point topRight = new Point(rectX2, rectY1);
	    Point bottomLeft = new Point(rectX1, rectY2);
	    Point bottomRight = new Point(rectX2, rectY2);

	    // Check if the line intersects with any of the rectangle's edges
	    return lineIntersectsLine(p, q, topLeft, topRight) || // Top edge
	           lineIntersectsLine(p, q, topRight, bottomRight) || // Right edge
	           lineIntersectsLine(p, q, bottomRight, bottomLeft) || // Bottom edge
	           lineIntersectsLine(p, q, bottomLeft, topLeft); // Left edge
	}
	
	private boolean lineIntersectsLine(Point p1, Point q1, Point p2, Point q2) {
	    // Find the four orientations
	    int o1 = orientation(p1, q1, p2);
	    int o2 = orientation(p1, q1, q2);
	    int o3 = orientation(p2, q2, p1);
	    int o4 = orientation(p2, q2, q1);

	    // General case: lines intersect if orientations differ
	    if (o1 != o2 && o3 != o4) {
	        return true;
	    }

	    // Special cases: Check if points are collinear and on the segment
	    if (o1 == 0 && onSegment(p1, p2, q1)) return true;
	    if (o2 == 0 && onSegment(p1, q2, q1)) return true;
	    if (o3 == 0 && onSegment(p2, p1, q2)) return true;
	    if (o4 == 0 && onSegment(p2, q1, q2)) return true;

	    return false; // No intersection
	}

	// Helper function to calculate the orientation of three points
	private int orientation(Point a, Point b, Point c) {
	    double val = (b.getY() - a.getY()) * (c.getX() - b.getX()) -
	                 (b.getX() - a.getX()) * (c.getY() - b.getY());
	    if (val == 0) return 0; // Collinear
	    return (val > 0) ? 1 : 2; // Clockwise or Counterclockwise
	}

	// Helper function to check if a point is on a line segment
	private boolean onSegment(Point a, Point b, Point c) {
	    return b.getX() <= Math.max(a.getX(), c.getX()) && b.getX() >= Math.min(a.getX(), c.getX()) &&
	           b.getY() <= Math.max(a.getY(), c.getY()) && b.getY() >= Math.min(a.getY(), c.getY());
	}

}
