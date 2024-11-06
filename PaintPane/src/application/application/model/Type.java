package model;

public enum Type {
    LINE,
    RECTANGLE,
    ELLIPSE,
    IMAGE;

    public static Type fromString(String string) {
        return switch (string.toLowerCase()) {
            case "line" -> Type.LINE;
            case "rectangle" -> Type.RECTANGLE;
            case "ellipse" -> Type.ELLIPSE;
            case "image" -> Type.IMAGE;
            default -> throw new IllegalArgumentException("Unknown type: " + string);
        };
    }
}
