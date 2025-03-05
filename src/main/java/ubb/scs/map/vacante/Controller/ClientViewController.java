package ubb.scs.map.vacante.Controller;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.Stage;
import ubb.scs.map.vacante.Domain.Client;
import ubb.scs.map.vacante.Domain.HotelDTO;
import ubb.scs.map.vacante.Domain.SpecialOffer;
import ubb.scs.map.vacante.Domain.SpecialOfferDTO;
import ubb.scs.map.vacante.Service.Service;

import java.time.LocalDate;
import java.util.List;
import java.util.Map;

public class ClientViewController {
    private Stage stage;
    private Service service;
    private Client client;
    ObservableList<SpecialOfferDTO> specialOffers = FXCollections.observableArrayList();
    ObservableList<HotelDTO> hotels = FXCollections.observableArrayList();
    ObservableList<String> locations = FXCollections.observableArrayList();

    @FXML
    private TableView<SpecialOfferDTO> specialOfferTable;
    @FXML
    private TableColumn<SpecialOfferDTO,String> specialOfferHotelNameColumn;
    @FXML
    private TableColumn<SpecialOfferDTO,String> specialOfferHotelLocationColumn;
    @FXML
    private TableColumn<SpecialOfferDTO, LocalDate> specialOfferStartDateColumn;
    @FXML
    private TableColumn<SpecialOfferDTO, LocalDate> specialOfferEndDateColumn;
    @FXML
    private ComboBox<String> locationCombo;
    @FXML
    private DatePicker startDatePicker;
    @FXML
    private DatePicker endDatePicker;
    @FXML
    private TableView<HotelDTO> hotelTable;
    @FXML
    private TableColumn<HotelDTO,String> hotelNameColumn;
    @FXML
    private TableColumn<HotelDTO,Double> hotelOccupacyColumn;
    @FXML
    private TableColumn<HotelDTO,Double> hotelPriceColumn;


    public void setServ(Stage stage,Service service,Client client){
        this.stage = stage;
        this.service = service;
        this.client=client;
        initModel();
    }

    public void initModel(){
        specialOffers.setAll(service.getSpecialOffers(client));
        locations.setAll(service.getAllLocationsOfHotels());
    }

    public void initialize(){
        specialOfferHotelNameColumn.setCellValueFactory(new PropertyValueFactory<>("hotelName"));
        specialOfferHotelLocationColumn.setCellValueFactory(new PropertyValueFactory<>("hotelLocation"));
        specialOfferStartDateColumn.setCellValueFactory(new PropertyValueFactory<>("startDate"));
        specialOfferEndDateColumn.setCellValueFactory(new PropertyValueFactory<>("endDate"));
        specialOfferTable.setItems(specialOffers);

        hotelNameColumn.setCellValueFactory(new PropertyValueFactory<>("hotelName"));
        hotelOccupacyColumn.setCellValueFactory(new PropertyValueFactory<>("occupacyGrade"));
        hotelPriceColumn.setCellValueFactory(new PropertyValueFactory<>("pricePerNight"));
        hotelTable.setItems(hotels);

        locationCombo.setItems(locations);

    }

    public void handleSearch(){
        String location = locationCombo.getSelectionModel().getSelectedItem();
        LocalDate startDate = startDatePicker.getValue();
        LocalDate endDate = endDatePicker.getValue();
        hotels.setAll(service.getHotelsFromPeriodAndLocation(location,startDate,endDate));
    }

    public void handleBook(){
        HotelDTO hotelDTO = hotelTable.getSelectionModel().getSelectedItem();
        if(hotelDTO == null){
            MessageAlert.showMessage(null, Alert.AlertType.ERROR,"Error","U didn't select a hotel");
        }
        else{

        }
    }

}
