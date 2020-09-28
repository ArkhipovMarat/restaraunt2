import java.util.LinkedHashMap;
import java.util.TreeMap;

public class Restaraunt {
    private LinkedHashMap <String, OrderStatus> orderList;

    public Restaraunt() {
        orderList = new LinkedHashMap<>() ;
    }

    void orderNotify (String visitorName) {
        orderList.put(visitorName,OrderStatus.NOTIFY);
    }

    void orderAccept (String visitorName) {
        if (orderList.containsKey(visitorName)) {
            orderList.put(visitorName,OrderStatus.ACCEPTED);
        }
    }

    void orderCoocking (String visitorName) {
        if (orderList.containsKey(visitorName)) {
            orderList.put(visitorName,OrderStatus.COCKING);
        }
    }

    void orderReady (String visitorName) {
        if (orderList.containsKey(visitorName)) {
            orderList.put(visitorName,OrderStatus.READY);
        }
    }

    void orderSubmitted (String visitorName) {
        if (orderList.containsKey(visitorName)) {
            orderList.put(visitorName,OrderStatus.SUBMITTED);
        }
    }

    void orderProcessed (String visitorName) {
        if (orderList.containsKey(visitorName)) {
            orderList.remove(visitorName);
        }
    }

    public LinkedHashMap <String, OrderStatus> getOrderList() {
        return orderList;
    }
}
