package com.jdc.mdy.maharbote;

import java.util.Arrays;
import java.util.Scanner;

public class MaharBoteDemo {

	public static void main(String[] args) {
		
		String[]mahar= {"Binga","Mayana","Ahtun","Thike","Yaza","Puti","Adipiti"};
		int []modulo= {1,4,0,3,6,2,5};
		
		Scanner sc=new Scanner(System.in);
		System.out.println("Please Select Your Birthday Name ?");
		System.out.println("1.Sunday\n2.Monday\n3.Tuesday\n4.Wednesday\n5.Thursday\n6.Friday\n7.Saturday");
		int bName=sc.nextInt();
		System.out.println("Please Type Your Birthday Myanmar ?");
		int dob=sc.nextInt();
		
		int x=bName-1;
		int y=dob%7;
		int[]temp=new int[mahar.length];
		int a=0;
		
		for(int i=0;i<modulo.length;i++) {
			
			if(y==modulo[i]) {
				
//				temp[]=mahar[i];
//				System.out.println(i);
			}
		}
		
		for(int z=0;z<7;z++) {
//			int y=a+z;
//			if(y==7) {
//				y=0;
//			}
//			temp[z]=modulo[y];
//			break;
		}
		
		for(int b:temp) {
			System.out.println(b);
		}
	}
}
