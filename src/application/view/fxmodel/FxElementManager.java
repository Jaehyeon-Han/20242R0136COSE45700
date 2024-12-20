package view.fxmodel;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import common.Point;
import common.ModelInfo;
import model.Element;
import observer.ElementListObserver;
import observer.SelectedStateObserver;
import view.DrawingPane;
import view.HandlerManager;

public class FxElementManager implements ElementListObserver, SelectedStateObserver {
	private DrawingPane drawingPane;
	private List<FxElement> fxElements = new ArrayList<>();
	private Map<String, FxElement> idMap = new HashMap<>();
	private FxElement selectedFxElement;
	private String selectedId;
	
	// Getter
	public String getSelectedId() {
		return selectedId;
	}
	
	// Setter
	public void setDrawingPane(DrawingPane drawingPane) {
		this.drawingPane = drawingPane;
	}
	
	// Observe model instance creation/deletion
	@Override
	public void onCreate(Element newElement) {
		ModelInfo dto = newElement.getModelInfo();
		FxElement newFxElement =  FxElementFactory.getInstance().create
				(dto.getId(), dto.getType(), dto.getP(), dto.getQ(), dto.getColor(),
				dto.getImageFile(), dto.getText());
		
		newElement.addObserver(newFxElement);
		idMap.put(dto.getId(), newFxElement);
		fxElements.add(newFxElement);
		drawingPane.add(newFxElement.getNode());
	}

	@Override
	public void onRemove(Element element) {
		String id = element.getId();
		FxElement toRemove = idMap.get(id);
		idMap.remove(id);
		fxElements.remove(toRemove);
		drawingPane.remove(toRemove.getNode());
		HandlerManager.getInstance().detachHandler();
	}

	// Observe SelectState
	@Override
	public void onSelect(Element selectedElement) {
		if(selectedFxElement != null) {
			selectedFxElement.unHighlight();
		}
		
		FxElement selected = idMap.get(selectedElement.getId());
		
		// Composite Case
		if(selected == null) {
			String id = selectedElement.getId();
			Point p = selectedElement.getP();
			Point q = selectedElement.getQ();
			selected = FxElementFactory.getInstance().createComposite(id, p, q);
			selectedElement.addObserver(selected);
			drawingPane.add(selected.getNode());
		}
		
		selectedId = selectedElement.getId();
		selectedFxElement = selected;
		selectedFxElement.highlight();
	}

	@Override
	public void onUnSelect() {
		if (selectedFxElement != null) {
			selectedFxElement.unHighlight();
			selectedId = null;
		}
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
}
