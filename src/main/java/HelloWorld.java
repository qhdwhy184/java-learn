import org.junit.Test;

import java.util.HashSet;
import java.util.Observable;
import java.util.Set;

import static org.junit.Assert.assertEquals;

public class HelloWorld {
    public static void main(String[] args) {
        System.out.println("Hello World!");
        Set<Person> personSet = new HashSet<>();
        Person p1 = new Person("1");
        Person p2 = new Person("2");
        Person p3 = new Person("3");
        personSet.add(p1);
        personSet.add(p2);
        personSet.add(p3);
        Set<Person> personSet2 = new HashSet<>(personSet);
        personSet2.remove(p1);
        System.out.println("personSet:" + personSet);
        System.out.println("personSet2:" + personSet2);
    }

}


class Person {
    private String name;
    Person(String name) {
        this.name = name;
    }

    @Override
    public String toString() {
        return this.name;
    }
}