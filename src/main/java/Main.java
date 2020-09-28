public class Main {
    public static void main(String[] args) {
        Restaraunt restaraunt = new Restaraunt();
        Visitor visitor = new Visitor(restaraunt);
        Officiant officiant = new Officiant(restaraunt);

        new Thread(visitor, "1ый").start();
        new Thread(visitor, "2ой").start();
        new Thread(officiant,"первый").start();;
        new Thread(officiant,"второй").start();;
    }
}
