package com.hust.edu;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;

class SynchronizedExample {
    int     a    = 0;
    boolean flag = false;

    public synchronized void writer() { //��ȡ��
        a = 1;
        flag = true;
    } //�ͷ���

    public synchronized void reader() { //��ȡ��
        if (flag) {
            int i = a;
            System.out.println(i);
            //����
        } //�ͷ���
    }

    public static void main(String[] args) throws InterruptedException {
        SynchronizedExample synchronizedExample=new SynchronizedExample();
        ExecutorService executorService= Executors.newCachedThreadPool();
        executorService.submit(new Runnable() {
            @Override
            public void run() {
//                try {
//                    Thread.sleep(20);
//                } catch (InterruptedException e) {
//                    e.printStackTrace();
//                }
                synchronizedExample.writer();
            }
        });
        executorService.submit(new Runnable() {
            @Override
            public void run() {
                synchronizedExample.reader();
            }
        });

        executorService.shutdown();
        executorService.awaitTermination(50, TimeUnit.SECONDS);
        System.out.println("a: "+synchronizedExample.a+" flag:"+synchronizedExample.flag);
    }
}
