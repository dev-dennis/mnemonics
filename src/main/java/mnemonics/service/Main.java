package mnemonics.service;

import java.util.logging.Logger;

public class Main {

	static Logger logger = Logger.getLogger("MyLog");

	public static void main(String[] args) {

		System.setProperty("java.util.logging.SimpleFormatter.format", "%5$s%6$s%n");
		String[] args2 = { "A,a,p,f,l,B,b,i,r,n,C,c,l,m,n,t,i,n", "Apfel,Birne,Clementine,Haus" };
		MnemonicsService.mnemonic(args2);
	}

}
