package mnemonics.service;

import static mnemonics.constants.TestConstants.*;
import static org.junit.jupiter.api.Assertions.*;

import java.util.*;
import java.util.stream.Stream;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.*;

import mnemonics.model.*;

class SolutionGenerator2Test {

	@Test
	void shouldBe1Results() {

		List<Solution> allValidSolutions = SolutionGenerator2.createAllValidSolutions(WORDS_ABC, new ArrayList<Mnemonic>(), 10);
		assertEquals(1, allValidSolutions.size());
	}

	@Test
	void shouldBe98Results() {

		List<Solution> allValidSolutions = SolutionGenerator2.createAllValidSolutions(List.of(WORD_NEU, WORD_LÖSCHEN, WORD_PRÜFEN), new ArrayList<Mnemonic>(), 100);
		assertEquals(98, allValidSolutions.size());
	}

	@ParameterizedTest
	@MethodSource("getAllMaxResults")
	void shouldBeNotMoreThanMaxResults(int maxResults) {

		List<Solution> allValidSolutions = SolutionGenerator2.createAllValidSolutions(List.of(WORD_NEU, WORD_LÖSCHEN, WORD_PRÜFEN), new ArrayList<Mnemonic>(), maxResults);
		assertTrue(allValidSolutions.size() <= maxResults);
	}

	static Stream<Arguments> getAllMaxResults() {

		return Stream.of(

				Arguments.of(MAXRESULTS_10),

				Arguments.of(MAXRESULTS_20),

				Arguments.of(MAXRESULTS_50)

		);

	}

}
