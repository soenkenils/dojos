import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * Problem statement:
 * http://community.topcoder.com/stat?c=problem_statement&pm=12778
 *
 * Validates "delayed wolf" strings where:
 * - One or more "wolf" patterns appear in sequence
 * - Each pattern has w, o, l, f in that order
 * - All four letters must repeat the same number of times
 * - Example: "wwoollff" is valid (2x each), "wwollff" is invalid (2,1,2,2)
 */
public class WolfDelaymaster {

    private static final Pattern WOLF_PATTERN = Pattern.compile("w+o+l+f+");

    public String check(String str) {
        if (str == null || str.isEmpty()) {
            return "INVALID";
        }

        return isValidWolfSequence(str) ? "VALID" : "INVALID";
    }

    private boolean isValidWolfSequence(String str) {
        Matcher matcher = WOLF_PATTERN.matcher(str);
        int lastEnd = 0;
        boolean foundAtLeastOne = false;

        // Match all wolf patterns and verify they cover the entire string
        while (matcher.find()) {
            if (matcher.start() != lastEnd) {
                // Gap or invalid characters between patterns
                return false;
            }

            if (!hasEqualLetterCounts(matcher.group())) {
                return false;
            }

            lastEnd = matcher.end();
            foundAtLeastOne = true;
        }

        // Verify entire string was consumed and at least one pattern found
        return foundAtLeastOne && lastEnd == str.length();
    }

    private boolean hasEqualLetterCounts(String wolfPattern) {
        int wCount = countChar(wolfPattern, 'w');
        int oCount = countChar(wolfPattern, 'o');
        int lCount = countChar(wolfPattern, 'l');
        int fCount = countChar(wolfPattern, 'f');

        // All letters must appear and in equal quantities
        return wCount > 0 && wCount == oCount && oCount == lCount && lCount == fCount;
    }

    private int countChar(String str, char ch) {
        return (int) str.chars().filter(c -> c == ch).count();
    }
}
