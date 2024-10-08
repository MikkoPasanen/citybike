package citybike.rest;

import citybike.entity.Station;
import citybike.entity.StationResponse;
import citybike.services.StationRepository;
import citybike.services.StationService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

@RestController
@RequestMapping("/stations")
public class StationsController {

    private final StationService stationService;

    public StationsController(StationService stationService) {
        this.stationService = stationService;
    }

    @CrossOrigin(origins = "http://localhost:5173")
    @GetMapping("/all")
    public ResponseEntity<StationResponse> getAllStations(
            @RequestParam(defaultValue = "1") int page,
            @RequestParam(defaultValue = "5") int size
    ) {
        List<Station> stations = stationService.getAllStations(page, size);
        Long amountOfStations = stationService.getStationCount();

        return new ResponseEntity<>(new StationResponse(amountOfStations, stations), HttpStatus.OK);
    }

//    @CrossOrigin(origins = "http://localhost:5173")
//    @GetMapping("/singular/{id}")
//    public ResponseEntity<Station> getStationById(@PathVariable int id) {
//        return new ResponseEntity<>(stationService.getStationById(id), HttpStatus.OK);
//    }
}
