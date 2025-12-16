package mnemonics.service;

import java.util.List;

import mnemonics.model.*;

interface SolutionGenerator {

	List<Solution> createAllValidSolutions(List<Word> words, int maxResults);

	static SolutionGenerator forWordCount(int wordsCount) {

		if (wordsCount > 5) {
			return new BacktrackingGenerator();
		} else {
			return new DivideConquerGenerator();
		}
	}
}
