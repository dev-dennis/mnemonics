package mnemonics.service;

import java.util.ArrayList;
import java.util.List;
import java.util.logging.FileHandler;
import java.util.logging.Formatter;
import java.util.logging.LogRecord;
import java.util.logging.Logger;

public class Main {

	static Logger logger = Logger.getLogger("MyLog");

	public static void main(String[] args) {

		initLogger();

		MnemonicGenerator.main(args);
	}

	private static void initLogger() {

		try {
			FileHandler fh;
			fh = new FileHandler("Log.log");
			logger.addHandler(fh);
			fh.setFormatter(new MyCustomFormatter());
		} catch (Exception ex) {
			ex.printStackTrace();
		}
	}

	private static class MyCustomFormatter extends Formatter {

		@Override
		public String format(LogRecord record) {

			StringBuffer sb = new StringBuffer();
			sb.append(record.getMessage());
			sb.append("\n");
			return sb.toString();
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

		logger.info("Vergebene Buchstaben: ");
		logger.info(lVergebeneBuchstaben.toString());

		int lCount = 0;
		boolean found = false;
		while (!found && lCount < lWoerter.size()) {
			logger.info(Integer.toString(lCount));
			logger.info(" Wörter entfernt");
			found = solveOhneWoerter(lWoerter, lCount, found);
			lCount++;
		}

	}

	private static boolean solveOhneWoerter(List<Wort> pWoerter, int pI, boolean pFound) {

		if (pI == 0) {

			ArrayList<Loesung> lLoesungen = solve(pWoerter);
			if (!lLoesungen.isEmpty()) {
				pFound = true;
				logger.info("Anzahl Lösungen: ");
				logger.info(Integer.toString(lLoesungen.size()));
				logger.info("Begin Lösungen");
				for (Loesung lLoesung : lLoesungen) {
					logger.info(lLoesung.toString());
				}
				logger.info("Ende Lösungen");
			}

		} else if (pI == 1) {

			ArrayList<List<Wort>> lGekuerzteListen = kuerzeListeUm(pWoerter, 1);
			for (List<Wort> lGekuerzteList : lGekuerzteListen) {
				pFound = solveOhneWoerter(lGekuerzteList, 0, pFound);
			}

		} else {

			ArrayList<List<Wort>> lGekuerzteListen = kuerzeListeUm(pWoerter, 1);
			for (List<Wort> lGekuerzteList : lGekuerzteListen) {
				pFound = solveOhneWoerter(lGekuerzteList, pI - 1, pFound);
			}

		}

		return pFound;
	}

	private static ArrayList<List<Wort>> kuerzeListeUm(List<Wort> pWoerter, int pI) {

		ArrayList<List<Wort>> lGekuerzteListen = new ArrayList<>();

		if (pI == 1) {

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
