package tjobah.a3;


/**
 * Oval class for a3
 * @author Tawfic Jobah
 * @version 1.0
 */
public class Oval extends Circle{
    private float radius2;
    private final Exception equalRad = new Exception("if radius1 and radius2 are = then use a circle");

    /**
     * default construcotor for Oval that sets all values to 0
     */
    public Oval(){
        super();
        this.setType(Type.OVAL);
        this.setRadius(0.0f);
        this.radius2 = 0.0f;
    }

    /**
     * overloaded constructor for oval
     * @param radius
     * @param radius2
     * @throws Exception
     */
    public Oval(float radius, float radius2)throws Exception {
        super();
        if(radius == radius2)
            throw equalRad;
        this.setType(Type.OVAL);
        this.setRadius(radius);
        this.radius2 = radius2;
    }

    /**
     * getter for radius2 of oval
     * @return radius2
     */
    public float getRadius2() {
        return radius2;
    }

    /**
     * sets second radius for oval
     * @param radius2
     */
    public void setRadius2(float radius2) {
        this.radius2 = radius2;
    }

    /**
     * calculates the area of a oval
     * @return area of oval
     */
    @Override
    public double getArea() {
        return super.getArea() * this.radius2 * 3.14159;
    }

    @Override
    public String toString() {
        return String.format("%s Radius2: %-12.2f |",
                super.toString(), this.radius2);

        //return String.format("Area: %-12.2f | Radius: %-12.2f | Radius2: %-12.2f |",
        //        this.getArea(), this.getRadius(), this.radius2);
    }
}
