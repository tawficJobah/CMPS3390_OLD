package tjobah.a3;

/**
 * Rectangle class for a3
 * @author Tawfic Jobah
 * @version 1.0
 */
public class Rectangle extends Square{
    private float height;
    private final Exception nonRectExc = new Exception("if width and height are = then use a square");

    public Rectangle() {
        super();
        this.setType(Type.RECTANGLE);
        this.setWidth(0);
        this.height = 0;
    }

    public Rectangle(float width, float height) throws Exception {
        super();
        if (width == height){ throw nonRectExc; }
        this.setType(Type.RECTANGLE);
        this.setWidth(width);
        this.height = height;
        this.setWidth(width);
    }

    public float getHeight() {
        return height;
    }

    public void setHeight(float height) {
        this.height = height;
    }

    @Override
    public double getArea() {
        return height * super.getWidth();
    }

    @Override
    public String toString() {

        return String.format("%s height: %-12.2f  |",
                super.toString(),this.height);
        //return String.format("Type: %-12.2 | Area: %-12.2f | Width: %-12.2f | Height: %-12.2f  |",
         //       super.getType(),this.getArea(), this.getWidth(),this.height);
    }
}
