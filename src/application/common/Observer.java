package common;

public interface Observer {
    default void onCreate(PropertyDTO dto) {}
    default void onChange(PropertyDTO dto) {}
    default void onSelect(PropertyDTO dto) {}
    default void onUnselect() {}
}