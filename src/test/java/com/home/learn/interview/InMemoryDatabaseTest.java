package com.home.learn.interview;

import com.home.learn.lyft.InMemoryDatabase;
import org.junit.jupiter.api.Test;

public class InMemoryDatabaseTest {

    @Test
    public void test() {
        InMemoryDatabase<String, Integer> db = new InMemoryDatabase<>();
        db.set("test", 123);
        db.set("test1", 123);
        db.set("test2", 123);
        System.out.println(db.get("test"));
        System.out.println(db.numWithValue(123));
        db.unSet("test2");
        System.out.println(db.numWithValue(123));
        db.unSet("test1");
        System.out.println(db.numWithValue(123));
        db.unSet("test");
        System.out.println(db.numWithValue(123));
        db.set("test", 123);
        db.set("test1", 345);
        db.set("test2", 345);
        db.block();
        db.set("test", 456);
        db.unSet("test1");
        System.out.println(db.get("test"));
        System.out.println(db.get("test1"));
        db.commit();
        System.out.println(db.get("test"));
        System.out.println(db.get("test1"));
        db.block();
        db.set("test1",456);
        db.set("test",345);
        db.rollBack();
        System.out.println(db.numWithValue(456));
        System.out.println(db.get("test"));
        System.out.println(db.get("test1"));
        db.block();
        db.unSet("test");
        System.out.println(db.get("test"));
        db.block();
        db.set("test", 678);
        db.set("test", 234);
        db.commit();
        System.out.println(db.get("test"));
        db.commit();
        System.out.println(db.get("test"));
    }
}
