package com.jdc.mdy.planet_Sleep;

import java.util.Scanner;

public class PlanetTime {

	public static void main(String[] args) {
		
		int[]array= {6,15,8,17,10,19,12,21};
		String[]dates= {"Sunday","Monday","Tuesday",
				"Wednesday","Saturday","Thursday","Yarhoo","Friday"};
		
		Scanner sc=new Scanner(System.in);
		System.out.println("Please Type Birthday Name.\n1.Sun\n2.Mon\n3.Tue\n4.Wed"
				+ "\n5.Sat\n6.Thur,\n7.yaho(saturday for after 12 o'clock.)"
				+ "\n8.Fri");
		int name=sc.nextInt();
		System.out.println("Please Type Age.");
		int age=sc.nextInt();
		
		int index=name-1;		
		int lifes=0;
		
		while(lifes<age) {
			
			if(index>7) {
				index=0;
			}
			
			lifes+=array[index];
			index++;
		}
		
		System.out.println("You are now in :"+dates[index-1]+
				"\nand you will out when your age is "+lifes);
			
	}
}
