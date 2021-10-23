package edu.uncc.cci.is.hillclimbing;

import java.util.Random;
import java.util.Scanner;

import edu.uncc.cci.is.hillclimbing.randomrestart.RandomRestartHillClimbingWithSidewaysMove;
import edu.uncc.cci.is.hillclimbing.randomrestart.RandomRestartHillClimbingWithoutSidewaysMove;

public class ExecuteHillClimbing {

	public static void main(String[] args) {

		Random random = new Random();

		float finalNumberOfSuccessSteps = 0.0f;
		float finalNumberOfExecutionSuccess = 0.0f;
		float executionPercentageOfSuccess = 0;
		float executionAverageOfSuccess = 0;

		float finalNumberOfFailureSteps = 0.0f;
		float finalNumberOfExecutionFailure = 0.0f;
		float executionPercentageOfFailure = 0;
		float executionAverageOfFailure = 0;

		float finalNumberOfAttemptedRestarts = 0.0f;
		float totalRestartsPerformed = 0.0f;
		int finalNumberOfExecutionStepsWithRestart = 0;

		int choice;
		int totalNumberOfQueens;
		int timesExecuted = random.nextInt(400) + 100;
		
		boolean continueExecution = true;
		boolean displayResult = false;
		
		System.out.println("____________________________________________________________________");
		System.out.println("ITCS 6150 : Intelligent Systems");
		System.out.println("Project 2 : Hill Climbing Algorithm");
		System.out.println();
		System.out.println("Team Members");
		System.out.println("1. Apnav Poptani | apoptani@uncc.edu | 801137923");
		System.out.println("2. Sumit Kawale | skawale1@uncc.edu | 801135233");
		System.out.println("3. Abhishek Satpute | asatpute@uncc.edu | 801137929");
		System.out.println("____________________________________________________________________");
		System.out.println("\n\nPlease provide the number of Queens : ");

		Scanner in = new Scanner(System.in);

		totalNumberOfQueens = in.nextInt();

		//	Minimum 4 queens are required to start
		if (totalNumberOfQueens < 4) {
			System.out.println("Atleast 4 Queens are required.");
			System.exit(0);
		}

		System.out.println("Would you like to display result with execution statistics?");
		System.out.println("Yes : Y");
		System.out.println("No : N");

		if (in.next().equalsIgnoreCase("Y"))
			displayResult = true;

		while (continueExecution) {
			
			System.out.println("Enter your choice : ");
			System.out.println("1. N - Queens problem with Hill Climbing");
			System.out.println("2. N - Queens problem with Hill Climbing with Sideway Moves");
			System.out.println("3. N - Queens problem with Random Restart without Sideway Moves");
			System.out.println("4. N - Queens problem with Random Restart with Sideway Moves");
			
			choice = in.nextInt();

			switch (choice) {
			
			//	Hill Climbing Algorithm
			case 1:
				
				for (int i = 0; i < timesExecuted; i++) {
					
					HillClimbingAlgorithm steepestAscentObj = new HillClimbingAlgorithm();
					
					int[] solution = steepestAscentObj.hillClimbingAlgorithm(totalNumberOfQueens, displayResult);
					
					finalNumberOfSuccessSteps = finalNumberOfSuccessSteps + solution[0];
					
					finalNumberOfExecutionSuccess = finalNumberOfExecutionSuccess + solution[1];
					
					finalNumberOfFailureSteps = finalNumberOfFailureSteps + solution[2];
					
					finalNumberOfExecutionFailure = finalNumberOfExecutionFailure + solution[3];
				}
				
				System.out.println("");
				System.out.println("_______________________________________");
				System.out.println("N - Queens Problem with Hill Climbing");
				System.out.println("Number of Queens : " + totalNumberOfQueens);
				System.out.println("Algorithm will execute " + timesExecuted + " times");
				System.out.println("Execution Statistics are : ");
				
				if (finalNumberOfExecutionSuccess != 0) {
					executionPercentageOfSuccess = (finalNumberOfExecutionSuccess / timesExecuted) * 100;
					executionAverageOfSuccess = finalNumberOfSuccessSteps / finalNumberOfExecutionSuccess;
				}
				
				if (finalNumberOfExecutionFailure != 0) {
					executionPercentageOfFailure = (finalNumberOfExecutionFailure / timesExecuted) * 100;
					executionAverageOfFailure = finalNumberOfFailureSteps / finalNumberOfExecutionFailure;
				}
				
				System.out.println("Percentage Success : " + String.format("%.2f", executionPercentageOfSuccess) + "%");
				System.out.println("Percentage Failure : " + String.format("%.2f", executionPercentageOfFailure) + "%");
				System.out.println("Average Steps for Success : " + String.format("%.2f", executionAverageOfSuccess));
				System.out.println("Average Steps for Failure : " + String.format("%.2f", executionAverageOfFailure));
				break;

			//	Hill Climbing with Sideways Move
			case 2:
				
				for (int p = 0; p < timesExecuted; p++) {
					
					HillClimbingWithSidewaysMove hillClimbingWithSidewaysMove = new HillClimbingWithSidewaysMove();
					
					int[] solution = hillClimbingWithSidewaysMove.hillClimbingWithSideways(totalNumberOfQueens, displayResult);
					
					finalNumberOfSuccessSteps = finalNumberOfSuccessSteps + solution[0];
					
					finalNumberOfExecutionSuccess = finalNumberOfExecutionSuccess + solution[1];
					
					finalNumberOfFailureSteps = finalNumberOfFailureSteps + solution[2];
					
					finalNumberOfExecutionFailure = finalNumberOfExecutionFailure + solution[3];
				}
				
				System.out.println("");
				System.out.println("_______________________________________");
				System.out.println("N - Queens Problem with Hill Climbing with Sideways Move");
				System.out.println("Number of Queens : " + totalNumberOfQueens);
				System.out.println("Algorithm will execute " + timesExecuted + " times");
				System.out.println("Execution Statistics are : ");
				
				if (finalNumberOfExecutionSuccess != 0) {
					executionPercentageOfSuccess = (finalNumberOfExecutionSuccess / timesExecuted) * 100;
					executionAverageOfSuccess = (finalNumberOfSuccessSteps / finalNumberOfExecutionSuccess);
				}
				
				if (finalNumberOfExecutionFailure != 0) {
					executionPercentageOfFailure = (finalNumberOfExecutionFailure / timesExecuted) * 100;
					executionAverageOfFailure = (finalNumberOfFailureSteps / finalNumberOfExecutionFailure);
				}
				
				System.out.println("Percentage Success : " + String.format("%.2f", executionPercentageOfSuccess) + "%");
				System.out.println("Percentage Failure : " + String.format("%.2f", executionPercentageOfFailure) + "%");
				System.out.println("Average Steps for Success : " + String.format("%.2f", executionAverageOfSuccess));
				System.out.println("Average Steps for Failure : " + String.format("%.2f", executionAverageOfFailure));

				break;
			
			//	Random Restart Hill Climbing Without Sideways Move
			case 3:
				
				finalNumberOfExecutionStepsWithRestart = 0;
				
				for (int i = 0; i < timesExecuted; i++) {
					
					RandomRestartHillClimbingWithoutSidewaysMove randomRestartHillClimbingWithoutSidewaysMove = new RandomRestartHillClimbingWithoutSidewaysMove();
					
					int[] solution = randomRestartHillClimbingWithoutSidewaysMove.randomRestartHillClimbingWithoutSidewayMove(totalNumberOfQueens, displayResult);
					
					finalNumberOfSuccessSteps = finalNumberOfSuccessSteps + solution[0];
					
					finalNumberOfExecutionSuccess = finalNumberOfExecutionSuccess + solution[1];
					
					finalNumberOfFailureSteps = finalNumberOfFailureSteps + solution[2];
					
					finalNumberOfExecutionFailure = finalNumberOfExecutionFailure + solution[3];
					
					finalNumberOfAttemptedRestarts = finalNumberOfAttemptedRestarts + solution[4];
					
					finalNumberOfExecutionStepsWithRestart = finalNumberOfExecutionStepsWithRestart + solution[5];
				}
				
				System.out.println("");
				System.out.println("_______________________________________");
				System.out.println("N - Queens Problem with Random Restart Hill Climbing without Sideways Move");
				System.out.println("Number of Queens : " + totalNumberOfQueens);
				System.out.println("Algorithm will execute " + timesExecuted + " times");
				System.out.println("Execution Statistics are : ");
				
				if (finalNumberOfExecutionSuccess != 0) {
					executionPercentageOfSuccess = (finalNumberOfExecutionSuccess / timesExecuted) * 100;
					executionAverageOfSuccess = (finalNumberOfSuccessSteps / finalNumberOfExecutionSuccess);
				}
				
				if (finalNumberOfExecutionFailure != 0) {
					executionPercentageOfFailure = (finalNumberOfExecutionFailure / timesExecuted) * 100;
					executionAverageOfFailure = (finalNumberOfFailureSteps / finalNumberOfExecutionFailure);
				}
				
				if (finalNumberOfExecutionStepsWithRestart != 0) {
					totalRestartsPerformed = (finalNumberOfAttemptedRestarts / finalNumberOfExecutionStepsWithRestart);
				}
				
				System.out.println("Percentage Success : " + String.format("%.2f", executionPercentageOfSuccess) + "%");
				System.out.println("Percentage Failure : " + String.format("%.2f", executionPercentageOfFailure) + "%");
				System.out.println("Average Steps for Success : " + String.format("%.2f", executionAverageOfSuccess));
				System.out.println("Average Steps for Failure : " + String.format("%.2f", executionAverageOfFailure));
				System.out.println("Average Restarts Done : " + String.format("%.2f", totalRestartsPerformed));
				break;
				
			//	Random Restart Hill Climbing With Sideways Move
			case 4:
				
				for (int i = 0; i < timesExecuted; i++) {
				
					RandomRestartHillClimbingWithSidewaysMove randomRestartHillClimbingWithSidewaysMove = new RandomRestartHillClimbingWithSidewaysMove();
					
					int[] solution = randomRestartHillClimbingWithSidewaysMove.randomRestartHillClimbingWithSidewayMove(totalNumberOfQueens, displayResult);
					
					finalNumberOfSuccessSteps = finalNumberOfSuccessSteps + solution[0];
					
					finalNumberOfExecutionSuccess = finalNumberOfExecutionSuccess + solution[1];
					
					finalNumberOfFailureSteps = finalNumberOfFailureSteps + solution[2];
					
					finalNumberOfExecutionFailure = finalNumberOfExecutionFailure + solution[3];
					
					finalNumberOfAttemptedRestarts = finalNumberOfAttemptedRestarts + solution[4];
					
					finalNumberOfExecutionStepsWithRestart = finalNumberOfExecutionStepsWithRestart + solution[5];
				}
				
				System.out.println("");
				System.out.println("_______________________________________");
				System.out.println("N - Queens Problem with Random Restart Hill Climbing with Sideways Move");
				System.out.println("Number of Queens : " + totalNumberOfQueens);
				System.out.println("Algorithm will execute " + timesExecuted + " times");
				System.out.println("Execution Statistics are : ");
				
				if (finalNumberOfExecutionSuccess != 0) {
					executionPercentageOfSuccess = (finalNumberOfExecutionSuccess / timesExecuted) * 100;
					executionAverageOfSuccess = (finalNumberOfSuccessSteps / finalNumberOfExecutionSuccess);
				}
				
				if (finalNumberOfExecutionFailure != 0) {
					executionPercentageOfFailure = (finalNumberOfExecutionFailure / timesExecuted) * 100;
					executionAverageOfFailure = (finalNumberOfFailureSteps / finalNumberOfExecutionFailure);
				}

				if (finalNumberOfExecutionStepsWithRestart != 0) {
					totalRestartsPerformed = (finalNumberOfAttemptedRestarts / finalNumberOfExecutionStepsWithRestart);
				}
				
				System.out.println("Percentage Success : " + String.format("%.2f", executionPercentageOfSuccess) + "%");
				System.out.println("Percentage Failure : " + String.format("%.2f", executionPercentageOfFailure) + "%");
				System.out.println("Average Steps for Success : " + String.format("%.2f", executionAverageOfSuccess));
				System.out.println("Average Steps for Failure : " + String.format("%.2f", executionAverageOfFailure));
				System.out.println("Average Restarts Done : " + String.format("%.2f", totalRestartsPerformed));
				break;

			default:
				System.out.println("Your choice is invalid");
				break;
			}

			System.out.println("Continue execution?");
			System.out.println("Y : Yes");
			System.out.println("N : No");
			
			String continueChoice = in.next();
			
			if ("Y".equalsIgnoreCase(continueChoice)) {
				
				executionPercentageOfSuccess = 0.0f;
				executionPercentageOfFailure = 0.0f;
				
				executionAverageOfSuccess = 0.0f;
				executionAverageOfFailure = 0.0f;
				
				finalNumberOfSuccessSteps = 0.0f;
				finalNumberOfExecutionSuccess = 0.0f;
				
				finalNumberOfFailureSteps = 0.0f;
				finalNumberOfExecutionFailure = 0.0f;
				
				totalRestartsPerformed = 0.0f;
				finalNumberOfAttemptedRestarts = 0.0f;
				finalNumberOfExecutionStepsWithRestart = 0;
				
				continueExecution = true;
			} else {
				continueExecution = false;
			}
		}
		
		in.close();
	}
	
}