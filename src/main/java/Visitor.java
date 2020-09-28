import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

public class Visitor implements Runnable {
    private Restaraunt restaraunt;
    private Lock lock;

    public Visitor(Restaraunt restaraunt) {
        this.restaraunt = restaraunt;
        this.lock = new ReentrantLock();

    }

    public void run() {
        String visitorName = Thread.currentThread().getName();
        System.out.println("Посетитель " + visitorName + ": в ресторане");

        lock.lock();
        try {
            restaraunt.orderNotify(visitorName);
            System.out.println("Посетитель " + visitorName + ": сделал заказ");
        } finally {
            lock.unlock();
        }

        Condition condition = lock.newCondition();
        lock.lock();
        while (!restaraunt.getOrderList().get(visitorName).equals(OrderStatus.READY)) {
            try {
                System.out.println(restaraunt.getOrderList());
                condition.await();
            } catch (InterruptedException e) {
                e.printStackTrace();
            } finally {
                lock.unlock();
            }
        }

        condition.signal();
        System.out.println("Посетитель" + visitorName + ": получил заказ и приступил к еде");

        lock.lock();
        try {
            restaraunt.orderProcessed(visitorName);
            System.out.println("Посетитель" + visitorName + ": вышел из ресторана");
        } finally {
            lock.unlock();
        }
    }
}
