package view.fxmodel;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import common.Observer;
import common.PropertyDTO;
import javafx.scene.shape.Circle;
import model.Element;
import view.DrawingPane;

public class FxElementManager implements Observer {
	private DrawingPane drawingPane;
	private List<FxElement> fxElements = new ArrayList<>();
	private Map<String, FxElement> idMap = new HashMap<>();
	private FxElement selectedFxElement;

	@Override
	public void onCreate(Element newElement) {
		FxElementFactory fxFactory = FxElementFactory.getInstance();
		PropertyDTO dto = newElement.toDTO();
		FxElement newFxElement = fxFactory.create(dto.getType(), dto.getP(), dto.getQ(), dto.getColor(),
				dto.getImageFile(), dto.getText());
		newElement.setMatchingNode(newFxElement);
		idMap.put(dto.getId(), newFxElement);
		fxElements.add(newFxElement);
		drawingPane.add(newFxElement);
	}

	@Override
	public void onSelect(PropertyDTO dto) {
		FxElement selected = idMap.get(dto.getId());
		if (selectedFxElement != null) {
			selectedFxElement.setOpacity(1);
		}
		selected.setOpacity(0.7);
		System.out.println("Element is selected");
		drawingPane.detachHandler();
		drawingPane.attachHandler(dto.getQ());
		selectedFxElement = selected;
	}

	@Override
	public void onUnselect() {
		if (selectedFxElement != null) {
			selectedFxElement.setOpacity(1);
			drawingPane.detachHandler();
		}
	}

	@Override
	public void onChange(PropertyDTO dto) {
		FxElement changedFxElement = idMap.get(dto.getId());
		updateFxElement(changedFxElement, dto);
		drawingPane.updateHandler(dto.getQ());
	}

	private void updateFxElement(FxElement changedFxElement, PropertyDTO dto) {
		changedFxElement.setP(dto.getP());
		changedFxElement.setQ(dto.getQ());
		changedFxElement.setColor(dto.getColor());
	}

	// Singleton
	private FxElementManager() {
	}

	private static class FxElementManagerHelper {
		private static final FxElementManager INSTANCE = new FxElementManager();
	}

	public static FxElementManager getInstance() {
		return FxElementManagerHelper.INSTANCE;
	}

	public void setDrawingPane(DrawingPane drawingPane) {
		this.drawingPane = drawingPane;
	}
}
