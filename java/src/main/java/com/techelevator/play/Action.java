package com.techelevator.play;

public class Action {

	private String name;
	private double actionCost;
	//private String description;
	
	
	public Action(String name, double actionCost) {
		this.name = name;
		this.actionCost = actionCost;
	}


	public String getName() {
		return name;
	}


	public double getActionCost() {
		return actionCost;
	}
	
	
	
}
