package tn.esprit.tpfoyer.controller;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import tn.esprit.tpfoyer.control.ChambreRestController;
import tn.esprit.tpfoyer.entity.Chambre;
import tn.esprit.tpfoyer.service.IChambreService;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.*;

class ChambreRestControllerTest {

    @InjectMocks
    private ChambreRestController chambreController;

    @Mock
    private IChambreService chambreService;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void testGetChambres() {
        List<Chambre> chambres = new ArrayList<>();
        chambres.add(new Chambre());
        when(chambreService.retrieveAllChambres()).thenReturn(chambres);

        List<Chambre> result = chambreController.getChambres();

        assertEquals(1, result.size());
        verify(chambreService, times(1)).retrieveAllChambres();
    }

    // Comment out all other test methods to focus on the isolated test
    // @Test
    // void testRetrieveChambre() { ... }

    // @Test
    // void testAddChambre() { ... }

    // @Test
    // void testRemoveChambre() { ... }

    // @Test
    // void testModifyChambre() { ... }

    // @Test
    // void testTrouverChSelonTC() { ... }

    // @Test
    // void testTrouverChSelonEt() { ... }
}
