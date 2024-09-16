// package citybike.rest;

// import citybike.entity.Journey;
// import citybike.exceptions.JourneyNotFoundException;
// import citybike.services.JourneyService;
// import org.junit.jupiter.api.Disabled;
// import org.junit.jupiter.api.Test;
// import org.junit.jupiter.api.extension.ExtendWith;
// import org.mockito.junit.jupiter.MockitoExtension;
// import org.springframework.beans.factory.annotation.Autowired;
// import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
// import org.springframework.boot.test.mock.mockito.MockBean;
// import org.springframework.http.HttpStatus;
// import org.springframework.test.web.servlet.MockMvc;

// import java.time.LocalDateTime;
// import java.util.Arrays;

// import static org.hamcrest.Matchers.hasSize;
// import static org.mockito.Mockito.when;
// import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
// import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;


// @ExtendWith(MockitoExtension.class)
// @WebMvcTest(JourneysController.class)
// class JourneysControllerTest {

//     @Autowired
//     private MockMvc mockMvc;

//     @MockBean
//     private JourneyService journeyService;

// //    @Disabled
// //    @Test
// //    void canGetAllJourneysFromEndpoint() throws Exception {
// //        // Given
// //        LocalDateTime start = LocalDateTime.now();
// //        LocalDateTime end = LocalDateTime.now().plusHours(1);
// //
// //        Journey journey1 = new Journey(1, start, end, 1, 2, 10, 10);
// //        Journey journey2 = new Journey(2, start, end, 2, 1, 20, 20);
// //        when(journeyService.getAllJourneys(1, 100)).thenReturn(Arrays.asList(journey1, journey2));
// //
// //        // Then & When
// //        mockMvc.perform(get("/journeys/all")
// //                        .param("page", "1")
// //                        .param("size", "5")
// //                )
// //                .andExpect(status().isOk())
// //                .andExpect(content().contentType("application/json"))
// //                .andExpect(jsonPath("$", hasSize(2)))
// //                .andExpect(jsonPath("$[0].id").value(1))
// //                .andExpect(jsonPath("$[0].departureStation").value("1"))
// //                .andExpect(jsonPath("$[0].returnStation").value("2"))
// //                .andExpect(jsonPath("$[0].distance").value("10"))
// //                .andExpect(jsonPath("$[0].duration").value("10"))
// //                .andExpect(jsonPath("$[1].id").value(2))
// //                .andExpect(jsonPath("$[1].departureStation").value("2"))
// //                .andExpect(jsonPath("$[1].returnStation").value("1"))
// //                .andExpect(jsonPath("$[1].distance").value("20"))
// //                .andExpect(jsonPath("$[1].duration").value("20"));
// //    }

//     @Test
//     void canGetJourneyByIdFromEndpoint() throws Exception {
//         // Given
//         int id = 1;
//         LocalDateTime start = LocalDateTime.now();
//         LocalDateTime end = LocalDateTime.now().plusHours(1);
//         Journey journey = new Journey(id, start, end, 1, 2, 10, 10);
//         when(journeyService.getJourneyById(id)).thenReturn(journey);

//         // Then & When
//         mockMvc.perform(get("/journeys/singular/"+id))
//                 .andExpect(status().isOk())
//                 .andExpect(content().contentType("application/json"))
//                 .andExpect(jsonPath("$.id").value(id))
//                 .andExpect(jsonPath("$.departureStation").value("1"))
//                 .andExpect(jsonPath("$.returnStation").value("2"))
//                 .andExpect(jsonPath("$.distance").value("10"))
//                 .andExpect(jsonPath("$.duration").value("10"));

//     }

//     @Test
//     void canNotGetJourneyByIdFromEndpoint() throws Exception {
//         // Given
//         int id = 1;
//         when(journeyService.getJourneyById(id)).thenThrow(new JourneyNotFoundException("Couldn't find journey with that id"));

//         // Then & When
//         mockMvc.perform(get("/journeys/singular/"+id))
//                 .andExpect(status().isNotFound())
//                 .andExpect(content().contentType("application/json"))
//                 .andExpect(jsonPath("$.message").value("Couldn't find journey with that id"))
//                 .andExpect(jsonPath("$.status").value(HttpStatus.NOT_FOUND.value()));
//     }
// }