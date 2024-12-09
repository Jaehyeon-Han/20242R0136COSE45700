package common;

import model.Element;

public interface Observer {
    default void onCreate(Element element) {}
    default void onChange(PropertyDTO dto) {}
    default void onSelect(PropertyDTO dto) {}
    default void onUnselect() {}
}