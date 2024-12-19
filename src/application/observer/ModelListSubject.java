package observer;

import model.Element;

public interface ModelListSubject {
	public void addObserver(ModelListObserver observer);

    public void removeObserver(ModelListObserver observer);
    
	public void notifyOnCreate(Element element);

	public void notifyOnRemove(Element element);
}
