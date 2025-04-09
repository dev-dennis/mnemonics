package mnemonics.service;

import java.util.*;
import java.util.logging.*;

import mnemonics.model.*;

public class MnemonicsService {

	private MnemonicsService() {

	}

	static Logger logger = Logger.getLogger("MyLog");

	public static void mnemonic(String[] args) {

		if (args.length != 2) {
			logger.info("Erwartet: abc Apfel,Birne,Clementine");
			return;
		}

		String[] pVergebeneBuchstaben = args[0].split("\\,");
		ArrayList<Character> lVergebeneBuchstaben = new ArrayList<>();
		for (String lString : pVergebeneBuchstaben) {
			if (lString.length() == 1) {
				lVergebeneBuchstaben.add(lString.charAt(0));
			} else {
				logger.info("Erwartet: abc");
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

			ArrayList<Solution> lLoesungen = erzeugeAlleGueltigenMnemonics(pWoerter);
			if (!lLoesungen.isEmpty()) {
				pFound = true;
				logger.log(Level.INFO, "Anzahl Lösungen: {0}", Integer.toString(lLoesungen.size()));
				for (Solution lLoesung : lLoesungen) {
					logger.log(Level.INFO, "{0}", lLoesung);
				}

			} else {
				logger.log(Level.INFO, "Keine Lösungen");
			}
			logger.log(Level.INFO, "");

		} else {

			ArrayList<List<Word>> lGekuerzteListen = getReducedListPermutations(pWoerter);
			for (List<Word> lGekuerzteList : lGekuerzteListen) {
				pFound = findeLoesungen(lGekuerzteList, pKuerzeListeUm - 1, pFound);
			}

		}

		return pFound;
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

	private static ArrayList<Solution> erzeugeAlleGueltigenMnemonics(List<Word> pWoerter) {

		ArrayList<Solution> lLoesungen = new ArrayList<>();

		if (pWoerter.isEmpty()) {
			return new ArrayList<>();
		} else if (pWoerter.size() == 1) {
			List<Mnemonic> mnemonics = Mnemonic.getAll(pWoerter.get(0));
			for (Mnemonic mnemonic : mnemonics) {
				lLoesungen.add(new Solution(mnemonic));
			}
		} else {
			ArrayList<Solution> lLoesungen1 = erzeugeAlleGueltigenMnemonics(pWoerter.subList(0, (pWoerter.size() + 1) / 2));
			ArrayList<Solution> lLoesungen2 = erzeugeAlleGueltigenMnemonics(pWoerter.subList((pWoerter.size() + 1) / 2, pWoerter.size()));
			for (Solution loesung1 : lLoesungen1) {
				for (Solution loesung2 : lLoesungen2) {
					Solution loesung = new Solution(loesung1, loesung2);
					if (loesung.isValid()) {
						lLoesungen.add(loesung);
					}
				}
			}
		}
		return lLoesungen;
	}
}
