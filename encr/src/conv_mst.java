package src;

import java.util.Random;
import java.util.Scanner;

/**
 * <h4>Basic letter shifting + spacing</h4>
 * This is a basic method of encoding that modifies half-width characters
 * to their full-width (used in Japanese) counterparts, shifts them forwards
 * in the alphabet by a random number, and inserts a random amount of
 * spaces after each character.<br><br>
 * <i>Note: This method only accepts input via <code>java.util.Scanner(System.in)</code>.</i>
 *
 * @see java.util.Scanner
 */

public class conv_mst {
    public static void main(String[] args) {
        Scanner scn = new Scanner(System.in);
        Random random = new Random();

        // Console text only -- Read line through the scanner
        System.out.println("変換するテキストを入力してください:");
        String input = scn.nextLine();

        StringBuilder rst = new StringBuilder();
        for (char c : input.toCharArray()) {
            char convertedChar = c;

            if (Character.isLetter(c)) {
                char base = Character.isUpperCase(c) ? 'A' : 'a';
                convertedChar = (char) (((c - base + 3) % 26) + base);
            } else if (Character.isDigit(c)) {
                convertedChar = (char) (((c - '0' + 3) % 10) + '0');
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

        System.out.println("変換されたテキスト:");
        System.out.println(rst);

        scn.close();
    }
}

