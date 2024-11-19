package common;

public interface Observer {
	void onCreate(PropertyDTO dto);
	void onChange(PropertyDTO dto);
}
