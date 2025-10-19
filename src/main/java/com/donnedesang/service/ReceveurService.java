package com.donnedesang.service;

import com.donnedesang.dao.ReceveurDAO;
import com.donnedesang.model.Donneur;
import com.donnedesang.model.Receveur;
import com.donnedesang.model.GroupeSanguin;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

public class ReceveurService {

    private ReceveurDAO dao = new ReceveurDAO();

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

    // Retourne receveurs compatibles et non satisfaits pour un donneur donné (utilisé si besoin)
    public List<Receveur> receveursCompatiblesPourDonneur(GroupeSanguin donneurGroupe) {
        return listerTousParPriorite().stream()
                .filter(r -> !r.isSatisfait())
                .filter(r -> GroupeSanguin.estCompatible(donneurGroupe, r.getGroupeSanguin()))
                .collect(Collectors.toList());
    }

    // associer un donneur à un receveur : met à jour both
    public void associerDonneurAReceveur(Donneur d, Receveur r) {
        if (d == null || r == null) return;
        if (r.isSatisfait()) return;
        // 1 don par receveur max (r penses: each donor adds 1 poche, receveur can have multiple donors until besoin)
        if (d.getStatut() != null && d.getStatut().name().equals("DISPONIBLE")) {
            d.setReceveur(r);
            r.ajouterDonneur(d);
            // Donneur becomes non available
            d.setStatut(com.donnedesang.model.StatutDisponibilite.NON_DISPONIBLE);
            // persist changes: update via DAO
              // use DAOs directly (simple)
            new com.donnedesang.dao.DonneurDAO().update(d);
            new com.donnedesang.dao.ReceveurDAO().update(r);
        }
    }
}