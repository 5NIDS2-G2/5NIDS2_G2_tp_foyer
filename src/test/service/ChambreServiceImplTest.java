package tn.esprit.tpfoyer.service;


import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import tn.esprit.tpfoyer.entity.Chambre;
import tn.esprit.tpfoyer.entity.TypeChambre;
import tn.esprit.tpfoyer.repository.ChambreRepository;
import tn.esprit.tpfoyer.service.ChambreServiceImpl;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyLong;
import static org.mockito.Mockito.*;

class ChambreServiceImplTest {

    @InjectMocks
    private ChambreServiceImpl chambreService;

    @Mock
    private ChambreRepository chambreRepository;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void testRetrieveAllChambres() {
        List<Chambre> chambres = new ArrayList<>();
        chambres.add(new Chambre());
        when(chambreRepository.findAll()).thenReturn(chambres);

        List<Chambre> result = chambreService.retrieveAllChambres();

        assertEquals(1, result.size());
        verify(chambreRepository, times(1)).findAll();
    }

    @Test
    void testRetrieveChambre() {
        Chambre chambre = new Chambre();
        when(chambreRepository.findById(anyLong())).thenReturn(Optional.of(chambre));

        Chambre result = chambreService.retrieveChambre(1L);

        assertEquals(chambre, result);
        verify(chambreRepository, times(1)).findById(anyLong());
    }

    @Test
    void testAddChambre() {
        Chambre chambre = new Chambre();
        when(chambreRepository.save(any(Chambre.class))).thenReturn(chambre);

        Chambre result = chambreService.addChambre(chambre);

        assertEquals(chambre, result);
        verify(chambreRepository, times(1)).save(any(Chambre.class));
    }

    @Test
    void testModifyChambre() {
        Chambre chambre = new Chambre();
        when(chambreRepository.save(any(Chambre.class))).thenReturn(chambre);

        Chambre result = chambreService.modifyChambre(chambre);

        assertEquals(chambre, result);
        verify(chambreRepository, times(1)).save(any(Chambre.class));
    }

    @Test
    void testRemoveChambre() {
        doNothing().when(chambreRepository).deleteById(anyLong());

        chambreService.removeChambre(1L);

        verify(chambreRepository, times(1)).deleteById(anyLong());
    }

    @Test
    void testRecupererChambresSelonTyp() {
        List<Chambre> chambres = new ArrayList<>();
        chambres.add(new Chambre());
        when(chambreRepository.findAllByTypeC(any(TypeChambre.class))).thenReturn(chambres);

        List<Chambre> result = chambreService.recupererChambresSelonTyp(TypeChambre.SIMPLE);

        assertEquals(1, result.size());
        verify(chambreRepository, times(1)).findAllByTypeC(any(TypeChambre.class));
    }

    @Test
    void testTrouverchambreSelonEtudiant() {
        Chambre chambre = new Chambre();
        when(chambreRepository.trouverChselonEt(anyLong())).thenReturn(chambre);

        Chambre result = chambreService.trouverchambreSelonEtudiant(12345L);

        assertEquals(chambre, result);
        verify(chambreRepository, times(1)).trouverChselonEt(anyLong());
    }
}
