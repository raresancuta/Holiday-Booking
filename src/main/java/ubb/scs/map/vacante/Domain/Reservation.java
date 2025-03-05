package ubb.scs.map.vacante.Domain;

import java.time.LocalDateTime;

public class Reservation extends Entity<Double>{
    private Long clientId;
    private Double hotelId;
    private LocalDateTime startDate;
    private int noNights;
    public Reservation(Double id,Long clientId, Double hotelId, LocalDateTime startDate, int noNights) {
        super(id);
        this.clientId = clientId;
        this.hotelId = hotelId;
        this.startDate = startDate;
        this.noNights = noNights;
    }
    public Long getClientId() {
        return clientId;
    }
    public Double getHotelId() {
        return hotelId;
    }
    public LocalDateTime getStartDate() {
        return startDate;
    }
    public int getNoNights() {
        return noNights;
    }
}
