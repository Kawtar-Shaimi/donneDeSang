package com.donnedesang.service;

import com.donnedesang.model.Donor;
import com.donnedesang.model.Receiver;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

public class DonorServiceTest {

    private DonorService donorService;

    @BeforeEach
    void setUp() {
        donorService = new DonorService();
    }

    // 🧪 Test 1 : Donneur éligible
    @Test
    void testUpdateDonorStatus_Eligible() {
        Donor donor = new Donor();
        donor.setAge(25);
        donor.setWeight(70);
        donor.setHasContraindications(false);
        donor.setReceivers(new ArrayList<>());

        donorService.updateDonorStatus(donor);

        assertEquals("DISPONIBLE", donor.getStatus(),
                "Le donneur doit être marqué comme DISPONIBLE");
    }

    // 🧪 Test 2 : Donneur non éligible (trop jeune)
    @Test
    void testUpdateDonorStatus_NotEligible_YoungAge() {
        Donor donor = new Donor();
        donor.setAge(16);
        donor.setWeight(60);
        donor.setHasContraindications(false);

        donorService.updateDonorStatus(donor);

        assertEquals("NON_ELIGIBLE", donor.getStatus(),
                "Le donneur doit être NON_ELIGIBLE s'il a moins de 18 ans");
    }

    // 🧪 Test 3 : Donneur avec un receveur assigné
    @Test
    void testUpdateDonorStatus_NotDisponible() {
        Donor donor = new Donor();
        donor.setAge(30);
        donor.setWeight(70);
        donor.setHasContraindications(false);

        List<Receiver> receivers = new ArrayList<>();
        receivers.add(new Receiver());
        donor.setReceivers(receivers);

        donorService.updateDonorStatus(donor);

        assertEquals("NON_DISPONIBLE", donor.getStatus(),
                "Le donneur doit être NON_DISPONIBLE s'il a déjà un receveur");
    }

    // 🧪 Test 4 : Trouver les donneurs compatibles
    @Test
    void testFindCompatibleDonors() {
        Receiver receiver = new Receiver();
        receiver.setBloodGroup("A+");

        Donor d1 = new Donor();
        d1.setBloodGroup("A+");
        d1.setStatus("DISPONIBLE");

        Donor d2 = new Donor();
        d2.setBloodGroup("B+");
        d2.setStatus("DISPONIBLE");

        List<Donor> donors = List.of(d1, d2);

        List<Donor> compatibles = donorService.findCompatibleDonors(donors, receiver);

        assertEquals(1, compatibles.size(), "Il ne doit y avoir qu’un seul donneur compatible");
        assertEquals("A+", compatibles.get(0).getBloodGroup());
    }

    // 🧪 Test 5 : Associer un donneur compatible à un receveur
    @Test
    void testAssignDonorToReceiver_Success() {
        Donor donor = new Donor();
        donor.setBloodGroup("O-");
        donor.setStatus("DISPONIBLE");
        donor.setReceivers(new ArrayList<>());

        Receiver receiver = new Receiver();
        receiver.setBloodGroup("A+");
        receiver.setSatisfied(false);

        donorService.assignDonorToReceiver(donor, receiver);

        assertEquals(donor, receiver.getDonor(),
                "Le receveur doit être associé au donneur");
        assertEquals("NON_DISPONIBLE", donor.getStatus(),
                "Le donneur devient NON_DISPONIBLE après l’attribution");
    }
}
