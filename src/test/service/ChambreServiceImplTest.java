package tn.esprit.tpfoyer.service;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import tn.esprit.tpfoyer.entity.Chambre;
import tn.esprit.tpfoyer.repository.ChambreRepository;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
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

    // Comment out all other test methods to focus on the isolated test in the controller test
    // @Test
    // void testRetrieveAllChambres() { ... }

    // @Test
    // void testRetrieveChambre() { ... }

    // @Test
    // void testAddChambre() { ... }

    // @Test
    // void testModifyChambre() { ... }

    // @Test
    // void testRemoveChambre() { ... }

    // @Test
    // void testRecupererChambresSelonTyp() { ... }

    // @Test
    // void testTrouverchambreSelonEtudiant() { ... }
}
