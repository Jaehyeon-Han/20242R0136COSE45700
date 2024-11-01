package viewmodel;

import java.util.HashMap;
import java.util.Map;

public class ColorMapper {
	private static Map<String, javafx.scene.paint.Color> map = new HashMap<String, javafx.scene.paint.Color>();
	
	static {
		map.put("red", javafx.scene.paint.Color.RED);
		map.put("green", javafx.scene.paint.Color.GREEN);
		map.put("blue", javafx.scene.paint.Color.BLUE);
		map.put("black", javafx.scene.paint.Color.BLACK);
	}
	
	static javafx.scene.paint.Color mapColor(String color) {
		return map.get(color);
	}
}
