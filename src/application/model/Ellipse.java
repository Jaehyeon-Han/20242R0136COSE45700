package model;

import common.Color;
import common.Point;
import common.ModelInfo;

public class Ellipse extends BoxedElement {
	public Ellipse(String id, Point p, Point q, Color color) {
		super(id, p, q);
		this.color = color;
	}

	@Override
	public ModelInfo getModelInfo() {
		ModelInfo.Builder builder = new ModelInfo.Builder(id, "ellipse", p, q);
		ModelInfo dto = builder
				.setColor(color)
				.build();
		return dto;
	}
}
