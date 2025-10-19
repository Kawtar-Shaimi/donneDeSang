package com.donnedesang.service;

import com.donnedesang.dao.DonneurDAO;
import com.donnedesang.dao.ReceveurDAO;
import com.donnedesang.model.Donneur;
import com.donnedesang.model.Receveur;
import com.donnedesang.model.GroupeSanguin;
import com.donnedesang.model.StatutDisponibilite;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

public class ReceveurService {

    private final ReceveurDAO dao = new ReceveurDAO();
    private final DonneurDAO donneurDAO = new DonneurDAO();

    public void ajouterReceveur(Receveur r) {
        if (r.getBesoinPoches() <= 0) r.setBesoinPoches(1);
        dao.save(r);
    }

    public void modifierReceveur(Receveur r) {
        dao.update(r);
    }

    public void supprimerReceveur(Long id) {
        dao.delete(id);
    }

    public List<Receveur> listerTousParPriorite() {
        return dao.findAllOrderByPriorite();
    }

    public Optional<Receveur> trouverParId(Long id) {
        return dao.findById(id);
    }

    // Retourne receveurs compatibles et non satisfaits pour un donneur donné
    public List<Receveur> receveursCompatiblesPourDonneur(GroupeSanguin donneurGroupe) {
        return listerTousParPriorite().stream()
                .filter(r -> !r.isSatisfait())
                .filter(r -> GroupeSanguin.estCompatible(donneurGroupe, r.getGroupeSanguin()))
                .collect(Collectors.toList());
    }

    // associer un donneur à un receveur : met à jour both
    public boolean associerDonneurAReceveur(Donneur d, Receveur r) {
        if (d == null || r == null) return false;
        if (r.isSatisfait() || d.getStatut() != StatutDisponibilite.DISPONIBLE) return false;

        // Ajouter le donneur
        r.getDonneurs().add(d);
        d.setReceveur(r);
        d.setStatut(StatutDisponibilite.NON_DISPONIBLE);

        // Décrémenter le besoin de poches
        int nouvellesPoches = r.getBesoinPoches() - 1;
        r.setBesoinPoches(Math.max(nouvellesPoches, 0));

        // Mettre à jour le statut du receveur
        if (r.getBesoinPoches() == 0) {
            r.setSatisfait(true);
        }

        // Persist changes
        donneurDAO.update(d);
        dao.update(r);

        return true;
    }
}