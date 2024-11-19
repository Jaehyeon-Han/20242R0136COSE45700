package model;

import common.Color;
import common.Point;
import common.PropertyDTO;

public class Line extends Element {
	private static final double dThreshold = 20;
	
	public Line(Point p, Point q, Color color) {
		this.p = p;
		this.q = q;
		this.setColor(color);
	}

	public Color getColor() {
		return color;
	}
	
	@Override
	public boolean isInHere(Point r) {
		double x1 = p.getX(), x2 = q.getX(), x = r.getX();
		double y1 = p.getY(), y2 = q.getY(), y = r.getY();
		
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
	public PropertyDTO toDTO() {
		PropertyDTO.Builder builder = new PropertyDTO.Builder("line", p, q);
		PropertyDTO dto = builder.setColor(color)
		.build();
		return dto;
	}
}