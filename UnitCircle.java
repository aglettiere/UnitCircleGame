// This file contains: Variables, Constants, Conditionals, Iterations, User-Defined Methods (pass arguments and return values), a Constructor, File I/O, Arrays, String Methods
import java.util.Scanner;
import java.lang.Math;
import java.io.IOException;
import java.io.PrintWriter;
import java.io.FileWriter;
import java.io.File;
import java.util.Random;

public class UnitCircle{
	
	private final String[] angleRadians = {"π/6", "π/4", "π/3", "π/2", "2π/3", "3π/4", "5π/6", "π", "7π/6", "5π/4", "4π/3", "3π/2", "5π/3", "7π/4", "11π/6", "2π"};
	private final String[] angleDegrees = {"0", "30", "45", "60", "90", "120", "135", "150", "180", "210", "225", "240", "270", "300", "315", "330", "360"};
	private final String[] negativeAngleRadians = {"-π/6", "-π/4", "-π/3", "-π/2", "-2π/3", "-3π/4", "-5π/6", "-π", "-7π/6", "-5π/4", "-4π/3", "-3π/2", "-5π/3", "-7π/4", "-11π/6", "-2π"};
	private final String[] negativeAngleDegrees = {"0", "-30", "-45", "-60", "-90", "-120", "-135", "-150", "-180", "-210", "-225", "-240", "-270", "-300", "-315", "-330", "-360"};
	private final String[] trigFunctions = {"sine", "cosine", "tangent", "cosecant", "secant", "cotangent"};
	private String name ="";


	public UnitCircle(){
		int numQuestions=1;
		String angleDirection="";
		String angleType="";
	}

	public String[] createArray(int numQuestions, String angleDirection, String angleType){
		String[] questions = new String[numQuestions];
		int j = 0;
		for (int i=0; i<numQuestions;i++){
			if((angleDirection.equals("positive")) && (angleType.equals("radians"))){
				String problems = (angleRadians[new Random().nextInt(angleRadians.length)]);
				questions[i]= problems;
			}

			else if(angleDirection.equals("positive") && (angleType.equals("degrees"))){
				String problems = (angleDegrees[new Random().nextInt(angleDegrees.length)]);
				questions[i]= problems;
			}

			else if(angleDirection.equals("negative") && (angleType.equals("radians"))){
				String problems = (negativeAngleRadians[new Random().nextInt(negativeAngleRadians.length)]);
				questions[i]= problems;
			}

			else if(angleDirection.equals("negative") && (angleType.equals("degrees"))){
				String problems = (negativeAngleDegrees[new Random().nextInt(negativeAngleDegrees.length)]);
				questions[i]= problems;
			}
		}
		return questions;
	}

	public double eval(final String str) {
	    return new Object() {
	        int pos = -1, ch;

	        void nextChar() {
	            ch = (++pos < str.length()) ? str.charAt(pos) : -1;
	        }

	        boolean eat(int charToEat) {
	            while (ch == ' ') nextChar();
	            if (ch == charToEat) {
	                nextChar();
	                return true;
	            }
	            return false;
	        }

	        double parse() {
	            nextChar();
	            double x = parseExpression();
	            if (pos < str.length()) throw new RuntimeException("Unexpected: " + (char)ch);
	            return x;
	        }

	        double parseExpression() {
	            double x = parseTerm();
	            for (;;) {
	                if      (eat('+')) x += parseTerm(); 
	                else if (eat('-')) x -= parseTerm(); 
	                else return x;
	            }
	        }

	        double parseTerm() {
	            double x = parseFactor();
	            for (;;) {
	                if      (eat('*')) x *= parseFactor(); 
	                else if (eat('/')) x /= parseFactor(); 
	                else return x;
	            }
	        }

	        double parseFactor() {
	            if (eat('+')) return parseFactor(); 
	            if (eat('-')) return -parseFactor(); 

	            double x;
	            int startPos = this.pos;
	            if (eat('(')) { 
	                x = parseExpression();
	                eat(')');
	            } else if ((ch >= '0' && ch <= '9') || ch == '.') { 
	                while ((ch >= '0' && ch <= '9') || ch == '.') nextChar();
	                x = Double.parseDouble(str.substring(startPos, this.pos));
	            } else if (ch >= 'a' && ch <= 'z') { 
	                while (ch >= 'a' && ch <= 'z') nextChar();
	                String func = str.substring(startPos, this.pos);
	                x = parseFactor();
	                if (func.equals("sqrt")) x = Math.sqrt(x);
	                else if (func.equals("sin")) x = Math.sin(Math.toRadians(x));
	                else if (func.equals("cos")) x = Math.cos(Math.toRadians(x));
	                else if (func.equals("tan")) x = Math.tan(Math.toRadians(x));
	                else throw new RuntimeException("Unknown function: " + func);
	            } else {
	                throw new RuntimeException("Unexpected: " + (char)ch);
	            }

	            if (eat('^')) x = Math.pow(x, parseFactor()); 

	            return x;
	        }
	    }.parse();
	}

	public int promptQuestions(int numQuestions, String[] questions) throws IOException{
		Scanner scanner = new Scanner(System.in);
		int score = 0;
		boolean inputCorrect = false;
		for (int i=0; i<numQuestions;i++){
				System.out.println();
				System.out.println("Question "+ (i+1) + "/" + numQuestions);
				String trigPrompt = (trigFunctions[new Random().nextInt(trigFunctions.length)]);
				System.out.println(trigPrompt+"("+questions[i]+")");
				System.out.println("Would you like to view an image of the unit circle?");
				String pic = scanner.next();
				while (inputCorrect == false){
					if (pic.equals("yes")){
						inputCorrect=true;
						if(questions[i]== "0" || questions[i]== "30" || questions[i]== "60" || questions[i]== "90" || questions[i]=="π/6" || questions[i]== "π/4" || questions[i]== "π/3" || questions[i]== "π/2"){
							Quadrant1 photo = new Quadrant1();
        					photo.showImage();
						}
						else if(questions[i]== "120" || questions[i]== "135" || questions[i]== "150" || questions[i]== "180" || questions[i]=="2π/3" || questions[i]== "3π/4" || questions[i]== "5π/6" || questions[i]== "π"){
							Quadrant2 photo = new Quadrant2();
        					photo.showImage();
						}
						else if(questions[i]== "210" || questions[i]== "225" || questions[i]== "240" || questions[i]=="7π/6" || questions[i]== "5π/4" || questions[i]== "4π/3"){
							Quadrant3 photo = new Quadrant3();
        					photo.showImage();
						}
						else if(questions[i]== "270" || questions[i]== "300" || questions[i]== "315" || questions[i]== "330" || questions[i]=="5π/3" || questions[i]== "7π/4" || questions[i]== "11π/6"){
							Quadrant4 photo = new Quadrant4();
        					photo.showImage();	
						}		
					}
					else if (pic.equals("no")){
						inputCorrect = true;
					}	
					else{
						System.out.println("Please enter a valid input. The options are 'yes' and 'no'.");
						pic = scanner.next();
					}
				}
				System.out.println("Input your answer.");
				String answer = scanner.next();
			    double answerr = eval(answer);
				TrigFunctions trig = new TrigFunctions();
				if (trigPrompt.contains("sine")){
					double correct = trig.sine(questions[i]);
					if(Math.abs(correct - answerr) <= .02){
						++score;
						System.out.println("Correct!");
					}
					else{
						System.out.println("Sorry, that answer is not correct.");
						System.out.println("The correct answer is "+ correct+".");
					}
				}
				else if (trigPrompt.contains("cosine")){
					double correct = trig.cosine(questions[i]);
					if(Math.abs(correct - answerr) <= .02){
						++score;
						System.out.println("Correct!");
					}
					else{
						System.out.println("Sorry, that answer is not correct.");
						System.out.println("The correct answer is "+ correct+".");
					}
				}
				else if (trigPrompt.contains("tangent")){
					double correct = trig.tangent(questions[i]);
					if(Math.abs(correct - answerr) <= .02){
						++score;
						System.out.println("Correct!");
					}
					else{
						System.out.println("Sorry, that answer is not correct.");
						System.out.println("The correct answer is "+ correct+".");
					}
				}
				else if (trigPrompt.contains("cosecant")){
					double correct = trig.cosecant(questions[i]);
					if(Math.abs(correct - answerr) <= .02){
						++score;
						System.out.println("Correct!");
					}
					else{
						System.out.println("Sorry, that answer is not correct.");
						System.out.println("The correct answer is "+ correct+".");
					}
				}
				else if (trigPrompt.contains("secant")){
					double correct =trig.secant(questions[i]);
					if (Math.abs(correct - answerr) <= .02){
						++score;
						System.out.println("Correct!");
					}
					else{
						System.out.println("Sorry, that answer is not correct.");
						System.out.println("The correct answer is "+ correct+".");
					}
				}
				else if (trigPrompt.contains("cotangent")){
					double correct = trig.cotangent(questions[i]);
					if(Math.abs(correct - answerr) <= .02){
						++score;
						System.out.println("Correct!");
					}
					else{
						System.out.println("Sorry, that answer is not correct.");
						System.out.println("The correct answer is "+ correct+".");
					}
				}
		}
		return score;
	}

	public void writeFile(int score, int numQuestions) throws IOException{
		Scanner scanner = new Scanner(System.in);
		String filename="";
		System.out.println("Enter the filename for saving your score for the game (for example, game.txt)");
 		String outputFileName = scanner.next();
 		name=outputFileName;
 		boolean apporover = false;
 		boolean input = false;
 		File file = new File(outputFileName);
 		if (file.isFile() && file.exists()){
	 		PrintWriter outputFile = new PrintWriter(outputFileName);                                      
	 		Scanner inputFile = new Scanner(file);
 			while (apporover==false){
	 			System.out.println("The file already exists. Would you like to override or append to the existing file?");
	 			scanner.nextLine();	
	 			String chooseFile = scanner.nextLine();
	 			if(chooseFile.equals("append")){
	 				apporover=true;
	 				FileWriter fwriter = new FileWriter(outputFileName, true);
	 				PrintWriter pw = new PrintWriter(fwriter);
	 				pw.println(score + "/" + numQuestions);
	 				pw.close();
	 			}
	 			else if(chooseFile.equals("override")){
	 				break;
	 			}
	 			else{
	 				System.out.println("You have not entered a valid response to the question. Please choose append or override.");
	 			}
	 		}
 		}
	 	PrintWriter outputFile = new PrintWriter(outputFileName);                                      
	 	Scanner inputFile = new Scanner(file);
		outputFile.println(score + "/" + numQuestions);
 		outputFile.close();

 		System.out.println("Would you like to view the file (yes or no)?");
 		String response = scanner.next();
 		while (input==false){
	 		if (response.equals("yes")){
	 			input = true;
	 			System.out.println();
				while(inputFile.hasNext()){
					filename = inputFile.nextLine();
					System.out.println(filename);
					}
				inputFile.close(); 
	 		}
	 		else if (response.equals("no")){
	 			input = true;
	 			break;
	 		}
	 		else{
	 			System.out.println("Please enter a valid input (yes or no)");
	 			response = scanner.next();
	 		}
 		}
	}

}