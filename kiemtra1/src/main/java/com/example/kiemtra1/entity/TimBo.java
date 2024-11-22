package com.example.kiemtra1.entity;

public class TimBo {

    public int timBo(int N, int S) {
        if (N < 1 || S < 1) {
            throw new ArithmeticException("N hoac S phai lon hon 0");
        }

        int S1 = 0;

        for (int i = 0; i <= N; i++) {
            S1 += i;
        }
        return S1 - S;
    }
}
