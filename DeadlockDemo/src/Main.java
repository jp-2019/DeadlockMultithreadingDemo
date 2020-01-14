public class Main {
    public static void main(String[] args) throws InterruptedException {
        Shared s1 = new Shared();
        Shared s2 = new Shared();

        Thread1 t1 = new Thread1(s1, s2);
        t1.start();

        Thread2 t2 = new Thread2(s1, s2);
        t2.start();

        Utils.sleep(2000);
    }
}

class Shared {
    synchronized void test1(Shared s2) throws InterruptedException {
        System.out.println("test1 begins");
        Utils.sleep(1000);

        s2.test2(this);
        System.out.println("test1 ends");
    }

    synchronized void test2(Shared s1) throws InterruptedException {
        System.out.println("test2 begins");
        Utils.sleep(1000);

        s1.test1(this);
        System.out.println("test2 ends");
    }
}

class Thread1 extends Thread {
    private Shared s1;
    private Shared s2;

    public Thread1(Shared s1, Shared s2) {
        this.s1 = s1;
        this.s2 = s2;
    }

    @Override
    public void run() {
        try {
            s1.test1(s2);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}

class Thread2 extends Thread {
    private Shared s1;
    private Shared s2;

    public Thread2(Shared s1, Shared s2) {
        this.s1 = s1;
        this.s2 = s2;
    }

    @Override
    public void run() {
        try {
            s2.test2(s1);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}

class Utils {
    static void sleep(long mills) throws InterruptedException {
        Thread.sleep(mills);
    }
}
