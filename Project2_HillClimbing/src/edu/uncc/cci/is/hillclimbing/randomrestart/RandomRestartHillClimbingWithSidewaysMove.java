package edu.uncc.cci.is.hillclimbing.randomrestart;

import java.util.ArrayList;
import java.util.List;

import edu.uncc.cci.is.hillclimbing.ProcessNode;
import edu.uncc.cci.is.hillclimbing.util.HillClimbingUtility;

public class RandomRestartHillClimbingWithSidewaysMove {

	public int numberOfQueens = 0;
	public int steps = 0;
	public int numberOfSideSteps = 0;
	public int noOfCorrectSteps = 0;
	public int noOfIncorrectSteps = 0;
	public int numberOfExecutionSuccess = 0;
	public int numberOfExecutionFailure = 0;
	public int noOfAttemptedRestarts = 0;
	public int restartCounter = 0;
	public int maximumSideWalkCount = 100;
	
	public boolean restartFlag = false;
	public boolean executionSuccessFlag = false;
	public boolean executionFailedFlag = false;
	
	public boolean modifyProcessNode = false;
	
	public int[] executionStatistics = new int[6];
	
	ProcessNode nextCorrectStep = new ProcessNode();
	
	List<ProcessNode> nextSteps = new ArrayList<ProcessNode>();
	
	public HillClimbingUtility hillClimbingUtility = new HillClimbingUtility();
	
	/**
	 * Random Restart Hill Climbing with Sideways Move
	 * 
	 * @param noOfQueens
	 * @param displayResult
	 * @return
	 */
	public int[] randomRestartHCSearchWithSidewayMove(int numberOfQueens) {

		this.numberOfQueens = numberOfQueens;

		ProcessNode processNode = hillClimbingUtility.formArbitraryArrangement(numberOfQueens);

		hillClimbingUtility.determineHeuristicCostValue(processNode);

	
			System.out.println("*** BEGIN STATE ***");
			hillClimbingUtility.displayResult(processNode);
		
		
		while (!executionSuccessFlag && !executionFailedFlag) {
		
			modifyProcessNode = false;

			//	determine possible next steps
			nextSteps = hillClimbingUtility.discoverNextSteps(processNode);

			//	find heuristic for next steps
			for (int p = 0; p < nextSteps.size(); p++) {
				hillClimbingUtility.determineHeuristicCostValue(nextSteps.get(p));
			}

			//	find the next correct step based on heuristics
			nextCorrectStep = hillClimbingUtility.discoverNextBestStep(nextSteps);

			if (nextCorrectStep.getHeuristicCost() == processNode.getHeuristicCost()) {
				nextCorrectStep = hillClimbingUtility.discoverNextBestStep(nextSteps);
			}
			
			if (processNode.getHeuristicCost() == 0) {
				
				noOfCorrectSteps = +steps;
				
				numberOfExecutionSuccess++;
				
				executionSuccessFlag = true;
				
				
					System.out.println("*** GOAL STATE ***");
					hillClimbingUtility.displayResult(processNode);
				
				
			} else if (nextCorrectStep.getHeuristicCost() < processNode.getHeuristicCost()) {
				
				numberOfSideSteps = 0;
				steps++;
				modifyProcessNode = true;
			} else if (nextCorrectStep.getHeuristicCost() == processNode.getHeuristicCost() && numberOfSideSteps < maximumSideWalkCount) {																													

				numberOfSideSteps++;
				steps++;
				modifyProcessNode = true;
			} else {

				processNode = hillClimbingUtility.formArbitraryArrangement(numberOfQueens);
				hillClimbingUtility.determineHeuristicCostValue(processNode);
				noOfAttemptedRestarts++;
				restartFlag = true;
			}
			if (modifyProcessNode) {
				processNode = nextCorrectStep;
			}
		}

		if (restartFlag) {
			restartCounter++;
		}

		//	collect execution statistics
		executionStatistics[0] = noOfCorrectSteps;
		
		executionStatistics[1] = numberOfExecutionSuccess;
		
		executionStatistics[2] = noOfIncorrectSteps;
		
		executionStatistics[3] = numberOfExecutionFailure;
		
		executionStatistics[4] = noOfAttemptedRestarts;
		
		executionStatistics[5] = restartCounter;

		return executionStatistics;
	}
	
}