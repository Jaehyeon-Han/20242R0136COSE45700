package common;

import java.io.File;

public class CreateInfo {
    private String id;
	private final String type, text;
    private final Point p, q;
    private final Color color;
    private final File imageFile;

    private CreateInfo(Builder builder) {
        this.id = builder.id;
        this.type = builder.type;
        this.p = builder.p;
        this.q = builder.q;
        this.color = builder.color;
        this.text = builder.text;
        this.imageFile = builder.imageFile;
    }

    public void setId(String id) {
    	this.id = id;
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
    	private final String id, type;
    	private String text;
        private final Point p, q;
        private Color color;
        private File imageFile;

        public Builder(String id, String type, Point p, Point q) {
            this.id = id;
        	this.type = type;
            this.p = p;
            this.q = q;
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

        public CreateInfo build() {
            return new CreateInfo(this);
        }
    }
}
