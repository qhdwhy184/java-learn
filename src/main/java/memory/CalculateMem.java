package memory;

import java.lang.instrument.Instrumentation;

public class CalculateMem {
//    Instrumentation instrumentation = new Instrumentation();
//    instrumentation.getObjectSize(o);
}


class Parent {
    Inner inner;
    int i;
    int j;
}

class Inner {
    int i;
    int j;
}
