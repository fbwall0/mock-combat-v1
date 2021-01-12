package com.techelevator.view;

import java.io.InputStream;
import java.io.OutputStream;
import java.io.PrintWriter;
import java.util.List;
import java.util.Scanner;

import com.techelevator.play.Spell;

public class SpellMenu {

	private PrintWriter out;
	private Scanner in;

	public SpellMenu(InputStream input, OutputStream output) {
		this.out = new PrintWriter(output);
		this.in = new Scanner(input);
	}

	public Object getChoiceFromOptions(List<Spell> spells) {
		Object choice = null;
		while(choice == null) {
			displayMenuOptions(spells);
			choice = getChoiceFromUserInput(spells);
		}
		return choice;
	}

	private Object getChoiceFromUserInput(List<Spell> spells) {
		Object choice = null;
		String userInput = in.nextLine();
		try {
			int selectedOption = Integer.valueOf(userInput);
			if(selectedOption > 0 && selectedOption <= spells.size() + 1) {
				choice = selectedOption - 1;
			}
		} catch(NumberFormatException e) {
			 // eat the exception -- means do not do anything
			 // an error message will be displayed below since choice will be null
		}
		if(choice == null) {
			out.println("\n*** " + userInput + " is not a valid spell ***\n");
		}
		return choice;
	}

	private void displayMenuOptions(List<Spell> spells) {
		out.println();
		for(int i = 0; i < spells.size(); i++) {
			int spellNum = i + 1;
		    Spell spell = spells.get(i);
			out.println(spellNum + ") " + spell.getName() + " - Action Cost: " + spell.getActionCost() + " - Mana Cost: " + spell.getManaCost());
		}
		out.println((spells.size() + 1) + ") Reconsider");
		
		out.print("\nPlease choose an spell >>> ");
		out.flush();
	}
}
