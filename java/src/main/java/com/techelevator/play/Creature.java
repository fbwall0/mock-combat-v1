package com.techelevator.play;

import java.util.ArrayList;
import java.util.List;

public class Creature {

	private String name;
	private int hpMax;
	private int hp = hpMax;
	private int ac;
	
	public Creature(String name, int hpMax, int ac) {
		this.name = name;
		this.hpMax = hpMax;
		this.ac = ac;
	}

	
}
