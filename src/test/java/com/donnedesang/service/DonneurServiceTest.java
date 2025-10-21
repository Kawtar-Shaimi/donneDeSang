//package com.donnedesang.service;
//
//import com.donnedesang.model.Donneur;
//import com.donnedesang.model.StatutDisponibilite;
//import org.junit.jupiter.api.Test;
//
//import java.time.LocalDate;
//
//import static org.junit.jupiter.api.Assertions.*;
//
//public class DonneurServiceTest {
//
//    private DonneurService service = new DonneurService();
//
//    @Test
//    public void testDetermineStatut_AgeValide() {
//        // Arrange
//        Donneur donneur = new Donneur();
//        donneur.setDateNaissance(LocalDate.now().minusYears(25)); // 25 ans
//        donneur.setPoids(70.0);
//        donneur.setHepatiteB(false);
//        donneur.setHepatiteC(false);
//        donneur.setVih(false);
//        donneur.setDiabete(false);
//        donneur.setGrossesse(false);
//        donneur.setAllaitement(false);
//        donneur.setReceveur(null);
//
//        // Act
//        // On appelle une méthode publique qui utilise estAgeValide()
//        // (determineStatut est private mais utilisé dans ajouterDonneur)
//        StatutDisponibilite statut = serviceTestHelper_determineStatut(service, donneur);
//
//        // Assert
//        assertEquals(StatutDisponibilite.DISPONIBLE, statut);
//    }
//
//    @Test
//    public void testDetermineStatut_AgeNonValide() {
//        // Arrange
//        Donneur donneur = new Donneur();
//        donneur.setDateNaissance(LocalDate.now().minusYears(16)); // 16 ans
//        donneur.setPoids(70.0);
//        donneur.setHepatiteB(false);
//        donneur.setHepatiteC(false);
//        donneur.setVih(false);
//        donneur.setDiabete(false);
//        donneur.setGrossesse(false);
//        donneur.setAllaitement(false);
//        donneur.setReceveur(null);
//
//        // Act
//        StatutDisponibilite statut = serviceTestHelper_determineStatut(service, donneur);
//
//        // Assert
//        assertEquals(StatutDisponibilite.NON_ELIGIBLE, statut);
//    }
//
//    // Petit helper pour tester la méthode private determineStatut()
//    private StatutDisponibilite serviceTestHelper_determineStatut(DonneurService service, Donneur d) {
//        try {
//            var method = DonneurService.class.getDeclaredMethod("determineStatut", Donneur.class);
//            method.setAccessible(true);
//            return (StatutDisponibilite) method.invoke(service, d);
//        } catch (Exception e) {
//            throw new RuntimeException(e);
//        }
//    }
//}