package com.donnedesang.model;

import java.util.Arrays;
import java.util.List;

public enum GroupeSanguin {
    O_NEGATIF("O-"),
    O_POSITIF("O+"),
    A_NEGATIF("A-"),
    A_POSITIF("A+"),
    B_NEGATIF("B-"),
    B_POSITIF("B+"),
    AB_NEGATIF("AB-"),
    AB_POSITIF("AB+");

    private String label;

    GroupeSanguin(String label) {
        this.label = label;
    }

    public String getLabel() {
        return label;
    }

    // Méthode pour vérifier la compatibilité
    public static boolean estCompatible(GroupeSanguin donneur, GroupeSanguin receveur) {
        // O- est donneur universel
        if (donneur == O_NEGATIF) {
            return true;
        }

        // AB+ est receveur universel
        if (receveur == AB_POSITIF) {
            return true;
        }

        // Matrice de compatibilité
        switch (receveur) {
            case O_NEGATIF:
                return donneur == O_NEGATIF;
            case O_POSITIF:
                return Arrays.asList(O_NEGATIF, O_POSITIF).contains(donneur);
            case A_NEGATIF:
                return Arrays.asList(O_NEGATIF, A_NEGATIF).contains(donneur);
            case A_POSITIF:
                return Arrays.asList(O_NEGATIF, O_POSITIF, A_NEGATIF, A_POSITIF).contains(donneur);
            case B_NEGATIF:
                return Arrays.asList(O_NEGATIF, B_NEGATIF).contains(donneur);
            case B_POSITIF:
                return Arrays.asList(O_NEGATIF, O_POSITIF, B_NEGATIF, B_POSITIF).contains(donneur);
            case AB_NEGATIF:
                return Arrays.asList(O_NEGATIF, A_NEGATIF, B_NEGATIF, AB_NEGATIF).contains(donneur);
            default:
                return false;
        }
    }
}