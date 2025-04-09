package mnemonics.constants;

import java.util.List;

import mnemonics.model.*;

public class TestConstants {

	public static final Character A = Character.valueOf('A');
	public static final Character B = Character.valueOf('B');
	public static final Character C = Character.valueOf('C');
	public static final Character D = Character.valueOf('D');

	public static final Character LOWERA = Character.valueOf('a');
	public static final Character LOWERB = Character.valueOf('b');
	public static final Character LOWERC = Character.valueOf('c');
	public static final Character LOWERD = Character.valueOf('d');

	public static final Word WORD_A = new Word("A", List.of());
	public static final Word WORD_B = new Word("B", List.of());
	public static final Word WORD_C = new Word("C", List.of());
	public static final Word WORD_D = new Word("D", List.of());
	public static final Word WORD_ABC = new Word("ABC", List.of());

	public static final List<Word> WORDS_ABC = List.of(WORD_A, WORD_B, WORD_C);
	public static final List<Word> WORDS_ABD = List.of(WORD_A, WORD_B, WORD_D);
	public static final List<Word> WORDS_BCD = List.of(WORD_B, WORD_C, WORD_D);
	public static final List<Word> WORDS_ACD = List.of(WORD_A, WORD_C, WORD_D);

	public static final Mnemonic MNEMONIC_A_A = new Mnemonic(WORD_A, A);
	public static final Mnemonic MNEMONIC_ABC_A = new Mnemonic(WORD_ABC, A);
	public static final Mnemonic MNEMONIC_ABC_B = new Mnemonic(WORD_ABC, B);
	public static final Mnemonic MNEMONIC_ABC_C = new Mnemonic(WORD_ABC, C);

}
