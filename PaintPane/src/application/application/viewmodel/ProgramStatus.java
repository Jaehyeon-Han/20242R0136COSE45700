package viewmodel;

import java.io.File;
import java.util.ArrayList;

import model.Color;
import model.Element;
import model.Type;

public class ProgramStatus {
	private MoustEventHandler selectedHandler;
	private Color selectedColor;
	private Type selectedType;
	private ArrayList<Element> shapes;
	private Element selectedElement;
	private File selectedImageFile;

	public ProgramStatus(MoustEventHandler selectedTool, Color selectedColor, Type selectedType, ArrayList<Element> shapes) {
		this.selectedHandler = selectedTool;
		this.selectedColor = selectedColor;
		this.selectedType = selectedType;
		this.shapes = shapes;
	}

	public MoustEventHandler getSelectedHandler() {
		return selectedHandler;
	}

	public void setSelectMode() {
		this.selectedHandler = SelectModeHandler.getInstance();
	}
	public void setCreateMode() {
		this.selectedHandler = CreateModeHandler.getInstance();
	}

	public Color getSelectedColor() {
		return selectedColor;
	}

	public void setSelectedColor(Color selectedColor) {
		this.selectedColor = selectedColor;
	}

	public Type getSelectedType() {
		return selectedType;
	}

	public void setSelectedType(Type type) {
		this.selectedType = type;
	}

	public ArrayList<Element> getShapes() {
		return shapes;
	}

	public void setShapes(ArrayList<Element> shapes) {
		this.shapes = shapes;
	}

	public Element getSelectedElement() {
		return selectedElement;
	}

	public void setSelectedElement(Element selectedElement) {
		this.selectedElement = selectedElement;
	}

	public File getSelectedImageFile() {
		return selectedImageFile;
	}

	public void setSelectedImageFile(File selectedImageFile) {
		this.selectedImageFile = selectedImageFile;
	}
	
	public boolean isElementSelected() {
		return selectedElement != null;
	}
}