package edu.uncc.cci.intelligentsystems;

import java.util.ArrayList;
import java.util.Scanner;

//Logic of 8 Puzzle problem solving with A* algorithm is covered in this class
public class AStarAlgorithmHeuristic {

	public static void main(String[] args) {
        //@Author Renu Karule 
		//Moving main function code to method executeAStarAlgorithm()
		
		AStarAlgorithmHeuristic algorithmheuristic = new AStarAlgorithmHeuristic();
		
		try {
			
			algorithmheuristic.executeAStarAlgorithm();
		
		}catch(Exception e){
			
			System.out.println("Something went wrong..."+e.getMessage());
			
		}
		
	}
	
	//@Author Added below method to move all main method code
	public void executeAStarAlgorithm() {
		
		//Execution of application starts here
		
		int heuristicChoice = 0;

		Boolean success = false;

		Integer[][] initialPuzzleGrid = new Integer[3][3];

		Integer[][] goalPuzzleGrid = new Integer[3][3];

		GridNodeStateList gridNodeList = new GridNodeStateList();

		GridNodeStateList solvedPuzzle = new GridNodeStateList();

		ArrayList<GridNode> visitedNodeList = new ArrayList<GridNode>();
		

		System.out.println("Select a Heuristic function\nPress\n1. Misplaced Tile Heuristic\n2. Manhattan Distance Heuristic\n");
		while (heuristicChoice != 1 && heuristicChoice != 2) {
			Scanner scanner = new Scanner(System.in);
			heuristicChoice = scanner.nextInt();
		}

		System.out.println("Please provide initial 8 Puzzle grid. Enter 0 for blank tile");

		Scanner scan = new Scanner(System.in);
		
		
		for (int p = 0; p < 3; p++) {
			for (int q = 0; q < 3; q++) {
				do{
					System.out.println("Please enter [" + p + "] [" + q + "] location of initial 8 Puzzle");
					initialPuzzleGrid[p][q] = scan.nextInt();
				}while((initialPuzzleGrid[p][q] < 0 || initialPuzzleGrid[p][q] > 9));   
			}
		}

		System.out.println("Please provide goal 8 Puzzle grid. Enter 0 for blank tile");

		Scanner scan2 = new Scanner(System.in);
		for (int p = 0; p < 3; p++) {
			for (int q = 0; q < 3; q++) {
				do{
					System.out.println("Please enter [" + p + "] [" + q + "] location of final 8 Puzzle");
					goalPuzzleGrid[p][q] = scan2.nextInt();
				}while((goalPuzzleGrid[p][q] < 0 || goalPuzzleGrid[p][q] > 9));
			}
		}
		GridNodeState initialPuzzleGridState = new GridNodeState(initialPuzzleGrid);
		GridNodeState goalPuzzleArrayGridState = new GridNodeState(goalPuzzleGrid);

		GridNode gridNode = new GridNode(0, 0, null, initialPuzzleGridState);
		gridNode.setParentNode(null);
		gridNodeList.addElementToNodeList(gridNode);
		visitedNodeList.add(gridNode);

		if(solvedPuzzle.getNodeListSize()>31){
			System.out.println("This problem cannot be solved");

		}else {
			while (gridNodeList.getNodeListSize() > 0 && !success) {

				GridNode processGridNode = gridNodeList.retrieveGridNodeFromNodeList();

				if (initialPuzzleGridState.checkSimilarPuzzleGridState(processGridNode.puzzleGridState, goalPuzzleArrayGridState)) {

					GridNode tempGridNode = processGridNode;

					do {
						solvedPuzzle.addElementToNodeList(tempGridNode);
						tempGridNode=tempGridNode.getParentNode();
					} while (null != tempGridNode);


					System.out.println("Transition States");
					for (int p=solvedPuzzle.getNodeListSize() - 1; p >= 0; p--) {

						displayPathFollowed(solvedPuzzle.getNodeList().get(p).getPuzzleState().getPuzzleElementState());
					}
					System.out.println("Total moves required: " + (solvedPuzzle.getNodeListSize() - 1));
					System.out.println("Number of nodes expanded: " + gridNodeList.getNodeListSize());
					System.out.println("Number of nodes generated: " + visitedNodeList.size());

					success=true;
					break;
				}

				Integer[] emptyTile = processGridNode.puzzleGridState.fetchEmptyPuzzleTileLocation();

				ArrayList<String> adjacentTile =
						processGridNode.puzzleGridState.findAdjacentNode(emptyTile, processGridNode.puzzleGridState.fetchCurrentElementState());


				for (String tile : adjacentTile) {

					GridNodeState grid=new GridNodeState(processGridNode.getPuzzleState());
					grid.fetchEmptyPuzzleTileLocation();

					Integer[] tileToReplace=new Integer[2];

					String[] tileIndex=tile.split(" ");

					tileToReplace[0]=Integer.parseInt(tileIndex[0]);

					tileToReplace[1]=Integer.parseInt(tileIndex[1]);

					grid.swapEmptyPuzzleTile(tileToReplace);


					if (!checkRepetitiveState(grid, visitedNodeList)) {

						GridNode puzzleNode=new GridNode(processGridNode.level + 1, grid);

						puzzleNode.parentNode=processGridNode;

						puzzleNode.totalCost=
								puzzleNode.getPuzzleState().calculateHeuristicValue(grid.fetchCurrentElementState(), goalPuzzleArrayGridState.fetchCurrentElementState(), heuristicChoice) + puzzleNode.getLevel();


						gridNodeList.addElementToNodeList(puzzleNode);

						visitedNodeList.add(puzzleNode);

					}
				}
			}
		}
		
	}
	public static Boolean checkRepetitiveState(GridNodeState processState, ArrayList<GridNode> vistedNodeList) {
		for (int p = 0; p < vistedNodeList.size(); p++) {
			Boolean foundNode = processState.checkSimilarPuzzleGridState(processState,
					vistedNodeList.get(p).getPuzzleState());
			if (true == foundNode) {
				return true;
			}

		}
		
		return false;
	}
	
	public static void displayPathFollowed(Integer[][] state){
		for (int i=0; i<3; i++){
			for (int j=0; j<3; j++){
				System.out.print(state[i][j] + " ");
			}
			
			System.out.println();
		}
		System.out.println(" ");
		System.out.println("");
		System.out.println("  | ");
		System.out.println("  | ");
		System.out.println(" \\\'/ \n");
	}
}