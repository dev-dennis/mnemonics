package mnemonics;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class Mnemonic {

    private Wort mWort;

    private Character mBuchstabe;

    public Character getBuchstabe() {
        return mBuchstabe;
    }

    private void setBuchstabe(Character mBuchstabe) {
        this.mBuchstabe = mBuchstabe;
    }

    public Wort getWort() {
        return mWort;
    }

    private void setWort(Wort pWort) {
        this.mWort = pWort;
    }

    public Mnemonic(Wort pWort, Character pBuchstabe) {

        setWort(pWort);
        setBuchstabe(pBuchstabe);
    }

    @Override
    public String toString() {
        return getWort().getWort() + "," + getBuchstabe();
    }

    public static List<Mnemonic> getAll(Wort pWort) {

        Set<Character> charSet = new HashSet<>();
        for (Character c : pWort.getErlaubteBUchstaben().toCharArray()) {
            charSet.add(c);
        }

        List<Mnemonic> lMnemonics = new ArrayList<>();
        for (Character character : charSet) {
            lMnemonics.add(new Mnemonic(pWort, character));
        }

        return lMnemonics;
    }
}
