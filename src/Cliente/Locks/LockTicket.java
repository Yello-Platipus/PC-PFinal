package Cliente.Locks;

import java.util.concurrent.atomic.AtomicInteger;

public class LockTicket {

    private volatile int next;
    private volatile AtomicInteger numero;

    public LockTicket() {
        numero = new AtomicInteger(0);
        next = 0;
    }

    public void lock() {
        int turno = numero.getAndAdd(1);
        while (turno != next) ;
    }

    public void unlock() {
        next++;
        next = next;
    }

}