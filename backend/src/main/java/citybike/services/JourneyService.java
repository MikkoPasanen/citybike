package citybike.services;

import citybike.entity.Journey;
import citybike.entity.Station;
import citybike.entity.ToReturnJourney;
import citybike.exceptions.JourneysNotFoundException;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class JourneyService {
    private final JourneyRepository journeyRepository;
    private final StationRepository stationRepository;

    public JourneyService(JourneyRepository journeyRepository, StationRepository stationRepository) {
        this.journeyRepository = journeyRepository;
        this.stationRepository = stationRepository;
    }

    public List<ToReturnJourney> getAllJourneys(int page, int size) {

        // Find pageful of journeys
        Pageable pageable = PageRequest.of(page, size);
        Page<Journey> journeyPage = journeyRepository.findAll(pageable);

        // If no content, throw an error
        if (journeyPage.getContent().isEmpty()) throw new JourneysNotFoundException("Couldn't find any Journeys!");

        // For each journey, find the names of the departure and return station
        List<ToReturnJourney> journeys = journeyPage.getContent().stream().map(journey -> {
            Station departureStation = stationRepository.findById(journey.getDepartureStation()).orElse(null);
            Station returnStation = stationRepository.findById(journey.getReturnStation()).orElse(null);

            return new ToReturnJourney(
                    journey.getId(),
                    journey.getDepartureTime(),
                    journey.getReturnTime(),
                    departureStation != null ? departureStation.getStationName() : "Unknown Station",
                    returnStation != null ? returnStation.getStationName() : "Unknown Station",
                    departureStation != null ? departureStation.getCoordinateX() : "0,0",
                    departureStation != null ? departureStation.getCoordinateY() : "0,0",
                    returnStation != null ? returnStation.getCoordinateX() : "0,0",
                    returnStation != null ? returnStation.getCoordinateY() : "0,0",
                    journey.getDistance(),
                    journey.getDuration() / 60
            );
        }).toList();

        return journeys;
    }

//    public Journey getJourneyById(int id) {
//        return journeyRepository.findById(id)
//                .orElseThrow(() -> new JourneyNotFoundException("Couldn't find journey with that id"));
//    }
}
