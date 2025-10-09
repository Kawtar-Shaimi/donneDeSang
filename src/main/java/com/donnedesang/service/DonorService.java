package com.donnedesang.service;

import com.donnedesang.dao.DonorDAO;
import com.donnedesang.model.Donor;
import com.donnedesang.model.Receiver;

import java.util.List;
import java.util.stream.Collectors;

public class DonorService {

    private DonorDAO donorDAO = new DonorDAO();

    // Met à jour automatiquement le statut d’un donneur
    public void updateDonorStatus(Donor donor) {
        int age = donor.getAge();
        double weight = donor.getWeight();
        boolean hasContraindications = donor.isHasContraindications();

        if (age < 18 || age > 65 || weight < 50 || hasContraindications) {
            donor.setStatus("NON_ELIGIBLE");
        } else if (donor.getReceivers() != null && !donor.getReceivers().isEmpty()) {
            donor.setStatus("NON_DISPONIBLE");
        } else {
            donor.setStatus("DISPONIBLE");
        }

        donorDAO.update(donor);
    }

    // Trouver les donneurs compatibles et disponibles pour un receveur
    public List<Donor> findCompatibleDonors(List<Donor> donors, Receiver receiver) {
        return donors.stream()
                .filter(d -> "DISPONIBLE".equals(d.getStatus()))
                .filter(d -> isCompatible(d.getBloodGroup(), receiver.getBloodGroup()))
                .collect(Collectors.toList());
    }

    // Vérifie la compatibilité sanguine
    private boolean isCompatible(String donorGroup, String receiverGroup) {
        if (donorGroup.equals("O-")) return true; // donneur universel
        if (receiverGroup.equals("AB+")) return true; // receveur universel
        return donorGroup.equals(receiverGroup);
    }

    // Associer un donneur à un receveur
    public void assignDonorToReceiver(Donor donor, Receiver receiver) {
        if (isCompatible(donor.getBloodGroup(), receiver.getBloodGroup())
                && "DISPONIBLE".equals(donor.getStatus())
                && !receiver.isSatisfied()) {

            receiver.setDonor(donor);
            donor.getReceivers().add(receiver);
            donor.setStatus("NON_DISPONIBLE");

            donorDAO.update(donor);
        }
    }

    public List<Donor> getAllDonors() {
        return donorDAO.findAll();
    }
}