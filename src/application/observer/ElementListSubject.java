package observer;

import model.Element;

public interface ElementListSubject {
	public void addObserver(ElementListObserver observer);

    public void removeObserver(ElementListObserver observer);
    
	public void notifyCreate(Element element);

	public void notifyRemove(Element element);
}
