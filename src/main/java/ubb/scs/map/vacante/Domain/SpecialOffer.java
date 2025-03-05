package ubb.scs.map.vacante.Domain;

import java.time.LocalDate;

public class SpecialOffer extends Entity<Double>{
    private Double hotelId;
    private LocalDate startDate;
    private LocalDate endDate;
    private int percents;
    public SpecialOffer(Double id,Double hotelId, LocalDate startDate, LocalDate endDate, int percents) {
        super(id);
        this.hotelId = hotelId;
        this.startDate = startDate;
        this.endDate = endDate;
        this.percents = percents;
    }
    public Double getHotelId() {
        return hotelId;
    }
    public LocalDate getStartDate() {
        return startDate;
    }
    public LocalDate getEndDate() {
        return endDate;
    }
    public int getPercents() {
        return percents;
    }
}
