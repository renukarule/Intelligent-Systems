package edu.uncc.cci.is.hillclimbing;

import java.util.ArrayList;
import java.util.List;

import edu.uncc.cci.is.hillclimbing.util.HillClimbingUtility;

public class HillClimbingWithSidewaysMove {

	public int noOfQueens = 0;
	public int maxSideWalkCount = 100;
	public int normalSteps = 0;
	public int numberOfCorrectSteps = 0;
	public int numberOfIncorrectSteps = 0;
	public int numberOfExecutionSuccess = 0;
	public int numberOfExecutionFailure = 0;
	public int numberOfSideWalkSteps = 0;
	
	public boolean executionSuccess = false;
	public boolean executionFailed = false;
	boolean modifyProcessNode = false;
	
	int[] executionStatistics = new int[4];
	
	ProcessNode nextCorrectStep = new ProcessNode();
	
	List<ProcessNode> nextSteps = new ArrayList<ProcessNode>();
	
	public HillClimbingUtility hillClimbingUtility = new HillClimbingUtility();
	
	/**
	 * Hill Climbing Algorithm with Sideways Move
	 * 
	 * @param noOfQueens
	 * @param displayResult
	 * @return
	 */
	public int[] hillClimbingSearchWithSideways(int noOfQueens) {
		
		this.noOfQueens = noOfQueens;

		ProcessNode processNode = hillClimbingUtility.formArbitraryArrangement(noOfQueens);

		hillClimbingUtility.determineHeuristicCostValue(processNode);

		
			System.out.println("*** BEGIN STATE ***");
			hillClimbingUtility.displayResult(processNode);
		
		
		while (!executionSuccess && !executionFailed) {
			
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
				
				numberOfCorrectSteps = +normalSteps;
				
				numberOfExecutionSuccess++;
				
				executionSuccess = true;
				
				
					System.out.println("*** FINAL STATE ***");
					hillClimbingUtility.displayResult(processNode);
				
				
			} else if (nextCorrectStep.getHeuristicCost() < processNode.getHeuristicCost()) {
				
				numberOfSideWalkSteps = 0;
				
				normalSteps++;
				
				modifyProcessNode = true;
				
			} else if (nextCorrectStep.getHeuristicCost() == processNode.getHeuristicCost()
					&& numberOfSideWalkSteps < maxSideWalkCount) {

				numberOfSideWalkSteps++;
				
				normalSteps++;
				
				modifyProcessNode = true;
				
			} else {
				
				numberOfExecutionFailure++;
				
				numberOfIncorrectSteps = +normalSteps;
				
				executionFailed = true;
				
			
					System.out.println("*** FLAT MINIMUM STATE ***");
					hillClimbingUtility.displayResult(processNode);
				
			}
			
			if (modifyProcessNode) {
				processNode = nextCorrectStep;
			}
		}

		//	collect execution statistics
		executionStatistics[0] = numberOfCorrectSteps;
		
		executionStatistics[1] = numberOfExecutionSuccess;
		
		executionStatistics[2] = numberOfIncorrectSteps;
		
		executionStatistics[3] = numberOfExecutionFailure;

		return executionStatistics;
	}
}