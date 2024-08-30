package citybike.rest;

import citybike.entity.Journey;
import citybike.services.JourneyRepository;
import citybike.services.JourneyService;
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

    private final JourneyService journeyService;

    public JourneysController(JourneyService journeyService) {
        this.journeyService = journeyService;
    }

    @GetMapping("/all")
    public ResponseEntity<List<Journey>> getAllStations(
            @RequestParam(defaultValue = "1") int page,
            @RequestParam(defaultValue = "100") int size) {
        return new ResponseEntity<>(journeyService.getAllJourneys(page, size), HttpStatus.OK);
    }

    @GetMapping("/singular/{id}")
    public ResponseEntity<Journey> getStationById(@PathVariable int id) {
        return new ResponseEntity<>(journeyService.getJourneyById(id), HttpStatus.OK);
    }
}
