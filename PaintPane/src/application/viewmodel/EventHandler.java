package viewmodel;

import java.io.File;
import java.util.ArrayList;

import model.Color;
import model.Element;
import model.Type;

public class EventHandler {
	private static final int FORWARD = 1;
	private static final int BACKWARD = -1;
	
	ProgramStatus status; 
	private NodeEventHandler nodeHandler = NodeEventHandler.getInstance();

	public EventHandler() {
		status = new ProgramStatus
				(CreateModeHandler.getInstance(), new Color(0, 0, 0), 
				Type.fromString("line"), new ArrayList<>());
		NodeEventHandler.getInstance().setProgramStatus(status);
		SelectModeHandler.getInstance().setProgramStatus(status);
	}
	
	public void selectSelectMode() {
		status.setSelectMode();
	}
	public void selectCreateMode() {
		status.setCreateMode();
		nodeHandler.unselect();
	}
	public void selectColor(int red, int green, int blue) {
		status.getSelectedColor().setColor(red, green, blue);
	}
	public void selectType(String type) {
		status.setSelectedType(Type.fromString(type));
	}
	public void setImageFile(File file) {
		status.setSelectedImageFile(file);
	}
	
	public void forward() {
		nodeHandler.reOrder(status.getSelectedElement(), FORWARD);
	}
	public void backward() {
		nodeHandler.reOrder(status.getSelectedElement(), BACKWARD);
	}
	
	public void handleMousePressed(double x, double y) {
		status.getSelectedHandler().handlePressed(x, y);
	}
	public void handleMouseReleased(double x, double y) {
		status.getSelectedHandler().handleReleased(x, y);
	}
	
//	public void createText(String content, double x, double y) {
//		selectedText = content;
//		Adapter.getInstance().createText(content, x, y);
//	}
	
	public void changeX(double value) {
		nodeHandler.move(value - status.getSelectedElement().getX1(), 0);
	}
	public void changeY(double value) {
		nodeHandler.move(0, value - status.getSelectedElement().getY1());
	}
	public void changeWidth(double value) {
		Element selectedElement = status.getSelectedElement();
		selectedElement.resize(value, selectedElement.getHeight());
	}
	public void changeHeight(double value) {
		Element selectedElement = status.getSelectedElement();
		selectedElement.resize(selectedElement.getWidth(), value);
	}
	public void changeColor(int r, int g, int b) {
		status.getSelectedElement().setColor(new Color(r, g, b));
	}
}
