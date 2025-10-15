package com.donnedesang.model;

import jakarta.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "receivers")
public class Receiver {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String firstName;
    private String lastName;
    private String email;
    private String phone;
    private String cin;
    private String birthday;
    private String sexe;
    private String bloodGroup;
    private String needType; // NORMAL, URGENT, CRITIQUE
    private boolean satisfied = false;

    // Nouvelle relation : un receveur peut avoir plusieurs donneurs
    @ManyToMany
    @JoinTable(
            name = "receiver_donor",
            joinColumns = @JoinColumn(name = "receiver_id"),
            inverseJoinColumns = @JoinColumn(name = "donor_id")
    )
    private List<Donor> donors = new ArrayList<>();

    public Receiver() {}

    // ===== Getters / Setters =====
    public Long getId() { return id; }
    public void setId(Long id) { this.id = id; }

    public String getFirstName() { return firstName; }
    public void setFirstName(String firstName) { this.firstName = firstName; }

    public String getLastName() { return lastName; }
    public void setLastName(String lastName) { this.lastName = lastName; }

    public String getEmail() { return email; }
    public void setEmail(String email) { this.email = email; }

    public String getPhone() { return phone; }
    public void setPhone(String phone) { this.phone = phone; }

    public String getCin() { return cin; }
    public void setCin(String cin) { this.cin = cin; }

    public String getBirthday() { return birthday; }
    public void setBirthday(String birthday) { this.birthday = birthday; }

    public String getSexe() { return sexe; }
    public void setSexe(String sexe) { this.sexe = sexe; }

    public String getBloodGroup() { return bloodGroup; }
    public void setBloodGroup(String bloodGroup) { this.bloodGroup = bloodGroup; }

    public String getNeedType() { return needType; }
    public void setNeedType(String needType) { this.needType = needType; }

    public boolean isSatisfied() { return satisfied; }
    public void setSatisfied(boolean satisfied) { this.satisfied = satisfied; }

    public List<Donor> getDonors() { return donors; }

    // Ajouter un donneur
    public void addDonor(Donor donor) {
        if (!donors.contains(donor)) {
            donors.add(donor);
        }
    }

    // Retourne le nombre de poches nécessaires selon le besoin
    public int getRequiredPouches() {
        switch (needType) {
            case "CRITIQUE": return 4;
            case "URGENT": return 3;
            case "NORMAL": return 1;
            default: return 1;
        }
    }
}