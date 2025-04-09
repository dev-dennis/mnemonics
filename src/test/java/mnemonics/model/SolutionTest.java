package mnemonics.model;

import static mnemonics.constants.TestConstants.*;
import static org.junit.jupiter.api.Assertions.assertFalse;

import java.util.List;

import org.junit.jupiter.api.Test;

class SolutionTest {

	@Test
	void shouldBeInvalidWhenTwoMnemonicHaveSameLetter() {

		Solution solution = new Solution(MNEMONIC_ABC_A);
		solution.setMnemonics(List.of(MNEMONIC_ABC_A, MNEMONIC_A_A));

		assertFalse(solution.isValid());
	}

	@Test
	void shouldBeInvalidWhenTwoMnemonicHaveSameWord() {

		Solution solution = new Solution(MNEMONIC_ABC_A);
		solution.setMnemonics(List.of(MNEMONIC_ABC_A, MNEMONIC_ABC_A));

		assertFalse(solution.isValid());
	}

}
