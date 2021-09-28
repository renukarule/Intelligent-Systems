package edu.uncc.cci.intelligentsystems;

import java.util.ArrayList;

public class GridNodeState {

	public Integer[][] puzzleGridElementState;

	public Integer[][] getPuzzleElementState() {
		return puzzleGridElementState;
	}

	
	public GridNodeState(GridNodeState puzzleState) {

		emptyPuzzleGridTile = puzzleState.emptyPuzzleGridTile;

		puzzleGridElementState = new Integer[3][3];

		for (int p = 0; p < 3; p++) {
			for (int q = 0; q < 3; q++) {
				puzzleGridElementState[p][q] = puzzleState.fetchCurrentElementState()[p][q];
			}
		}
	}
	
	public GridNodeState(Integer[][] puzzle) {

		puzzleGridElementState = new Integer[3][3];

		for (int p = 0; p < 3; p++) {
			for (int q = 0; q < 3; q++) {
				puzzleGridElementState[p][q] = puzzle[p][q];
				if (puzzle[p][q] == 0) {
					emptyPuzzleGridTile[0] = p;
					emptyPuzzleGridTile[1] = q;
				}
			}
		}
	}


	public Integer[] emptyPuzzleGridTile = { 0, 0 };

	public Integer[] fetchEmptyPuzzleTileLocation() {

		for (int p = 0; p < 3; p++) {
			for (int q = 0; q < 3; q++) {
				if (0 == puzzleGridElementState[p][q]) {
					emptyPuzzleGridTile[0] = p;
					emptyPuzzleGridTile[1] = q;
				}
			}
		}

		return emptyPuzzleGridTile;
	}


	public Integer[][] fetchCurrentElementState() {
		return puzzleGridElementState;
	}
	public int calculateHeuristicValue(Integer[][] previousState, Integer[][] nextState, int choiceHeuristic) {

		int numberOfMisplacedTiles = 0;

		int manhattanDistanceValue = 0;

		Integer[] previousArrangement = new Integer[2];

		Integer[] processArrangement = new Integer[2];

		for (int p = 0; p < 3; p++) {
			for (int q = 0; q < 3; q++) {
				if ((previousState[p][q] == nextState[p][q]) || previousState[p][q] == 0) {
				} else {

					previousArrangement[0] = p;

					previousArrangement[1] = q;

					processArrangement = getProcessLocation(nextState, previousState[p][q]);

					manhattanDistanceValue = manhattanDistanceValue
							+ (Math.abs(previousArrangement[1] - processArrangement[1])
									+ Math.abs(previousArrangement[0] - processArrangement[0]));

					numberOfMisplacedTiles = numberOfMisplacedTiles + 1;
				}
			}
		}

		switch (choiceHeuristic) {
		case 1:
			return numberOfMisplacedTiles;
		case 2:
			return manhattanDistanceValue;
		default:
			return 0;
		}
	}
	
	
	public Boolean checkSimilarPuzzleGridState(GridNodeState puzzleStateBefore, GridNodeState puzzleStateAfter) {

		for (int p = 0; p < 3; p++) {
			for (int q = 0; q < 3; q++) {
				if (puzzleStateBefore.fetchCurrentElementState()[p][q] != puzzleStateAfter
						.fetchCurrentElementState()[p][q]) {
					return false;
				}
			}
		}

		return true;
	}
	

	public Integer[] getProcessLocation(Integer[][] processArrangement, int processValue) {

		Integer[] processLocation = new Integer[2];

		for (int p = 0; p < 3; p++) {
			for (int q = 0; q < 3; q++) {
				if (processArrangement[p][q].equals(processValue)) {
					processLocation[0] = p;
					processLocation[1] = q;
				}
			}
		}

		return processLocation;
	}
	
	public void swapEmptyPuzzleTile(Integer[] EmptyPuzzleTileDimension) {

		int swapTile = puzzleGridElementState[EmptyPuzzleTileDimension[0]][EmptyPuzzleTileDimension[1]];

		puzzleGridElementState[EmptyPuzzleTileDimension[0]][EmptyPuzzleTileDimension[1]] = puzzleGridElementState[emptyPuzzleGridTile[0]][emptyPuzzleGridTile[1]];

		puzzleGridElementState[emptyPuzzleGridTile[0]][emptyPuzzleGridTile[1]] = swapTile;

		emptyPuzzleGridTile[0] = EmptyPuzzleTileDimension[0];

		emptyPuzzleGridTile[1] = EmptyPuzzleTileDimension[1];
	}

	
	public ArrayList<String> findAdjacentNode(Integer[] emptyPuzzleTile, Integer[][] processArrangement) {

		ArrayList<String> nodes = new ArrayList<String>();

		for (int p = 0; p < 3; p++) {
			for (int q = 0; q < 3; q++) {
				if ((Math.abs(p - emptyPuzzleTile[0]) + Math.abs(q - emptyPuzzleTile[1])) == 1) {
					nodes.add(p + " " + q);
				}
			}
		}

		return nodes;
	}

	
}