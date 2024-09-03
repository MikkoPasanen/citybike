package citybike.services;

import citybike.entity.Station;
import citybike.exceptions.StationNotFoundException;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class StationService {
    private final StationRepository stationRepository;

    public StationService(StationRepository stationRepository) {
        this.stationRepository = stationRepository;
    }

    public List<Station> getAllStations() {
        return stationRepository.findAll();
    }

    public Station getStationById(int id) {
        return stationRepository.findById(id)
                .orElseThrow(() -> new StationNotFoundException("Couldn't find station with that id"));
    }
}
