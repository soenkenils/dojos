import java.util.Arrays;
import java.util.stream.Stream;

/**
 * Problem statement:
 * http://community.topcoder.com/stat?c=problem_statement&pm=12778
 */
public class WolfDelaymaster {

    public String check(String str) {
        if (extractSingleWolfs(str).anyMatch(s -> !isValid(s))) return "INVALID";
        else return "VALID";
    }

    private Stream<String> extractSingleWolfs(String str) {
        String s = str.replaceAll("fw", "f#w");
        return Arrays.stream(s.split("#"));
    }

    private boolean isValid(String wolf) {
        String s = wolf
            .replaceAll("wo", "w#o")
            .replaceAll("ol", "o#l")
            .replaceAll("lf", "l#f");

        String[] split = s.split("#");

        return split.length == "wolf".length() && Stream.of(split)
            .mapToInt(String::length)
            .distinct()
            .count() == 1;
    }
}
