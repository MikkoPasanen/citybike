package citybike.rest;

import citybike.backend.entity.Station;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

@org.springframework.web.bind.annotation.RestController
@RequestMapping("/stations")
public class StationsController {

    @GetMapping("/all")
    public ResponseEntity<List<Station>> getAllStations() {
        List<Station> stations = new ArrayList<>();
        Collections.addAll(stations,
                new Station(1, "Asema 1", "Asemankatu 1", "7.5263", "5.5235"),
                new Station(2, "Asema 2", "Asemankatu 2", "8.5732", "8.3251")
        );
        return new ResponseEntity<>(stations, HttpStatus.OK);
    }

    @GetMapping("/singular/{id}")
    public ResponseEntity<Station> getStationById(@PathVariable int id) {
        Station station100 = new Station(100, "Asema sata", "Asemankatu 100", "7.5263", "5.5235");
        Station station200 = new Station(200, "Asema kaksataa", "Asemankatu 200", "2.1263", "9.2235");

        return id == 100
                ? new ResponseEntity<>(station100, HttpStatus.OK)
                : new ResponseEntity<>(station200, HttpStatus.OK);
    }
}
