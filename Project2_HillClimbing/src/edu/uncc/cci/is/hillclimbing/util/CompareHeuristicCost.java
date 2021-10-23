package edu.uncc.cci.is.hillclimbing.util;

import java.util.Comparator;

import edu.uncc.cci.is.hillclimbing.ProcessNode;

public class CompareHeuristicCost implements Comparator<ProcessNode> {

	@Override
	public int compare(ProcessNode o1, ProcessNode o2) {
		
		if (o1.getHeuristicCost() < o2.getHeuristicCost()) {
			return -1;
		}
			
		if (o1.getHeuristicCost() > o2.getHeuristicCost()) {
			return 1;
		}
			
		return 0;
	}
	
}