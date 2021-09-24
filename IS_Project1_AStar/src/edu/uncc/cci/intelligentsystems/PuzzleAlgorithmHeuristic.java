package edu.uncc.cci.intelligentsystems;

import java.util.ArrayList;
import java.util.Scanner;

public class PuzzleAlgorithmHeuristic {

	public static void main(String[] args) {

		int choiceHeuristic = 0;

		Boolean success = false;

		Integer[][] initialPuzzleArrangement = new Integer[3][3];

		Integer[][] goalPuzzleArrangement = new Integer[3][3];

		PuzzleNodeList nodeList = new PuzzleNodeList();

		PuzzleNodeList solvedPuzzle = new PuzzleNodeList();

		ArrayList<PuzzleNode> nodeVisitedList = new ArrayList<PuzzleNode>();

		System.out.println("Select a Heuristic\nPress\n1. Misplaced Tile Heuristic Implementation\n2. Manhattan Distance Heuristic\n");
		while (choiceHeuristic != 1 && choiceHeuristic != 2) {
			Scanner scanner = new Scanner(System.in);
			choiceHeuristic = scanner.nextInt();
		}

		System.out.println("Please input initial 8 Puzzle arrangement. Enter 0 for blank tile");

		Scanner scan = new Scanner(System.in);
		
		for (int p = 0; p < 3; p++) {
			for (int q = 0; q < 3; q++) {
				System.out.println("Please enter [" + p + "] [" + q + "] location of initial 8 Puzzle");
				initialPuzzleArrangement[p][q] = scan.nextInt();
			}
		}

		System.out.println("Please input expected final 8 Puzzle arrangement. Enter 0 for blank tile");

		Scanner scan2 = new Scanner(System.in);
		for (int p = 0; p < 3; p++) {
			for (int q = 0; q < 3; q++) {
				System.out.println("Please enter [" + p + "] [" + q + "] location of final 8 Puzzle");
				goalPuzzleArrangement[p][q] = scan2.nextInt();
			}
		}

		PuzzleState initialPuzzleState = new PuzzleState(initialPuzzleArrangement);

		PuzzleState goalPuzzleArraState = new PuzzleState(goalPuzzleArrangement);



		 PuzzleNode puzzleNode = new PuzzleNode(0, 0, null, initialPuzzleState);
		 puzzleNode.setParentNode(null);
		 nodeList.addElementToNodeList(puzzleNode);
		 nodeVisitedList.add(puzzleNode);

		if(solvedPuzzle.getNodeListSize()>31){
			System.out.println("This problem cannot be solved");

		}else {
			while (nodeList.getNodeListSize() > 0 && !success) {

				PuzzleNode processPuzzleNode=nodeList.retrieveNodeFromNodeList();

				if (initialPuzzleState.checkSimilarPuzzleState(processPuzzleNode.puzzleState, goalPuzzleArraState)) {

					PuzzleNode puzzleNodeTemp=processPuzzleNode;

					do {
						solvedPuzzle.addElementToNodeList(puzzleNodeTemp);
						puzzleNodeTemp=puzzleNodeTemp.getParentNode();
					} while (null != puzzleNodeTemp);


					System.out.println("Transition States");
					for (int p=solvedPuzzle.getNodeListSize() - 1; p >= 0; p--) {

						printStatesFollowed(solvedPuzzle.getNodeList().get(p).getPuzzleState().getPuzzleElementState());
					}
					System.out.println("Total moves required: " + (solvedPuzzle.getNodeListSize() - 1));
					System.out.println("Number of nodes expanded: " + nodeList.getNodeListSize());
					System.out.println("Number of nodes generated: " + nodeVisitedList.size());

					success=true;
					break;
				}

				Integer[] blankTile=processPuzzleNode.puzzleState.fetchBlankPuzzleTileLocation();

				ArrayList<String> adjacentTile=
						processPuzzleNode.puzzleState.findAdjacentNode(blankTile, processPuzzleNode.puzzleState.fetchCurrentElementState());


				for (String tile : adjacentTile) {

					PuzzleState puzzle=new PuzzleState(processPuzzleNode.getPuzzleState());
					puzzle.fetchBlankPuzzleTileLocation();

					Integer[] replaceTile=new Integer[2];

					String[] tilePosition=tile.split(" ");

					replaceTile[0]=Integer.parseInt(tilePosition[0]);

					replaceTile[1]=Integer.parseInt(tilePosition[1]);

					puzzle.swapBlankPuzzleTile(replaceTile);


					if (!checkDuplicateState(puzzle, nodeVisitedList)) {

						PuzzleNode puzzleNoe=new PuzzleNode(processPuzzleNode.level + 1, puzzle);

						puzzleNoe.parentNode=processPuzzleNode;

						puzzleNoe.totalCost=
								puzzleNoe.getPuzzleState().determineHeuristicValue(puzzle.fetchCurrentElementState(), goalPuzzleArraState.fetchCurrentElementState(), choiceHeuristic) + puzzleNoe.getLevel();


						nodeList.addElementToNodeList(puzzleNoe);

						nodeVisitedList.add(puzzleNoe);

					}
				}
			}
		}
	}
	
	public static Boolean checkDuplicateState(PuzzleState processState, ArrayList<PuzzleNode> vistedNodeList) {
		for (int p = 0; p < vistedNodeList.size(); p++) {
			Boolean foundNode = processState.checkSimilarPuzzleState(processState,
					vistedNodeList.get(p).getPuzzleState());
			if (true == foundNode) {
				return true;
			}

		}
		
		return false;
	}
	public static void printStatesFollowed(Integer[][] state){
		for (int i=0; i<3; i++){
			for (int j=0; j<3; j++){
				System.out.print(state[i][j] + " ");
			}
			System.out.println();
		}
		System.out.println(" ");
	}
}