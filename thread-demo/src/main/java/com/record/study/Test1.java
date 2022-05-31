package com.record.study;

import org.junit.Test;

import java.util.concurrent.ExecutionException;
import java.util.concurrent.FutureTask;

public class Test1 {

    //方式一
    @Test
    public void test1(){
        Thread t1 = new Thread("t1"){
            @Override
            public void run(){
                System.out.println("方式一创建的线程"+Thread.currentThread().getName()+"开始运行");
            }
        };
        t1.start();
    }

    //方式二
    @Test
    public void test2(){
        Runnable runnable = new Runnable() {
            @Override
            public void run() {
                System.out.println("方式二创建的线程"+Thread.currentThread().getName()+"开始运行");
            }
        };
        Thread t2 = new Thread(runnable);
        t2.start();
    }

    //方式三
    @Test
    public void test3(){
        // 创建任务对象
        FutureTask<Integer> task3 = new FutureTask<>(() -> {
            System.out.println("方式三创建的线程"+Thread.currentThread().getName()+"开始运行");
            return 100;
        });
        // 参数1 是任务对象; 参数2 是线程名字，推荐
        new Thread(task3, "t3").start();
        // 主线程阻塞，同步等待 task 执行完毕的结果
        try {
            Integer result = task3.get();
            System.out.println("结果是"+result);
        } catch (InterruptedException e) {
            e.printStackTrace();
        } catch (ExecutionException e) {
            e.printStackTrace();
        }
    }
}
