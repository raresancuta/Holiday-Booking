package ubb.scs.map.vacante.Repository;

import ubb.scs.map.vacante.Domain.Client;
import ubb.scs.map.vacante.Domain.Hobby;
import ubb.scs.map.vacante.Domain.Hotel;
import ubb.scs.map.vacante.Domain.SpecialOffer;

import java.sql.*;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class HotelsRepository implements Repository<Double, Hotel> {
    private String url;
    private String username;
    private String password;

    public HotelsRepository(String url, String username, String password) {
        this.url = url;
        this.username = username;
        this.password = password;
    }

    public Optional<Hotel> find(Double id){
        try (Connection connection = DriverManager.getConnection(url, username, password);
             PreparedStatement preparedStatement = connection.prepareStatement("SELECT * FROM hotels WHERE hotel_id = ?");
        ) {
            preparedStatement.setDouble(1, id);
            try (ResultSet resultSet = preparedStatement.executeQuery()) {
                if (resultSet.next()) {
                    String location = resultSet.getString("location_name");
                    String hotel_name = resultSet.getString("hotel_name");
                    int noRooms = resultSet.getInt("no_rooms");
                    Double price = resultSet.getDouble("price_per_night");
                    return Optional.of(new Hotel(id, location, hotel_name, noRooms, price));
                }
            }
        } catch (SQLException e) {
            return Optional.empty();
        }
        return Optional.empty();
    }

    public List<Hotel> findAll(){
        List<Hotel> hotels = new ArrayList<>();
        try (Connection connection = DriverManager.getConnection(url, username, password);
             PreparedStatement preparedStatement = connection.prepareStatement("SELECT * FROM hotels");
        ) {
            try (ResultSet resultSet = preparedStatement.executeQuery()) {
                while (resultSet.next()) {
                    Double id = resultSet.getDouble("hotel_id");
                    String location = resultSet.getString("location_name");
                    String hotel_name = resultSet.getString("hotel_name");
                    int noRooms = resultSet.getInt("no_rooms");
                    Double price = resultSet.getDouble("price_per_night");
                    hotels.add(new Hotel(id, location, hotel_name, noRooms, price));
                }
            }
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
        return hotels;
    }

}
