package com.jdc.mdy.lotteryLuck;

import javax.swing.JFrame;
import javax.swing.JOptionPane;

import javafx.application.Application;
import javafx.stage.Stage;

public class LotteryLuck {

	public static void main(String[] args) {
		// by salay U punya
		// luck formula =>(((mdob+sign)%43)+age)%7
		// mdob must be your myanmar year
		// sign must be your planet sign of year
		// age must be your age
		// if result is o and 5 you will win and 0 should share with other and 5 shold
		// try onlyone

		// y planet sign of year=>{12,6,15,8,17,19,21,10}

		JFrame j = new JFrame();

		String ask = "";
		do {
			
			String name = JOptionPane.showInputDialog(j, "Please Type your name !");

			int mdob = Integer.parseInt(JOptionPane.showInputDialog(j, "Please Type your birthday !"));

			int sign = getPlanetYear(JOptionPane.showInputDialog(j,
					"Please select One \n1.Sunday\n2.Monday\n3.Tuesday\n4.Wednesday\n5.Thursday\n6.Friday\n7.Saturday\n8.Rahu"));

			int age = Integer.parseInt(JOptionPane.showInputDialog(j, "Please Type your age !"));
			JOptionPane.showMessageDialog(j, getCalculate(name, mdob, sign, age));
		} while (JOptionPane.showConfirmDialog(j, "do you want to run again") == 0);

	}

	// This is for planet of year
	static int getPlanetYear(String s) {

		switch (s) {
		case "1":
			return 6;
		case "2":
			return 15;
		case "3":
			return 8;
		case "4":
			return 17;
		case "5":
			return 19;
		case "6":
			return 21;
		case "7":
			return 10;
		case "8":
			return 12;

		}

		return 0;
	}

	static String getCalculate(String s, int x, int y, int z) {

		switch ((((x + y) % 43) + z) % 7) {

		case 0:
		case 5:
			return s + ", You can win big prize lottery \n" + suggestionNum(x);
		case 1:
		case 6:
			return s + ", You can win small prize lottery \n" + suggestionNum(x);
		default:
			return s + "You shold try next year !";
		}

	}

	static String suggestionNum(int x) {

		switch (x % 7) {
		case 1:
			return "start with 7 and end with 5 or start with 6 and end with 5";
		case 2:
			return "start with 1 and end with 6 or start with 6 and end with 7";
		case 3:
			return "start with 2 and end with 7 or start with 5 and end with 7";
		case 4:
			return "start with 3 and end with 1 or start with 2 and end with 1";
		case 5:
			return "start with 4 and end with 2 or start with 3 and end with 2";
		case 6:
			return "start with 5 and end with 3 or start with 3 and end with 4";
		default:
			return "start with 6 and end with 4 or start with 5 and end with 4";
		}
	}

}
