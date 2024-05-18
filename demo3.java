class myBlockQueue{
    private String[] data = new String[1000];
    private volatile int head = 0;//记录可消费的当前位置
    private volatile int tail = 0;//记录可生产的当前位置
    private volatile int size = 0;//记录已经生产的数据量

    public void put(String elem) throws InterruptedException {
        synchronized (this){
            while(size == data.length){//这里用while是为了保证数据可靠性
                this.wait();
            }
            data[tail++] = elem;
            if(tail==data.length){
                tail = 0;
            }
            ++size;
            this.notify();
        }
    }

    public String take() throws InterruptedException {
        while(size == 0){
            this.wait();
        }
        String ret = data[head++];
        if(head== data.length){
            head = 0;
        }
        --size;
        this.notify();
        return ret;
    }
}

class myInstance{
    public static volatile myInstance instance = null;//加volatile是为了防止指令重排序导致的bug
    private myInstance(){}
    public myInstance getInstance(){
        if(instance==null){
            synchronized (this){
                if(instance==null){
                    instance = new myInstance();
                }
            }
        }
        return instance;
    }
}

public class demo3 {

}
