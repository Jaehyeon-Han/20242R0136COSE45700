package common;

import model.Element;

public interface Observer {
    default void onCreate(Element element) {}
    default void onChange(Element element) {}
    default void onSelect(Element element) {}
    default void onUnselect() {}
    default void onRemove(Element element) {}
}