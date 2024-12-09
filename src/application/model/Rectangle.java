package model;

import java.util.UUID;

import common.Color;
import common.Point;
import common.PropertyDTO;

public class Rectangle extends Element {
	public Rectangle(String id, Point a, Point b, Color color) {
		super(id, a, b);
		this.color = color;
	}

	@Override
	public PropertyDTO toDTO() {
		PropertyDTO.Builder builder = new PropertyDTO.Builder("rectangle", p, q);
		PropertyDTO dto = builder.setId(this.id)
			.setColor(color)
			.build();
		return dto;
	}
}
