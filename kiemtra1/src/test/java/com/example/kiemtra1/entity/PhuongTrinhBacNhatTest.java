package com.example.kiemtra1.entity;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class PhuongTrinhBacNhatTest {

    PhuongTrinhBacNhat ptbn;

    @BeforeEach
    void setUp() {
        ptbn = new PhuongTrinhBacNhat();
    }

    @AfterEach
    void tearDown() {
        ptbn = null;
    }

    @Test
    void testGiaiPTBN1() {
        ArithmeticException exception = assertThrows(ArithmeticException.class, () ->{
            ptbn.giaiPTBN(0, 1);
        });
        assertEquals("Phuong trinh vo nghiem",exception.getMessage());
    }

    @Test
    void testGiaiPTBN2() {
        ArithmeticException exception = assertThrows(ArithmeticException.class, () ->{
            ptbn.giaiPTBN(0, 0);
        });
        assertEquals("Phuong trinh co vo so nghiem",exception.getMessage());
    }

    @Test
    void testGiaiPTBN3(){
        assertEquals(1, ptbn.giaiPTBN(3, -3));
    }

    @Test
    void testGiaiPTBN4(){
        assertEquals(-1, ptbn.giaiPTBN(Integer.MAX_VALUE, Integer.MIN_VALUE));
    }

    @Test
    void testGiaiPTBN5(){
        assertEquals(-1, ptbn.giaiPTBN(Integer.MAX_VALUE, Integer.MAX_VALUE));
    }

    @Test
    void testGiaiPTBN6(){
        assertEquals(0, ptbn.giaiPTBN(Integer.MIN_VALUE, Integer.MAX_VALUE));
    }

    @Test
    void testGiaiPTBN7(){
        assertEquals(1, ptbn.giaiPTBN(Integer.MIN_VALUE, Integer.MIN_VALUE));
    }

    @Test
    void testGiaiPTBN8(){
        assertEquals(-1, ptbn.giaiPTBN(3, 3));
    }
}