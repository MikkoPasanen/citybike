package citybike.rest;

import citybike.entity.Journey;
import citybike.services.JourneyRepository;
import citybike.services.StationRepository;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

@RestController
@CrossOrigin(origins = "http://localhost:5173")
@RequestMapping("/journeys")
public class JourneysController {

    private final JourneyRepository journeyRepository;

    public JourneysController(JourneyRepository journeyRepository) {
        this.journeyRepository = journeyRepository;
    }

    @GetMapping("/all")
    public ResponseEntity<List<Journey>> getAllStations(@RequestParam(defaultValue = "100") int size) {
        List<Journey> journeys = journeyRepository.findLimited(size);
        return new ResponseEntity<>(journeys, HttpStatus.OK);
    }

    @GetMapping("/singular/{id}")
    public ResponseEntity<Journey> getStationById(@PathVariable int id) {
        Journey journey100 = new Journey(100, LocalDateTime.now(), LocalDateTime.now().plusHours(1), 1, 2, 10, 15);

        Journey journey200 = new Journey(200, LocalDateTime.now(), LocalDateTime.now().plusHours(2), 2, 1, 20, 30);

        return id == 100
                ? new ResponseEntity<>(journey100, HttpStatus.OK)
                : new ResponseEntity<>(journey200, HttpStatus.OK);
    }
}
