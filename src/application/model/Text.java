package model;

import common.Color;
import common.Point;
import common.ModelInfo;

public class Text extends BoxedElement {
	private final String content; // currently immutable
	
	public Text(String id, Point p, Point q, Color color, String content) {
		super(id, p, q);
		this.color = color;
		this.content = content;
	}
	
	public String getContent() {
		return content;
	}

	@Override
	public ModelInfo getModelInfo() {
		ModelInfo.Builder builder = new ModelInfo.Builder(id, "text", p, q);
		ModelInfo dto = builder
			.setColor(color)
			.setText(content)
			.build();
		return dto;
	}
}
