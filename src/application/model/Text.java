package model;

import common.Color;
import common.Point;
import common.PropertyDTO;
import java.util.UUID;

public class Text extends Element {
	private String content; // currently immutable
	
	public Text(String id, Point p, Point q, Color color, String content) {
		super(id, p, q);
		this.color = color;
		this.content = content;
	}
	
	public String getContent() {
		return content;
	}

	@Override
	public PropertyDTO toDTO() {
		PropertyDTO.Builder builder = new PropertyDTO.Builder("text", p, q);
		PropertyDTO dto = builder.setId(this.id)
			.setColor(color)
			.setText(content)
			.build();
		return dto;
	}
}
