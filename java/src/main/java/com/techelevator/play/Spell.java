package com.techelevator.play;

public class Spell extends Attack {

	private int manaCost;
	private boolean targetSelf;
	private int xpCost;
	
	
	public Spell(String name, double actionCost, String damageType1, int damageDie1, int dieSize1, int bonusDamage1,
			boolean isMagic, int manaCost, boolean targetSelf) { //for starting spells
		super(name, actionCost, damageType1, damageDie1, dieSize1, bonusDamage1, isMagic);
		this.manaCost = manaCost;
		this.targetSelf = targetSelf;
	}
	
	public Spell(String name, double actionCost, String damageType1, int damageDie1, int dieSize1, int bonusDamage1,
			boolean isMagic, int manaCost, boolean targetSelf, int xpCost) { //for spells bought with xp
		this(name, actionCost, damageType1, damageDie1, dieSize1, bonusDamage1, isMagic, manaCost, targetSelf);
		this.xpCost = xpCost;
	}
	

	public int getManaCost() {
		return manaCost;
	}


	public boolean isTargetSelf() {
		return targetSelf;
	}
	
	public int getXpCost() {
		return xpCost;
	}
	
	
}
