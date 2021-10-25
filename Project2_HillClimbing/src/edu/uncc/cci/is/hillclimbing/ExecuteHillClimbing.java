package edu.uncc.cci.is.hillclimbing;

import java.util.Random;
import java.util.Scanner;

import edu.uncc.cci.is.hillclimbing.randomrestart.RandomRestartHillClimbingWithSidewaysMove;
import edu.uncc.cci.is.hillclimbing.randomrestart.RandomRestartHillClimbingWithoutSidewaysMove;

public class ExecuteHillClimbing {

	public static void main(String[] args) {

		Random random = new Random();
		
		int choice;
		int numberOfQueens;
		int timesExecuted = random.nextInt(100) + 100;

		float numberOfStepsForSuccess = 0.0f;
		float numberOfExecutionSuccess = 0.0f;
		float percentageOfExecutionSuccess = 0;
		float averageExecutionOfSuccess = 0;

		float numberOfStepsForFailure = 0.0f;
		float numberOfExecutionFailure = 0.0f;
		float percentageOfExecutionFailure = 0;
		float averageExecutionOfFailure = 0;

		float numberOfAttemptedRestarts = 0.0f;
		float finalRestartsPerformed = 0.0f;
		int numberOfExecutionStepsWithRestart = 0;

		
		
		boolean continueExecution = true;
		
		Scanner in = new Scanner(System.in);
		
		// Take number of queens as an input from user
		while (continueExecution) {
		System.out.println("\n\nPlease provide the number of Queens : ");

		

		numberOfQueens = in.nextInt();

		//	Minimum 4 queens are required to start
		if (numberOfQueens < 4) {
			System.out.println("Minimum 4 Queens are required to proceed.");
			System.exit(0);
		}

		
			
			System.out.println("Please select Hill Climbing variant: ");
			System.out.println("1. N - Queens Hill Climbing Search");
			System.out.println("2. N - Queens Hill Climbing search with Sideway Moves");
			System.out.println("3. N - Queens Random Restart Hill Climbing search without Sideway Moves");
			System.out.println("4. N - Queens Random Restart Hill Climbing search with Sideway Moves");
			
			choice = in.nextInt();

			switch (choice) {
			
			//	Hill Climbing Algorithm
			case 1:
				
				for (int i = 0; i < timesExecuted; i++) {
					
					HillClimbingAlgorithm steepestAscentObj = new HillClimbingAlgorithm();
					
					int[] solution = steepestAscentObj.hillClimbingAlgorithm(numberOfQueens);
					
					numberOfStepsForSuccess = numberOfStepsForSuccess + solution[0];
					
					numberOfExecutionSuccess = numberOfExecutionSuccess + solution[1];
					
					numberOfStepsForFailure = numberOfStepsForFailure + solution[2];
					
					numberOfExecutionFailure = numberOfExecutionFailure + solution[3];
				}
				
				System.out.println("");
				System.out.println("_______________________________________");
				System.out.println("N - Queens Problem with Hill Climbing");
				System.out.println("Number of Queens : " + numberOfQueens);
				System.out.println("Algorithm will execute " + timesExecuted + " times");
				System.out.println("Execution Statistics are : ");
				
				if (numberOfExecutionSuccess != 0) {
					percentageOfExecutionSuccess = (numberOfExecutionSuccess / timesExecuted) * 100;
					averageExecutionOfSuccess = numberOfStepsForSuccess / numberOfExecutionSuccess;
				}
				
				if (numberOfExecutionFailure != 0) {
					percentageOfExecutionFailure = (numberOfExecutionFailure / timesExecuted) * 100;
					averageExecutionOfFailure = numberOfStepsForFailure / numberOfExecutionFailure;
				}
				
				System.out.println("Percentage Success : " + String.format("%.2f", percentageOfExecutionSuccess) + "%");
				System.out.println("Percentage Failure : " + String.format("%.2f", percentageOfExecutionFailure) + "%");
				System.out.println("Average Steps for Success : " + String.format("%.2f", averageExecutionOfSuccess));
				System.out.println("Average Steps for Failure : " + String.format("%.2f", averageExecutionOfFailure));
				break;

			//	Hill Climbing with Sideways Move
			case 2:
				
				for (int p = 0; p < timesExecuted; p++) {
					
					HillClimbingWithSidewaysMove hillClimbingWithSidewaysMove = new HillClimbingWithSidewaysMove();
					
					int[] solution = hillClimbingWithSidewaysMove.hillClimbingSearchWithSideways(numberOfQueens);
					
					numberOfStepsForSuccess = numberOfStepsForSuccess + solution[0];
					
					numberOfExecutionSuccess = numberOfExecutionSuccess + solution[1];
					
					numberOfStepsForFailure = numberOfStepsForFailure + solution[2];
					
					numberOfExecutionFailure = numberOfExecutionFailure + solution[3];
				}
				
				System.out.println("");
				System.out.println("_______________________________________");
				System.out.println("N - Queens Problem with Hill Climbing with Sideways Move");
				System.out.println("Number of Queens : " + numberOfQueens);
				System.out.println("Algorithm will execute " + timesExecuted + " times");
				System.out.println("Execution Statistics are : ");
				
				if (numberOfExecutionSuccess != 0) {
					percentageOfExecutionSuccess = (numberOfExecutionSuccess / timesExecuted) * 100;
					averageExecutionOfSuccess = (numberOfStepsForSuccess / numberOfExecutionSuccess);
				}
				
				if (numberOfExecutionFailure != 0) {
					percentageOfExecutionFailure = (numberOfExecutionFailure / timesExecuted) * 100;
					averageExecutionOfFailure = (numberOfStepsForFailure / numberOfExecutionFailure);
				}
				
				System.out.println("Percentage Success : " + String.format("%.2f", percentageOfExecutionSuccess) + "%");
				System.out.println("Percentage Failure : " + String.format("%.2f", percentageOfExecutionFailure) + "%");
				System.out.println("Average Steps for Success : " + String.format("%.2f", averageExecutionOfSuccess));
				System.out.println("Average Steps for Failure : " + String.format("%.2f", averageExecutionOfFailure));

				break;
			
			//	Random Restart Hill Climbing Without Sideways Move
			case 3:
				
				numberOfExecutionStepsWithRestart = 0;
				
				for (int i = 0; i < timesExecuted; i++) {
					
					RandomRestartHillClimbingWithoutSidewaysMove randomRestartHillClimbingWithoutSidewaysMove = new RandomRestartHillClimbingWithoutSidewaysMove();
					
					int[] solution = randomRestartHillClimbingWithoutSidewaysMove.randomRestartHCSearchWithoutSidewayMove(numberOfQueens);
					
					numberOfStepsForSuccess = numberOfStepsForSuccess + solution[0];
					
					numberOfExecutionSuccess = numberOfExecutionSuccess + solution[1];
					
					numberOfStepsForFailure = numberOfStepsForFailure + solution[2];
					
					numberOfExecutionFailure = numberOfExecutionFailure + solution[3];
					
					numberOfAttemptedRestarts = numberOfAttemptedRestarts + solution[4];
					
					numberOfExecutionStepsWithRestart = numberOfExecutionStepsWithRestart + solution[5];
				}
				
				System.out.println("");
				System.out.println("_______________________________________");
				System.out.println("N - Queens Problem with Random Restart Hill Climbing without Sideways Move");
				System.out.println("Number of Queens : " + numberOfQueens);
				System.out.println("Algorithm will execute " + timesExecuted + " times");
				System.out.println("Execution Statistics are : ");
				
				if (numberOfExecutionSuccess != 0) {
					percentageOfExecutionSuccess = (numberOfExecutionSuccess / timesExecuted) * 100;
					averageExecutionOfSuccess = (numberOfStepsForSuccess / numberOfExecutionSuccess);
				}
				
				if (numberOfExecutionFailure != 0) {
					percentageOfExecutionFailure = (numberOfExecutionFailure / timesExecuted) * 100;
					averageExecutionOfFailure = (numberOfStepsForFailure / numberOfExecutionFailure);
				}
				
				if (numberOfExecutionStepsWithRestart != 0) {
					finalRestartsPerformed = (numberOfAttemptedRestarts / numberOfExecutionStepsWithRestart);
				}
				
				System.out.println("Percentage Success : " + String.format("%.2f", percentageOfExecutionSuccess) + "%");
				System.out.println("Percentage Failure : " + String.format("%.2f", percentageOfExecutionFailure) + "%");
				System.out.println("Average Steps for Success : " + String.format("%.2f", averageExecutionOfSuccess));
				System.out.println("Average Steps for Failure : " + String.format("%.2f", averageExecutionOfFailure));
				System.out.println("Average Restarts Done : " + String.format("%.2f", finalRestartsPerformed));
				break;
				
			//	Random Restart Hill Climbing With Sideways Move
			case 4:
				
				for (int i = 0; i < timesExecuted; i++) {
				
					RandomRestartHillClimbingWithSidewaysMove randomRestartHillClimbingWithSidewaysMove = new RandomRestartHillClimbingWithSidewaysMove();
					
					int[] solution = randomRestartHillClimbingWithSidewaysMove.randomRestartHCSearchWithSidewayMove(numberOfQueens);
					
					numberOfStepsForSuccess = numberOfStepsForSuccess + solution[0];
					
					numberOfExecutionSuccess = numberOfExecutionSuccess + solution[1];
					
					numberOfStepsForFailure = numberOfStepsForFailure + solution[2];
					
					numberOfExecutionFailure = numberOfExecutionFailure + solution[3];
					
					numberOfAttemptedRestarts = numberOfAttemptedRestarts + solution[4];
					
					numberOfExecutionStepsWithRestart = numberOfExecutionStepsWithRestart + solution[5];
				}
				
				System.out.println("");
				System.out.println("_______________________________________");
				System.out.println("N - Queens Problem with Random Restart Hill Climbing with Sideways Move");
				System.out.println("Number of Queens : " + numberOfQueens);
				System.out.println("Algorithm will execute " + timesExecuted + " times");
				System.out.println("Execution Statistics are : ");
				
				if (numberOfExecutionSuccess != 0) {
					percentageOfExecutionSuccess = (numberOfExecutionSuccess / timesExecuted) * 100;
					averageExecutionOfSuccess = (numberOfStepsForSuccess / numberOfExecutionSuccess);
				}
				
				if (numberOfExecutionFailure != 0) {
					percentageOfExecutionFailure = (numberOfExecutionFailure / timesExecuted) * 100;
					averageExecutionOfFailure = (numberOfStepsForFailure / numberOfExecutionFailure);
				}

				if (numberOfExecutionStepsWithRestart != 0) {
					finalRestartsPerformed = (numberOfAttemptedRestarts / numberOfExecutionStepsWithRestart);
				}
				
				System.out.println("Percentage Success : " + String.format("%.2f", percentageOfExecutionSuccess) + "%");
				System.out.println("Percentage Failure : " + String.format("%.2f", percentageOfExecutionFailure) + "%");
				System.out.println("Average Steps for Success : " + String.format("%.2f", averageExecutionOfSuccess));
				System.out.println("Average Steps for Failure : " + String.format("%.2f", averageExecutionOfFailure));
				System.out.println("Average Restarts Done : " + String.format("%.2f", finalRestartsPerformed));
				break;

			default:
				System.out.println("Your choice is invalid");
				break;
			}

			System.out.println("Do you want to continue execution?");
			System.out.println("Y : Yes");
			System.out.println("N : No");
			
			String continueChoice = in.next();
			
			if ("Y".equalsIgnoreCase(continueChoice)) {
				
				percentageOfExecutionSuccess = 0.0f;
				percentageOfExecutionFailure = 0.0f;
				
				averageExecutionOfSuccess = 0.0f;
				averageExecutionOfFailure = 0.0f;
				
				numberOfStepsForSuccess = 0.0f;
				numberOfExecutionSuccess = 0.0f;
				
				numberOfStepsForFailure = 0.0f;
				numberOfExecutionFailure = 0.0f;
				
				finalRestartsPerformed = 0.0f;
				numberOfAttemptedRestarts = 0.0f;
				numberOfExecutionStepsWithRestart = 0;
				
				continueExecution = true;
			} else {
				continueExecution = false;
			}
		}
		
		in.close();
	}
	
}