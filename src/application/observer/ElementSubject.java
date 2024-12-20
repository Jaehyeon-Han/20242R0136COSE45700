package observer;

public interface ElementSubject {
	public void addObserver(ElementObserver observer);
	
	public void removeObserver(ElementObserver observer);
	
	public void notifyChange();
}
