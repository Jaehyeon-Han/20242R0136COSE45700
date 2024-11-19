package controller;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.UUID;

import common.PropertyDTO;

import model.Element;
import model.ElementFactory;

public class ElementManager {
    private List<Element> elements = new ArrayList<>();
    private Map<String, Element> idMap = new HashMap<>();
    private ElementFactory factory = ElementFactory.getInstance();

    public PropertyDTO create(PropertyDTO dto) {
        Element newElement = factory.create(
    		dto.getType(),
            dto.getP(), 
            dto.getQ(), 
            dto.getColor(), 
            dto.getImageFile(), 
            dto.getText()
        );

        String idString = UUID.randomUUID().toString(); 
        idMap.put(idString, newElement);
        elements.add(newElement);
        return newElement.toDTO();
    }

    public Element getElementById(String id) {
        return idMap.get(id);
    }
    
    // Singleton
    private ElementManager() {}

    private static class ShapeManagerHelper {
        private static final ElementManager INSTANCE = new ElementManager();
    }

    public static ElementManager getInstance() {
        return ShapeManagerHelper.INSTANCE;
    }
}
