package model;

import common.Color;
import common.Point;
import common.ModelInfo;

public class Rectangle extends BoxedElement {
	public Rectangle(String id, Point a, Point b, Color color) {
		super(id, a, b);
		this.color = color;
	}

	@Override
	public ModelInfo getModelInfo() {
		ModelInfo.Builder builder = new ModelInfo.Builder(id, "rectangle", p, q);
		ModelInfo dto = builder
			.setColor(color)
			.build();
		return dto;
	}
}
