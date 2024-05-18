import java.util.PriorityQueue;
class MyTimerTask implements Comparable<MyTimerTask>{
    private Runnable runnable;//如何执行任务

    private long time;//执行时间(时间戳)

    public MyTimerTask(Runnable runnable,long delay){
        this.runnable = runnable;
        this.time = System.currentTimeMillis() + delay;
    }

    public long getTime(){
        return time;
    }

    public Runnable getRunnable() {
        return runnable;
    }

    @Override
    public int compareTo(MyTimerTask o) {
        return (int) (this.time - o.time);
    }//把最早执行的放前面
}

class MyTimer{
    private PriorityQueue<MyTimerTask> queue = new PriorityQueue<MyTimerTask>();//保存任务的优先级队列

    private Object locker = new Object();//锁对象

    public void schedule(Runnable runnable,long delay){//添加任务方法
        synchronized (locker){
            queue.offer(new MyTimerTask(runnable,delay));
            locker.notify();//唤醒等待线程
        }
    }

    public MyTimer(){
        //创建扫描线程
        Thread t = new Thread(()->{
           while(true){
               try {
                   synchronized (locker) {
                       if (queue.isEmpty()) {
                           locker.wait();//没有任务就等待，有任务的时候添加任务的线程会notify
                       }
                       MyTimerTask myTimerTask = queue.peek();//取得最早执行的任务
                       long currentTime = System.currentTimeMillis();
                       if(currentTime >= myTimerTask.getTime()){//已经到了要执行任务的时间
                           myTimerTask.getRunnable().run();
                           queue.poll();//删除已经执行的任务
                       }
                       else{
                           locker.wait(myTimerTask.getTime() - currentTime);//最早的任务都还没有到时间，就等待,存有超时时间。
                       }
                   }
               } catch (InterruptedException e){
                   e.printStackTrace();
               }
           }
        });
        t.start();
    }
}

public class demo4 {
    public static void main(String[] args) {
        MyTimer myTimer = new MyTimer();
        myTimer.schedule(()->{
            System.out.println("1000");
        },1000);
        myTimer.schedule(()->{
            System.out.println("2000");
        },2000);
        myTimer.schedule(()->{
            System.out.println("3000");
        },3000);
    }
}
