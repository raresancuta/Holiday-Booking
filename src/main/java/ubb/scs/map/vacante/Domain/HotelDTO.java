package ubb.scs.map.vacante.Domain;

public class HotelDTO extends Entity<Double>{
    private String hotelName;
    private Double occupacyGrade;
    private Double pricePerNight;
    public HotelDTO(Double id, String hotelName, Double occupacyGrade, Double pricePerNight) {
        super(id);
        this.hotelName = hotelName;
        this.occupacyGrade = occupacyGrade;
        this.pricePerNight = pricePerNight;

    }
    public String getHotelName() {
        return hotelName;
    }
    public void setHotelName(String hotelName) {
        this.hotelName = hotelName;
    }
    public Double getOccupacyGrade() {
        return occupacyGrade;
    }
    public void setOccupacyGrade(Double occupacyGrade) {
        this.occupacyGrade = occupacyGrade;
    }
    public Double getPricePerNight() {
        return pricePerNight;
    }
    public void setPricePerNight(Double pricePerNight) {
        this.pricePerNight = pricePerNight;
    }

}
