package ubb.scs.map.vacante.Repository;

import ubb.scs.map.vacante.Domain.Hotel;
import ubb.scs.map.vacante.Domain.Reservation;
import ubb.scs.map.vacante.Domain.SpecialOffer;

import java.sql.*;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class ReservationsRepository implements Repository<Double, Reservation> {
    private String url;
    private String username;
    private String password;

    public ReservationsRepository(String url, String username, String password) {
        this.url = url;
        this.username = username;
        this.password = password;
    }

    public Optional<Reservation> find(Double id){
        try (Connection connection = DriverManager.getConnection(url, username, password);
             PreparedStatement preparedStatement = connection.prepareStatement("SELECT * FROM reservations WHERE reservation_id = ?");
        ) {
            preparedStatement.setDouble(1, id);
            try (ResultSet resultSet = preparedStatement.executeQuery()) {
                if (resultSet.next()) {
                    Long client_id = resultSet.getLong("client_id");
                    Double hotel_id = resultSet.getDouble("hotel_id");
                    LocalDateTime startDate = resultSet.getTimestamp("start_date").toLocalDateTime();
                    int noNights = resultSet.getInt("no_nights");
                    return Optional.of(new Reservation(id,client_id,hotel_id,startDate,noNights));
                }
            }
        } catch (SQLException e) {
            return Optional.empty();
        }
        return Optional.empty();
    }
    public List<Reservation> findAll(){
        List<Reservation> reservations = new ArrayList<>();
        try (Connection connection = DriverManager.getConnection(url, username, password);
             PreparedStatement preparedStatement = connection.prepareStatement("SELECT * FROM reservations");
        ) {
            try (ResultSet resultSet = preparedStatement.executeQuery()) {
                while (resultSet.next()) {
                    Double id = resultSet.getDouble("reservation_id");
                    Long client_id = resultSet.getLong("client_id");
                    Double hotel_id = resultSet.getDouble("hotel_id");
                    LocalDateTime startDate = resultSet.getTimestamp("start_date").toLocalDateTime();
                    int noNights = resultSet.getInt("no_nights");
                    reservations.add(new Reservation(id,client_id,hotel_id,startDate,noNights));
                }
            }
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
        return reservations;
    }

    public void add(Reservation reservation){
        try (Connection connection = DriverManager.getConnection(url, username, password);
             PreparedStatement preparedStatement = connection.prepareStatement("""
            insert into reservations (client_id, hotel_id, start_date, no_nights) values (?, ?, ?, ?)""");
        ) {
            preparedStatement.setLong(1, reservation.getClientId());
            preparedStatement.setDouble(2, reservation.getHotelId());
            preparedStatement.setTimestamp(3,Timestamp.valueOf(reservation.getStartDate()));
            preparedStatement.setInt(4, reservation.getNoNights());
            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
    }

}
