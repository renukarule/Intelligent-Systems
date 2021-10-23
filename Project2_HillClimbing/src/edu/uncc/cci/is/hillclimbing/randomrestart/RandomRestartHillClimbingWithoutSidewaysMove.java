package edu.uncc.cci.is.hillclimbing.randomrestart;

import java.util.ArrayList;
import java.util.List;

import edu.uncc.cci.is.hillclimbing.ProcessNode;
import edu.uncc.cci.is.hillclimbing.util.HillClimbingUtility;

public class RandomRestartHillClimbingWithoutSidewaysMove {

	public int noOfQueens = 0;
	public int normalSteps = 0;
	public int numberOfCorrectSteps = 0;
	public int numberOfIncorrectSteps = 0;
	public int numberOfExecutionSuccess = 0;
	public int numberOfExecutionFailure = 0;
	
	public int numberOfAttemptedRestarts = 0;
	public int restartCounter = 0;
	
	public boolean executionSuccess = false;
	public boolean executionFailed = false;
	public boolean restartFlag = false;
	
	public int[] executionStatistics = new int[6];
	
	ProcessNode nextCorrectStep = new ProcessNode();
	
	List<ProcessNode> nextSteps = new ArrayList<ProcessNode>();
	
	public HillClimbingUtility hillClimbingUtility = new HillClimbingUtility();
	
	/**
	 * Random Restart Without Sideways Move
	 * 
	 * @param noOfQueens
	 * @param displayResult
	 * @return
	 */
	public int[] randomRestartHillClimbingWithoutSidewayMove(int noOfQueens, boolean displayResult) {
	
		this.noOfQueens = noOfQueens;

		ProcessNode processNode = hillClimbingUtility.determineArbitraryArrangement(noOfQueens);

		hillClimbingUtility.findHeuristicCostValue(processNode);

		if (displayResult) {
			System.out.println("*** BEGIN STATE ***");
			hillClimbingUtility.displayResult(processNode);
		}
		
		while (!executionSuccess) {

			//	determine possible next steps
			nextSteps = hillClimbingUtility.determineNextSteps(processNode);

			//	find heuristic for next steps
			for (int p = 0; p < nextSteps.size(); p++) {
				hillClimbingUtility.findHeuristicCostValue(nextSteps.get(p));
			}

			//	find the next correct step based on heuristics
			nextCorrectStep = hillClimbingUtility.determineNextBestStep(nextSteps);

			if (processNode.getHeuristicCost() == 0) {

				numberOfCorrectSteps = +normalSteps;
				
				numberOfExecutionSuccess++;
				
				executionSuccess = true;
				
				if (displayResult) {
					System.out.println("*** FINAL STATE ***");
					hillClimbingUtility.displayResult(processNode);
				}
				
			} else if (nextCorrectStep.getHeuristicCost() < processNode.getHeuristicCost()) {
				
				processNode = nextCorrectStep;
				
				normalSteps++;
			} else {
				
				processNode = hillClimbingUtility.determineArbitraryArrangement(noOfQueens);
				
				hillClimbingUtility.findHeuristicCostValue(processNode);
				
				numberOfAttemptedRestarts++;
				
				restartFlag = true;
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