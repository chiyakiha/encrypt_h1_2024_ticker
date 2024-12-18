package src;

import java.util.Random;
import java.util.Scanner;

/**
 * <h4>Combined Encoder Implementation</h4>
 * Implements a three-stage encoding process:<br>
 * 1. Character conversion with spacing<br>
 * 2. Base64 encoding<br>
 * 3. Alphanumeric to Kanji conversion<br>
 * NOTICE: While this should be relatively accurate, modifying the output
 * after encoding or using an input that already has kanji may break the decoder.
 */
public class combi extends jax3 {
    private final Random random = new Random();

    public static void main(String[] args) {
        combi encoder = new combi();
        Scanner scanner = new Scanner(System.in);

        System.out.println("テキストを入力してください:");
        String input = scanner.nextLine();

        // Step 1: Character conversion
        String convResult = encoder.convEncode(input);
        System.out.println("\n1 - 文字変換: " + convResult);

        // Step 2: Base64 encoding
        String b64Result = encoder.convertToBase64(convResult);
        System.out.println("\n2 - Base64: " + b64Result);

        // Step 3: Convert to Kanji
        String kanjiResult = encoder.rplKji(b64Result);
        System.out.println("\n3 - 漢字変換: " + kanjiResult);

        scanner.close();
    }

    /// Performs per-character conversion using the ruleset, adds
    /// random spaces after non-whitespace characters.<br>
    /// Note: This is a duplicate fragment from <code>conv_mst</code>, thanks to
    /// the original method being implemented directly in the class, and not a particular function.
    private String convEncode(String input) {
        if (input == null || input.isEmpty()) {
            return "";
        }

        StringBuilder rst = new StringBuilder();
        for (char c : input.toCharArray()) {
            char convertedChar = c;

            if (Character.isLetter(c)) {
                char base = Character.isUpperCase(c) ? 'A' : 'a';
                convertedChar = (char) (((c - base + 3) % 26) + base);
            } else if (Character.isDigit(c)) {
                convertedChar = (char) (((c - '0' + 3) % 10) + '0');
                // Note: The whitespace check here doesn't work, but
                // I've left it in for the sake of not breaking something else.
            } else if (Character.isWhitespace(input.charAt(input.length() - 1))) {
                rst.append(' ');
            }

            if (convertedChar >= '!' && convertedChar <= '~') {
                convertedChar = (char) (convertedChar - 0x21 + 0xFF01);
            }

            rst.append(convertedChar);

            int spaces = 0;
            rst.append(" ".repeat(spaces));
        }

        return rst.toString();
    }

    private String convertToBase64(String text) {
        return b64_mst.convertToBase64(text);
    }

    /// Converts input string into an array of kanji characters based on
    /// predefined mapping arrays. Uses random selection for multiple matches.
    public String rplKji(String input) {
        if (input == null || input.isEmpty()) {
            return "";
        }

        StringBuilder result = new StringBuilder();
        for (char c : input.toCharArray()) {
            if (Character.isLetter(c)) {
                String[] array = aryLtrMod(c);
                if (array != null && array.length > 0) {
                    result.append(array[random.nextInt(array.length)]);
                } else {
                    result.append(c);
                }
            } else {
                result.append(c);
            }
        }
        return result.toString();
    }

    /// It is otherwise pointless to duplicate this fragment but JVM was giving me a hard time
    /// trying to use the original object.
    private String[] aryLtrMod(char letter) {
        return switch (letter) {
            case 'A' -> up_a;
            case 'B' -> up_b;
            case 'C' -> up_c;
            case 'D' -> up_d;
            case 'E' -> up_e;
            case 'F' -> up_f;
            case 'G' -> up_g;
            case 'H' -> up_h;
            case 'I' -> up_i;
            case 'J' -> up_j;
            case 'K' -> up_k;
            case 'L' -> up_l;
            case 'M' -> up_m;
            case 'N' -> up_n;
            case 'O' -> up_o;
            case 'P' -> up_p;
            case 'Q' -> up_q;
            case 'R' -> up_r;
            case 'S' -> up_s;
            case 'T' -> up_t;
            case 'U' -> up_u;
            case 'V' -> up_v;
            case 'W' -> up_w;
            case 'X' -> up_x;
            case 'Y' -> up_y;
            case 'Z' -> up_z;
            case 'a' -> down_a;
            case 'b' -> down_b;
            case 'c' -> down_c;
            case 'd' -> down_d;
            case 'e' -> down_e;
            case 'f' -> down_f;
            case 'g' -> down_g;
            case 'h' -> down_h;
            case 'i' -> down_i;
            case 'j' -> down_j;
            case 'k' -> down_k;
            case 'l' -> down_l;
            case 'm' -> down_m;
            case 'n' -> down_n;
            case 'o' -> down_o;
            case 'p' -> down_p;
            case 'q' -> down_q;
            case 'r' -> down_r;
            case 's' -> down_s;
            case 't' -> down_t;
            case 'u' -> down_u;
            case 'v' -> down_v;
            case 'w' -> down_w;
            case 'x' -> down_x;
            case 'y' -> down_y;
            case 'z' -> down_z;
            default -> null;
        };
    }
}