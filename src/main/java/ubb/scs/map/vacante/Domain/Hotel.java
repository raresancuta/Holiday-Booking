package ubb.scs.map.vacante.Domain;

public class Hotel extends Entity<Double>{
    private String locationName;
    private String hotelName;
    private int noRooms;
    private Double pricePerNight;
    public Hotel(Double id, String locationName, String hotelName, int noRooms, Double pricePerNight) {
        super(id);
        this.locationName = locationName;
        this.hotelName = hotelName;
        this.noRooms = noRooms;
        this.pricePerNight = pricePerNight;
    }

    public String getHotelName() {
        return hotelName;
    }
    public String getLocationName() {
        return locationName;
    }
    public int getNoRooms() {
        return noRooms;
    }
    public Double getPricePerNight() {
        return pricePerNight;
    }
}
