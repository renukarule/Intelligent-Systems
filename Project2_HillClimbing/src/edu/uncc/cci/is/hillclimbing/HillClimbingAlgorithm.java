package edu.uncc.cci.is.hillclimbing;

import java.util.ArrayList;
import java.util.List;

import edu.uncc.cci.is.hillclimbing.util.HillClimbingUtility;

public class HillClimbingAlgorithm {

	public int numberOfQueens = 0;
	public int steps = 0;
	public int noOfCorrectSteps = 0;
	public int noOfIncorrectSteps = 0;
	public int numberOfExecutionSuccess = 0;
	public int numberOfExecutionFailure = 0;
	
	public boolean executionSuccessFlag = false;
	public boolean executionFailedFlag = false;
	
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
	public int[] hillClimbingAlgorithm(int numberOfQueens) {
	
		this.numberOfQueens = numberOfQueens;

		ProcessNode processNode = hillClimbingUtility.formArbitraryArrangement(numberOfQueens);

		hillClimbingUtility.determineHeuristicCostValue(processNode);

		
			System.out.println("*** BEGIN STATE ***");
			hillClimbingUtility.displayResult(processNode);
		
		
		while (!executionSuccessFlag && !executionFailedFlag) {
			
			//	determine possible next steps
			nextSteps = hillClimbingUtility.discoverNextSteps(processNode);
			
			//	find heuristic for next steps
			for (int p = 0; p < nextSteps.size(); p++) {
				hillClimbingUtility.determineHeuristicCostValue(nextSteps.get(p));
			}

			//	find the next correct step based on heuristics
			nextCorrectStep = hillClimbingUtility.discoverNextBestStep(nextSteps);

			if (processNode.getHeuristicCost() == 0) {
				
				noOfCorrectSteps = +steps;
				
				numberOfExecutionSuccess++;
				
				executionSuccessFlag = true;
				
				
					System.out.println("*** FINAL STATE ***");
					hillClimbingUtility.displayResult(processNode);
			
				
			} else if (nextCorrectStep.getHeuristicCost() < processNode.getHeuristicCost()) {
				processNode = nextCorrectStep;
				steps++;
			} else {
				
				numberOfExecutionFailure++;

				noOfIncorrectSteps = +steps;
				
				executionFailedFlag = true;
				
				
					System.out.println("*** Local Minima State ***");
					System.out.println("Heuristic Value entered at: "+nextCorrectStep.getHeuristicCost());
					hillClimbingUtility.displayResult(processNode);
				
			}
		}

		//	collect execution statistics
		executionStatistics[0] = noOfCorrectSteps;
		
		executionStatistics[1] = numberOfExecutionSuccess;
		
		executionStatistics[2] = noOfIncorrectSteps;
		
		executionStatistics[3] = numberOfExecutionFailure;

		return executionStatistics;

	}
}