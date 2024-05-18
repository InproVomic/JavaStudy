import java.util.concurrent.CountDownLatch;

public class demo6 {
    public static void main(String[] args) throws InterruptedException {
        CountDownLatch countDownLatch = new CountDownLatch(10);
        for (int i = 0;i < 10;++i){
            int id = i;
            Thread t = new Thread(()->{
                System.out.println(id);
                countDownLatch.countDown();
            });
            t.start();
        }
        countDownLatch.await();

    }
}
