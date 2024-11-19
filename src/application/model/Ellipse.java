package model;

import common.Color;
import common.Point;
import common.PropertyDTO;

public class Ellipse extends Element {
	public Ellipse(Point a, Point b, Color color) {
		super(a, b);
		this.color = color;
	}

	@Override
	public PropertyDTO toDTO() {
		PropertyDTO.Builder builder = new PropertyDTO.Builder("ellipse", p, q);
		PropertyDTO dto = builder.setColor(color)
		.build();
		return dto;
	}
}
