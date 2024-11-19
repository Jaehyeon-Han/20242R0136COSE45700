package common;

import java.io.File;

public class PropertyDTO {
    private final String id;
	private final String type;
    private final Point p;
    private final Point q;
    private final Color color;
    private final String text;
    private final File imageFile;

    private PropertyDTO(Builder builder) {
        this.id = builder.id;
        this.type = builder.type;
        this.p = builder.p;
        this.q = builder.q;
        this.color = builder.color;
        this.text = builder.text;
        this.imageFile = builder.imageFile;
    }

    public String getId() {
        return id;
    }
    public String getType() {
        return type;
    }

    public Point getP() {
        return p;
    }

    public Point getQ() {
        return q;
    }

    public Color getColor() {
        return color;
    }

    public String getText() {
        return text;
    }

    public File getImageFile() {
        return imageFile;
    }
    


    // Static Builder Class
    public static class Builder {
    	private String id;
    	private final String type;
        private final Point p;
        private final Point q;
        private Color color;
        private String text;
        private File imageFile;

        public Builder(String type, Point p, Point q) {
            this.type = type;
            this.p = p;
            this.q = q;
        }

        public Builder setId(String id) {
            this.id = id;
            return this;
        }
        
        public Builder setColor(Color color) {
            this.color = color;
            return this;
        }

        public Builder setText(String text) {
            this.text = text;
            return this;
        }

        public Builder setImageFile(File imageFile) {
            this.imageFile = imageFile;
            return this;
        }

        public PropertyDTO build() {
            return new PropertyDTO(this);
        }
    }
}