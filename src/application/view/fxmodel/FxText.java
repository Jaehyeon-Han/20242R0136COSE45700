package view.fxmodel;

import common.Color;
import common.Point;
import javafx.scene.Group;
import javafx.scene.Node;
import javafx.scene.shape.Rectangle;
import javafx.scene.text.Text;
import model.Element;

public class FxText extends FxElement {
	private Rectangle boundBox;
	private Text fxText;
    private Point topLeft, bottomRight;
    
    public FxText(String id, Point p, Point q, Color color, String text) {
        super(id);
        this.topLeft = p;
        this.bottomRight = q;
        this.boundBox = new Rectangle(p.getX(), p.getY(), 
				q.getX() - p.getX(), 
				q.getY() - p.getY());
        boundBox.setFill(javafx.scene.paint.Color.TRANSPARENT);
        
        fxText = new Text(text);
        setColor(color);
        update();
    }

    private void alignTextInBox() {
        // 텍스트의 너비와 높이 계산
        double width = Math.abs(bottomRight.getX() - topLeft.getX());
        double height = Math.abs(bottomRight.getY() - topLeft.getY());

        // wrappingWidth와 X 좌표를 일치시킴
        fxText.setWrappingWidth(width);
        fxText.setX(Math.min(topLeft.getX(), bottomRight.getX())); // 상자의 왼쪽 경계

        // Y 좌표는 텍스트의 높이를 보정하여 설정
        double textHeight = fxText.getBoundsInLocal().getHeight();
        fxText.setY(Math.min(topLeft.getY(), bottomRight.getY()) + textHeight);
    }

    @Override
    public void setP(Point p) {
        this.topLeft = p;
        update();
    }

    @Override
    public void setQ(Point q) {
        this.bottomRight = q;
        update();
    }

    public Point getP() {
        return this.topLeft;
    }

    public Point getQ() {
        return this.bottomRight;
    }

    private void update() {
        double width = Math.abs(bottomRight.getX() - topLeft.getX());
        double height = Math.abs(bottomRight.getY() - topLeft.getY());

        boundBox.setWidth(bottomRight.getX() - topLeft.getX());
		boundBox.setHeight(bottomRight.getY() - topLeft.getY());
		boundBox.setX(topLeft.getX());
		boundBox.setY(topLeft.getY());
        
        setWidth(width);
        alignTextInBox();
    }

    public void setWidth(double width) {
        fxText.setWrappingWidth(width);
    }

    public void setHeight(double height) {
        // 현재 구현에서는 높이 조정 필요 없음
    }

    @Override
    public void setColor(Color color) {
        fxText.setFill(color.toFxColor());
    }

    @Override
    public void onChange(Element element) {
        setP(element.getP());
        setQ(element.getQ());
        setColor(element.getColor());
    }

	@Override
	public void highlight() {
		boundBox.setOpacity(0.7);
		boundBox.setStroke(javafx.scene.paint.Color.BLUE);
	}
	
	@Override
	public void unHighlight() {
		boundBox.setOpacity(1);
		boundBox.setStroke(javafx.scene.paint.Color.TRANSPARENT);
	}

    @Override
    public Node getNode() {
    	Group group = new Group(boundBox, fxText);
        return group;
    	// return fxText;
    }
}
