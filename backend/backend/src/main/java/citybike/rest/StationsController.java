package citybike.rest;

import citybike.entity.Station;
import citybike.services.StationRepository;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

@RestController
@RequestMapping("/stations")
public class StationsController {

    private final StationRepository stationRepository;

    public StationsController(StationRepository stationRepository) {
        this.stationRepository = stationRepository;
    }

    @CrossOrigin(origins = "http://localhost:5173")
    @GetMapping("/all")
    public ResponseEntity<List<Station>> getAllStations() {
        List<Station> stations = stationRepository.findAll();
        return new ResponseEntity<>(stations, HttpStatus.OK);
    }

    @CrossOrigin(origins = "http://localhost:5173")
    @GetMapping("/singular/{id}")
    public ResponseEntity<Station> getStationById(@PathVariable int id) {
        Station station100 = new Station(100, "Asema sata", "Asemankatu 100", "7.5263", "5.5235");
        Station station200 = new Station(200, "Asema kaksataa", "Asemankatu 200", "2.1263", "9.2235");

        return id == 100
                ? new ResponseEntity<>(station100, HttpStatus.OK)
                : new ResponseEntity<>(station200, HttpStatus.OK);
    }
}
