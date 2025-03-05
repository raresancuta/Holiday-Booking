package ubb.scs.map.vacante.Repository;

import ubb.scs.map.vacante.Domain.Client;
import ubb.scs.map.vacante.Domain.Hobby;

import java.sql.*;
import java.util.Optional;

public class ClientsRepository implements Repository<Long, Client> {
    private String url;
    private String username;
    private String password;

    public ClientsRepository(String url, String username, String password) {
        this.url = url;
        this.username = username;
        this.password = password;
    }

    public Optional<Client> find(Long id){
        try (Connection connection = DriverManager.getConnection(url, username, password);
             PreparedStatement preparedStatement = connection.prepareStatement("SELECT * FROM clients WHERE client_id = ?");
        ) {
            preparedStatement.setLong(1, id);
            try (ResultSet resultSet = preparedStatement.executeQuery()) {
                if (resultSet.next()) {
                    String name = resultSet.getString("name");
                    int fidelity = resultSet.getInt("fidelity_grade");
                    int age = resultSet.getInt("age");
                    Hobby hobby = Hobby.valueOf(resultSet.getString("hobby"));
                    return Optional.of(new Client(id,name,fidelity,age,hobby));
                }
            }
        } catch (SQLException e) {
            return Optional.empty();
        }
        return Optional.empty();
    }

}
