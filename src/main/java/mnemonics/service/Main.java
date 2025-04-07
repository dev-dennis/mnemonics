package mnemonics.service;

import java.util.logging.FileHandler;
import java.util.logging.Logger;
import java.util.logging.SimpleFormatter;

public class Main {

	static Logger logger = Logger.getLogger("MyLog");

	public static void main(String[] args) {

		initLogger();
		System.setProperty("java.util.logging.SimpleFormatter.format", "%5$s%6$s%n");
		String[] args2 = { "A,a,p,f,l,B,b,i,r,n,C,c,l,m,n,t,i,n", "Apfel,Birne,Clementine" };
		MnemonicsService.mnemonic(args2);
	}

	private static void initLogger() {

		try {
			FileHandler fh;
			fh = new FileHandler("Log.log");
			logger.addHandler(fh);
			fh.setFormatter(new SimpleFormatter());
		} catch (Exception ex) {
			ex.printStackTrace();
		}
	}

}
