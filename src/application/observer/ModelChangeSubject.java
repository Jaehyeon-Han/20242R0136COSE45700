package observer;

public interface ModelChangeSubject {
	public void addObserver(ModelChangeObserver observer);
	
	public void removeObserver(ModelChangeObserver observer);
	
	public void notifyOnChange();
}
