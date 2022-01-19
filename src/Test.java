import jianzhioffer.Array;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.Hashtable;
import java.util.List;
import java.util.TreeSet;
import java.util.concurrent.Callable;
import java.util.concurrent.ThreadPoolExecutor;

public class Test {
    public static void main(String[] args) {
        TreeSet<Integer> a = new TreeSet<>();
    }

    class T extends Thread {
        @Override
        public void run() {
            System.out.println("我是继承Thread的任务");
        }
    }

    class R implements Runnable {

        @Override
        public void run() {
            System.out.println("我是实现Runnable的任务");
        }
    }

    class C implements Callable<String> {

        @Override
        public String call() throws Exception {
            System.out.println("我是实现Callable的任务");
            return "success";
        }
    }
}
