package Main.Rides;

/**
 * The BusReservation interface defines a contract for updating ticket prices of bus rides.
 * Classes implementing this interface must provide an implementation for the 'UpdateTicketPrice' method.
 */
public interface BusReservation {

    /**
     * Updates the ticket price for a bus ride.
     * This method should be implemented to recalculate the ticket price based on reservations or other factors.
     */
    public void UpdateTicketPrice();

}
