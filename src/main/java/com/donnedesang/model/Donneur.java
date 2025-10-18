package com.donnedesang.model;

import javax.persistence.*;
import java.time.LocalDate;

@Entity
@Table(name = "donneurs")
public class Donneur {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private String nom;

    @Column(nullable = false)
    private String prenom;

    @Column(nullable = false, unique = true)
    private String cin;

    @Column(nullable = false)
    private String telephone;

    @Column(nullable = false)
    private LocalDate dateNaissance;

    @Column(nullable = false)
    private Double poids;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private Sexe sexe;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private GroupeSanguin groupeSanguin;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private StatutDisponibilite statut;

    @Column(nullable = false)
    private Boolean hepatiteB = false;

    @Column(nullable = false)
    private Boolean hepatiteC = false;

    @Column(nullable = false)
    private Boolean vih = false;

    @Column(nullable = false)
    private Boolean diabete = false;

    @Column(nullable = false)
    private Boolean grossesse = false;

    @Column(nullable = false)
    private Boolean allaitement = false;

    @ManyToOne
    @JoinColumn(name = "receveur_id")
    private Receveur receveur;

    // Constructeurs
    public Donneur() {
    }

    // Getters et Setters
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getNom() {
        return nom;
    }

    public void setNom(String nom) {
        this.nom = nom;
    }

    public String getPrenom() {
        return prenom;
    }

    public void setPrenom(String prenom) {
        this.prenom = prenom;
    }

    public String getCin() {
        return cin;
    }

    public void setCin(String cin) {
        this.cin = cin;
    }

    public String getTelephone() {
        return telephone;
    }

    public void setTelephone(String telephone) {
        this.telephone = telephone;
    }

    public LocalDate getDateNaissance() {
        return dateNaissance;
    }

    public void setDateNaissance(LocalDate dateNaissance) {
        this.dateNaissance = dateNaissance;
    }

    public Double getPoids() {
        return poids;
    }

    public void setPoids(Double poids) {
        this.poids = poids;
    }

    public Sexe getSexe() {
        return sexe;
    }

    public void setSexe(Sexe sexe) {
        this.sexe = sexe;
    }

    public GroupeSanguin getGroupeSanguin() {
        return groupeSanguin;
    }

    public void setGroupeSanguin(GroupeSanguin groupeSanguin) {
        this.groupeSanguin = groupeSanguin;
    }

    public StatutDisponibilite getStatut() {
        return statut;
    }

    public void setStatut(StatutDisponibilite statut) {
        this.statut = statut;
    }

    public Boolean getHepatiteB() {
        return hepatiteB;
    }

    public void setHepatiteB(Boolean hepatiteB) {
        this.hepatiteB = hepatiteB;
    }

    public Boolean getHepatiteC() {
        return hepatiteC;
    }

    public void setHepatiteC(Boolean hepatiteC) {
        this.hepatiteC = hepatiteC;
    }

    public Boolean getVih() {
        return vih;
    }

    public void setVih(Boolean vih) {
        this.vih = vih;
    }

    public Boolean getDiabete() {
        return diabete;
    }

    public void setDiabete(Boolean diabete) {
        this.diabete = diabete;
    }

    public Boolean getGrossesse() {
        return grossesse;
    }

    public void setGrossesse(Boolean grossesse) {
        this.grossesse = grossesse;
    }

    public Boolean getAllaitement() {
        return allaitement;
    }

    public void setAllaitement(Boolean allaitement) {
        this.allaitement = allaitement;
    }

    public Receveur getReceveur() {
        return receveur;
    }

    public void setReceveur(Receveur receveur) {
        this.receveur = receveur;
    }
}