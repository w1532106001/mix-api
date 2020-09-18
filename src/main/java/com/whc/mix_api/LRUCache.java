package com.whc.mix_api;

import lombok.Data;

import java.util.*;

/**
 * @author whc
 * @date 2020/9/1
 * @description LRUCache cache = new LRUCache( 2 /* 缓存容量 * /);
 * cache.put(1,1);
 * cache.put(2,2);
 * cache.get(1);       // 返回  1
 * cache.put(3,3);    // 该操作会使得关键字 2 作废
 * cache.get(2);       // 返回 -1 (未找到)
 * cache.put(4,4);    // 该操作会使得关键字 1 作废
 * cache.get(1);       // 返回 -1 (未找到)
 * cache.get(3);       // 返回  3
 * cache.get(4);       // 返回  4
 */

class LRUCache {
    int capacity;
    LinkedList<Node> nodeList;
    public LRUCache(int capacity) {
        this.capacity = capacity;
        nodeList = new LinkedList<>();
    }

    class Node{
        private int key;
        private int value;

        public Node(int key, int value) {
            this.key = key;
            this.value = value;
        }
    }

    public static void main(String[] args) {
        int num = 3000;
        LRUCache cache = new LRUCache( num /* 缓存容量 */ );
        System.out.println("put开始");
        for (int i = 0; i < num; i++) {
            cache.put(i,i);
        }
        System.out.println("put结束");
        long startTime = System.currentTimeMillis();
        System.out.println(cache.get(2580));
        System.out.println(System.currentTimeMillis()-startTime);
    }

    public int get(int key) {
        for (int i = 0; i < nodeList.size(); i++) {
           Node node = nodeList.get(i);
            if (node.key == key) {
                nodeList.remove(i);
                nodeList.addFirst(node);
                return node.value;
            }
        }
        return -1;
    }

    public void put(int key, int value) {
        boolean isHas = false;
        for (int i = 0; i < nodeList.size(); i++) {
            Node node = nodeList.get(i);
            if(node.key==key){
                nodeList.remove(i);
                nodeList.addFirst(new Node(key,value));
                isHas = true;
                break;
            }
        }
        if(!isHas){
            if(nodeList.size()<capacity){
                nodeList.addFirst(new Node(key,value));
            }else{
                nodeList.remove(capacity-1);
                nodeList.addFirst(new Node(key,value));
            }
        }

    }
}

