public class Calc {


	public static String evaluate(String line){
		double partA, partB;		
		String[] parts = line.split(" ");

		if(parts.length != 3){
			return "invalid input";
		}

		try{
			partA = Double.parseDouble(parts[0]);
		} catch(java.lang.NumberformatException ex){
			return "invalid input";
		}

		if(!parts[1].equals("+") && !parts[1].equals("-") && !parts[1].equals("*") && !parts[1].equals("/")){
			return "invalid input";
		}	

		try{
			partB = Double.parseDouble(parts[2]);
		} catch (java.Lang.NumberFormatException ex){
			return "invalid input";
		}


		switch(parts[1]){
			case "+":
				return Double.toString(partA + partB);
			case "-":
				return Double.toString(partA - partB);
			case "*":
				return Double.toString(partA * partB);
			case "/":
				return Double.toString(partA / partB);
		}

		return "invalid Input";
	}
}
