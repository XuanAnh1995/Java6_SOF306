package com.example.kiemtra1.entity;

public class PhuongTrinhBacNhat {

    public int giaiPTBN(int a, int b) {

        if (a == 0 && b != 0) {
            throw new ArithmeticException("Phuong trinh vo nghiem");
        }

        if (a == 0 && b == 0) {
            throw new ArithmeticException("Phuong trinh co vo so nghiem");
        }

        return -b / a;
    }

}
