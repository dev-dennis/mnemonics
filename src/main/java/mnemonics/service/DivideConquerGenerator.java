package mnemonics.service;

import java.util.*;

import mnemonics.model.*;

public class SolutionGenerator1 implements SolutionGenerator {

	@Override
	public List<Solution> createAllValidSolutions(List<Word> words, int maxResults) {

		if (words.isEmpty()) {
			return List.of();
		}

		if (words.size() == 1) {
			return Solution.createAll(words.get(0));
		}

		List<Solution> solutions = new ArrayList<>();
		List<Solution> solutions1 = createAllValidSolutions(words.subList(0, words.size() / 2), maxResults);
		List<Solution> solutions2 = createAllValidSolutions(words.subList(words.size() / 2, words.size()), maxResults);
		for (Solution solution1 : solutions1) {
			Set<Character> letterSet1 = solution1.getLetterSet();
			for (Solution solution2 : solutions2) {
				if (Collections.disjoint(letterSet1, solution2.getLetterSet())) {
					solutions.add(new Solution(solution1, solution2));
				}
			}
		}
		return solutions;

	}

}
