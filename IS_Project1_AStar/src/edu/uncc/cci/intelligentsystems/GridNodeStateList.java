package edu.uncc.cci.intelligentsystems;

import java.util.ArrayList;
import java.util.List;


public class GridNodeStateList {

	ArrayList<GridNode> nodeList = new ArrayList<GridNode>();
	
	public GridNode retrieveGridNodeFromNodeList() {
		
		int index = 0;
		
		int costOfNode = nodeList.get(0).totalCost;
		
		for (int p = 1; p < nodeList.size(); p++) {
			if (costOfNode > nodeList.get(p).totalCost) {
				costOfNode = nodeList.get(p).totalCost;
				index = p;
			}
		}
	
		GridNode puzzleNode = nodeList.get(index);
		
		nodeList.remove(index);
		
		return puzzleNode;
	}
	
	public List<GridNode> getNodeList() {
		return nodeList;
	}
	
	public void addElementToNodeList(GridNode puzzleNode) {
		nodeList.add(puzzleNode);
	}
	
	public int getNodeListSize() {
		return nodeList.size();
	}
	
}