package common;

import lombok.Data;

import java.util.Random;

/**
 *
 *  
 *  * @author zz_huns  
 *  @version Id: ThreadLocalDemo.java, v 0.1 2020/6/19 12:38 AM zz_huns Exp $$
 *
 */
public class ThreadLocalDemo {

    static ThreadLocal<App> local = new ThreadLocal<App>(){
        @Override
        protected App initialValue() {
            return new App();
        }
    };


//    static ThreadLocal<Integer> local = new ThreadLocal<Integer>(){
//        @Override
//        protected Integer initialValue() {
//            return 0;
//        }
//    };



    public static void main(String[] args) {

        String name = "";
        Thread[] threads = new Thread[5];
        for (int i = 0; i <5 ; i++) {
            threads[i] = new Thread(() -> {
                App app = local.get();
                Integer age = app.getAge();
                app.setAge(age);
                local.set(app);
                System.out.println(Thread.currentThread().getName()+"::"+app.getAge());
            });
        }


        for (int i = 0; i <5 ; i++) {
            threads[i].start();
        }
    }

}
