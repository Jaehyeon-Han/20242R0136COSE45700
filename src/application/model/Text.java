package model;

import common.Color;
import common.Point;
import common.PropertyDTO;

public class Text extends Element {
	private String content; // currently immutable
	
	public Text(Point p, Point q, Color color, String content) {
		super(p, q);
		this.color = color;
		this.content = content;
	}
	
	public String getContent() {
		return content;
	}

	@Override
	public PropertyDTO toDTO() {
		PropertyDTO.Builder builder = new PropertyDTO.Builder("text", p, q);
		PropertyDTO dto = builder.setColor(color)
		.setText(content)
		.build();
		return dto;
	}
}
