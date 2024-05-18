//import java.util.Scanner;
//
//public class demo2 {
//    public volatile static boolean k = true;
//    public static void main(String[] args) throws InterruptedException {
//        Thread t1 = new Thread(()->{
//            while(k){
//
//            }
//        });
//        Thread t2 = new Thread(()->{
//            System.out.println("输入:");
//            Scanner scanner = new Scanner(System.in);
//            k = scanner.nextBoolean();
//        });
//        t1.start();
//        t2.start();
//        t1.join();
//        t2.join();
//    }
//}
