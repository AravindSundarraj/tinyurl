package com.project.tinyurl;

import java.util.zip.CRC32;

public class Test {
    public static void main(String args[]){


       //==========
        String input = "Aravind Sundarraj";
        CRC32 crc = new CRC32();
        crc.update(input.getBytes());
        System.out.println("input:"+input);
        System.out.println("CRC32:"+crc.getValue());
    }
}
