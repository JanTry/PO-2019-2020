package com.company;

import java.io.IOException;

public class Main {
    public static void main(String[] args) throws IOException {
        Evolution darwin = new Evolution(50, 30, 40, 10);
        darwin.run(100);
    }
}
