package citybike.services;

import citybike.entity.Journey;
import citybike.entity.Station;
import citybike.exceptions.StationNotFoundException;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class StationService {
    private final StationRepository stationRepository;

    public StationService(StationRepository stationRepository) {
        this.stationRepository = stationRepository;
    }

    public List<Station> getAllStations(int page, int size) {
        Pageable pageable = PageRequest.of(page, size);
        Page<Station> stationPage = stationRepository.findAll(pageable);
        return stationPage.getContent();
    }

    public Station getStationById(int id) {
        return stationRepository.findById(id)
                .orElseThrow(() -> new StationNotFoundException("Couldn't find station with that id"));
    }
}
