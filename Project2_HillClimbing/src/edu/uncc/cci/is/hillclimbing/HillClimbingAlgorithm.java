package edu.uncc.cci.is.hillclimbing;

import java.util.ArrayList;
import java.util.List;

import edu.uncc.cci.is.hillclimbing.util.HillClimbingUtility;

public class HillClimbingAlgorithm {

	public int noOfQueens = 0;
	public int normalSteps = 0;
	public int numberOfCorrectSteps = 0;
	public int numberOfIncorrectSteps = 0;
	public int numberOfExecutionSuccess = 0;
	public int numberOfExecutionFailure = 0;
	
	public boolean executionSuccess = false;
	public boolean executionFailed = false;
	
	public int[] executionStatistics = new int[4];
	
	ProcessNode nextCorrectStep = new ProcessNode();
	
	public HillClimbingUtility hillClimbingUtility = new HillClimbingUtility();
	
	List<ProcessNode> nextSteps = new ArrayList<ProcessNode>();
	
	/**
	 * HillClimb Algorithm without Sideways Move
	 * 
	 * @param noOfQueens
	 * @param displayResult
	 * @return
	 */
	public int[] hillClimbingAlgorithm(int noOfQueens) {
	
		this.noOfQueens = noOfQueens;

		ProcessNode processNode = hillClimbingUtility.determineArbitraryArrangement(noOfQueens);

		hillClimbingUtility.findHeuristicCostValue(processNode);

		
			System.out.println("*** BEGIN STATE ***");
			hillClimbingUtility.displayResult(processNode);
		
		
		while (!executionSuccess && !executionFailed) {
			
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
				
				
					System.out.println("*** FINAL STATE ***");
					hillClimbingUtility.displayResult(processNode);
			
				
			} else if (nextCorrectStep.getHeuristicCost() < processNode.getHeuristicCost()) {
				processNode = nextCorrectStep;
				normalSteps++;
			} else {
				
				numberOfExecutionFailure++;

				numberOfIncorrectSteps = +normalSteps;
				
				executionFailed = true;
				
				
					System.out.println("*** Local Minima State ***");
					hillClimbingUtility.displayResult(processNode);
				
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