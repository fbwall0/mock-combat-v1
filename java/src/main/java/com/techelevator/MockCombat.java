package com.techelevator;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

import com.techelevator.play.Action;
import com.techelevator.play.Attack;
import com.techelevator.play.Enemy;
import com.techelevator.play.PlayerCharacter;
import com.techelevator.play.Spell;
import com.techelevator.view.AttackMenu;
import com.techelevator.view.Menu; // Menu processing class
import com.techelevator.view.SpellMenu;

public class MockCombat { // Class representing the MenuCLI process to be used


	private final static String MAIN_MENU_OPTION_1 = "New Game";
	private final static String MAIN_MENU_OPTION_2 = "Load Game";
	private final static String MAIN_MENU_OPTION_EXIT = "Exit";
	private final static String[] MAIN_MENU_OPTIONS = { MAIN_MENU_OPTION_1, MAIN_MENU_OPTION_2, MAIN_MENU_OPTION_EXIT };
	
	private final static String PLAY_MENU_OPTION_1 = "Fight";
	private final static String PLAY_MENU_OPTION_2 = "Boost";
	private final static String PLAY_MENU_OPTION_3 = "Save";
	private final static String PLAY_MENU_OPTION_EXIT = "Exit";
	private final static String[] PLAY_MENU_OPTIONS = { PLAY_MENU_OPTION_1, PLAY_MENU_OPTION_2, PLAY_MENU_OPTION_3, PLAY_MENU_OPTION_EXIT };
	
	private final static String FIGHT_MENU_OPTION_1 = "Use an Action";
	private final static String FIGHT_MENU_OPTION_2 = "Cast a Spell";
	private final static String FIGHT_MENU_OPTION_EXIT = "End Turn";
	private final static String[] FIGHT_MENU_OPTIONS = { FIGHT_MENU_OPTION_1, FIGHT_MENU_OPTION_2, FIGHT_MENU_OPTION_EXIT};
	
	
	private final static String LEVEL_UP_MENU_OPTION_1 = "Increase Strength";
	private final static String LEVEL_UP_MENU_OPTION_2 = "Increase Constitution";
	private final static String LEVEL_UP_MENU_OPTION_3 = "Increase Magical Ability";
	private final static String LEVEL_UP_MENU_OPTION_4 = "Increase Increase Proficiency";
	private final static String LEVEL_UP_MENU_OPTION_5 = "Increase AC";
	private final static String LEVEL_UP_MENU_OPTION_6 = "Increase Health Regneration";
	private final static String LEVEL_UP_MENU_OPTION_7 = "Increase Mana Regeneration";
	private final static String LEVEL_UP_MENU_OPTION_EXIT = "Exit";
	
//	{ PLAY_MENU_OPTION_1, PLAY_MENU_OPTION_2, PLAY_MENU_OPTION_3, PLAY_MENU_OPTION_EXIT };
	
	private static List<Enemy> enemies = new ArrayList<>();
	
	private static Menu menu = new Menu(System.in, System.out);
	private static AttackMenu attackMenu = new AttackMenu(System.in, System.out);
	private static SpellMenu spellMenu = new SpellMenu(System.in, System.out);
	
	private static PlayerCharacter pc;
	private static Enemy enemy;
	
	public static void main(String[] args) throws FileNotFoundException {

		enemies = generateEnemies(enemies);
		
		Scanner userInput = new Scanner(System.in);

		boolean shouldLoop = true;
		while (shouldLoop) { // Loop while loop control variable is true

			String choice = (String) menu.getChoiceFromOptions(MAIN_MENU_OPTIONS);
			System.out.println(choice + " was selected");
			
			
			switch (choice) {
				case MAIN_MENU_OPTION_1: {
					System.out.println("Welcome to the game!");// process for option 1 choice - good place for a method call
					System.out.print("Please enter a name for your character: ");
					String name = userInput.nextLine();
					pc = new PlayerCharacter(name);
					play(pc);
					break;
				}
				case MAIN_MENU_OPTION_2: {
					System.out.println("Welcome to the game!");// process for option 1 choice - good place for a method call
					pc = loadPc(userInput);
					play(pc);
					break;
				}
				case MAIN_MENU_OPTION_EXIT: {
					// do any end of loop processing
					shouldLoop = false;
					break;
				}
			}

		}
		userInput.close();

	}

	private static List<Enemy> generateEnemies(List<Enemy> enemies) {
		List<Attack> goblinAttacks = new ArrayList<>();
		goblinAttacks.add(new Attack("Claw", 0.5, "Slashing", 1, 4, 1, false));
		goblinAttacks.add(new Attack("Bite", 1, "Piercing", 1, 6, 2, false));
		enemies.add(new Enemy("Goblin", 15, 0, 100, 12, 1, 1, goblinAttacks));
		
		List<Attack> koboldAttacks = new ArrayList<>();
		koboldAttacks.add(new Attack("Claw", 0.5, "Slashing", 1, 4, 2, false));
		koboldAttacks.add(new Attack("Spear", 1, "Piercing", 1, 8, 1, false));
		koboldAttacks.add(new Attack("Bite", 1, "Piercing", 1, 6, 2, false));
		enemies.add(new Enemy("Kobold",  20, 1, 150, 13, 1.5, 2, koboldAttacks));
		
		List<Attack> banditActions = new ArrayList<>();
		banditActions.add(new Attack("Dagger", 0.5, "Piercing", 1, 4, 2, false));
		banditActions.add(new Attack("Sword", 1, "Slashing", 1, 8, 1, false));
		banditActions.add(new Attack("Sucker Punch", 1.5, "Bludgeoning", 2, 6, 1, false));
		enemies.add(new Enemy("Bandit", 30, 2, 250, 14, 2, 3, banditActions));
		
		// add higher CR enemies later
		
		return enemies;
	}

	private static void play(PlayerCharacter pc) throws FileNotFoundException {
		boolean shouldLoop = true;
		while (shouldLoop) { // Loop while loop control variable is true

			String choice = (String) menu.getChoiceFromOptions(PLAY_MENU_OPTIONS);
			
			switch (choice) {
				case PLAY_MENU_OPTION_1: {
					boolean win = fight();
					if (win) {
						pc.resetHp();
						pc.resetMana();
					}
					else {
						shouldLoop = false;
					}
					break;
				}
				case PLAY_MENU_OPTION_2: {
					levelUp();					
					break;
				}
				case PLAY_MENU_OPTION_3: {
					savePc();
					break;
				}
				case PLAY_MENU_OPTION_EXIT: {
					// do any end of loop processing
					shouldLoop = false;
					break;
				}
			}
		}
		
	}

	private static boolean fight() {
		System.out.println("Tentatively implemented!");
		enemy = randomEnemy();
		pc.resetHp();
		pc.resetMana();
		System.out.println("You have encountered a " + enemy.getName());
		while (true) {
			pcTurn();
			pc.regenHp();
			pc.regenMana();
			if (enemy.getHp() <= 0) {
				pc.addXp(enemy.getXp());
				enemy.resetHp(); //makes it so the next version of the enemy isn't already dead
				break;
			}
			enemyTurn();
			if (pc.getHp() <= 0) {
				System.out.println("You have fallen");
				return false;
			}
		}
		return true;
	}
	
	private static void pcTurn() {
		boolean shouldLoop = true;
		pc.resetActionPoints();
		System.out.println("It is your turn. \nYou have " + pc.getHp() + " hp and " + pc.getMana() + " mana remaining\nThe " + enemy.getName() + " has " + enemy.getHp() + " hp remaining");
		while (shouldLoop) {
			System.out.println("You have " + pc.getActionPoints() + " remaining action points");
			String choice = (String) menu.getChoiceFromOptions(FIGHT_MENU_OPTIONS);
			
			switch (choice) {
				case FIGHT_MENU_OPTION_1: {
					System.out.println("Choose an attack");// process for option 1 choice - good place for a method call
					Integer choice2 = (Integer) attackMenu.getChoiceFromOptions(pc.getAttacks());
					if (choice2 == pc.getAttacks().size() ) {
						break;
					}
					int damage = pc.makeAttack(pc.getAttacks().get(choice2), enemy);
					enemy.takeDamage(damage);
					break;
				}
				case FIGHT_MENU_OPTION_2: {
					System.out.println("Choose a spell.  Current Mana: " + pc.getMana());// process for option 1 choice - good place for a method call
					Integer choice2 = (Integer) spellMenu.getChoiceFromOptions(pc.getSpells());
					if (choice2 == pc.getSpells().size() ) {
						break;
					}
					int damage = pc.castSpell(pc.getSpells().get(choice2), enemy);
					enemy.takeDamage(damage);
					break;
				}
				case FIGHT_MENU_OPTION_EXIT: {
					// do any end of loop processing
					System.out.println("You've ended your turn");
					shouldLoop = false;
					break;
				}
			}
			if (enemy.getHp() <= 0) {
				System.out.println("You have defeated the " + enemy.getName());
				break;
			}
		}
	}


	private static void enemyTurn() {
		enemy.resetAP();
		for (int i = 0; i < 3; i++) {
			int j = (int) (Math.random() * enemy.getAttacks().size());
			int damage = enemy.makeAttack((Attack) enemy.getAttacks().get(j), pc);
			pc.takeDamage(damage);
		}
	}

	private static Enemy randomEnemy() {
		double bigCr = 0.0;
		List<Integer> bigCrIndex = new ArrayList<>();
		for (int i = 0; i < enemies.size(); i++) {
			if (enemies.get(i).getCr() > bigCr) {
				bigCr = enemies.get(i).getCr();
			}
		}
		for (int i = 0; i < enemies.size(); i++) {
			if (enemies.get(i).getCr() == bigCr) {
				bigCrIndex.add(i);
			}
		}
		if (bigCr < pc.getSpentXp() / 1000) { // if no enemies are strong enough, randomly pull from the strongest ones
			int i = (int) (Math.random() * bigCrIndex.size());
			return enemies.get(bigCrIndex.get(i));
		}
		while (true) {
			int ix = (int) (Math.random() * enemies.size());
			if (enemies.get(ix).getCr() >= pc.getSpentXp() / 2000 && enemies.get(ix).getCr() <= pc.getSpentXp() / 1000) { // if the enemy is of suitable level
				return enemies.get(ix);
			}
		}
	}

	private static void levelUp() {
		boolean shouldLoop = true;
		while (shouldLoop) {
			List<String> levelUpMenuOptions = levelUpOptions();
			
			int xp = pc.getXp();
			System.out.println("You have " + xp + " xp to spend");
			String choice = (String) menu.getChoiceFromOptions(levelUpMenuOptions.toArray()); //to do: make a variant menu specifically for leveling up
			
			switch (choice) {
				case LEVEL_UP_MENU_OPTION_1: {
					System.out.println("You have spent " + (pc.getAbility().get("Str") - 9) * 100 + " XP to increase your Strength");
					pc.addStr();
					break;
				}
				case LEVEL_UP_MENU_OPTION_2: {
					System.out.println("You have spent " + (pc.getAbility().get("Con") - 9) * 100 + " XP to increase your Constitution");
					pc.addCon();
					break;
				}
				case LEVEL_UP_MENU_OPTION_3: {
					System.out.println("You have spent " + (pc.getAbility().get("Mag") - 9) * 100 + " XP to increase your Magic");
					pc.addMag();
					break;
				}
				case LEVEL_UP_MENU_OPTION_4: {
					System.out.println("You have spent " + pc.getProfBonus() * 300 + " XP to increase your Proficiency");
					pc.addProf();
					break;
				}
				case LEVEL_UP_MENU_OPTION_5: {
					System.out.println("You have spent " + pc.getAc() * 200 + " XP to increase your AC");
					pc.addAC();
					break;
				}
				case LEVEL_UP_MENU_OPTION_6: {
					System.out.println("You have spent " + pc.getHpRegen() * 1000 + " XP to increase your Health Regeneration");
					pc.addHpRegen();
					break;
				}
				case LEVEL_UP_MENU_OPTION_7: {
					System.out.println("You have spent " + pc.getManaRegen() * 1000 + " XP to increase your Mana Regeneration");
					pc.addManaRegen();
					break;
				}
				case LEVEL_UP_MENU_OPTION_EXIT: {
					// do any end of loop processing
					shouldLoop = false;
					break;
				}
			}
			
		}
		

	}
	
	private static List<String> levelUpOptions() {
		List<String> levelUpMenuOptions = new ArrayList<>();
		if (pc.getXp() >= (pc.getAbility().get("Str") - 9) * 100) {
			levelUpMenuOptions.add(LEVEL_UP_MENU_OPTION_1);			
		}
		if (pc.getXp() >= (pc.getAbility().get("Con") - 9) * 100) {
			levelUpMenuOptions.add(LEVEL_UP_MENU_OPTION_2);
		}
		if (pc.getXp() >= (pc.getAbility().get("Mag") - 9) * 100) {
			levelUpMenuOptions.add(LEVEL_UP_MENU_OPTION_3);
		}
		if (pc.getXp() >= pc.getProfBonus() * 300) {
			levelUpMenuOptions.add(LEVEL_UP_MENU_OPTION_4);
		}
		if (pc.getXp() >= pc.getAc() * 200) {
			levelUpMenuOptions.add(LEVEL_UP_MENU_OPTION_5);
		}
		if (pc.getXp() >= pc.getHpRegen() * pc.getHpRegen() * 1000) {
			levelUpMenuOptions.add(LEVEL_UP_MENU_OPTION_6);
		}
		if (pc.getXp() >= pc.getManaRegen() * pc.getManaRegen() * 1000) {
			levelUpMenuOptions.add(LEVEL_UP_MENU_OPTION_7);
		}
		//will eventually have variable options based on new learnable spells and attacks
		
		levelUpMenuOptions.add(LEVEL_UP_MENU_OPTION_EXIT);
		
		return levelUpMenuOptions;
	}

	private static PlayerCharacter loadPc(Scanner userInput) {
		System.out.println("Sorry, not implemented yet\nPlease enter a new character name");
		String name = userInput.nextLine();
		pc = new PlayerCharacter(name);
		return pc;
	}
	
	private static void savePc(){
		Scanner userInput = new Scanner(System.in);
		
		while (true) {
			System.out.print("Please enter a save file name >>> ");
			String name = userInput.nextLine();
			try {
				File saveFile = new File("savefiles/" + name + ".txt");
				String overwrite = "y";
				if (saveFile.exists()) {
					System.out.print("This save file already exists. Do you want to overwrite? Y/N >>>");
					overwrite = userInput.nextLine();
				}
				if (overwrite.toLowerCase().equals("y")) {
					PrintWriter saver = new PrintWriter(saveFile);
					saver.println(pc.getName());
					saver.println(pc.getXp());
					saver.println(pc.getSpentXp());
					saver.println(pc.getProfBonus());
					saver.println(pc.getBaseHp());
					saver.println(pc.getBaseMana());
					saver.println(pc.getBaseAC());
					saver.println(pc.getHpRegen());
					saver.println(pc.getManaRegen());
					List<Attack> attacks = pc.getAttacks();
					List<Spell> spells = pc.getSpells();
					for (Attack attack : attacks) {
						saver.println(attack.getName() + "|" + attack.getActionCost() + "|" + attack.getDamageType1() + "|" + attack.getDamageDie1() + "|" + attack.getDieSize1() + "|" + attack.getBonusDamage1() + "|" + attack.isMagic());
					}
					saver.println("***");
					for (Spell spell : spells) {
						saver.println(spell.getName() + "|" + spell.getActionCost() + "|" + spell.getDamageType1() + "|" + spell.getDamageDie1() + "|" + spell.getDieSize1() + "|" + spell.getBonusDamage1() + "|" + spell.getManaCost() + "|" + spell.isTargetSelf());
					}
				}
				
			}
			catch (FileNotFoundException e){
				System.out.println("Invalid file path");
			}
			
			
		}
	}
	
}
