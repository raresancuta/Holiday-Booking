package ubb.scs.map.vacante.Domain;

import java.time.LocalDate;

public class SpecialOfferDTO extends Entity<Double>{
    private String hotelName;
    private String hotelLocation;
    private LocalDate startDate;
    private LocalDate endDate;

    public SpecialOfferDTO(Double id,String hotelName, String hotelLocation, LocalDate startDate, LocalDate endDate) {
        super(id);
        this.hotelName = hotelName;
        this.hotelLocation = hotelLocation;
        this.startDate=startDate;
        this.endDate=endDate;
    }
    public String getHotelName() {
        return hotelName;
    }
    public void setHotelName(String hotelName) {
        this.hotelName = hotelName;
    }
    public String getHotelLocation() {
        return hotelLocation;
    }
    public void setHotelLocation(String hotelLocation) {
        this.hotelLocation = hotelLocation;
    }
    public LocalDate getStartDate() {
        return startDate;
    }
    public void setStartDate(LocalDate startDate) {
        this.startDate = startDate;
    }
    public LocalDate getEndDate() {
        return endDate;
    }
    public void setEndDate(LocalDate endDate) {
        this.endDate = endDate;
    }
}
