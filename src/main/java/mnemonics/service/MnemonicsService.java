package mnemonics.service;

import java.util.*;

import org.springframework.stereotype.Service;

import mnemonics.model.*;

@Service
public class MnemonicsService {

	public List<Solution> findSolutions(String wordString, String forbiddenString, int wordsToUse) {

		List<Word> words = WordParser.createWords(wordString, forbiddenString);
		List<List<Word>> reducedWordLists = WordGenerator.generateReducedWordLists(words, wordsToUse);
		List<Solution> result = new ArrayList<>();
		for (List<Word> list : reducedWordLists) {
			List<Solution> allValidSolutions = SolutionGenerator.createAllValidSolutions(list);
			result.addAll(allValidSolutions);
		}
		return result;
	}

}
