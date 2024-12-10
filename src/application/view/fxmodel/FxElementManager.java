package view.fxmodel;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import common.Observer;
import common.PropertyDTO;
import model.Element;
import view.DrawingPane;
import view.HandlerManager;
import javafx.scene.Node;

public class FxElementManager implements Observer {
	private DrawingPane drawingPane;
	private List<FxElement> fxElements = new ArrayList<>();
	private Map<String, FxElement> idMap = new HashMap<>();
	private FxElement selectedFxElement;
	private String selectedId;

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
	public void onSelect(Element element) {
		FxElement selected = idMap.get(element.getId());
		selectedId = element.getId();
		if (selectedFxElement != null) {
			selectedFxElement.setOpacity(1);
		}
		selected.setOpacity(0.7);
		System.out.println("Element is selected");
		HandlerManager.getInstance().detachHandler();
		HandlerManager.getInstance().attachHandler(element.getQ());
		selectedFxElement = selected;
	}

	@Override
	public void onUnselect() {
		if (selectedFxElement != null) {
			selectedFxElement.setOpacity(1);
			HandlerManager.getInstance().detachHandler();
			selectedId = null;
		}
	}

	@Override
	public void onChange(Element element) {
		HandlerManager.getInstance().updateHandler(element.getQ()); // 따로?
		
	}
	
	public String getSelectedId() {
		return selectedId;
	}
	
	public void remove(Element element) {
		FxElement toRemove = idMap.get(element.getId());
		idMap.remove(element.getId());
		fxElements.remove(toRemove);
		drawingPane.remove((Node) toRemove);
		HandlerManager.getInstance().detachHandler();
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
