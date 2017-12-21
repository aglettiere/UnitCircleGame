// This file contains: Variables, Conditionals, Iterations
import java.util.Scanner;
import java.io.IOException;
public class UnitCircleTest{
	public static void main(String[] args) throws IOException{
			boolean again=true;
			boolean correct = true;
			Scanner scanner = new Scanner(System.in);
			System.out.println();
			System.out.println("Welcome to the Unit Circle Practice Game! You will be able to test your knowledge of the unit circle and the various trig functions involved.");
			System.out.println();
			System.out.println("Once prompted with a trig function and an angle, you will need to evaluate the trig function and enter a simplified exact answer.");
			System.out.println();
			System.out.println("To indicate a square root symbol, type 'sqrt(number)'.");
			System.out.println();

		do{
			System.out.println("How many questions would you like include? Please enter a number between 1 and 10 inclusive.");
			int numQuestions = scanner.nextInt();
			while (numQuestions<1 || numQuestions>10){
				System.out.println("Please enter a valid input. The number of questions must be between 1 and 10 inclusive.");
				numQuestions = scanner.nextInt();
			}

			System.out.println();
			System.out.println("Would you like positive angles or negative angles?");
			String angleDirection = scanner.next();
			boolean inputCorrect = false;
			while (inputCorrect == false){
				if (angleDirection.equals("positive")){
					inputCorrect = true;
					break;
				}
				else if (angleDirection.equals("negative")){
					inputCorrect = true;
					break;
				}
				else{
					System.out.println("Please enter a valid input for the type of angle. The options are 'positive' and'negative'.");
					angleDirection = scanner.next();
				}
			}

			System.out.println();
			System.out.println("Would you like to include angles in degrees or radians?");
			String angleType = scanner.next();
			boolean inCorrect = false;
			while (inCorrect == false){
				if (angleType.equals("radians")){
					inCorrect = true;
					break;
				}
				else if (angleType.equals("degrees")){
					inCorrect = true;
					break;
				}
				else{
					System.out.println("Please enter a valid input for the type of angle. The options are 'degrees' and 'radians'.");
					angleType = scanner.next();
				}
			}

			UnitCircle circle = new UnitCircle();
			circle.writeFile(circle.promptQuestions(numQuestions, circle.createArray(numQuestions, angleDirection, angleType)),numQuestions);	
			
			
			while (correct ==true){
				System.out.println("Do you want to play again?");
				String response = scanner.next();
				if (response.equals("yes")){
					again=true;
					correct=false;	
				}
				else if (response.equals("no")){
					again=false;
					correct=false;	
				}
				else{	
					System.out.println("Please input a correct response. The options are 'yes' and 'no'.");
				}
			}

		}while(again==true);
	}
}