package learnenum;

public class LearnEnum {
    public static void main(String[] args) {

        print(Country.CHINA);
        print(Country.CHINA.name());
        Country c = null;
        print(c);
    }

    private static void print(Country country){
        System.out.println(country);
    }

    private static void print(String country){
        System.out.println(country);
    }
}
