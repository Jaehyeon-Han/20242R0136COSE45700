package model;

import java.util.List;

import common.Point;
import common.PropertyDTO;

public class Composite extends Element {
	public Composite(String id, List<Element> elements) {
		
	}

	@Override
	public double getWidth() {
		return 0;
	}

	@Override
	public void setWidth(double width) {
	}

	@Override
	public double getHeight() {
		return 0;
	}

	@Override
	public void setHeight(double height) {
	}

	@Override
	public PropertyDTO toDTO() {
		return null;
	}
}
