package com.skilldistillery.makechange;

public class MakeChange {
	
	public static void main(String[] args) {
		int[] billsCoins = {20*100, 10*100, 5*100, 1*100, 25, 10, 5, 1};
		String     bills = "Twenties:,Tens:    ,Fives:   ,Dollars: ,";
		String     coins = "Quarters:,Dimes:   ,Nickels: ,Pennies: ";
		String[] nameLUT = (bills + coins).split(",");
		int    amountDue = normalizedDueFromUser();
		
		displayChangeCount(amountDue, billsCoins, nameLUT);
	}
	
	// Output is expressed in pennies to be comparable to units in billsCoins[]
	public static int normalizedDueFromUser() {
		java.util.Scanner sc = new java.util.Scanner(System.in);
		
		System.out.println("Please enter price of the item:");
		float askingPrice = sc.nextFloat();

		System.out.println("Please enter amount received from customer: ");
		float customerGave = sc.nextFloat();
		sc.close();
		
		// avoid future floating point issues by rounding now
		return Math.round(100*customerGave - 100*askingPrice);
	}
	
	// presumes billsCoins[] is sorted from high to low values, like [10 5 1]
	public static void displayChangeCount(int amountDue, int[] billsCoins, String[] nameLUT) {
		if (amountDue < 0) {
			System.out.println("Customer has not provided enough money.");
			return;
		}
		if (amountDue == 0) {
			System.out.println("Customer has provided exact change.");
			return;
		}
		System.out.println("Customer needs the following: ");
		
		// the core algorithm: take the maximum amount at each step
		int current;
		for (int i = 0; i < billsCoins.length; i++) {
			current = amountDue / billsCoins[i];
			amountDue %= billsCoins[i];
			if (current != 0) {
				System.out.println(nameLUT[i] + " " + current);
			}
		}
	}
}
