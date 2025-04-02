package mnemonics;

import java.util.ArrayList;
import java.util.LinkedHashSet;
import java.util.List;
import java.util.Set;

public class Wort {

    private String mWort;
    private ArrayList<Character> mVergebeneBuchstaben;

    public Wort(String pWort, List<Character> pVergebeneBuchstaben) {
        setVergebeneBuchstaben(pVergebeneBuchstaben);
        setWort(pWort);

        StringBuilder sb = new StringBuilder();
        sb.append("Wort: ");
        sb.append(getWort());
        sb.append(", Buchstaben: ");
        sb.append(getErlaubteBUchstaben());

        System.out.println(sb.toString());
    }

    public String getWort() {
        return mWort;
    }

    private void setWort(String pWort) {
        mWort = pWort;
    }

    private String getBuchstaben() {
        return entferneDoppelte(getWort().toLowerCase());
    }

    private static String entferneDoppelte(String pWort) {

        char[] lChars = pWort.toCharArray();
        Set<Character> charSet = new LinkedHashSet<>();
        for (char c : lChars) {
            charSet.add(c);
        }

        StringBuilder sb = new StringBuilder();
        for (Character character : charSet) {
            sb.append(character);
        }
        return sb.toString();
    }

    public String getErlaubteBUchstaben() {

        char[] lChars = getBuchstaben().toCharArray();
        Set<Character> charSet = new LinkedHashSet<>();
        for (char c : lChars) {
            if (!getVergebeneBuchstaben().contains(c)) {
                charSet.add(c);
            }
        }

        StringBuilder sb = new StringBuilder();
        for (Character character : charSet) {
            sb.append(character);
        }
        return sb.toString();
    }

    private List<Character> getVergebeneBuchstaben() {
        return mVergebeneBuchstaben;
    }

    private void setVergebeneBuchstaben(List<Character> pVergebeneBuchstaben) {
        mVergebeneBuchstaben = (ArrayList<Character>) pVergebeneBuchstaben;
    }
}
