package edu.uncc.cci.intelligentsystems;

import java.util.ArrayList;

public class PuzzleState {

	public Integer[][] puzzleElementState;

	public Integer[][] getPuzzleElementState() {
		return puzzleElementState;
	}

	public PuzzleState(Integer[][] puzzle) {

		puzzleElementState = new Integer[3][3];

		for (int p = 0; p < 3; p++) {
			for (int q = 0; q < 3; q++) {
				puzzleElementState[p][q] = puzzle[p][q];
				if (puzzle[p][q] == 0) {
					blankPuzzleTile[0] = p;
					blankPuzzleTile[1] = q;
				}
			}
		}
	}

	public PuzzleState(PuzzleState puzzleState) {

		blankPuzzleTile = puzzleState.blankPuzzleTile;

		puzzleElementState = new Integer[3][3];

		for (int p = 0; p < 3; p++) {
			for (int q = 0; q < 3; q++) {
				puzzleElementState[p][q] = puzzleState.fetchCurrentElementState()[p][q];
			}
		}
	}

	public Integer[][] fetchCurrentElementState() {
		return puzzleElementState;
	}

	public Integer[] blankPuzzleTile = { 0, 0 };

	public Integer[] fetchBlankPuzzleTileLocation() {

		for (int p = 0; p < 3; p++) {
			for (int q = 0; q < 3; q++) {
				if (0 == puzzleElementState[p][q]) {
					blankPuzzleTile[0] = p;
					blankPuzzleTile[1] = q;
				}
			}
		}

		return blankPuzzleTile;
	}

	public int determineHeuristicValue(Integer[][] previousState, Integer[][] nextState, int choiceHeuristic) {

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
	
	public void swapBlankPuzzleTile(Integer[] blankPuzzleTileDimension) {

		int swapTile = puzzleElementState[blankPuzzleTileDimension[0]][blankPuzzleTileDimension[1]];

		puzzleElementState[blankPuzzleTileDimension[0]][blankPuzzleTileDimension[1]] = puzzleElementState[blankPuzzleTile[0]][blankPuzzleTile[1]];

		puzzleElementState[blankPuzzleTile[0]][blankPuzzleTile[1]] = swapTile;

		blankPuzzleTile[0] = blankPuzzleTileDimension[0];

		blankPuzzleTile[1] = blankPuzzleTileDimension[1];
	}

	public Boolean checkSimilarPuzzleState(PuzzleState puzzleStateBefore, PuzzleState puzzleStateAfter) {

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
	
	public ArrayList<String> findAdjacentNode(Integer[] blankPuzzleTile, Integer[][] processArrangement) {

		ArrayList<String> nodes = new ArrayList<String>();

		for (int p = 0; p < 3; p++) {
			for (int q = 0; q < 3; q++) {
				if ((Math.abs(p - blankPuzzleTile[0]) + Math.abs(q - blankPuzzleTile[1])) == 1) {
					nodes.add(p + " " + q);
				}
			}
		}

		return nodes;
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
	
}