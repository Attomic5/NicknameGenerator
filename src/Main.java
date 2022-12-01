import java.util.*;
import java.util.concurrent.atomic.AtomicInteger;


public class Main {
    static AtomicInteger counter3 = new AtomicInteger();
    static AtomicInteger counter4 = new AtomicInteger();
    static AtomicInteger counter5 = new AtomicInteger();

    public static void main(String[] args) throws InterruptedException {
        Random random = new Random();
        String[] texts = new String[100_000];
        for (int i = 0; i < texts.length; i++) {
            texts[i] = generateText("abc", 3 + random.nextInt(3));
        }

        new Thread(() -> {
            for (String nickname : texts) {
                if (nickname.equals("aaa") || nickname.equals("bbb") || nickname.equals("ccc")) {
                    counter3.getAndIncrement();
                } else if (nickname.equals("aaaa") || nickname.equals("bbbb") || nickname.equals("cccc")) {
                    counter4.getAndIncrement();
                } else if (nickname.equals("aaaaa") || nickname.equals("bbbbb") || nickname.equals("ccccc")) {
                    counter5.getAndIncrement();
                }
            }
        }).start();


        new Thread(() -> {
            for (String nickname : texts) {
                if(isWordPalindrome(nickname)) {
                    if(nickname.length() == 3) {
                        counter3.getAndIncrement();
                    } else if (nickname.length() == 4) {
                        counter4.getAndIncrement();
                    } else if (nickname.length() == 5) {
                        counter5.getAndIncrement();
                    }
                }
            }
        }).start();

        new Thread(() -> {
            for (String nickname : texts) {
                if(isAbcOrder(nickname)) {
                    if(nickname.length() == 3) {
                        counter3.getAndIncrement();
                    } else if (nickname.length() == 4) {
                        counter4.getAndIncrement();
                    } else if (nickname.length() == 5) {
                        counter5.getAndIncrement();
                    }
                }
            }
        }).start();

        System.out.println("Красивых слов с длиной 3: " + counter3 + " шт");
        System.out.println("Красивых слов с длиной 4: " + counter4 + " шт");
        System.out.println("Красивых слов с длиной 5: " + counter5 + " шт");
    }

    public static String generateText(String letters, int length) {
        Random random = new Random();
        StringBuilder text = new StringBuilder();
        for (int i = 0; i < length; i++) {
            text.append(letters.charAt(random.nextInt(letters.length())));
        }
        return text.toString();
    }

    public static boolean isWordPalindrome(String word) {
        char[] chars = word.toCharArray();
        int left = 0; // индекс первого символа
        int right = chars.length - 1; // индекс последнего символа
        while (left < right) { // пока не дошли до середины слова
            if (chars[left] != chars[right]) {
                return false;
            }
            left++;
            right--;
        }
        return true;
    }

    public static boolean isAbcOrder(String word) {
        char[] chars = word.toCharArray();
        int left = 0;
        int right = chars.length - 1;
        while (left < right) {
            if (chars[left] == chars[right]) {
                return false;
            }
            left++;
            right--;
        }
        return true;
    }
}