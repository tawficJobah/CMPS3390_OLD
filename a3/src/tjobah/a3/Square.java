package tjobah.a3;
/**
 * Square class for a3
 * @author Tawfic Jobah
 * @version 1.0
 */
public class Square extends Shape{
    private float width;

    public Square(float width) {
        super();
        this.setType(Type.SQUARE);
        this.width = width;
    }

    public Square() {
        //super();
        this.setType(Type.SQUARE);
        this.width = 0;
    }

    public float getWidth() { return width; }

    public void setWidth(float width) { this.width = Math.max(0.0f,width); }

    public double getArea(){ return width * width; }

    @Override
    public String toString() {
        return String.format("%s Area: %-12.2f | Width: %-12.2f |",
                super.toString(),this.getArea(), this.width);
    }
}
