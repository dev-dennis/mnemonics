package mnemonics.service;

import java.util.*;

import org.springframework.stereotype.Service;

import mnemonics.model.*;

@Service
public class MnemonicsService {

	public List<Solution> findSolutions(String wordString, String forbiddenString, int wordsCount, int resultCount) {

		List<Word> words = WordParser.createWords(wordString, forbiddenString);
		List<List<Word>> reducedWordLists = WordGenerator.generateReducedWordLists(words, wordsCount);
		List<Solution> result = new ArrayList<>();
		SolutionGenerator solutionGenerator;
		if (wordsCount > 5) {
			solutionGenerator = new SolutionGenerator2();
		} else {
			solutionGenerator = new SolutionGenerator1();
		}
		for (List<Word> list : reducedWordLists) {
			List<Solution> allValidSolutions = solutionGenerator.createAllValidSolutions(list, resultCount);
			for (Solution solution : allValidSolutions) {
				if (result.size() < resultCount) {
					result.add(solution);
				} else {
					return result;
				}
			}
		}
		return result;
	}

}
