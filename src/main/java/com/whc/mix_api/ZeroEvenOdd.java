package com.whc.mix_api;

import java.util.function.IntConsumer;

/**
 * @author whc
 * @date 2020/8/27
 * @description
 */

public class ZeroEvenOdd {
    private static Object object = new Object();

    public static void main(String[] args) {
        Thread zero = new Thread() {
            @Override
            public void run() {
                super.run();
                while (i != n) {
                    synchronized (object) {
                        if (isPrintZero) {
                            System.out.println(0);
                            i++;
                            isPrintZero = false;
                        }
                        try {
                            object.notifyAll();
                            object.wait();
                        } catch (InterruptedException e) {
                            e.printStackTrace();
                        }
                    }
                }
            }
        };
        Thread even = new Thread() {
            @Override
            public void run() {
                super.run();
                while (i <= n && !Thread.currentThread().isInterrupted()) {
                    synchronized (object) {
                        if (!isPrintZero && (i & 1) == 0) {
                            System.out.println(i);
                            isPrintZero = true;
                        }
                        if (i == n) {
                            object.notifyAll();
                            Thread.currentThread().interrupt();
                        } else {
                            try {
                                object.notifyAll();
                                object.wait();
                            } catch (InterruptedException e) {
                                e.printStackTrace();
                            }
                        }

                    }
                }

            }
        };

        Thread odd = new Thread() {
            @Override
            public void run() {
                super.run();
                while (i <= n && !Thread.currentThread().isInterrupted()) {
                    synchronized (object) {
                        if (!isPrintZero && (i & 1) == 1) {
                            System.out.println(i);
                            isPrintZero = true;
                        }
                        if (i == n) {
                            object.notifyAll();
                            Thread.currentThread().interrupt();
                        } else {
                            try {
                                object.notifyAll();
                                object.wait();

                            } catch (InterruptedException e) {
                                e.printStackTrace();
                            }
                        }

                    }
                }
            }
        };

        zero.start();
        even.start();
        odd.start();
    }

    private static int n = 2;
    static int i = 0;
    static boolean isPrintZero = true;

    public ZeroEvenOdd(int n) {
        this.n = n;
    }

    // printNumber.accept(x) outputs "x", where x is an integer.
    public void zero(IntConsumer printNumber) throws InterruptedException {
        while (i + 1 != n) {
            synchronized (this) {
                if (!isPrintZero) {
                    wait();
                }
                notifyAll();
                printNumber.accept(0);
                i++;
                isPrintZero = false;
            }
        }

    }

    public void even(IntConsumer printNumber) throws InterruptedException {
        while (i + 1 != n) {
            synchronized (this) {
                if (isPrintZero || (i & 1) == 1) {
                    wait();
                }
                notifyAll();
                printNumber.accept(i);
                isPrintZero = true;
            }
        }
    }

    public void odd(IntConsumer printNumber) throws InterruptedException {
        while (i + 1 != n) {
            synchronized (this) {
                if (isPrintZero || (i & 1) == 0) {
                    wait();
                }
                notifyAll();
                printNumber.accept(i);
                isPrintZero = true;
            }
        }
    }

}
