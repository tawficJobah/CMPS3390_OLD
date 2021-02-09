package tjobah.a3;

import java.util.Random;
import java.util.Scanner;

/**
 * Main Driver class for a3
 * @author Tawfic Jobah
 * @version 1.0
 */
public class Main {

    public static void main(String[] args) throws Exception {
        Scanner scanner = new Scanner(System.in);
        Random rand = new Random();
        System.out.print("How many Shapes");

        int count = scanner.nextInt();
        Shape[] shapes = new Shape[count];
        for(int i = 0; i < count; i++){
            int t = rand.nextInt(5);
            switch(t){
                case 0:
                    shapes[i] = new Circle(rand.nextFloat()* 20f);
                    break;
                case 1:
                    shapes[i] = new Oval(rand.nextFloat() * 20f, rand.nextFloat() * 20f);
                    break;
                case 2:
                    shapes[i] = new Square(rand.nextFloat() * 20f);
                    break;
                case 3:
                    shapes[i] = new Rectangle(rand.nextFloat()*20f, rand.nextFloat()* 20f);
                    break;
                case 4:
                    shapes[i] = new RightTriangle(rand.nextFloat()* 20f, rand.nextFloat()* 20f);
                    break;
            }
        }

        for(Shape s : shapes) {
            System.out.println(s);
        }
    }
}
