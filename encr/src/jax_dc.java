package src;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;

/**
 * <h4>DECODE: Alpha to Kanji encoding</h4>
 * <b><i>See <code>jax_mst</code> for information about the complementing encoding system.</i></b><br><br>
 * <i>Note: This method is capable of accepting input via <code>java.util.Scanner(System.in)</code> OR <code>procFle(filePath)</code>.</i>
 */

public class jax_dc extends jax3 {
    private final Map<String, Character> kanjiToAlpha = new HashMap<>();

    public jax_dc() {
        initDcMp();
    }

    public static void main(String[] args) {
        jax_dc decoder = new jax_dc();

        System.out.println("\n### コンソール入力 ###");
        decoder.procCon();

        System.out.println("\n### ファイル入力サンプル ###");
        try {
            System.out.println(decoder.procFle("/Users/aki/IdeaProjects/2024h2-main/encr/src/jax3/encoded.txt"));
        } catch (IOException e) {
            System.err.println("「jax3/encoded.txt」を読み取ることができません。ファイルは存在しますか?");
            System.out.println(e);
        }
    }

    private void initDcMp() {
        AryLtrMp(up_a, 'A');
        AryLtrMp(up_b, 'B');
        AryLtrMp(up_c, 'C');
        AryLtrMp(up_d, 'D');
        AryLtrMp(up_e, 'E');
        AryLtrMp(up_f, 'F');
        AryLtrMp(up_g, 'G');
        AryLtrMp(up_h, 'H');
        AryLtrMp(up_i, 'I');
        AryLtrMp(up_j, 'J');
        AryLtrMp(up_k, 'K');
        AryLtrMp(up_l, 'L');
        AryLtrMp(up_m, 'M');
        AryLtrMp(up_n, 'N');
        AryLtrMp(up_o, 'O');
        AryLtrMp(up_p, 'P');
        AryLtrMp(up_q, 'Q');
        AryLtrMp(up_r, 'R');
        AryLtrMp(up_s, 'S');
        AryLtrMp(up_t, 'T');
        AryLtrMp(up_u, 'U');
        AryLtrMp(up_v, 'V');
        AryLtrMp(up_w, 'W');
        AryLtrMp(up_x, 'X');
        AryLtrMp(up_y, 'Y');
        AryLtrMp(up_z, 'Z');

        AryLtrMp(down_a, 'a');
        AryLtrMp(down_b, 'b');
        AryLtrMp(down_c, 'c');
        AryLtrMp(down_d, 'd');
        AryLtrMp(down_e, 'e');
        AryLtrMp(down_f, 'f');
        AryLtrMp(down_g, 'g');
        AryLtrMp(down_h, 'h');
        AryLtrMp(down_i, 'i');
        AryLtrMp(down_j, 'j');
        AryLtrMp(down_k, 'k');
        AryLtrMp(down_l, 'l');
        AryLtrMp(down_m, 'm');
        AryLtrMp(down_n, 'n');
        AryLtrMp(down_o, 'o');
        AryLtrMp(down_p, 'p');
        AryLtrMp(down_q, 'q');
        AryLtrMp(down_r, 'r');
        AryLtrMp(down_s, 's');
        AryLtrMp(down_t, 't');
        AryLtrMp(down_u, 'u');
        AryLtrMp(down_v, 'v');
        AryLtrMp(down_w, 'w');
        AryLtrMp(down_x, 'x');
        AryLtrMp(down_y, 'y');
        AryLtrMp(down_z, 'z');
    }

    /// This utility Map replaces the original kanji <code>key</code> with the
    /// originating letter.
    private void AryLtrMp(String[] array, char letter) {
        for (String kanji : array) {
            kanjiToAlpha.put(kanji, letter);
        }
    }

    /// Method reads original string to match to array.
    public String dcKanji(String input) {
        if (input == null || input.isEmpty()) {
            return "";
        }

        StringBuilder result = new StringBuilder();
        int i = 0;
        while (i < input.length()) {
            char currentChar = input.charAt(i);
            if (Character.isIdeographic(currentChar)) {
                String kanji = String.valueOf(currentChar);
                Character mappedChar = kanjiToAlpha.getOrDefault(kanji, currentChar);
                result.append(mappedChar);
            } else {
                result.append(currentChar);
            }
            i++;
        }

        return result.toString();
    }

    /// This method does not specify the static file-path, but is specified at
    /// <code>main()</code> at <code>decoder.procFle()</code>.
    public String procFle(String filePath) throws IOException {
        StringBuilder result = new StringBuilder();
        try (BufferedReader reader = new BufferedReader(new FileReader(filePath))) {
            String line;
            while ((line = reader.readLine()) != null) {
                result.append(dcKanji(line)).append("\n");
            }
        }
        return result.toString().trim();
    }

    /// This method requests console input - "exit" command can be specified but must be entered using
    /// half-width characters.
    public void procCon() {
        Scanner scanner = new Scanner(System.in);
        System.out.println("漢字テキストを入力してください（終了するには「exit」と入力してください）:");

        StringBuilder result = new StringBuilder();
        String input;

        while (!(input = scanner.nextLine()).equalsIgnoreCase("exit")) {
            result.append(dcKanji(input)).append("\n");
            System.out.println("デコードされた: " + result.toString().trim());
            result.setLength(0);
        }
    }
}