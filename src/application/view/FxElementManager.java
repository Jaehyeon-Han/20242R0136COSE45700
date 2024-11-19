package view;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import common.Observer;
import common.PropertyDTO;
import javafx.scene.shape.Rectangle;
import view.FxModel.FxElement;
import view.FxModel.FxElementFactory;

public class FxElementManager implements Observer {
	private DrawingPane drawingPane;
	private List<FxElement> fxElements = new ArrayList<>();
	private Map<String, FxElement> idMap = new HashMap<>();
	
	@Override
	public void onCreate(PropertyDTO dto) {
		FxElementFactory factory = FxElementFactory.getInstance();
		FxElement newElement = factory.create(
	   		dto.getType(),
            dto.getP(), 
            dto.getQ(), 
            dto.getColor(), 
            dto.getImageFile(), 
            dto.getText()
        );
        String id = dto.getId();
        idMap.put(id, newElement);
        fxElements.add(newElement);
        
        drawingPane.add(newElement);
	}
	
	@Override
	public void onChange(PropertyDTO dto) {
		FxElement changedElement = idMap.get(dto.getId());
		updateElement(changedElement, dto);
	}
	
	private void updateElement(FxElement changedElement, PropertyDTO dto) {
		double x1 = dto.getP().getX();
		double y1 = dto.getP().getY();
		double x2 = dto.getQ().getX();
		double y2 = dto.getQ().getY();
		
		changedElement.setX(x1);
		changedElement.setY(y1);
		changedElement.setWidth(Math.abs(x2 - x1));
		changedElement.setHeight(Math.abs(y2 - y1));
		changedElement.setColor(dto.getColor());
	}

	// Singleton
    private FxElementManager() {}

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
