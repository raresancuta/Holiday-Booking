package ubb.scs.map.vacante.Service;

import ubb.scs.map.vacante.Domain.*;
import ubb.scs.map.vacante.Repository.ClientsRepository;
import ubb.scs.map.vacante.Repository.HotelsRepository;
import ubb.scs.map.vacante.Repository.ReservationsRepository;
import ubb.scs.map.vacante.Repository.SpecialOffersRepository;
import ubb.scs.map.vacante.Utils.Observable;
import ubb.scs.map.vacante.Utils.Observer;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.temporal.ChronoUnit;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

public class Service {
    private ClientsRepository clientsRepository;
    private HotelsRepository hotelsRepository;
    private ReservationsRepository reservationsRepository;
    private SpecialOffersRepository specialOffersRepository;

    public Service(ClientsRepository clientsRepository,HotelsRepository hotelsRepository, ReservationsRepository reservationsRepository, SpecialOffersRepository specialOffersRepository) {
        this.clientsRepository = clientsRepository;
        this.hotelsRepository = hotelsRepository;
        this.reservationsRepository = reservationsRepository;
        this.specialOffersRepository = specialOffersRepository;
    }

    public Optional<Hotel> findHotel(Double id_hotel){
        return hotelsRepository.find(id_hotel);
    }

    public Optional<Client> verifyClient(Long id){
        return clientsRepository.find(id);
    }

    public List<SpecialOfferDTO> getSpecialOffers(Client client){
        List<SpecialOffer> filteredOffers = specialOffersRepository.findAll().stream().filter(specialOffer -> specialOffer.getPercents()<client.getFidelityGrade() && LocalDate.now().isBefore(specialOffer.getEndDate())).collect(Collectors.toList());
        List<SpecialOfferDTO> specialOfferDTOs = new ArrayList<>();
        for(SpecialOffer specialOffer : filteredOffers){
            specialOfferDTOs.add(new SpecialOfferDTO(specialOffer.getId(),findHotel(specialOffer.getHotelId()).get().getHotelName(),findHotel(specialOffer.getHotelId()).get().getLocationName(),specialOffer.getStartDate(),specialOffer.getEndDate()));
        }
        return specialOfferDTOs;
    }

    public List<String> getAllLocationsOfHotels(){
        return hotelsRepository.findAll().stream().map(Hotel::getLocationName).distinct().collect(Collectors.toList());
    }

    public List<HotelDTO> getHotelsFromPeriodAndLocation(String location, LocalDate startDate, LocalDate endDate) {
        // Filtrăm hotelurile din locația specificată
        List<Hotel> hotels = hotelsRepository.findAll().stream()
                .filter(hotel -> hotel.getLocationName().equals(location))
                .collect(Collectors.toList());

        // Lista rezultat
        List<HotelDTO> hotelDTOs = new ArrayList<>();

        for (Hotel hotel : hotels) {
            // Obținem toate rezervările pentru hotel
            List<Reservation> reservations = reservationsRepository.findAll().stream()
                    .filter(reservation -> reservation.getHotelId().equals(hotel.getId()))
                    .collect(Collectors.toList());

            // Calculăm gradul de ocupare pentru perioada selectată
            double totalOccupancy = 0.0;
            int totalDays = 0;

            for (LocalDate date = startDate; !date.isAfter(endDate); date = date.plusDays(1)) {
                totalDays++;
                LocalDate finalDate = date;
                int roomsReservedForDate = reservations.stream()
                        .filter(reservation -> isDateWithinReservation(finalDate, reservation))
                        .mapToInt(reservation -> 1)
                        .sum();

                totalOccupancy += (double) roomsReservedForDate / hotel.getNoRooms();
            }

            // Calculăm gradul mediu de ocupare pe întreaga perioadă
            double averageOccupancy = totalDays > 0 ? totalOccupancy / totalDays : 0.0;

            // Adăugăm HotelDTO în lista rezultat
            hotelDTOs.add(new HotelDTO(hotel.getId(), hotel.getHotelName(), averageOccupancy, hotel.getPricePerNight()));
        }

        return hotelDTOs;
    }

    // Funcție auxiliară pentru a verifica dacă o dată este inclusă într-o rezervare
    private boolean isDateWithinReservation(LocalDate date, Reservation reservation) {
        LocalDate startDate = reservation.getStartDate().toLocalDate();
        LocalDate endDate = startDate.plusDays(reservation.getNoNights() - 1);
        return (date.isEqual(startDate) || date.isAfter(startDate)) && date.isBefore(endDate);
    }

    public void makeReservation(HotelDTO hotelDTO,Client client,LocalDate startDate,LocalDate endDate){
        reservationsRepository.add(new Reservation(null,client.getId(),hotelDTO.getId(),startDate.atStartOfDay(), (int)ChronoUnit.DAYS.between(startDate,endDate)));

    }
}
