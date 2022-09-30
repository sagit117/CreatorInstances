package ru.axel.creator.instances;

import java.lang.reflect.InvocationTargetException;

public class Main {
    public static void main(String[] args) throws InvocationTargetException, InstantiationException, IllegalAccessException {
        CreatorInstances.createInstance(Test.class);
    }
}

class Test {
    public Test() {
        System.out.println("Test created");
    }
}