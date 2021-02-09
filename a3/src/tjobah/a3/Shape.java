package tjobah.a3;

/**
 * Shape class for a3
 * @author Tawfic Jobah
 * @version 1.0
 */
public class Shape {
    private Type type;

    /**
     * default constructor for shape
     * @return type
     */
    public Type getType(){ return this.type; }
    public void setType(Type type){ this.type = type; }

    @Override
    public String toString() {
        return String.format(" Type: %-12s |", this.type);
    }
}
