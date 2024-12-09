package model;

import java.util.UUID;

import common.Color;
import common.Point;
import common.PropertyDTO;

public class Ellipse extends Element {
	public Ellipse(String id, Point a, Point b, Color color) {
		super(id, a, b);
		this.color = color;
	}

	@Override
	public PropertyDTO toDTO() {
		PropertyDTO.Builder builder = new PropertyDTO.Builder("ellipse", p, q);
		PropertyDTO dto = builder.setId(this.id)
				.setColor(color)
				.build();
		return dto;
	}
}
