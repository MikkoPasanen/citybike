 package citybike.rest;

 import citybike.entity.ToReturnJourney;
 import citybike.exceptions.JourneysNotFoundException;
 import citybike.services.JourneyService;
 import org.junit.jupiter.api.Test;
 import org.junit.jupiter.api.extension.ExtendWith;
 import org.mockito.junit.jupiter.MockitoExtension;
 import org.springframework.beans.factory.annotation.Autowired;
 import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
 import org.springframework.boot.test.mock.mockito.MockBean;
 import org.springframework.http.HttpStatus;
 import org.springframework.test.web.servlet.MockMvc;

 import java.time.LocalDateTime;
 import java.util.Arrays;

 import static org.hamcrest.Matchers.hasSize;
 import static org.mockito.Mockito.when;
 import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
 import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;


 @ExtendWith(MockitoExtension.class)
 @WebMvcTest(JourneysController.class)
 class JourneysControllerTest {

     @Autowired
     private MockMvc mockMvc;

     @MockBean
     private JourneyService journeyService;

     @Test
     void canGetAllJourneysFromEndpoint() throws Exception {
         // Given
         LocalDateTime fixedStartTime = LocalDateTime.of(2024, 9, 17, 0, 47, 58);
         LocalDateTime fixedEndTime = LocalDateTime.of(2024, 9, 17, 1, 47, 58);


         ToReturnJourney toReturn1 = new ToReturnJourney(
                 1,
                 fixedStartTime,
                 fixedEndTime,
                 "Name1",
                 "Name2",
                 "1.111",
                 "2.222",
                 "2.222",
                 "1.111",
                 10,
                 10 // duration in minutes
         );
         ToReturnJourney toReturn2 = new ToReturnJourney(
                 2,
                 fixedStartTime,
                 fixedEndTime,
                 "Name2",
                 "Name1",
                 "2.222",
                 "1.111",
                 "1.111",
                 "2.222",
                 20,
                 20 // duration in minutes
         );
         when(journeyService.getAllJourneys(1, 5)).thenReturn(Arrays.asList(toReturn1, toReturn2));

         // Then & When
         mockMvc.perform(get("/journeys/all")
                         .param("page", "1")
                         .param("size", "5")
                 )
                 .andExpect(status().isOk())
                 .andExpect(content().contentType("application/json"))
                 .andExpect(jsonPath("$", hasSize(2)))
                 .andExpect(jsonPath("$[0].id").value(1))
                 .andExpect(jsonPath("$[0].departureTime").value(fixedStartTime.toString()))
                 .andExpect(jsonPath("$[0].returnTime").value(fixedEndTime.toString()))
                 .andExpect(jsonPath("$[0].departureStation").value("Name1"))
                 .andExpect(jsonPath("$[0].returnStation").value("Name2"))
                 .andExpect(jsonPath("$[0].departureStationCoordX").value("1.111"))
                 .andExpect(jsonPath("$[0].departureStationCoordY").value("2.222"))
                 .andExpect(jsonPath("$[0].returnStationCoordX").value("2.222"))
                 .andExpect(jsonPath("$[0].returnStationCoordY").value("1.111"))
                 .andExpect(jsonPath("$[0].distance").value(10))
                 .andExpect(jsonPath("$[0].duration").value(10))
                 .andExpect(jsonPath("$[1].id").value(2))
                 .andExpect(jsonPath("$[1].departureTime").value(fixedStartTime.toString()))
                 .andExpect(jsonPath("$[1].returnTime").value(fixedEndTime.toString()))
                 .andExpect(jsonPath("$[1].departureStation").value("Name2"))
                 .andExpect(jsonPath("$[1].returnStation").value("Name1"))
                 .andExpect(jsonPath("$[1].departureStationCoordX").value("2.222"))
                 .andExpect(jsonPath("$[1].departureStationCoordY").value("1.111"))
                 .andExpect(jsonPath("$[1].returnStationCoordX").value("1.111"))
                 .andExpect(jsonPath("$[1].returnStationCoordY").value("2.222"))
                 .andExpect(jsonPath("$[1].distance").value(20))
                 .andExpect(jsonPath("$[1].duration").value(20));
     }

     @Test
     void shouldReturnErrorInEndpointIfNoJourneys() throws Exception {

         when(journeyService.getAllJourneys(1, 5)).thenThrow(new JourneysNotFoundException("Couldn't find any Journeys!"));

         // Then & When
         mockMvc.perform(get("/journeys/all")
                         .param("page", "1")
                         .param("size", "5")
                 )
                 .andExpect(status().isNotFound())
                 .andExpect(content().contentType("application/json"))
                 .andExpect(jsonPath("$.message").value("Couldn't find any Journeys!"))
                 .andExpect(jsonPath("$.status").value(HttpStatus.NOT_FOUND.value()));
     }



//     @Test
//     void canGetJourneyByIdFromEndpoint() throws Exception {
//         // Given
//         int id = 1;
//         LocalDateTime start = LocalDateTime.now();
//         LocalDateTime end = LocalDateTime.now().plusHours(1);
//         Journey journey = new Journey(id, start, end, 1, 2, 10, 10);
//         when(journeyService.getJourneyById(id)).thenReturn(journey);
//
//         // Then & When
//         mockMvc.perform(get("/journeys/singular/"+id))
//                 .andExpect(status().isOk())
//                 .andExpect(content().contentType("application/json"))
//                 .andExpect(jsonPath("$.id").value(id))
//                 .andExpect(jsonPath("$.departureStation").value("1"))
//                 .andExpect(jsonPath("$.returnStation").value("2"))
//                 .andExpect(jsonPath("$.distance").value("10"))
//                 .andExpect(jsonPath("$.duration").value("10"));
//
//     }
//
//     @Test
//     void canNotGetJourneyByIdFromEndpoint() throws Exception {
//         // Given
//         int id = 1;
//         when(journeyService.getJourneyById(id)).thenThrow(new JourneyNotFoundException("Couldn't find journey with that id"));
//
//         // Then & When
//         mockMvc.perform(get("/journeys/singular/"+id))
//                 .andExpect(status().isNotFound())
//                 .andExpect(content().contentType("application/json"))
//                 .andExpect(jsonPath("$.message").value("Couldn't find journey with that id"))
//                 .andExpect(jsonPath("$.status").value(HttpStatus.NOT_FOUND.value()));
//     }
 }