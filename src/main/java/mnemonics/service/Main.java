package mnemonics.service;

import java.util.ArrayList;
import java.util.List;
import java.util.logging.FileHandler;
import java.util.logging.Level;
import java.util.logging.Logger;
import java.util.logging.SimpleFormatter;

public class Main {

	static Logger logger = Logger.getLogger("MyLog");

	public static void main(String[] args) {

		initLogger();
		System.setProperty("java.util.logging.SimpleFormatter.format", "%5$s%6$s%n");
		String[] args2 = { "A,a,p,f,l,B,b,i,r,n,C,c,l,m,n,t,i,n", "Apfel,Birne,Clementine" };
		mnemonic(args2);
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

	public static void mnemonic(String[] args) {

		if (args.length != 2) {
			logger.info("Erwartet: a,b,c Apfel,Birne,Clementine");
			return;
		}

		String[] pVergebeneBuchstaben = args[0].split("\\,");
		ArrayList<Character> lVergebeneBuchstaben = new ArrayList<>();
		for (String lString : pVergebeneBuchstaben) {
			if (lString.length() == 1) {
				lVergebeneBuchstaben.add(lString.charAt(0));
			} else {
				logger.info("Erwartet: a,b,c");
				return;
			}
		}

		String[] pWoerter = args[1].split("\\,");
		ArrayList<Wort> lWoerter = new ArrayList<>();
		for (String pWort : pWoerter) {
			lWoerter.add(new Wort(pWort, lVergebeneBuchstaben));
		}

		logger.log(Level.INFO, "Vergebene Buchstaben: {0}", lVergebeneBuchstaben);
		logger.log(Level.INFO, "");

		int lCount = 0;
		boolean found = false;
		while (!found && lCount < lWoerter.size()) {
			logger.log(Level.INFO, "{0} Wort(e) entfernt", Integer.toString(lCount));
			logger.log(Level.INFO, "");
			found = solveOhneWoerter(lWoerter, lCount, found);
			lCount++;
		}

	}

	private static boolean solveOhneWoerter(List<Wort> pWoerter, int pKuerzeListeUm, boolean pFound) {

		if (pKuerzeListeUm == 0) {

			ArrayList<Loesung> lLoesungen = solve(pWoerter);
			if (!lLoesungen.isEmpty()) {
				pFound = true;
				logger.log(Level.INFO, "Anzahl Lösungen: {0}", Integer.toString(lLoesungen.size()));
				for (Loesung lLoesung : lLoesungen) {
					logger.log(Level.INFO, "{0}", lLoesung);
				}

			} else {
				logger.log(Level.INFO, "Keine Lösungen");
			}
			logger.log(Level.INFO, "");

		} else {

			ArrayList<List<Wort>> lGekuerzteListen = erzeugeWortListenMitEinemWortWeniger(pWoerter);
			for (List<Wort> lGekuerzteList : lGekuerzteListen) {
				pFound = solveOhneWoerter(lGekuerzteList, pKuerzeListeUm - 1, pFound);
			}

		}

		return pFound;
	}

	private static ArrayList<List<Wort>> erzeugeWortListenMitEinemWortWeniger(List<Wort> pWoerter) {

		ArrayList<List<Wort>> lGekuerzteListen = new ArrayList<>();

		for (int lI = 0; lI < pWoerter.size(); lI++) {
			if (lI == 0) {
				lGekuerzteListen.add(pWoerter.subList(1, pWoerter.size()));
			} else if (lI == pWoerter.size()) {
				lGekuerzteListen.add(pWoerter.subList(0, pWoerter.size() - 1));
			} else {
				List<Wort> lSubListA = pWoerter.subList(0, lI);
				List<Wort> lSubListB = pWoerter.subList(lI + 1, pWoerter.size());
				List<Wort> lSubListC = new ArrayList<>();
				lSubListC.addAll(lSubListA);
				lSubListC.addAll(lSubListB);
				lGekuerzteListen.add(lSubListC);
			}
		}
		return lGekuerzteListen;
	}

	private static ArrayList<Loesung> solve(List<Wort> pWoerter) {

		ArrayList<Loesung> lLoesungen = new ArrayList<>();

		if (pWoerter.isEmpty()) {
			return new ArrayList<>();
		} else if (pWoerter.size() == 1) {
			List<Mnemonic> lMnemonics = Mnemonic.getAll(pWoerter.get(0));
			for (Mnemonic lMnemonic : lMnemonics) {
				lLoesungen.add(new Loesung(lMnemonic));
			}
		} else {
			ArrayList<Loesung> lLoesungen1 = solve(pWoerter.subList(0, (pWoerter.size() + 1) / 2));
			ArrayList<Loesung> lLoesungen2 = solve(pWoerter.subList((pWoerter.size() + 1) / 2, pWoerter.size()));
			for (Loesung loesung1 : lLoesungen1) {
				for (Loesung loesung2 : lLoesungen2) {
					Loesung loesung = new Loesung(loesung1, loesung2);
					if (loesung.isValid()) {
						lLoesungen.add(loesung);
					}
				}
			}
		}
		return lLoesungen;
	}
}
