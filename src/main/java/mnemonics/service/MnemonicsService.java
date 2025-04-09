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
			found = findSolutions(lWoerter, lKuerzeListeUm, found);
			lKuerzeListeUm++;
		}

	}

	private static boolean findSolutions(List<Word> words, int reduceListBy, boolean found) {

		if (reduceListBy == 0) {

			List<Solution> solutions = createAllValidSolutions(words);
			if (!solutions.isEmpty()) {
				found = true;
				logger.log(Level.INFO, "Anzahl Lösungen: {0}", Integer.toString(solutions.size()));
				for (Solution lLoesung : solutions) {
					logger.log(Level.INFO, "{0}", lLoesung);
				}

			} else {
				logger.log(Level.INFO, "Keine Lösungen");
			}
			logger.log(Level.INFO, "");

		} else {

			List<List<Word>> reducedLists = createReducedListPermutations(words);
			for (List<Word> reducedList : reducedLists) {
				found = findSolutions(reducedList, reduceListBy - 1, found);
			}
		}

		return found;
	}

	static ArrayList<List<Word>> createReducedListPermutations(List<Word> words) {

		ArrayList<List<Word>> reducedLists = new ArrayList<>();
		for (Word word : words) {
			List<Word> copy = new ArrayList<>(words);
			copy.remove(word);
			reducedLists.add(copy);
		}
		return reducedLists;
	}

	private static List<Solution> createAllValidSolutions(List<Word> words) {

		if (words.isEmpty()) {
			return List.of();
		} else if (words.size() == 1) {
			return Solution.createAll(words.get(0));
		} else {
			List<Solution> solutions = new ArrayList<>();
			List<Solution> solutions1 = createAllValidSolutions(words.subList(0, words.size() / 2));
			List<Solution> solutions2 = createAllValidSolutions(words.subList(words.size() / 2, words.size()));
			for (Solution solution1 : solutions1) {
				for (Solution solution2 : solutions2) {
					Solution solution = new Solution(solution1, solution2);
					if (solution.isValid()) {
						solutions.add(solution);
					}
				}
			}
			return solutions;
		}
	}

}
