package citybike.rest;

import citybike.entity.JourneyResponse;
import citybike.entity.Station;
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
    public ResponseEntity<JourneyResponse> getAllStations(
            @RequestParam(defaultValue = "1") int page,
            @RequestParam(defaultValue = "5") int size) {

        List<ToReturnJourney> journeys = journeyService.getAllJourneys(page, size);
        Long amountOfJourneys = journeyService.getJourneyCount();
        return new ResponseEntity<>(new JourneyResponse(amountOfJourneys, journeys), HttpStatus.OK);
    }

//    @GetMapping("/singular/{id}")
//    public ResponseEntity<Journey> getStationById(@PathVariable int id) {
//        return new ResponseEntity<>(journeyService.getJourneyById(id), HttpStatus.OK);
//    }
}
