package regex;

import java.util.ArrayList;
import java.util.List;
import java.util.regex.Pattern;

public class Regex {
    private static final String UUID_Regex = "/^[0-9a-f]{8}-[0-9a-f]{4}-[1-5][0-9a-f]{3}-[89ab][0-9a-f]{3}-[0-9a-f]{12}$/i";
    private static final String UUID_VMware = "[0-9a-f]{8}-[0-9a-f]{4}-[0-9a-f]{4}-[0-9a-f]{4}-[0-9a-f]{12}";
    public static void main(String[] args) {
        System.out.println("--------");
        List<Integer> list = new ArrayList<>();
        for(Integer i : list) {
            System.out.println("i:" + i);
        }
        System.out.println("--------");
        List<Integer> list1 = null;
        for (Integer i :
                list1) {
            System.out.println(i);
        }
//        match(1, uuid, UUID_Regex);//y
//        match(2, "00000000-0000-0000-0000-000000000000", UUID_Regex);//y
//        match(3, "d4d2f98-4373-4d67-9706-5ec3af7fcba1", UUID_Regex);//n
//        match(4, "1-f-d-s-1", UUID_Regex);//n


//        String str = "Is is the cost of of gasoline going up up";
//        String patt1 = "/\b([a-z]+) \1\b/";
//        String patt2 = "/is/i";
//        Pattern pattern = Pattern.compile(patt2);
//        Matcher m = pattern.matcher(str);
//        System.out.println(str.matches(patt1));
//        if(m.matches()){
//            System.out.println(m.group(1));
//            System.out.println(m.group(2));
//        }
    }

    static void match(int no, String input, String regex){
        if(Pattern.matches(regex, input)){
            System.out.println(no+":match");
        } else {
            System.out.println(no+":not match");
        }
    }
}
