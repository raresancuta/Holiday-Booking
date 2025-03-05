module ubb.scs.map.vacante {
    requires javafx.controls;
    requires javafx.fxml;
    requires java.sql;


    opens ubb.scs.map.vacante to javafx.fxml;
    opens ubb.scs.map.vacante.Controller to javafx.fxml;
    exports ubb.scs.map.vacante;
    exports ubb.scs.map.vacante.Service;
    opens ubb.scs.map.vacante.Service to javafx.fxml;

    exports ubb.scs.map.vacante.Repository;
    exports ubb.scs.map.vacante.Domain;
    exports ubb.scs.map.vacante.Utils;
}