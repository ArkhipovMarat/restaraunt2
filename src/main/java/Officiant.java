import java.util.Map;
import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

public class Officiant implements Runnable{
    private Restaraunt restaraunt;
    private Lock lock;

    public Officiant(Restaraunt restaraunt) {
        this.restaraunt = restaraunt;
        this.lock = new ReentrantLock();
        Condition condition = lock.newCondition();
    }

    public void run() {
        String officiantName = Thread.currentThread().getName();
        System.out.println("Оффициант " + officiantName + ": в ресторане");

        Condition condition = lock.newCondition();
//        пока не обслужат 5 посетителей

        while (true) {
            lock.lock();
            try {
                for (Map.Entry<String, OrderStatus> entry : restaraunt.getOrderList().entrySet()) {
                    switch ((entry.getValue())) {
                        case NOTIFY:
                            restaraunt.orderAccept(entry.getKey());
                            System.out.println("Оффициант " + officiantName + ": принял заказ от посетителя" + entry.getKey());
                            entry.setValue(OrderStatus.READY); // имитация повара, вручную
                            return;
                        case READY:
                            return;
                    }
                }
//                condition.await();
//            } catch (InterruptedException e) {
//                e.printStackTrace();
            } finally {
                lock.unlock();
            }
        }
    }
}