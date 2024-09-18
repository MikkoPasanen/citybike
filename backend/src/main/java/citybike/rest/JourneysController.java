package citybike.rest;

import citybike.entity.Journey;
import citybike.entity.ToReturnJourney;
import citybike.services.JourneyService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;


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
    public ResponseEntity<List<ToReturnJourney>> getAllStations(
            @RequestParam(defaultValue = "1") int page,
            @RequestParam(defaultValue = "5") int size) {
        return new ResponseEntity<>(journeyService.getAllJourneys(page, size), HttpStatus.OK);
    }

//    @GetMapping("/singular/{id}")
//    public ResponseEntity<Journey> getStationById(@PathVariable int id) {
//        return new ResponseEntity<>(journeyService.getJourneyById(id), HttpStatus.OK);
//    }
}
