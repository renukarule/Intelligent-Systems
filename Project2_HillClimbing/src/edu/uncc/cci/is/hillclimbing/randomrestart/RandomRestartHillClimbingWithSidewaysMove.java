package edu.uncc.cci.is.hillclimbing.randomrestart;

import java.util.ArrayList;
import java.util.List;

import edu.uncc.cci.is.hillclimbing.ProcessNode;
import edu.uncc.cci.is.hillclimbing.util.HillClimbingUtility;

public class RandomRestartHillClimbingWithSidewaysMove {

	public int noOfQueens = 0;
	public int normalSteps = 0;
	public int numberOfSideSteps = 0;
	public int numberOfCorrectSteps = 0;
	public int numberOfIncorrectSteps = 0;
	public int numberOfExecutionSuccess = 0;
	public int numberOfExecutionFailure = 0;
	public int numberOfAttemptedRestarts = 0;
	public int restartCounter = 0;
	public int maxSideWalkCount = 100;
	
	public boolean executionSuccess = false;
	public boolean executionFailed = false;
	public boolean restartFlag = false;
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
	public int[] randomRestartHillClimbingWithSidewayMove(int noOfQueens) {

		this.noOfQueens = noOfQueens;

		ProcessNode processNode = hillClimbingUtility.determineArbitraryArrangement(noOfQueens);

		hillClimbingUtility.findHeuristicCostValue(processNode);

	
			System.out.println("*** BEGIN STATE ***");
			hillClimbingUtility.displayResult(processNode);
		
		
		while (!executionSuccess && !executionFailed) {
		
			modifyProcessNode = false;

			//	determine possible next steps
			nextSteps = hillClimbingUtility.determineNextSteps(processNode);

			//	find heuristic for next steps
			for (int p = 0; p < nextSteps.size(); p++) {
				hillClimbingUtility.findHeuristicCostValue(nextSteps.get(p));
			}

			//	find the next correct step based on heuristics
			nextCorrectStep = hillClimbingUtility.determineNextBestStep(nextSteps);

			if (nextCorrectStep.getHeuristicCost() == processNode.getHeuristicCost()) {
				nextCorrectStep = hillClimbingUtility.determineNextBestStep(nextSteps);
			}
			
			if (processNode.getHeuristicCost() == 0) {
				
				numberOfCorrectSteps = +normalSteps;
				
				numberOfExecutionSuccess++;
				
				executionSuccess = true;
				
				
					System.out.println("*** GOAL STATE ***");
					hillClimbingUtility.displayResult(processNode);
				
				
			} else if (nextCorrectStep.getHeuristicCost() < processNode.getHeuristicCost()) {
				
				numberOfSideSteps = 0;
				normalSteps++;
				modifyProcessNode = true;
			} else if (nextCorrectStep.getHeuristicCost() == processNode.getHeuristicCost() && numberOfSideSteps < maxSideWalkCount) {																													

				numberOfSideSteps++;
				normalSteps++;
				modifyProcessNode = true;
			} else {

				processNode = hillClimbingUtility.determineArbitraryArrangement(noOfQueens);
				hillClimbingUtility.findHeuristicCostValue(processNode);
				numberOfAttemptedRestarts++;
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
		executionStatistics[0] = numberOfCorrectSteps;
		
		executionStatistics[1] = numberOfExecutionSuccess;
		
		executionStatistics[2] = numberOfIncorrectSteps;
		
		executionStatistics[3] = numberOfExecutionFailure;
		
		executionStatistics[4] = numberOfAttemptedRestarts;
		
		executionStatistics[5] = restartCounter;

		return executionStatistics;
	}
	
}