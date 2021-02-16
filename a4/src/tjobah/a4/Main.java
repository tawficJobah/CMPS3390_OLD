package tjobah.a4;

import tjobah.a3.*;

import java.awt.event.MouseWheelEvent;
import java.util.Random;
import java.util.Scanner;

public class Main {

    public static void main(String[] args) throws Exception {
        Scanner scanner = new Scanner(System.in);
        Random rand = new Random();

        System.out.print("Do you wat a [S]ingle thread or [D]ual thread or [Q]uad thread?");
        char selection = scanner.next().charAt(0);

        System.out.print("How many shapes do you want to sort?");
        int count = scanner.nextInt();
        Shape[] shapes = new Shape[count];

        for (int i = 0; i < count; i++) {
            int t = rand.nextInt(5);
            switch (t) {
                case 0:
                    shapes[i] = new Circle(rand.nextFloat() * 20f);
                    break;
                case 1:
                    shapes[i] = new Oval(rand.nextFloat() * 20f, rand.nextFloat() * 20f);
                    break;
                case 2:
                    shapes[i] = new Square(rand.nextFloat() * 20f);
                    break;
                case 3:
                    shapes[i] = new Rectangle(rand.nextFloat() * 20f, rand.nextFloat() * 20f);
                    break;
                case 4:
                    shapes[i] = new RightTriangle(rand.nextFloat() * 20f, rand.nextFloat() * 20f);
                    break;
            }
        }

        switch(selection){
            case 's':
            case'S':
                singleSort(shapes);
                break;
            case'd':
            case 'D':
                dualSort(shapes);
                break;
            case 'q':
            case 'Q':
                quadSort(shapes);
                break;
        }

    }

    private static void quadSort(Shape[] shapes) throws InterruptedException {
        int shapeCount = shapes.length /4;
        int shapesRem = shapes.length - (shapeCount * 3);

        ThreadSort[] sortThreads = new ThreadSort[4];

        long startTime = System.nanoTime();
        for(int i = 0; i < 3; i++){
            sortThreads[i] = new ThreadSort(shapes, i * shapeCount,
                    i * shapeCount + shapeCount);
            sortThreads[i].start();
        }
        sortThreads[3] = new ThreadSort(shapes, shapes.length - shapesRem, shapes.length);
        sortThreads[3].start();

        for(ThreadSort t : sortThreads){
            t.join();
        }
        MergeSort m1 = new MergeSort(sortThreads[0].gettShapes(),sortThreads[1].gettShapes());
        MergeSort m2 = new MergeSort(sortThreads[2].gettShapes(),sortThreads[1].gettShapes());
        m1.start();
        m2.start();
        m1.join();
        m2.join();

        MergeSort m3 = new MergeSort(m1.getSortedShapes(), m2.getSortedShapes());
        m3.start();
        m3.join();
        long endTime = System.nanoTime();

        long duration = (endTime - startTime) /1000000;

        for(Shape s : m3.getSortedShapes()) {
            System.out.println(s);
        }
        System.out.println("Quad Thread Sort TIme: " + duration);
    }

    private static void dualSort(Shape[] shapes) throws InterruptedException {
        int mid = Math.round(shapes.length / 2);

        ThreadSort t1 = new ThreadSort(shapes, 0, mid);
        ThreadSort t2 = new ThreadSort(shapes, mid, shapes.length);
        long startTime = System.nanoTime();
        t1.start();
        t2.start();

        t1.join();
        t2.join();

        MergeSort m = new MergeSort(t1.gettShapes(), t2.gettShapes());
        m.start();
        m.join();
        long endTime = System.nanoTime();
        long duration = (endTime - startTime) /1000000;
        for(Shape s : m.getSortedShapes()){
            System.out.println(s);
        }
        System.out.println("Multi Thread Sort: " + duration);

    }

    private static void singleSort(Shape[] shapes) throws InterruptedException {
        ThreadSort threadSort = new ThreadSort(shapes, 0, shapes.length);
        long startTime = System.nanoTime();
        threadSort.start();
        threadSort.join();
        long endTime = System.nanoTime();

        long duration = (endTime-startTime) / 1000000;
        for(Shape s : threadSort.gettShapes()){
            System.out.println(s);
        }
        System.out.println("Single Thread Sort: " + duration);
    }
}
