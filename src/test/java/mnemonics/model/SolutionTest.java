package mnemonics.model;

import static org.junit.jupiter.api.Assertions.assertFalse;

import java.util.List;

import org.junit.jupiter.api.Test;

class SolutionTest {

	private static final Character B = Character.valueOf('B');
	private static final Word wordAB = new Word("AB", List.of());
	private static final Word wordB = new Word("B", List.of());
	private static final Mnemonic mnemonicAB = new Mnemonic(wordAB, B);
	private static final Mnemonic mnemonicB = new Mnemonic(wordB, B);

	@Test
	void shouldBeInvalidWhenTwoMnemonicHaveSameLetter() {

		Solution solution = new Solution(mnemonicAB);
		solution.setMnemonics(List.of(mnemonicAB, mnemonicB));

		assertFalse(solution.isValid());
	}

	@Test
	void shouldBeInvalidWhenTwoMnemonicHaveSameWord() {

		Solution solution = new Solution(mnemonicB);
		solution.setMnemonics(List.of(mnemonicB, mnemonicB));

		assertFalse(solution.isValid());
	}

}
