package com.techelevator.view;

import java.io.InputStream;
import java.io.OutputStream;
import java.io.PrintWriter;
import java.util.List;
import java.util.Scanner;

import com.techelevator.play.Attack;

public class AttackMenu {

	private PrintWriter out;
	private Scanner in;

	public AttackMenu(InputStream input, OutputStream output) {
		this.out = new PrintWriter(output);
		this.in = new Scanner(input);
	}

	public Object getChoiceFromOptions(List<Attack> attacks) {
		Object choice = null;
		while(choice == null) {
			displayMenuOptions(attacks);
			choice = getChoiceFromUserInput(attacks);
		}
		return choice;
	}

	private Object getChoiceFromUserInput(List<Attack> attacks) {
		Object choice = null;
		String userInput = in.nextLine();
		try {
			int selectedOption = Integer.valueOf(userInput);
			if(selectedOption > 0 && selectedOption <= attacks.size() + 1) {
				choice = selectedOption - 1;
			}
		} catch(NumberFormatException e) {
			 // eat the exception -- means do not do anything
			 // an error message will be displayed below since choice will be null
		}
		if(choice == null) {
			out.println("\n*** " + userInput + " is not a valid attack ***\n");
		}
		return choice;
	}

	private void displayMenuOptions(List<Attack> attacks) {
		out.println();
		for(int i = 0; i < attacks.size(); i++) {
			int attackNum = i + 1;
		    Attack attack = attacks.get(i);
			out.println(attackNum + ") " + attack.getName() + " - Action Cost: " + attack.getActionCost());
		}
		out.println((attacks.size() + 1) + ") Reconsider");
		
		out.print("\nPlease choose an attack >>> ");
		out.flush();
	}
}
