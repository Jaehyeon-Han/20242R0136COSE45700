package model;

import common.Color;
import common.Point;
import common.PropertyDTO;

public class Rectangle extends BoxedElement {
	public Rectangle(String id, Point a, Point b, Color color) {
		super(id, a, b);
		this.color = color;
	}

	@Override
	public PropertyDTO toDTO() {
		PropertyDTO.Builder builder = new PropertyDTO.Builder(id, "rectangle", p, q);
		PropertyDTO dto = builder
			.setColor(color)
			.build();
		return dto;
	}
}
