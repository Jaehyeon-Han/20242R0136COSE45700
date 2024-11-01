package viewmodel;

import model.Point;

public class SelectionTool implements Tool {
	private static SelectionTool instance = new SelectionTool();

	@Override
	public void handle(Point a, Point b, ViewModel viewmodel) {

	}

	public static SelectionTool getInstance() {
		return instance;
	}

	@Override
	public void setColor(String color) {
		// TODO Auto-generated method stub
		
	}
}
