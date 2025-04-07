package mnemonics.service;

import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

public class MnemonicsService {

	private MnemonicsService() {

	}

	static Logger logger = Logger.getLogger("MyLog");

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
		ArrayList<Word> lWoerter = new ArrayList<>();
		for (String pWort : pWoerter) {
			lWoerter.add(new Word(pWort, lVergebeneBuchstaben));
		}

		logger.log(Level.INFO, "Vergebene Buchstaben: {0}", lVergebeneBuchstaben);
		logger.log(Level.INFO, "");

		int lKuerzeListeUm = 0;
		boolean found = false;
		while (!found && lKuerzeListeUm < lWoerter.size()) {
			logger.log(Level.INFO, "{0} Wort(e) entfernt", Integer.toString(lKuerzeListeUm));
			logger.log(Level.INFO, "");
			found = findeLoesungen(lWoerter, lKuerzeListeUm, found);
			lKuerzeListeUm++;
		}

	}

	private static boolean findeLoesungen(List<Word> pWoerter, int pKuerzeListeUm, boolean pFound) {

		if (pKuerzeListeUm == 0) {

			ArrayList<Loesung> lLoesungen = erzeugeAlleGueltigenMnemonics(pWoerter);
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

			ArrayList<List<Word>> lGekuerzteListen = erzeugeWortListenMitEinemWortWeniger(pWoerter);
			for (List<Word> lGekuerzteList : lGekuerzteListen) {
				pFound = findeLoesungen(lGekuerzteList, pKuerzeListeUm - 1, pFound);
			}

		}

		return pFound;
	}

	static ArrayList<List<Word>> erzeugeWortListenMitEinemWortWeniger(List<Word> pWoerter) {

		ArrayList<List<Word>> lGekuerzteListen = new ArrayList<>();

		for (int i = 0; i < pWoerter.size(); i++) {
			if (i == 0) {
				lGekuerzteListen.add(pWoerter.subList(1, pWoerter.size()));
			} else if (i == pWoerter.size()) {
				lGekuerzteListen.add(pWoerter.subList(0, pWoerter.size() - 1));
			} else {
				List<Word> lSubListA = pWoerter.subList(0, i);
				List<Word> lSubListB = pWoerter.subList(i + 1, pWoerter.size());
				List<Word> lSubListC = new ArrayList<>();
				lSubListC.addAll(lSubListA);
				lSubListC.addAll(lSubListB);
				lGekuerzteListen.add(lSubListC);
			}
		}
		return lGekuerzteListen;
	}

	static ArrayList<List<Word>> getReducedListPermutations(List<Word> words) {

		ArrayList<List<Word>> reducedLists = new ArrayList<>();
		for (Word word : words) {
			List<Word> copy = new ArrayList<>(words);
			copy.remove(word);
			reducedLists.add(copy);
		}
		return reducedLists;
	}

	private static ArrayList<Loesung> erzeugeAlleGueltigenMnemonics(List<Word> pWoerter) {

		ArrayList<Loesung> lLoesungen = new ArrayList<>();

		if (pWoerter.isEmpty()) {
			return new ArrayList<>();
		} else if (pWoerter.size() == 1) {
			List<Mnemonic> lMnemonics = Mnemonic.getAll(pWoerter.get(0));
			for (Mnemonic lMnemonic : lMnemonics) {
				lLoesungen.add(new Loesung(lMnemonic));
			}
		} else {
			ArrayList<Loesung> lLoesungen1 = erzeugeAlleGueltigenMnemonics(pWoerter.subList(0, (pWoerter.size() + 1) / 2));
			ArrayList<Loesung> lLoesungen2 = erzeugeAlleGueltigenMnemonics(pWoerter.subList((pWoerter.size() + 1) / 2, pWoerter.size()));
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
