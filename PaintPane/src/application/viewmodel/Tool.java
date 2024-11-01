package viewmodel;

import model.Point;

public interface Tool {
	void handle(Point a, Point b, ViewModel viewmodel);
	public void setColor(String color);
}
