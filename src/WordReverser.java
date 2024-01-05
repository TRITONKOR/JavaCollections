import java.util.Arrays;
import java.util.stream.Collectors;
public class WordReverser {
  public static String reverseWord(String input) {
    return Arrays.stream(input.split(" "))
        .map(word -> word.length() >= 5 ? reverseString(word) : word)
        .collect(Collectors.joining(" "));
  }
  public static String reverseString(String str){
    return new StringBuilder(str).reverse().toString();
  }
}
