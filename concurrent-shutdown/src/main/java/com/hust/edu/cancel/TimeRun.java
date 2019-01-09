package com.hust.edu.cancel;

import java.util.concurrent.*;

/**
 * locate com.hust.edu.cancel
 * Created by MasterTj on 2019/1/9.
 */
public class TimeRun {
    private static ExecutorService executorService= Executors.newFixedThreadPool(5);

    public static void timeRun(Runnable runnable, long timeout, TimeUnit timeUnit){
        Future<?> submit = executorService.submit(runnable);
        try {
            submit.get(timeout,timeUnit);
        } catch (InterruptedException e) {
            e.printStackTrace();
        } catch (ExecutionException e) {
            e.printStackTrace();
        } catch (TimeoutException e) {
            //���������񽫱�ȡ��
            e.printStackTrace();
        } finally {
            //��������Ѿ���������ôִ��ȡ������Ҳ��������κ�Ӱ��
            //��������������У���ô���ᱻ�ж�
            submit.cancel(true);
        }
    }
}
