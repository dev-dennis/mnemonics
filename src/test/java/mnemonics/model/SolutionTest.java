package mnemonics.model;

import static mnemonics.constants.TestConstants.*;
import static org.junit.jupiter.api.Assertions.*;

import java.util.*;

import org.junit.jupiter.api.Test;

class SolutionTest {

	@Test
	void shouldBeInvalidWhenTwoMnemonicHaveSameLetter() {

		Solution solution = new Solution(MNEMONIC_ABC_A);

		assertEquals(solution.getLetterSet(), Set.of(Character.toLowerCase(A)));
	}

}
