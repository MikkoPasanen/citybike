package citybike.code.rest;

import citybike.code.entity.Journey;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

@RestController
@RequestMapping("/journeys")
public class JourneysController {

    @GetMapping("/all")
    public ResponseEntity<List<Journey>> getAllStations() {
        List<Journey> journeys = new ArrayList<>();
        Collections.addAll(journeys,
                new Journey(1, LocalDateTime.now(), LocalDateTime.now().plusHours(1), 1, 2, 10, 15),
                new Journey(2, LocalDateTime.now(), LocalDateTime.now().plusHours(2), 2, 1, 20, 30)
        );
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
