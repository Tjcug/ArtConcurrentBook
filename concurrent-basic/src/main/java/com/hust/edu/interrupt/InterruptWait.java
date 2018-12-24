package com.hust.edu.interrupt;

import com.hust.edu.SleepUtils;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.concurrent.TimeUnit;

/**
 * locate com.hust.edu
 * Created by MasterTj on 2018/12/24.
 */
public class InterruptWait {
    static boolean flag = true;
    static Object  lock = new Object();

    public static void main(String[] args) throws Exception {
        Thread waitThread = new Thread(new Wait(), "WaitThread");
        waitThread.start();
        TimeUnit.SECONDS.sleep(1);

        waitThread.interrupt();
    }

    static class Wait implements Runnable {
        public void run() {
            // ������ӵ��lock��Monitor
            synchronized (lock) {
                // ������������ʱ������wait��ͬʱ�ͷ���lock����
                while (flag) {
                    try {
                        System.out.println(Thread.currentThread() + " flag is true. wait @ "
                                + new SimpleDateFormat("HH:mm:ss").format(new Date()));
                        lock.wait();
                    } catch (InterruptedException e) {
                        flag=false;
                        e.printStackTrace();
                    }
                }
                // ��������ʱ����ɹ���
                System.out.println(Thread.currentThread() + " flag is false. running @ "
                        + new SimpleDateFormat("HH:mm:ss").format(new Date()));
            }
        }
    }

    static class Notify implements Runnable {
        public void run() {
            // ������ӵ��lock��Monitor
            synchronized (lock) {
                // ��ȡlock������Ȼ�����֪ͨ��֪ͨʱ�����ͷ�lock������
                // ֱ����ǰ�߳��ͷ���lock��WaitThread���ܴ�wait�����з���
                System.out.println(Thread.currentThread() + " hold lock. notify @ " + new SimpleDateFormat("HH:mm:ss").format(new Date()));
                lock.notifyAll();
                flag = false;
                SleepUtils.second(5);
            }
        }
    }
}
