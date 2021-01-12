package com.techelevator.play;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class PlayerCharacter{

	private String name;
	//private int lvl = 1;
	private int xp = 300; //uses xp as resource for increasing stats
	private int spentXp = 0; //can be used to determine strength of enemies encountered
	private int profBonus = 1; //increases all stats and damage, cost 3 times as much xp
	private int baseHp = 15;
	private int hpMax = baseHp;
	private int hp = hpMax;
	private int baseMana = 15;
	private int manaMax = baseMana;
	private int mana = manaMax;
	private int baseAC = 10;
	private int ac = baseAC + profBonus;
	private double actionPointMax = 1.5 + (double) ((profBonus - 1) / 2) / 2;
	private double actionPoints = actionPointMax; 
	//private Occupation occupation;
	//private String ancestry;
	private int hpRegen = 1;
	private int manaRegen = 1;
	

	public void resetActionPoints() {
		this.actionPoints = this.actionPointMax;
	}

	public void resetHp() {
		this.hp = this.hpMax;
	}

	public void resetMana() {
		this.mana = this.manaMax;
	}

	private List<Attack> attacks = new ArrayList<>();
	private Map<String, Integer> ability = new HashMap<String, Integer>();
	// str, mag, con for now
	//private List<Action> actions = new ArrayList<>(); //collection of known actions
	//private Map<Action, Integer> learnableActions = new HashMap<>(); //collection of skills that can be learned for xp
	private List<Spell> spells = new ArrayList<>(); //collection of known spells
	//private Map<Spell, Integer> learnableSpells = new HashMap<>(); //collection of spells that can be learned for xp

	public PlayerCharacter(String name) {
		this.name = name;
		ability.put("Str", 10);
		ability.put("Con", 10);
		ability.put("Mag", 10);
		ability.put("StrMod", (ability.get("Str") - 10) / 5);
		ability.put("ConMod", (ability.get("Con") - 10) / 5);
		ability.put("MagMod", (ability.get("Mag") - 10) / 5);
		Attack punch = new Attack("Punch", 0.5, "Bludgeoning", 1, 4, 0, false);
		Attack kick = new Attack("Kick", 1, "Bludgeoning", 1, 8, 0, false);
		Spell heal = new Spell("Heal", 1, "Holy", 1, 8, 3, true, 5, true);
		Spell flare = new Spell("Flare", 1, "Fire", 1, 8, 0, true, 8, false);
		attacks.add(punch);
		attacks.add(kick);
		spells.add(heal);
		spells.add(flare);
	}

	public String getName() {
		return name;
	}

	public int getXp() {
		return xp;
	}

	public int getProfBonus() {
		return profBonus;
	}

	public int getBaseHp() {
		return baseHp;
	}

	public int getHpMax() {
		return hpMax;
	}

	public int getHp() {
		return hp;
	}

	public int getBaseMana() {
		return baseMana;
	}

	public int getManaMax() {
		return manaMax;
	}

	public int getMana() {
		return mana;
	}

	public int getBaseAC() {
		return baseAC;
	}

	public int getAc() {
		return ac;
	}
	
	public int getHpRegen() {
		return hpRegen;
	}
	
	public int getManaRegen() {
		return manaRegen;
	}
	
	public double getActionPoints() {
		return actionPoints;
	}
	
	public List<Attack> getAttacks() {
		return attacks;
	}

	public List<Spell> getSpells() {
		return spells;
	}

	public void regenHp() {
		this.hp += this.hpRegen;
		if (this.hp > this.hpMax) {
			this.hp = this.hpMax;
		}
	}
	
	public void regenMana() {
		this.mana += this.manaRegen;
		if (this.mana > this.manaMax) {
			this.mana = this.manaMax;
		}
	}

	public Map<String, Integer> getAbility() {
		return ability;
	}

	public int addXp(int xpToAdd) {
		this.xp += xpToAdd;
		return this.xp;
	}
	

	public int addStr() {
		this.xp -= (ability.get("Str") - 9) * 100;
		this.spentXp += (ability.get("Str") - 9) * 100;
		// this.spentXp += (ability.get("Str") - 9) * 100;
		ability.put("Str", ability.get("Str") + 1);
		ability.put("StrMod", (ability.get("Str") - 10) / 2);
		return this.xp;
	}
	
	public int addCon() {
		this.xp -= (ability.get("Con") - 9) * 100;
		this.spentXp += (ability.get("Con") - 9) * 100;
		// this.spentXp += (ability.get("Con") - 9) * 100;
		ability.put("Con", ability.get("Con") + 1);
		ability.put("ConMod", (ability.get("Con") - 10) / 2);
		this.hpMax = this.baseHp + ability.get("ConMod") * 10;
		return this.xp;
	}
	
	public int addMag() {
		this.xp -= (ability.get("Mag") - 9) * 100;
		this.spentXp += (ability.get("Mag") - 9) * 100;
		// this.spentXp += (ability.get("Mag") - 9) * 100;
		ability.put("Mag", ability.get("Mag") + 1);
		ability.put("MagMod", (ability.get("Mag") - 10) / 2);
		this.manaMax = this.baseMana + ability.get("MagMod") * 10;
		return this.xp;
	}
	
	public int addProf() {
		this.xp -= this.profBonus * 300;
		this.spentXp += this.profBonus * 300;
		// this.spentXp += this.profBonus * 300;
		this.profBonus++;
		this.baseHp += 5;
		this.baseMana += 5;
		this.ac = baseAC + profBonus;
		this.hpMax = this.baseHp + ability.get("ConMod") * 10;
		this.manaMax = this.baseMana + ability.get("MagMod") * 10;
		this.actionPointMax = 1.5 + (double) ((profBonus - 1) / 2) / 2;
		return this.xp;
	}
	
	public int addAC() {
		this.xp -= (this.ac - 9) * 200;
		this.spentXp += (this.ac - 9) * 200;
		// this.spentXp += (this.ac - 9) * 200;
		this.baseAC++;
		this.ac = baseAC + profBonus;
		return this.xp;
	}
	
	public int addHpRegen() {
		this.xp -= this.hpRegen * this.hpRegen * 1000;
		this.spentXp += this.hpRegen * this.hpRegen * 1000;
		this.hpRegen++;
		return this.xp;
	}
	
	public int addManaRegen() {
		this.xp -= this.manaRegen * this.manaRegen * 1000;
		this.spentXp += this.manaRegen * this.manaRegen * 1000;
		this.manaRegen++;
		return this.xp;
	}
	
	public int getSpentXp() {
		return spentXp;
	}

	public int castSpell(Spell spell, Enemy target) {
		
		if (this.mana >= spell.getManaCost() && this.actionPoints >= spell.getActionCost()) {
			System.out.println("You cast " + spell.getName());
			this.actionPoints -= spell.getActionCost();
			this.mana -= spell.getManaCost();
			int damage = 0;
			if (spell.isTargetSelf()) {
				for (int i = 0; i < spell.getDamageDie1(); i++){
					damage += Math.random() * spell.getDieSize1() + 1;
				}
				damage += spell.getBonusDamage1() + this.ability.get("MagMod") + this.profBonus;
				this.hp += damage;
				if (this.hp > this.hpMax) {
					this.hp = this.hpMax;
				}
				System.out.println("You heal " + damage + " points of healing");
				
			}
			else {
				int toHitBase = (int) (Math.random() * 20) + 1;
				int toHit = toHitBase + this.ability.get("MagMod") + this.profBonus;
				System.out.println("Rolled a " + toHitBase + " for a " + toHit + " against target's AC of " + target.getAc());
				if (toHit > target.getAc()) {
					for (int i = 0; i < spell.getDamageDie1(); i++){
						damage += (int) (Math.random() * spell.getDieSize1()) + 1;
					}
					if (toHitBase == 20) {
						System.out.println("CRIT!!!");
						for (int i = 0; i < spell.getDamageDie1(); i++){
							damage += (int) (Math.random() * spell.getDieSize1()) + 1;
						}
					}
					else {
						System.out.println("Hit");
					}
					damage += spell.getBonusDamage1() + this.ability.get("MagMod") + this.profBonus;
					System.out.println("You've dealt " + damage + " points of " + spell.getDamageType1() + " damage");
				}
				else {
					System.out.println("Miss!");
				}
			}
			return damage; //returns damage dealt or healed
		}
		else if (this.mana < spell.getManaCost()){
			System.out.println("Not enough Mana");
		}
		else {
			System.out.println("Not enough Action Points");
		}
		return 0;
	}
	
	public int makeAttack(Attack attack, Enemy target) {
		if (this.actionPoints >= attack.getActionCost()) {
			System.out.println("You make a " + attack.getName() + " attack against " + target.getName());
			int damage = 0;
			this.actionPoints -= attack.getActionCost();
			int toHitBase = (int) (Math.random() * 20) + 1;
			int toHit = toHitBase + this.ability.get("StrMod") + this.profBonus;
			System.out.println("Rolled a " + toHitBase + " for a " + toHit + " against target's AC of " + target.getAc());
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
				damage += attack.getBonusDamage1() + this.ability.get("StrMod") + this.profBonus;
				System.out.println("You've dealt " + damage + " points of " + attack.getDamageType1() + " damage");
				return damage; //returns damage dealt
			}
			else {
				System.out.println("Miss!");
			}
		}
		else {
			System.out.println("Not enough Action Points");
		}
		return 0;
	}
	
	public void takeDamage(int damageTaken) {
		this.hp -= damageTaken;
	}
}
