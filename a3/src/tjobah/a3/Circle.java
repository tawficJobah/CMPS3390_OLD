package tjobah.a3;

/**
 * Circle class for a3
 * @author Tawfic Jobah
 * @version 1.0
 */
public class Circle extends Shape{
    private float radius;

    /**
     * default constructor thats sets all values to 0
     */
    public Circle(){
        super();
        this.setType(Type.CIRCLE);
        this.radius = 0.0f;
    };

    /**
     * overloaded constructor that passes in radius
     * @param radius
     */
    public Circle(float radius) {
        this.setType(Type.CIRCLE);
        this.radius = Math.max(0.0f,radius);
    }

    /**
     * get radius of the circle
     * @return radius
     */
    public float getRadius() {
        return this.radius;
    }

    /**
     * sets radius from main driver
     * @param radius
     */
    public void setRadius(float radius) {
        this.radius = Math.max(0.0f,radius);
    }

    /**
     * calculates area of the circle
     * @return area of circle
     */
    public double getArea(){
        return this.radius * this.radius * 3.14159;
    }

    @Override
    public String toString() {
        return String.format("%s Area: %-12.2f | Radius: %-12.2f |",
                super.toString(),this.getArea(), this.getRadius());
    }
}
