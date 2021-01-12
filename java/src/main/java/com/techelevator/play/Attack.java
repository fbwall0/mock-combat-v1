package com.techelevator.play;

public class Attack extends Action{

	
	private String damageType1;
	//private String damageType2 = "";
	private int damageDie1;
	//private int damageDie2 = 0;
	private int dieSize1;
	//private int dieSize2;
	private int bonusDamage1;
	//private int bonusDamage2;
	//private boolean isRanged;
	private boolean isMagic;
	//private boolean hasCooldown;
	//private int cooldown;
	
	public Attack(String name, double actionCost, String damageType1, int damageDie1, int dieSize1, int bonusDamage1,
			boolean isMagic) {
		super(name, actionCost);
		this.damageType1 = damageType1;
		this.damageDie1 = damageDie1;
		this.dieSize1 = dieSize1;
		this.bonusDamage1 = bonusDamage1;
		this.isMagic = isMagic;
	}

	public String getDamageType1() {
		return damageType1;
	}

	public int getDamageDie1() {
		return damageDie1;
	}

	public int getDieSize1() {
		return dieSize1;
	}

	public int getBonusDamage1() {
		return bonusDamage1;
	}

	public boolean isMagic() {
		return isMagic;
	}
	
	
	
	
}
