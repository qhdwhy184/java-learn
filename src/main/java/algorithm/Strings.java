package algorithm;

/**
 * Created by wangyuanhui on 16/6/1.
 */
public class Strings {
    public static void main(String[] args) {
        StringBuilder sb = new StringBuilder("ABC");
        char[] carr = sb.toString().toCharArray();
        if(carr[0] == 'A'){
            System.out.println("is A");
        } else {
            System.out.println("No");
        }
    }
}
