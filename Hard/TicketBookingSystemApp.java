import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

class TicketBookingSystem {
    private int availableSeats;
    private final Lock lock = new ReentrantLock();

    public TicketBookingSystem(int seats) {
        this.availableSeats = seats;
    }

    public void bookTicket(String passengerName) {
        lock.lock();
        try {
            if (availableSeats > 0) {
                System.out.println(passengerName + " successfully booked a seat.");
                availableSeats--;
            } else {
                System.out.println("No available seats for " + passengerName);
            }
        } finally {
            lock.unlock();
        }
    }
}

class Passenger extends Thread {
    private final TicketBookingSystem bookingSystem;
    private final String passengerName;

    public Passenger(TicketBookingSystem bookingSystem, String passengerName, int priority) {
        this.bookingSystem = bookingSystem;
        this.passengerName = passengerName;
        setPriority(priority);
    }

    @Override
    public void run() {
        bookingSystem.bookTicket(passengerName);
    }
}

public class TicketBookingSystemApp {
    public static void main(String[] args) {
        TicketBookingSystem system = new TicketBookingSystem(5);

        Passenger p1 = new Passenger(system, "VIP John", Thread.MAX_PRIORITY);
        Passenger p2 = new Passenger(system, "VIP Alice", Thread.MAX_PRIORITY);
        Passenger p3 = new Passenger(system, "Regular Bob", Thread.NORM_PRIORITY);
        Passenger p4 = new Passenger(system, "Regular Charlie", Thread.NORM_PRIORITY);
        Passenger p5 = new Passenger(system, "Regular Dave", Thread.NORM_PRIORITY);
        Passenger p6 = new Passenger(system, "Regular Eve", Thread.NORM_PRIORITY);

        p1.start();
        p2.start();
        p3.start();
        p4.start();
        p5.start();
        p6.start();
    }
}
