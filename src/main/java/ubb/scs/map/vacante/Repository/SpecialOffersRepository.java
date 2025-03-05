package ubb.scs.map.vacante.Repository;

import ubb.scs.map.vacante.Domain.Client;
import ubb.scs.map.vacante.Domain.Hobby;
import ubb.scs.map.vacante.Domain.SpecialOffer;

import java.sql.*;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class SpecialOffersRepository implements Repository<Double, SpecialOffer> {
    private String url;
    private String username;
    private String password;

    public SpecialOffersRepository(String url, String username, String password) {
        this.url = url;
        this.username = username;
        this.password = password;
    }

    public Optional<SpecialOffer> find(Double id){
        try (Connection connection = DriverManager.getConnection(url, username, password);
             PreparedStatement preparedStatement = connection.prepareStatement("SELECT * FROM special_offers WHERE special_offer_id = ?");
        ) {
            preparedStatement.setDouble(1, id);
            try (ResultSet resultSet = preparedStatement.executeQuery()) {
                if (resultSet.next()) {
                    Double hotelId = resultSet.getDouble("hotel_id");
                    LocalDate startDate = resultSet.getTimestamp("start_date").toLocalDateTime().toLocalDate();
                    LocalDate endDate = resultSet.getTimestamp("end_date").toLocalDateTime().toLocalDate();
                    int percent = resultSet.getInt("percents");
                    return Optional.of(new SpecialOffer(id, hotelId, startDate, endDate, percent));
                }
            }
        } catch (SQLException e) {
            return Optional.empty();
        }
        return Optional.empty();
    }

    public List<SpecialOffer> findAll(){
        List<SpecialOffer> specialOffers = new ArrayList<>();
        try (Connection connection = DriverManager.getConnection(url, username, password);
             PreparedStatement preparedStatement = connection.prepareStatement("SELECT * FROM special_offers");
        ) {
            try (ResultSet resultSet = preparedStatement.executeQuery()) {
                while (resultSet.next()) {
                    Double id = resultSet.getDouble("special_offer_id");
                    Double hotelId = resultSet.getDouble("hotel_id");
                    LocalDate startDate = resultSet.getTimestamp("start_date").toLocalDateTime().toLocalDate();
                    LocalDate endDate = resultSet.getTimestamp("end_date").toLocalDateTime().toLocalDate();
                    int percent = resultSet.getInt("percents");
                    specialOffers.add(new SpecialOffer(id, hotelId, startDate, endDate, percent));
                }
            }
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
        return specialOffers;
    }

}