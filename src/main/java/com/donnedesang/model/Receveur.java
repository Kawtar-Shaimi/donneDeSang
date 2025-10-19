package com.donnedesang.model;

import jakarta.persistence.*;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "receveurs")
public class Receveur {

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

    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private Sexe sexe;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private GroupeSanguin groupeSanguin;

    // Besoin: CRITIQUE (4), URGENT (3), NORMAL (1)
    @Column(nullable = false)
    private int besoinPoches = 1;

    @Column(nullable = false)
    private int pocheRecues = 0;

    @Column(nullable = false)
    private boolean satisfait = false;

    @OneToMany(mappedBy = "receveur", fetch = FetchType.EAGER)
    private List<Donneur> donneurs = new ArrayList<>();


    public Receveur() {}

    // Getters / Setters

    public Long getId() { return id; }
    public void setId(Long id) { this.id = id; }
    public String getNom() { return nom; }
    public void setNom(String nom) { this.nom = nom; }
    public String getPrenom() { return prenom; }
    public void setPrenom(String prenom) { this.prenom = prenom; }
    public String getCin() { return cin; }
    public void setCin(String cin) { this.cin = cin; }
    public String getTelephone() { return telephone; }
    public void setTelephone(String telephone) { this.telephone = telephone; }
    public LocalDate getDateNaissance() { return dateNaissance; }
    public void setDateNaissance(LocalDate dateNaissance) { this.dateNaissance = dateNaissance; }
    public Sexe getSexe() { return sexe; }
    public void setSexe(Sexe sexe) { this.sexe = sexe; }
    public GroupeSanguin getGroupeSanguin() { return groupeSanguin; }
    public void setGroupeSanguin(GroupeSanguin groupeSanguin) { this.groupeSanguin = groupeSanguin; }
    public int getBesoinPoches() { return besoinPoches; }
    public void setBesoinPoches(int besoinPoches) { this.besoinPoches = besoinPoches; }
    public int getPocheRecues() { return pocheRecues; }
    public void setPocheRecues(int pocheRecues) { this.pocheRecues = pocheRecues; }
    public boolean isSatisfait() { return satisfait; }
    public void setSatisfait(boolean satisfait) { this.satisfait = satisfait; }
    public List<Donneur> getDonneurs() { return donneurs; }
    public void setDonneurs(List<Donneur> donneurs) { this.donneurs = donneurs; }

    // helper
    public void ajouterDonneur(Donneur d) {
        this.donneurs.add(d);
        this.pocheRecues++;
        if (this.pocheRecues >= this.besoinPoches) {
            this.satisfait = true;
        }
    }
}