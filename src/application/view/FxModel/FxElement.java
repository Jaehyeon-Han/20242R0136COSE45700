package view.FxModel;

import common.Color;

public interface FxElement {
	void setX(double x);
	void setY(double y);
	void setWidth(double width);
	void setHeight(double height);
	void setColor(Color color);
	void setOpacity(double opacity);
}