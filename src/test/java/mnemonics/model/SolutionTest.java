package mnemonics.model;

import static mnemonics.constants.TestConstants.*;
import static org.junit.jupiter.api.Assertions.*;

import java.util.*;

import org.junit.jupiter.api.Test;

class SolutionTest {

	@Test
	void shouldReturnCorrectLetterSet() {

		Solution solution = new Solution(MNEMONIC_ABC_A);

		assertEquals(Set.of('a'), solution.getLetterSet());
	}

}
