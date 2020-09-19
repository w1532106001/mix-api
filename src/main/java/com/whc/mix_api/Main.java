package com.whc.mix_api;

import org.springframework.util.Assert;

import java.io.IOException;

/**
 * @author whc
 * @date 2020/6/2
 * @description
 */

public class Main {
    public static void main(String[] args) throws IOException {
        Integer i = 1;
        Assert.notNull(i, "数据为空");
        System.out.println(123);
    }
}
