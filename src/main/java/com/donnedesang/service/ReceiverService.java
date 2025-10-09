package com.donnedesang.service;

import com.donnedesang.model.Receiver;
import com.donnedesang.model.Donor;

import java.util.List;
import java.util.stream.Collectors;

public class ReceiverService {

    // Calcul du nombre de poches nécessaires selon le type de besoin
    public int getRequiredBags(Receiver receiver) {
        String needType = receiver.getNeedType();

        if ("CRITIQUE".equalsIgnoreCase(needType)) {
            return 4;
        } else if ("URGENT".equalsIgnoreCase(needType)) {
            return 3;
        } else {
            return 1; // NORMAL ou par défaut
        }
    }

    // Vérifie si le receveur est satisfait
    public boolean isSatisfied(Receiver receiver) {
        return receiver.isSatisfied();
    }

    // Met à jour le statut de satisfaction
    public void markAsSatisfied(Receiver receiver) {
        receiver.setSatisfied(true);
    }

    // Filtrer les receveurs non satisfaits
    public List<Receiver> filterPendingReceivers(List<Receiver> receivers) {
        return receivers.stream()
                .filter(r -> !r.isSatisfied())
                .collect(Collectors.toList());
    }

    // Associer un donneur à un receveur (si compatible)
    public void assignDonorIfCompatible(Donor donor, Receiver receiver) {
        if (donor.getBloodGroup().equals(receiver.getBloodGroup()) || donor.getBloodGroup().equals("O-")) {
            receiver.setDonor(donor);
            receiver.setSatisfied(true);
        }
    }
}