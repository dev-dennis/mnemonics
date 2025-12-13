package mnemonics.service;

import java.util.List;

import mnemonics.model.*;

interface SolutionGenerator {

	List<Solution> createAllValidSolutions(List<Word> words, int maxResults);
}
