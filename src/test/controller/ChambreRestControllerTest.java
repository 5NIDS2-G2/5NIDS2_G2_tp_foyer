package tn.esprit.tpfoyer.controller;


import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import tn.esprit.tpfoyer.control.ChambreRestController;
import tn.esprit.tpfoyer.entity.Chambre;
import tn.esprit.tpfoyer.entity.TypeChambre;
import tn.esprit.tpfoyer.service.IChambreService;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyLong;
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

    @Test
    void testRetrieveChambre() {
        Chambre chambre = new Chambre();
        when(chambreService.retrieveChambre(anyLong())).thenReturn(chambre);

        Chambre result = chambreController.retrieveChambre(1L);

        assertEquals(chambre, result);
        verify(chambreService, times(1)).retrieveChambre(anyLong());
    }

    @Test
    void testAddChambre() {
        Chambre chambre = new Chambre();
        when(chambreService.addChambre(any(Chambre.class))).thenReturn(chambre);

        Chambre result = chambreController.addChambre(chambre);

        assertEquals(chambre, result);
        verify(chambreService, times(1)).addChambre(any(Chambre.class));
    }

    @Test
    void testRemoveChambre() {
        doNothing().when(chambreService).removeChambre(anyLong());

        chambreController.removeChambre(1L);

        verify(chambreService, times(1)).removeChambre(anyLong());
    }

    @Test
    void testModifyChambre() {
        Chambre chambre = new Chambre();
        when(chambreService.modifyChambre(any(Chambre.class))).thenReturn(chambre);

        Chambre result = chambreController.modifyChambre(chambre);

        assertEquals(chambre, result);
        verify(chambreService, times(1)).modifyChambre(any(Chambre.class));
    }

    @Test
    void testTrouverChSelonTC() {
        List<Chambre> chambres = new ArrayList<>();
        chambres.add(new Chambre());
        when(chambreService.recupererChambresSelonTyp(any(TypeChambre.class))).thenReturn(chambres);

        List<Chambre> result = chambreController.trouverChSelonTC(TypeChambre.SIMPLE);

        assertEquals(1, result.size());
        verify(chambreService, times(1)).recupererChambresSelonTyp(any(TypeChambre.class));
    }

    @Test
    void testTrouverChSelonEt() {
        Chambre chambre = new Chambre();
        when(chambreService.trouverchambreSelonEtudiant(anyLong())).thenReturn(chambre);

        Chambre result = chambreController.trouverChSelonEt(12345L);

        assertEquals(chambre, result);
        verify(chambreService, times(1)).trouverchambreSelonEtudiant(anyLong());
    }
}
