package tn.esprit.tpfoyer.controller;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import tn.esprit.tpfoyer.control.BlocRestController;
import tn.esprit.tpfoyer.entity.Bloc;
import tn.esprit.tpfoyer.service.IBlocService;

import java.util.Arrays;
import java.util.List;

import static org.mockito.Mockito.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.*;
import static org.springframework.test.web.servlet.setup.MockMvcBuilders.standaloneSetup;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;

import com.fasterxml.jackson.databind.ObjectMapper;

public class BlocRestControllerTest {

    @Mock
    private IBlocService blocService;

    @InjectMocks
    private BlocRestController blocRestController;

    private MockMvc mockMvc;

    private ObjectMapper objectMapper;

    @BeforeEach
    public void setup() {
        MockitoAnnotations.openMocks(this);
        mockMvc = standaloneSetup(blocRestController).build();
        objectMapper = new ObjectMapper();
    }

    @Test
    public void testGetBlocs() throws Exception {
        // Arrange
        Bloc bloc1 = new Bloc();
        bloc1.setNomBloc("Bloc A");
        Bloc bloc2 = new Bloc();
        bloc2.setNomBloc("Bloc B");

        List<Bloc> blocs = Arrays.asList(bloc1, bloc2);

        when(blocService.retrieveAllBlocs()).thenReturn(blocs);

        // Act and Assert
        mockMvc.perform(get("/bloc/retrieve-all-blocs")
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$[0].nomBloc").value("Bloc A"))
                .andExpect(jsonPath("$[1].nomBloc").value("Bloc B"))
                .andDo(print());

        verify(blocService, times(1)).retrieveAllBlocs();
    }

    @Test
    public void testRetrieveBloc() throws Exception {
        // Arrange
        Bloc bloc = new Bloc();
        bloc.setIdBloc(1L);
        bloc.setNomBloc("Bloc A");

        when(blocService.retrieveBloc(1L)).thenReturn(bloc);

        // Act and Assert
        mockMvc.perform(get("/bloc/retrieve-bloc/1")
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.idBloc").value(1L))
                .andExpect(jsonPath("$.nomBloc").value("Bloc A"))
                .andDo(print());

        verify(blocService, times(1)).retrieveBloc(1L);
    }

    @Test
    public void testAddBloc() throws Exception {
        // Arrange
        Bloc bloc = new Bloc();
        bloc.setNomBloc("Bloc C");

        when(blocService.addBloc(any(Bloc.class))).thenReturn(bloc);

        // Act and Assert
        mockMvc.perform(post("/bloc/add-bloc")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(bloc)))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.nomBloc").value("Bloc C"))
                .andDo(print());

        verify(blocService, times(1)).addBloc(any(Bloc.class));
    }

    @Test
    public void testRemoveBloc() throws Exception {
        // Act and Assert
        mockMvc.perform(delete("/bloc/remove-bloc/1")
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andDo(print());

        verify(blocService, times(1)).removeBloc(1L);
    }

    @Test
    public void testModifyBloc() throws Exception {
        // Arrange
        Bloc bloc = new Bloc();
        bloc.setNomBloc("Bloc D");

        when(blocService.modifyBloc(any(Bloc.class))).thenReturn(bloc);

        // Act and Assert
        mockMvc.perform(put("/bloc/modify-bloc")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(bloc)))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.nomBloc").value("Bloc D"))
                .andDo(print());

        verify(blocService, times(1)).modifyBloc(any(Bloc.class));
    }

    @Test
    public void testGetBlocsWithoutFoyer() throws Exception {
        // Arrange
        Bloc bloc1 = new Bloc();
        Bloc bloc2 = new Bloc();
        when(blocService.trouverBlocsSansFoyer()).thenReturn(Arrays.asList(bloc1, bloc2));

        // Act and Assert
        mockMvc.perform(get("/bloc/trouver-blocs-sans-foyer")
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$").isArray())
                .andExpect(jsonPath("$.length()").value(2))
                .andDo(print());

        verify(blocService, times(1)).trouverBlocsSansFoyer();
    }

    @Test
    public void testRecuperBlocsParNomEtCap() throws Exception {
        // Arrange
        Bloc bloc = new Bloc();
        bloc.setNomBloc("Bloc A");
        bloc.setCapaciteBloc(100);

        when(blocService.trouverBlocsParNomEtCap("Bloc A", 100)).thenReturn(Arrays.asList(bloc));

        // Act and Assert
        mockMvc.perform(get("/bloc/get-bloc-nb-c/Bloc A/100")
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$[0].nomBloc").value("Bloc A"))
                .andExpect(jsonPath("$[0].capaciteBloc").value(100))
                .andDo(print());

        verify(blocService, times(1)).trouverBlocsParNomEtCap("Bloc A", 100);
    }
}
