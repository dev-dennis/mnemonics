package mnemonics.service;

import java.util.*;

import mnemonics.model.*;

public class SolutionGenerator2 implements SolutionGenerator {

	@Override
	public List<Solution> createAllValidSolutions(List<Word> words, int maxResults) {

		return createAllValidSolutions(words, new ArrayList<>(), maxResults);
	}

	static List<Solution> createAllValidSolutions(List<Word> words, List<Mnemonic> mnemonics, int maxResults) {

		List<Solution> solutions = new ArrayList<>();

		if (words.isEmpty() || maxResults == 0) {
			return new ArrayList<>();
		}

		for (Character character : words.get(0).getAllowedCharacters()) {
			if (!mnemonics.stream().map(Mnemonic::getLetter).toList().contains(character)) {
				List<Mnemonic> newMnemonics = new ArrayList<>();
				newMnemonics.addAll(mnemonics);
				Mnemonic mnemonic = new Mnemonic(words.get(0), character);
				newMnemonics.add(mnemonic);
				if (words.size() > 1) {
					List<Solution> allValidSolutions = createAllValidSolutions(words.subList(1, words.size()), newMnemonics, maxResults);
					allValidSolutions.stream().limit((long) maxResults - solutions.size()).forEach(solutions::add);
				} else {
					solutions.add(new Solution(newMnemonics));
				}
				if (solutions.size() >= maxResults) {
					return solutions;
				}
			}
		}

		return solutions;

	}

}
