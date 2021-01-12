package com.techelevator.play;

import java.util.ArrayList;
import java.util.List;

public class Enemy {

	private String name;
	private List<Action> actions = new ArrayList<>();
	private List<Attack> attacks = new ArrayList<>();
	private int hpMax;
	private int hp;
 	private int ac;
	private int cr;
	private int xp;
	private int hitBonus;
	private double actionPointsMax; //may eventually be used if the enemy can regain actionPoints
	private double actionPoints;
	
	
	
	public Enemy(String name, List<Action> actions, int hpMax, int cr, int xp, int ac, double actionPoints, int hitBonus) {
		this.name = name;
		this.actions = actions;
		this.hpMax = hpMax;
		this.hp = hpMax;
		this.cr = cr;
		this.xp = xp;
		this.ac = ac;
		this.actionPointsMax = actionPoints;
		this.actionPoints = actionPoints;
		this.hitBonus = hitBonus;
	}
	
	public Enemy(String name, int hpMax, int cr, int xp, int ac, double actionPoints, int hitBonus, List<Attack> attacks) {
		this.name = name;
		this.attacks = attacks;
		this.hpMax = hpMax;
		this.hp = hpMax;
		this.cr = cr;
		this.xp = xp;
		this.ac = ac;
		this.actionPointsMax = actionPoints;
		this.actionPoints = actionPoints;
		this.hitBonus = hitBonus;
	}



	public int getHp() {
		return hp;
	}


	public void setHp(int hp) {
		this.hp = hp;
	}
	
	public void takeDamage(int damageTaken) {
		this.hp -= damageTaken;
	}


	public String getName() {
		return name;
	}


	public List<Action> getActions() {
		return actions;
	}


	public int getHpMax() {
		return hpMax;
	}


	public double getCr() {
		return cr;
	}


	public int getXp() {
		return xp;
	}


	public int getAc() {
		return ac;
	}
	
	public int getHitBonus() {
		return hitBonus;
	}

	public List<Attack> getAttacks() {
		return attacks;
	}
	
	public void resetHp() {
		hp = hpMax;
	}
	
	public void resetAP() {
		actionPoints = actionPointsMax;
	}

	public int makeAttack(Attack attack, PlayerCharacter target) {
		if (this.actionPoints >= attack.getActionCost()) {
			System.out.println(this.name + " attempts to attack with " + attack.getName());
			int damage = 0;
			this.actionPoints -= attack.getActionCost();
			int toHitBase = (int) (Math.random() * 20) + 1;
			int toHit = toHitBase + this.hitBonus;
			System.out.println("Rolled a " + toHit + " against target's AC of " + target.getAc());
			if (toHit >= target.getAc()) {
				for (int i = 0; i < attack.getDamageDie1(); i++){
					damage += (int) (Math.random() * attack.getDieSize1()) + 1;
				}
				if (toHitBase == 20) { //if it was a critical hit
					System.out.println("CRIT!!!");
					for (int i = 0; i < attack.getDamageDie1(); i++){
						damage += (int) (Math.random() * attack.getDieSize1()) + 1;
					}
				}
				else {
					System.out.println("Hit!");
				}
				damage += attack.getBonusDamage1();
				System.out.println("You've recieved " + damage + " points of " + attack.getDamageType1() + " damage");
				return damage; //returns damage dealt
			}
			else {
				System.out.println("Miss!");
			}
		}
		else {
//			System.out.println("Not enough Action Points");
		}
		return 0;
	}
	
}
