package viewmodel;

import java.util.HashMap;
import java.util.Map;

import javafx.scene.Node;
import model.Element;
import model.Point;
import view.View;

public class ViewModel {
	private Tool selectedTool = CreationTool.getInstance();
	String selectedShape = "line";
	String selectedColor = "black";
	private boolean firstClick = true;
	private Point firstPoint = new Point(0, 0), secondPoint = new Point(0, 0);
	private View view;
	Map<Element, javafx.scene.Node> model = new HashMap<Element, javafx.scene.Node>();
	
	public static void main(String[] args) {
		ViewModel viewModel = new ViewModel();
		View.begin(args, viewModel);
	}
	
	public void mockFunction() {
		System.out.println("test succeeded");
	}
	
	public void selectCreationTool() {
		this.selectedTool = CreationTool.getInstance();
		System.out.println("Creation Selected");
	}
	
	public void selectSelectionTool() {
		this.selectedTool = SelectionTool.getInstance();
		System.out.println("Selection Selected");
	}
	
	public void handlePaneClick(double x, double y) {
		if(firstClick) {
			firstPoint.setX(x);
			firstPoint.setY(y);
			firstClick = false;
		}
		else {
			secondPoint.setX(x);
			secondPoint.setY(y);
			firstClick = true;
			selectedTool.handle(firstPoint, secondPoint, this);
		}
	}
	
	public void attachPane(javafx.scene.Node newNode) {
		view.attachPane(newNode);
	}
	
	public void setView(View view) {
		this.view = view;
	}
	
	public void setSelectedShape(String shape) {
		this.selectedShape = shape;
	}

	public void setSelectedColor(String color) {
		selectedTool.setColor(color);
	}

	public void selectNode(Node newNode) {
		
	}
}
