package jforsythe.a2;
import java.util.Scanner;



public class Main{

	public static void main(String[] args){
		Scanner scanner = new Scanner(System.in);
		System.out.println("Enter either a math problem or quit");
		System.out.println("Math problem should be in the form of value mathOperator value");
		System.out.println("i.e. \n 43.1 + 2.2");

		String line;
		while(!(line = scanner.nextLine()).equalsIgnoreCase("quit")){
			System.out.println(Calc.evalute(line));
