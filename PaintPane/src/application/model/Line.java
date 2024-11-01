package model;

public class Line extends Shape {
	private Point start, end;

	public Point getStart() {
		return start;
	}

	public void setStart(Point start) {
		this.start = start;
	}

	public Point getEnd() {
		return end;
	}

	public void setEnd(Point end) {
		this.end = end;
	}

	public Line(Point start, Point end, String color) {
		this.start = start;
		this.end = end;
		this.color = color;
	}
	
	@Override
	public String toString() {
		return "line";
	}
}
