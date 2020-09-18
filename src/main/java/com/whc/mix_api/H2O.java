package com.whc.mix_api;

import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

/**
 * @author whc
 * @date 2020/8/31
 * @description
 */

public class H2O {
    static Object object = new Object();
    ConcurrentHashMap<String,Integer> map = new ConcurrentHashMap<>();

    public H2O() {
    map.put("H",0);
    map.put("O",0);
    }

    public static void main(String[] args) throws InterruptedException {
        H2O h2O = new H2O();
        String a = "OOHHHH";
        for (String s : a.split("")) {
            if(s.equals("H")){
                new Thread(() -> {
                    try {
                        h2O.hydrogen();
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }).start();
            }else{
                new Thread(()->{
                    try {
                        h2O.oxygen();
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }).start();
            }
        }
    }
    public void hydrogen() throws InterruptedException {
        synchronized (object){
            while (map.get("H")==2){
                object.wait();
            }
            if(map.get("H")<2){
                // releaseHydrogen.run() outputs "H". Do not change or remove this line.
//                releaseHydrogen.run();
                System.out.println("H");
                map.put("H",map.get("H")+1);
            }
            if(map.get("H")==2&&map.get("O")==1){
                map.put("H",0);
                map.put("O",0);
            }
            object.notifyAll();

        }

    }

    public void oxygen() throws InterruptedException {
        synchronized (object){
                while (map.get("O")==1){
                    object.wait();
                }
                if(map.get("O")<1){
                    // releaseOxygen.run() outputs "O". Do not change or remove this line.
//                releaseOxygen.run();
                    System.out.println("O");
                    map.put("O",map.get("O")+1);
                }
                if(map.get("H")==2&&map.get("O")==1){
                    map.put("H",0);
                    map.put("O",0);
                }
                object.notifyAll();

        }

    }
}
