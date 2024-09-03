package citybike.rest;

import citybike.entity.Station;
import citybike.exceptions.StationNotFoundException;
import citybike.services.StationService;
import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.HttpStatus;
import org.springframework.test.web.servlet.MockMvc;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import static org.hamcrest.Matchers.hasSize;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.verify;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.mockito.Mockito.when;


@ExtendWith(MockitoExtension.class)
@WebMvcTest(StationsController.class)
class StationsControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private StationService stationService;

    @Test
    void canGetAllStationsFromEndpoint() throws Exception {
        // Given
        Station station1 = new Station(1, "Name1", "Address1", "1.111", "2.222");
        Station station2 = new Station(2, "Name2", "Address2", "2.222", "1.111");
        when(stationService.getAllStations(1, 100)).thenReturn(Arrays.asList(station1, station2));

        // Then & When
        mockMvc.perform(get("/stations/all")
                        .param("page", "1")
                        .param("size", "100")
                )
                .andExpect(status().isOk())
                .andExpect(content().contentType("application/json"))
                .andExpect(jsonPath("$", hasSize(2)))
                .andExpect(jsonPath("$[0].id").value(1))
                .andExpect(jsonPath("$[0].stationName").value("Name1"))
                .andExpect(jsonPath("$[0].stationAddress").value("Address1"))
                .andExpect(jsonPath("$[0].coordinateX").value("1.111"))
                .andExpect(jsonPath("$[0].coordinateY").value("2.222"))
                .andExpect(jsonPath("$[1].id").value(2))
                .andExpect(jsonPath("$[1].stationName").value("Name2"))
                .andExpect(jsonPath("$[1].stationAddress").value("Address2"))
                .andExpect(jsonPath("$[1].coordinateX").value("2.222"))
                .andExpect(jsonPath("$[1].coordinateY").value("1.111"));
    }

    @Test
    void canGetStationByIdFromEndpoint() throws Exception {
        // Given
        int id = 1;
        Station station = new Station(id, "Name1", "Address1", "1.111", "2.222");
        when(stationService.getStationById(id)).thenReturn(station);

        // Then & When
        mockMvc.perform(get("/stations/singular/"+id))
                .andExpect(status().isOk())
                .andExpect(content().contentType("application/json"))
                .andExpect(jsonPath("$.id").value(1))
                .andExpect(jsonPath("$.stationName").value("Name1"))
                .andExpect(jsonPath("$.stationAddress").value("Address1"))
                .andExpect(jsonPath("$.coordinateX").value("1.111"))
                .andExpect(jsonPath("$.coordinateY").value("2.222"));
    }

    @Test
    void canNotGetStationByIdFromEndpoint() throws Exception {
        // Given
        int id = 1;
        when(stationService.getStationById(id)).thenThrow(new StationNotFoundException("Couldn't find station with that id"));

        // Then & When
        mockMvc.perform(get("/stations/singular/"+id))
                .andExpect(status().isNotFound())
                .andExpect(content().contentType("application/json"))
                .andExpect(jsonPath("$.message").value("Couldn't find station with that id"))
                .andExpect(jsonPath("$.status").value(HttpStatus.NOT_FOUND.value()));
    }
}