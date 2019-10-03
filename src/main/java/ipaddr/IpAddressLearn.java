package ipaddr;

import org.springframework.security.web.util.matcher.IpAddressMatcher;

public class IpAddressLearn {
    public static void main(String[] args) {
        System.out.println(matches("0.1.1.1", "0.0.0.0/8"));
        System.out.println(matches("0.0.0.1", "0.0.0.0/8"));
        System.out.println(matches("0.0.1.0", "0.0.0.0/8"));
        System.out.println(matches("0.0.0.0", "0.0.0.0/8"));
        System.out.println(matches("0.1.0.0", "0.0.0.0/8"));
        System.out.println(matches("1.0.0.0", "0.0.0.0/8"));
        System.out.println("------------------------------------------------");
        System.out.println(matches("1.0.0.0", "::/128"));
        System.out.println(matches("0.0.0.1", "::/128"));
        System.out.println(matches("1::0", "::/128"));
        System.out.println(matches("0:0::0", "::/128"));
        System.out.println(matches("0:0::1", "::/128"));
        System.out.println(matches("1:2:3:4:5:6:7:8", "::/128"));
        System.out.println(matches("1::8", "::/128"));
        System.out.println(matches("0:0:0:0:0:0:0:0", "::/128"));
        System.out.println(matches("0:0::0:0:0:0:0", "::/128"));
        System.out.println(matches("0::0", "::/128"));
        System.out.println(matches("::0", "::/128"));
        System.out.println(matches("0::", "::/128"));
        System.out.println(matches("::", "::/128"));
    }

    private static boolean matches(String ip, String subnet) {
        IpAddressMatcher ipAddressMatcher = new IpAddressMatcher(subnet);
        return ipAddressMatcher.matches(ip);
    }
}
