//package com.donnedesang.service;
//
//import com.donnedesang.model.Donor;
//import org.junit.jupiter.api.BeforeEach;
//import org.junit.jupiter.api.AfterEach;
//import org.junit.jupiter.api.Test;
//import java.util.List;
//import static org.junit.jupiter.api.Assertions.*;
//
//class DonorServiceTest {
//
//    private DonorService donorService;
//    private Donor testDonor;
//
//    @BeforeEach
//    void setUp() {
//        donorService = new DonorService();
//        testDonor = new Donor();
//        testDonor.setFirstName("John");
//        testDonor.setLastName("Doe");
//        testDonor.setCin("123456");
//        testDonor.setPhone("0123456789");
//        testDonor.setBloodGroup("A+");
//        testDonor.setAge(30);
//        testDonor.setWeight(70.0);
//
//        donorService.addDonor(testDonor);
//    }
//
//    @AfterEach
//    void tearDown() {
//        // Optionnel : supprimer testDonor pour garder la DB propre
//    }
//
//    @Test
//    void testGetDonorById() {
//        Donor donor = donorService.getDonorById(testDonor.getId());
//        assertNotNull(donor);
//        assertEquals("John", donor.getFirstName());
//    }
//
//    @Test
//    void testGetAllDonors() {
//        List<Donor> donors = donorService.getAllDonors();
//        assertTrue(donors.size() > 0);
//    }
//
//    @Test
//    void testUpdateDonor() {
//        testDonor.setPhone("0987654321");
//        donorService.updateDonor(testDonor);
//
//        Donor updatedDonor = donorService.getDonorById(testDonor.getId());
//        assertEquals("0987654321", updatedDonor.getPhone());
//    }
//}