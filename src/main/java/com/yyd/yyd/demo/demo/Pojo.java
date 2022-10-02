package com.yyd.yyd.demo.demo;

import java.util.Objects;

public class Pojo {

    int a;
    float b;


    public Pojo(int a, float b) {
        this.a = a;
        this.b = b;
    }

    @Override
    public String toString() {
        return "Pojo{" +
                "a=" + a +
                ", b=" + b +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Pojo pojo = (Pojo) o;
        return a == pojo.a && Float.compare(pojo.b, b) == 0;
    }

    @Override
    public int hashCode() {
        return Objects.hash(a, b);
    }


    public float getB() {
        return b;
    }

    public void setB(float b) {
        this.b = b;
    }
}
