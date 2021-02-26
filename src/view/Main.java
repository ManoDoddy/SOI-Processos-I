package view;

import java.util.Scanner;

import controller.RedesController;

public class Main {

	public static void main(String[] args) {
		Scanner in = new Scanner(System.in);
		
		RedesController rc = new RedesController();
		String so = rc.so();
		
		int option;
		
		do {
			System.out.println("1 - IP\n"
							+"2 - Ping\n"
							+"0 - Sair\n");
			option = in.nextInt();
			
			if(option==1) {
				System.out.println(rc.ip(so));
			}else if(option==2) {
				System.out.println(rc.ping(so));
			}
			
		}while(option!=0);
		
	}

}
