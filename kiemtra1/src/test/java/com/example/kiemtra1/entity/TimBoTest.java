package com.example.kiemtra1.entity;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class TimBoTest {

    TimBo timBo;

    @BeforeEach
    void setUp() {
        timBo = new TimBo();
    }

    @AfterEach
    void tearDown() {
        timBo = null;
    }

    @Test
    void testTimBo1() {
        ArithmeticException exception = assertThrows(ArithmeticException.class, () -> {
            timBo.timBo(0, 0);
        });
        assertEquals("N hoac S phai lon hon 0", exception.getMessage());
    }

    @Test
    void testTimBo2() {
        int expected = 3;
        int actual = timBo.timBo(5, 12);
        assertEquals(expected, actual);
    }

    @Test
    void testTimBo3() {
        int expected = 4;
        int actual = timBo.timBo(5, 11);
        assertEquals(expected, actual);
    }

    @Test
    void testTimBo4() {
        int expected = 5;
        int actual = timBo.timBo(5, 10);
        assertEquals(expected, actual);
    }

    @Test
    void testTimBo5() {
        int expected = 2;
        int actual = timBo.timBo(5, 13);
        assertEquals(expected, actual);
    }

    @Test
    void testTimBo6() {
        int expected = 1;
        int actual = timBo.timBo(5, 14);
        assertEquals(expected, actual);
    }
}