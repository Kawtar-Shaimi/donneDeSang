package com.donnedesang.service;

import com.donnedesang.dao.MatchDAO;
import com.donnedesang.model.Donor;
import com.donnedesang.model.Match;
import com.donnedesang.model.Receiver;

public class MatchService {

    private MatchDAO matchDAO = new MatchDAO();

    public void assignDonorToReceiver(Donor donor, Receiver receiver) {
        // Ici tu mets la logique de validation avant le match
        // Ex : donor DISPONIBLE, receiver non SATISFAIT, compatibilité sanguine...

        // Créer le match
        Match match = new Match(donor, receiver);
        matchDAO.create(match);

        // Mettre à jour les statuts du donneur et du receveur (à gérer dans DonorService/ReceiverService)
    }
}
