package tn.esprit.tpfoyer.repository;


import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import tn.esprit.tpfoyer.entity.Chambre;
import tn.esprit.tpfoyer.entity.TypeChambre;
import tn.esprit.tpfoyer.repository.ChambreRepository;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

@ExtendWith(SpringExtension.class)
@DataJpaTest
@ActiveProfiles("test")
class ChambreRepositoryTest {

    @Autowired
    private ChambreRepository chambreRepository;

    private Chambre chambre;

    @BeforeEach
    void setUp() {
        chambre = new Chambre();
        chambre.setNumeroChambre(101L);
        chambre.setTypeC(TypeChambre.SIMPLE);
        chambreRepository.save(chambre);
    }

    @Test
    void testFindAllByTypeC() {
        List<Chambre> result = chambreRepository.findAllByTypeC(TypeChambre.SIMPLE);
        assertEquals(1, result.size());
        assertEquals(TypeChambre.SIMPLE, result.get(0).getTypeC());
    }

    @Test
    void testFindChambreByNumeroChambre() {
        Chambre result = chambreRepository.findChambreByNumeroChambre(101L);
        assertNotNull(result);
        assertEquals(101L, result.getNumeroChambre());
    }

    @Test
    void testTrouverChselonEt() {
        // Assuming there is a reservation and student already mapped in Chambre
        Chambre result = chambreRepository.trouverChselonEt(12345L);  // Replace with actual test cin
        assertNotNull(result);
    }
}
