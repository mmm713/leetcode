package com.home.learn.interview;

import com.home.learn.lyft.InMemoryDatabase;
import com.home.learn.lyft.InMemoryDatabaseSingle;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNull;

class InMemoryDatabaseSingleTest {

    @Test
    public void test() {
        InMemoryDatabaseSingle<String, Integer> db = new InMemoryDatabaseSingle<>();
        db.set("test", 123);
        db.set("test1", 123);
        db.set("test2", 123);
        System.out.println(db.get("test"));
        assertEquals(3, db.numWithValue(123));
        db.unSet("test2");
        assertEquals(2, db.numWithValue(123));
        db.unSet("test1");
        assertEquals(1, db.numWithValue(123));
        db.unSet("test");
        assertEquals(0, db.numWithValue(123));
        db.set("test", 123);
        db.set("test1", 345);
        db.set("test2", 345);
        assertEquals(2, db.numWithValue(345));
        db.block();
        db.set("test", 456);
        db.unSet("test1");
        assertEquals(123, db.get("test"));
        assertEquals(345, db.get("test1"));
        assertEquals(2, db.numWithValue(345));
        db.commit();
        assertEquals(1, db.numWithValue(345));
        assertEquals(456, db.get("test"));
        assertNull(db.get("test1"));
        db.block();
        db.set("test1",456);
        db.set("test",345);
        db.rollBack();
        assertEquals(1, db.numWithValue(456));
        assertEquals(456, db.get("test"));
        assertNull(db.get("test1"));
        db.block();
        db.unSet("test");
        assertEquals(456, db.get("test"));
        db.block();
        db.set("test", 678);
        assertEquals(456, db.get("test"));
        db.set("test", 234);
        assertEquals(456, db.get("test"));
        db.commit();
        assertEquals(234, db.get("test"));
        db.commit();
        assertNull(db.get("test"));
    }
}
