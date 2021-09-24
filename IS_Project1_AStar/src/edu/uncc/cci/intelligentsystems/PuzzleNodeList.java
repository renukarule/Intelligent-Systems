package edu.uncc.cci.intelligentsystems;

import java.util.ArrayList;
import java.util.List;

public class PuzzleNodeList {

	ArrayList<PuzzleNode> nodeList = new ArrayList<PuzzleNode>();
	
	public PuzzleNode retrieveNodeFromNodeList() {
		
		int pointer = 0;
		
		int costOfNode = nodeList.get(0).totalCost;
		
		for (int p = 1; p < nodeList.size(); p++) {
			if (costOfNode > nodeList.get(p).totalCost) {
				costOfNode = nodeList.get(p).totalCost;
				pointer = p;
			}
		}
	
		PuzzleNode puzzleNode = nodeList.get(pointer);
		
		nodeList.remove(pointer);
		
		return puzzleNode;
	}
	
	public List<PuzzleNode> getNodeList() {
		return nodeList;
	}
	
	public void addElementToNodeList(PuzzleNode puzzleNode) {
		nodeList.add(puzzleNode);
	}
	
	public int getNodeListSize() {
		return nodeList.size();
	}
	
}