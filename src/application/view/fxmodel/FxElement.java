package view.fxmodel;

import common.Color;
import common.Point;

public interface FxElement {
	void setP(Point p);
	void setQ(Point q);
	void setColor(Color color);
	void setOpacity(double opacity);
}