package com.donnedesang.service;

import com.donnedesang.dao.DonneurDAO;
import com.donnedesang.model.*;

import java.time.LocalDate;
import java.time.Period;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

public class DonneurService {

    private DonneurDAO dao = new DonneurDAO();

    public void ajouterDonneur(Donneur d) {
        // Calculer statut automatiquement
        d.setStatut(determineStatut(d));
        dao.save(d);
    }

    public void modifierDonneur(Donneur d) {
        d.setStatut(determineStatut(d));
        dao.update(d);
    }

    public void supprimerDonneur(Long id) {
        dao.delete(id);
    }

    public List<Donneur> listerTous() {
        return dao.findAll();
    }

    public Optional<Donneur> trouverParId(Long id) {
        return dao.findById(id);
    }

    public List<Donneur> listerDisponiblesCompatibles(GroupeSanguin receveurGroupe) {
        List<Donneur> dispo = dao.findDisponibles();
        return dispo.stream()
                .filter(d -> GroupeSanguin.estCompatible(d.getGroupeSanguin(), receveurGroupe))
                .collect(Collectors.toList());
    }

    private StatutDisponibilite determineStatut(Donneur d) {
        // Vérifier l'éligibilité
        if (!estAgeValide(d.getDateNaissance()) || !estPoidsValide(d.getPoids()) || d.getHepatiteB() || d.getHepatiteC() || d.getVih() || d.getDiabete() || d.getGrossesse() || d.getAllaitement()) {
            return StatutDisponibilite.NON_ELIGIBLE;
        }
        // si déjà associé à un receveur => NON_DISPONIBLE
        if (d.getReceveur() != null) {
            return StatutDisponibilite.NON_DISPONIBLE;
        }
        return StatutDisponibilite.DISPONIBLE;
    }

    private boolean estAgeValide(LocalDate dateNaissance) {
        if (dateNaissance == null) return false;
        int age = Period.between(dateNaissance, LocalDate.now()).getYears();
        return age >= 18 && age <= 65;
    }

    private boolean estPoidsValide(Double poids) {
        if (poids == null) return false;
        return poids >= 50.0;
    }

    public List<Donneur> donneursMaigre(){
        return listerTous().stream()
                .filter(c -> c.getPoids() <  50.0)
                .sorted(Comparator.comparing(Donneur::getPoids))
                .toList();
    }

    public List<Donneur> donneurLourd(){
        return Collections.singletonList(listerTous().stream()
                .max(Comparator.comparing(Donneur::getPoids))
                .orElse(null));
    }
}