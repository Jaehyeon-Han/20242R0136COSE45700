package model;

import common.Color;
import common.Point;
import common.PropertyDTO;

public class Rectangle extends Element {
	public Rectangle(Point a, Point b, Color color) {
		super(a, b);
		this.color = color;
	}

	@Override
	public PropertyDTO toDTO() {
		PropertyDTO.Builder builder = new PropertyDTO.Builder("rectangle", p, q);
		PropertyDTO dto = builder.setColor(color)
		.build();
		return dto;
	}
}
