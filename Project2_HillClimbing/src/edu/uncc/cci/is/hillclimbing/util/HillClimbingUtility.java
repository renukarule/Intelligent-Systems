package edu.uncc.cci.is.hillclimbing.util;

import java.util.ArrayList;
import java.util.List;
import java.util.PriorityQueue;
import java.util.Random;

import edu.uncc.cci.is.hillclimbing.ProcessNode;

public class HillClimbingUtility {

	private static int SQUARE_HAS_QUEEN = 1;
	
	private static int SQUARE_HAS_NO_QUEEN = 0;

	/**
	 * Determine possible next steps for a given state.
	 * 
	 * @param processNode
	 * @return
	 */
	public List<ProcessNode> discoverNextSteps(ProcessNode processNode) {

		List<ProcessNode> nextStepsList = new ArrayList<ProcessNode>();

		int processNodeQueenCl; 
		int processNodeQueenRw;
		int nextNodeQueenRw;
		int nextNodeQueenCl;
		
		for (int cl = 0; cl < processNode.getPosition().length; cl++) {
			
			processNodeQueenRw = Integer.MAX_VALUE;
			
			processNodeQueenCl = cl;
			
			nextNodeQueenCl = cl;
			
			//	queen row position for processNode column
			for (int i = 0; i < processNode.getPosition().length; i++) {
				if (processNode.getPosition()[i][processNodeQueenCl] == SQUARE_HAS_QUEEN) {
					processNodeQueenRw = i;
					break;
				}
			}
			
			//	swap each row position of the processNode to create next / new possible arrangements
			for (int rw = 0; rw < processNode.getPosition().length; rw++) {
				if (rw != processNodeQueenRw && processNodeQueenRw != Integer.MAX_VALUE) {
					
					int[][] currentState = new int[processNode.getPosition().length][processNode.getPosition().length];
					
					passNodeStateData(processNode.getPosition(), currentState);
					
					nextNodeQueenRw = rw;
					
					currentState[processNodeQueenRw][processNodeQueenCl] = SQUARE_HAS_NO_QUEEN;
					currentState[nextNodeQueenRw][nextNodeQueenCl] = SQUARE_HAS_QUEEN;
					
					ProcessNode processNodeReplace = new ProcessNode();
					processNodeReplace.setPosition(currentState);
					nextStepsList.add(processNodeReplace);
				}
			}
		}
		
		return nextStepsList;
	}

	public void determineHeuristicCostValue(ProcessNode processNode) {

		int[][] processNodeState = new int[processNode.getPosition().length][processNode.getPosition().length];
		
		passNodeStateData(processNode.getPosition(), processNodeState);
		
		int[] queenRowPosition = new int[processNode.getPosition().length];
		int[] queenColumnPosition = new int[processNode.getPosition().length];
		
		int queenPosition = 0;
		
		for (int queenColumn = 0; queenColumn < processNode.getPosition().length; queenColumn++) {
			for (int queenRow = 0; queenRow < processNode.getPosition().length; queenRow++) {
				if (processNodeState[queenRow][queenColumn] == SQUARE_HAS_QUEEN) {
					queenRowPosition[queenPosition] = queenRow;
					queenColumnPosition[queenPosition] = queenColumn;
					queenPosition++;
				}
			}
		}

		int heuristicCostValue = 0;
		
		boolean noCalculation = true;
		
		for (int p = 0; p < queenRowPosition.length; p++) {
			for (int q = p + 1; q < queenRowPosition.length; q++) {

				noCalculation = true;

				if (queenRowPosition[p] == queenRowPosition[q]) {
					heuristicCostValue++;
				}

				if (queenColumnPosition[p] == queenColumnPosition[q]) {
					heuristicCostValue++;
				}

				if (Math.abs(queenRowPosition[p] - queenRowPosition[q]) == Math
						.abs(queenColumnPosition[p] - queenColumnPosition[q])) {
					heuristicCostValue++;
				}
			}
		}
		
		if (!noCalculation) {
			heuristicCostValue = Integer.MAX_VALUE;
		}
		
		processNode.setHeuristicCost(heuristicCostValue);
	}

	/**
	 * Transfer data from one state to another
	 * 
	 * @param processNodeStateFrom
	 * @param processNodeStateTo
	 */
	public void passNodeStateData(int[][] processNodeStateFrom, int[][] processNodeStateTo) {
		for (int p = 0; p < processNodeStateFrom.length; p++) {
			for (int q = 0; q < processNodeStateFrom.length; q++) {
				processNodeStateTo[p][q] = processNodeStateFrom[p][q];
			}
		}
	}

	/**
	 * Find the next best step from best heuristic
	 * 
	 * @param processNodeList
	 * @return
	 */
	public ProcessNode discoverNextBestStep(List<ProcessNode> processNodeList) {
		
		PriorityQueue<ProcessNode> processNodeQueue = new PriorityQueue<ProcessNode>(processNodeList.size(), new CompareHeuristicCost());
		
		for (int p = 0; p < processNodeList.size(); p++) {
			processNodeQueue.add(processNodeList.get(p));
		}
		
		List<ProcessNode> nextStepNodeList = new ArrayList<ProcessNode>();
		
		ProcessNode nextStepNode = new ProcessNode();
		
		nextStepNode = processNodeQueue.poll();
		
		int hcost = nextStepNode.getHeuristicCost();
		
		nextStepNodeList.add(nextStepNode);
		
		while (processNodeQueue.peek().getHeuristicCost() == hcost) {
			nextStepNodeList.add(processNodeQueue.poll());
		}
		
		Random random = new Random();
		
		int index = random.nextInt(nextStepNodeList.size());
		
		nextStepNode = nextStepNodeList.get(index);
		
		processNodeQueue.clear();
		
		return nextStepNode;
	}

	/**
	 * Determine the arbitrary arrangement
	 * 
	 * @param numberOfQueens
	 * @return
	 */
	public ProcessNode formArbitraryArrangement(int noOfQueens) {
		
		Random random = new Random();
		
		int[][] arbitraryArrangement = new int[noOfQueens][noOfQueens];
		
		ProcessNode processNode = new ProcessNode();
		
		for (int p = 0; p < noOfQueens; p++) {
			
			int number = random.nextInt(noOfQueens - 1);
			
			for (int q = 0; q < noOfQueens; q++) {
				if (q == number) {
					arbitraryArrangement[q][p] = SQUARE_HAS_QUEEN;
				} else {
					arbitraryArrangement[q][p] = SQUARE_HAS_NO_QUEEN;
				}
			}
		}
		
		processNode.setPosition(arbitraryArrangement);
		
		return processNode;
	}

	/**
	 * Display result
	 * 
	 * @param processNode
	 */
	public void displayResult(ProcessNode processNode) {
		
		for (int p = 0; p < processNode.getPosition().length; p++) {
			for (int q = 0; q < processNode.getPosition().length; q++) {
		
				String displayResult = "_";
				
				if ((processNode.getPosition())[p][q] == 1) {
					displayResult = "Q";
				}
				
				System.out.print("\t" + displayResult);
			}
			
			System.out.println();
		}
		
		System.out.println("------------------");
	}
	
	
}