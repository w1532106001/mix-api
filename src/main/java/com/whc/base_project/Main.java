package com.whc.base_project;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;

import java.io.IOException;

/**
 * @author whc
 * @date 2020/6/2
 * @description
 */

public class Main {
    public static void main(String[] args) throws IOException {
        String url = "/7_7067/166219_7.html";
        for (int i = 0; i < 20; i++) {
            Document document = Jsoup.connect("http://m.bz001.cc"+url).proxy("127.0.0.1", 1080).ignoreContentType(true).ignoreHttpErrors(true).get();
            System.out.println(document.getElementById("novelcontent"));
            url = document.getElementsByClass("p4").attr("href");
            if(i==19){
                System.out.println(document.getElementsByClass("p4").attr("href"));
                System.out.println(document.getElementById("p4").attr("src"));
            }
        }

    }
}
