package edu.uncc.cci.is.hillclimbing;

import java.util.Arrays;

public class ProcessNode {

	private int[][] position;

	private int heuristicCost;

	public ProcessNode() {
		super();
	}

	public ProcessNode(int[][] position, int heuristicCost) {
		super();
		this.position = position;
		this.heuristicCost = heuristicCost;
	}

	public int[][] getPosition() {
		return position;
	}

	public void setPosition(int[][] position) {
		this.position = position;
	}

	public int getHeuristicCost() {
		return heuristicCost;
	}

	public void setHeuristicCost(int heuristicCost) {
		this.heuristicCost = heuristicCost;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + heuristicCost;
		result = prime * result + Arrays.deepHashCode(position);
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		ProcessNode other = (ProcessNode) obj;
		if (heuristicCost != other.heuristicCost)
			return false;
		if (!Arrays.deepEquals(position, other.position))
			return false;
		return true;
	}

	@Override
	public String toString() {
		return "ProcessNode [position=" + Arrays.toString(position) + ", heuristicCost=" + heuristicCost + "]";
	}
	
}