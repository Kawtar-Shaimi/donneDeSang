package com.donnedesang.service;

import com.donnedesang.dao.MatchDAO;
import com.donnedesang.model.Donor;
import com.donnedesang.model.Match;
import com.donnedesang.model.Receiver;

import java.util.ArrayList;
import java.util.List;

public class MatchService {

    private MatchDAO matchDAO = new MatchDAO();

    // Associer un donneur à un receveur
    public boolean assignDonorToReceiver(Donor donor, Receiver receiver) {
        if (donor == null || receiver == null) return false;

        if (!donor.getStatus().equals("DISPONIBLE")) return false;
        if (receiver.isSatisfied()) return false;
        if (!isCompatible(donor.getBloodGroup(), receiver.getBloodGroup())) return false;

        // Crée le match
        Match match = new Match(donor, receiver);
        matchDAO.create(match);

        // Met à jour le statut du donneur
        donor.setStatus("NON_DISPONIBLE");

        // Ajoute le donneur au receveur
        receiver.addDonor(donor);

        // Vérifie si le receveur a reçu toutes les poches nécessaires
        if (receiver.getDonors().size() >= receiver.getRequiredPouches()) {
            receiver.setSatisfied(true);
        }

        return true;
    }

    // Retourne les donneurs compatibles et disponibles pour un receveur
    public List<Donor> findCompatibleDonors(List<Donor> donors, Receiver receiver) {
        List<Donor> compatibleDonors = new ArrayList<>();
        for (Donor d : donors) {
            if (d.getStatus().equals("DISPONIBLE") && isCompatible(d.getBloodGroup(), receiver.getBloodGroup())) {
                compatibleDonors.add(d);
            }
        }
        return compatibleDonors;
    }

    // Vérifie la compatibilité sanguine simple
    private boolean isCompatible(String donorGroup, String receiverGroup) {
        if (donorGroup.equals("O-")) return true;  // donneur universel
        if (receiverGroup.equals("AB+")) return true; // receveur universel
        return donorGroup.equals(receiverGroup); // sinon compatibilité exacte
    }
}
