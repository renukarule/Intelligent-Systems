package edu.uncc.cci.intelligentsystems;

public class GridNode {

	int totalCost;

	int level=0;

	GridNode parentNode = null;

	GridNodeState puzzleGridState;

	public GridNode(int totalCost, int level, GridNode parentNode, GridNodeState puzzleState) {
		super();
		this.totalCost = totalCost;
		this.level = level;
		this.parentNode = parentNode;
		this.puzzleGridState = puzzleState;
	}
	
	public GridNode(int level, GridNodeState puzzleState) {
		this.level = level;
		this.puzzleGridState = puzzleState;
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

	public GridNode getParentNode() {
		return parentNode;
	}

	public void setParentNode(GridNode parentNode) {
		this.parentNode = parentNode;
	}

	public GridNodeState getPuzzleState() {
		return puzzleGridState;
	}

	public void setPuzzleState(GridNodeState puzzleGridState) {
		this.puzzleGridState = puzzleGridState;
	}

}