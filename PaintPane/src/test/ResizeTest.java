import static org.junit.jupiter.api.Assertions.*;

import java.util.ArrayList;
import java.util.List;

import org.junit.jupiter.api.Test;
import model.*;

class ResizeTest {
	private ElementFactory factory = ElementFactory.getInstance();
	
	@Test
	void RectangleTest() {
		Rectangle rect = (Rectangle) factory.create(Type.RECTANGLE, 30, 200, 50, 150);
		rect.resize(100, 300);
		assertEquals(rect.getX2(), 130, "X2");
		assertEquals(rect.getY2(), 450, "Y2");
	}
	
	@Test
	void LineTest() {
		Line line = (Line) factory.create(Type.LINE, 30, 200, 50, 150);
		line.resize(25, 100);
		assertEquals(line.getX2() - line.getX1(), 40, "width");
		assertEquals(Math.abs(line.getY2() - line.getY1()), 100, "height");
	}
	
	@Test
	void CompositeTest() {
		Rectangle rect1 = (Rectangle) factory.create(Type.RECTANGLE, 10, 10, 30, 30);
		Rectangle rect2 = (Rectangle) factory.create(Type.RECTANGLE, 20, 20, 40, 40);
		List<Element> list = new ArrayList<Element>();
		list.add(rect1);
		list.add(rect2);
		Composite composite = (Composite) factory.createComposite(list);
		composite.resize(100, 100);
		assertEquals(rect1.getX1(), 10, "X1 of rect1");
		assertEquals(rect1.getY1(), 10, "Y1 of rect1");
		assertEquals((double)(Math.round(rect2.getX1() * 10) / 10.0), 43.3, "X1 of rect2");
		assertEquals((double)(Math.round(rect2.getY1() * 10) / 10.0), 43.3, "Y1 of rect2");
	}
}
