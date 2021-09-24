package edu.uncc.cci.intelligentsystems;

public class PuzzleNode {

	int totalCost;

	int level=0;

	PuzzleNode parentNode = null;

	PuzzleState puzzleState;

	public PuzzleNode(int level, PuzzleState puzzleState) {
		this.level = level;
		this.puzzleState = puzzleState;
	}

	public PuzzleNode(int totalCost, int level, PuzzleNode parentNode, PuzzleState puzzleState) {
		super();
		this.totalCost = totalCost;
		this.level = level;
		this.parentNode = parentNode;
		this.puzzleState = puzzleState;
	}

	public int getTotalCost() {
		return totalCost;
	}

	public void setTotalCost(int totalCost) {
		this.totalCost = totalCost;
	}

	public int getLevel() {
		return level;
	}

	public void setLevel(int level) {
		this.level = level;
	}

	public PuzzleNode getParentNode() {
		return parentNode;
	}

	public void setParentNode(PuzzleNode parentNode) {
		this.parentNode = parentNode;
	}

	public PuzzleState getPuzzleState() {
		return puzzleState;
	}

	public void setPuzzleState(PuzzleState puzzleState) {
		this.puzzleState = puzzleState;
	}

}