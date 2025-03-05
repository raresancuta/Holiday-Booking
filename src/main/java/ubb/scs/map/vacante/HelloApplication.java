package ubb.scs.map.vacante;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.stage.Stage;
import ubb.scs.map.vacante.Controller.ClientViewController;
import ubb.scs.map.vacante.Domain.Client;
import ubb.scs.map.vacante.Repository.ClientsRepository;
import ubb.scs.map.vacante.Repository.HotelsRepository;
import ubb.scs.map.vacante.Repository.ReservationsRepository;
import ubb.scs.map.vacante.Repository.SpecialOffersRepository;
import ubb.scs.map.vacante.Service.Service;

import java.io.IOException;
import java.util.Optional;

public class HelloApplication extends Application {
    private Service service;
    private static String[] sysArgs;
    @Override
    public void start(Stage stage) throws IOException {
        String username = "postgres";
        String password = "postgres";
        String url = "jdbc:postgresql://localhost:5432/Vacante";
        ClientsRepository clientsRepository = new ClientsRepository(url, username, password);
        HotelsRepository hotelsRepository = new HotelsRepository(url, username, password);
        ReservationsRepository reservationsRepository = new ReservationsRepository(url, username, password);
        SpecialOffersRepository specialOffersRepository = new SpecialOffersRepository(url, username, password);
        service = new Service(clientsRepository, hotelsRepository, reservationsRepository, specialOffersRepository);

        for(String id: sysArgs){
            Optional<Client> optionalClient = service.verifyClient(Long.parseLong(id));
            if(optionalClient.isPresent()){
                Client client = optionalClient.get();
                FXMLLoader fxmlLoader1 = new FXMLLoader(HelloApplication.class.getResource("/ubb/scs/map/vacante/client-view.fxml"));
                Scene scene1 = new Scene(fxmlLoader1.load());
                Stage stage1 = new Stage();
                stage1.setTitle(client.getName());
                stage1.setScene(scene1);
                stage1.show();
                ClientViewController loginController = fxmlLoader1.getController();
                loginController.setServ(stage,service,client);
            }
        }
    }

    public static void main(String[] args) {
        sysArgs = args;
        launch();
    }
}