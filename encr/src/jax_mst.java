package src;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.Random;
import java.util.Scanner;

/**
 * <h4>Alpha to Kanji encoding</h4>
 * This is a custom method of encoding that changes alphanumericals
 * to corresponding Kanji based on the first character the Kanji starts
 * with. The encoding preserves the original case of the input.
 * <p>
 * The character converts are drawn from the <code>jax3</code> class.
 * <p>
 * See below URI for more information.
 * <a href="https://p.akiha.sh/j/M3L9DB1J17DX0F">...</a>
 */

public class jax_mst extends jax3 {
    private final Random random = new Random();

    /**
     * This is the main thread. The input/output interfaces are as follows:<br>
     * 1. Static output (from string "Hello")<br>
     * 2. Console I/O (typed input)<br>
     * 3. File I/O (read from static file at path <code>jax3/sample.txt</code>)<br>
     */
    public static void main(String[] args) {
        jax_mst replacer = new jax_mst();

        System.out.println("### 固定入力サンプル ###");
        System.out.println("Hello -> " + replacer.rplKji("Hello"));

        System.out.println("\n### コンソール入力 ###");
        replacer.procCon();

        System.out.println("\n### ファイル入力サンプル ###");
        try {
            System.out.println(replacer.procFle("/Users/aki/IdeaProjects/2024h2-main/encr/src/jax3/sample.txt"));
        } catch (IOException e) {
            System.err.println("「jax3/sample.txt」を読み取ることができません。ファイルは存在しますか?");
            System.out.println(e);
        }
    }

    // Read from original line and preserve case
    public String rplKji(String input) {
        if (input == null || input.isEmpty()) {
            return "";
        }

        StringBuilder result = new StringBuilder();

        for (char c : input.toCharArray()) {
            if (Character.isLetter(c)) {
                String[] array = aryLtrMod(c);
                if (array != null) {
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

    /**
     * Takes letters from the given string (input variable) and matches them
     * to the class IDs of the arrays in <code>jax3</code>. Preserves case by
     * using different arrays for upper and lowercase letters.
     *
     * @param letter The input letter to match
     * @return The corresponding kanji array based on case
     */
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

    /// This method does not specify the static file-path, but is specified at
    /// <code>main()</code> at <code>replacer.procFle()</code>.
    public String procFle(String filePath) throws IOException {
        StringBuilder result = new StringBuilder();
        try (BufferedReader reader = new BufferedReader(new FileReader(filePath))) {
            String line;
            while ((line = reader.readLine()) != null) {
                result.append(rplKji(line)).append("\n");
            }
        }
        return result.toString().trim();
    }

    /// This method specifically relies on an exit method being present.<br>
    /// Note that any specific casing is ignored, meaning that any conversions
    /// given to <code>jax_dc</code> are always going to be returned in all uppercase.
    public void procCon() {
        Scanner scanner = new Scanner(System.in);
        System.out.println("テキストを入力してください（終了するには「exit」と入力してください）:");

        StringBuilder result = new StringBuilder();
        String input;

        while (!(input = scanner.nextLine()).equalsIgnoreCase("exit")) {
            result.append(rplKji(input)).append("\n");
            System.out.println("変換された: " + result.toString().trim());
            result.setLength(0);
        }
    }
}