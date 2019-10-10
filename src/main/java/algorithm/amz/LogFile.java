package algorithm.amz;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

/**
 * Log File
 * You have been given a task of recording some data 40M a log file. In the log file, every line is a
 * space-delimited list of strings. All lines begin with an alphanumeric identifier. There will be no lines
 * consisting only of an identifier.
 * After the alphanumeric identifier a line will consist of either:from aonecode.com
 * - a list of words using only lowercase English lettersfrom aonecode.com
 * - or list of only integers.
 * You have to reorder the data such that all of the lines with words are at the top of the log file. The
 * lines with words are ordered lexicographically. ignoring the identifier except in the case of ties In
 * the case of ties (if there are two lines that are identical except for the identifier) the identifier
 * is used to order lexicographically. Alphanumeric should be sorted in ASCII order (numbers come before
 * letters) The identifiers most still be pan of the lines in the output Strings. Lines with integers must
 * be left in the original order they were in the file.from aonecode.com
 *
 * Write an algorithm to reorder the data in the log file, according to the rules above.from aonecode.com
 * Input
 * The input to the function/method consists of two argument - logFileSize, an integer representing the
 * number of log lines.
 * logLines, a list of strings representing the log file.
 *
 * from aonecode.com from aonecode.com
 * Outputfrom aonecode.com
 * Return a list of strings representing the reordered log file data.from aonecode.com
 *
 * Note
 * Identifier consists of only lower case english character and numbers.from aonecode.com
 *
 * Example from aonecode.com
 *
 * Input
 * logFileSize = 5
 * logLines =
 * [a1 9 2 3 1]
 * [g1 act car]
 * [zo4 4 7]
 * [ab1 off key dog]
 * [a8 act zoo]
 *
 * Output
 * [g1 act car]
 * [a8 act zoo]
 * [ab1 off key dog]
 * [zo4 4 7]
 * [a1 9 2 3 1]
 */

public class LogFile {
    List<String> sort(int size, String[] logFile) {
        List<String> numLog = new ArrayList<>();
        List<String> strLog = new ArrayList<>();
        for(String s : logFile) {
            int spaceIdx = s.indexOf(' ');
            if(s.charAt(spaceIdx + 1) >= '0' && s.charAt(spaceIdx + 1) <= '9') {
                numLog.add(s);
            } else {
                strLog.add(s);
            }
        }

        Collections.sort(strLog, new Comparator<String>() {
            @Override
            public int compare(String o1, String o2) {
                int o1SpaceIdx = o1.indexOf(' ');
                int o2SpaceIdx = o2.indexOf(' ');
                return doCompare(o1.substring(o1SpaceIdx + 1), o2.substring(o2SpaceIdx +1)) != 0 ?
                        doCompare(o1.substring(o1SpaceIdx + 1), o2.substring(o2SpaceIdx +1)):
                        doCompare(o1, o2);
            }
        });
        strLog.addAll(numLog);
        return strLog;
    }
    private int doCompare(String s1, String s2) {
        int i = 0;
        for(; i < Math.min(s1.length(), s2.length()); i++) {
            if(s1.charAt(i) > s2.charAt(i)) {
                return 1;
            }

            if(s1.charAt(i) < s2.charAt(i)) {
                return -1;
            }
        }

        if(i < s1.length()) {
            return 1;
        }

        if(i < s2.length()) {
            return -1;
        }

        return 0;
    }

    public static void main(String[] args) {
        String[] testCase1 = new String[]{"[a1 9 2 3 1]",
                "[g1 act car]","[zo4 4 7]","[ab1 off key dog]","[a8 act zoo]"};
        System.out.println(new LogFile().sort(5, testCase1));
    }
}
