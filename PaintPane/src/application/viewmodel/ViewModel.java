package viewmodel;

import java.util.ArrayList;

import model.Color;
import model.Element;

public class ViewModel {
	private Tool selectedTool = CreationTool.getInstance();
	Color selectedColor = Color.getInstance();
	String selectedType = "line";
	ArrayList<Element> shapes = new ArrayList<>();
	Element selectedElement;
	private Adapter adapter = Adapter.getInstance();
	
	// private Element selectedElement = null;

	public void selectSelectionTool() {
		selectedTool = SelectionTool.getInstance();
		System.out.println("Selection Selected");
	}
	public void selectCreationTool() {
		selectedTool = CreationTool.getInstance();
		System.out.println("Creation Selected");
	}
	public void selectColor(int red, int green, int blue) {
		selectedColor.setColor(red, green, blue);
	}
	public void selectType(String type) {
		this.selectedType = type;
	}
	
	public void notImplemented() {
		
	}
	public void foward() {
		// TODO Auto-generated method stub
		
	}
	public void backward() {
		// TODO Auto-generated method stub
		
	}
	
	public void handleMousePressed(double x, double y) {
		// TODO Auto-generated method stub
		selectedTool.handlePressed(x, y);
	}
	public void handleMouseReleased(double x, double y) {
		// TODO Auto-generated method stub
		selectedTool.handleReleased(x, y);
	}
	public void changeX(double value) {
		adapter.setDeltas(value - selectedElement.getX1(), 0);
		adapter.move();
		Adapter.getInstance().updatePropertyWindow();
	}
	
	public void changeY(double value) {
		adapter.setDeltas(0, value - selectedElement.getY1());
		adapter.move();
		Adapter.getInstance().updatePropertyWindow();
	}
	public void changeWidth(double value) {
		selectedElement.resize(value, Math.abs(selectedElement.getY2() - selectedElement.getY1()));
		Adapter.getInstance().updatePropertyWindow();
	}
	public void changeHeight(double value) {
		selectedElement.resize(selectedElement.getX2() - selectedElement.getX1(), value);
		Adapter.getInstance().updatePropertyWindow();
	}
	public void changeColor(int r, int g, int b) {
		selectedElement.setColor(new Color(r, g, b));
		System.out.println("changeColor: " + r + g + b);
	}
}
