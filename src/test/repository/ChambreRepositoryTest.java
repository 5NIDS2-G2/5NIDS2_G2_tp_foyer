//package repository;
//
//import org.junit.jupiter.api.BeforeEach;
//import org.junit.jupiter.api.Test;
//import org.junit.jupiter.api.extension.ExtendWith;
//import org.mockito.Mock;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
//import org.springframework.boot.test.context.SpringBootTest;
//import org.springframework.test.context.junit.jupiter.SpringExtension;
//import tn.esprit.tpfoyer.entity.Chambre;
//import tn.esprit.tpfoyer.entity.TypeChambre;
//import tn.esprit.tpfoyer.repository.ChambreRepository;
//
//import java.util.List;
//
//import static org.junit.jupiter.api.Assertions.assertEquals;
//
//@SpringBootTest
//class ChambreRepositoryTest {
//
//    @Mock
//    private ChambreRepository chambreRepository;
//
//
//
//
//    @BeforeEach
//    void setUp() {
//        Chambre chambre = new Chambre();
//        chambre.setNumeroChambre(101L);
//        chambre.setTypeC(TypeChambre.SIMPLE);
//        chambreRepository.save(chambre);
//    }
//
//    @Test
//    void testFindByType() {
//        List<Chambre> chambres = chambreRepository.findByTypeC(TypeChambre.SIMPLE);
//        assertEquals(1, chambres.size());
//        assertEquals(TypeChambre.SIMPLE, chambres.get(0).getTypeC());
//    }
//}
