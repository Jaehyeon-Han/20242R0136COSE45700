package model;

import common.Color;
import common.Point;
import common.PropertyDTO;

public class Ellipse extends BoxedElement {
	public Ellipse(String id, Point a, Point b, Color color) {
		super(id, a, b);
		this.color = color;
	}

	@Override
	public PropertyDTO toDTO() {
		PropertyDTO.Builder builder = new PropertyDTO.Builder(id, "ellipse", p, q);
		PropertyDTO dto = builder
				.setColor(color)
				.build();
		return dto;
	}
}
