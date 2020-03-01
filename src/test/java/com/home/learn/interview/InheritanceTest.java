package com.home.learn.interview;

import com.home.learn.inheritance.Father;
import com.home.learn.inheritance.Son;
import org.junit.jupiter.api.Test;

class InheritanceTest {

    @Test
    public void test() {
        Son s = new Son();
        Father f = new Father();

        Father fs = (Father) s;

        System.out.println(f.x);
        System.out.println(fs.x);
        System.out.println(s.x);

        f.print();
        fs.print();
        s.print();

        f.printMe();
        fs.printMe();
        s.printMe();
        s.printMe("a");
    }
}
