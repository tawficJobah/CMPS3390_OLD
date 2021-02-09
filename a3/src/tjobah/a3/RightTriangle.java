package tjobah.a3;

/**
 * Right triangle  class for a3
 * @author Tawfic Jobah
 * @version 1.0
 */
public class RightTriangle extends Rectangle{
    private float height;

    public RightTriangle() {
        super();
        this.setType(Type.RIGHTTRIANGLE);
        this.setWidth(0.0f);
        this.setHeight(0.0f);
    }

    public RightTriangle(float width, float height) {
        super();
        this.setType(Type.RIGHTTRIANGLE);
        this.setHeight(height);
        this.setWidth(width);
    }

    @Override
    public float getHeight() {
        return this.height;
    }

    @Override
    public void setHeight(float height) {
        super.setHeight(height);
    }

    @Override
    public double getArea() {
        return (super.getWidth() * super.getHeight()/2);
    }

    @Override
    public String toString() {
        return String.format("%s ",
                super.toString());
        //return String.format("Area: %-12.2f | Width: %-12.2f | Height: %-12.2f  |",
        //        this.getArea(), this.getWidth(),this.height);
    }
}
