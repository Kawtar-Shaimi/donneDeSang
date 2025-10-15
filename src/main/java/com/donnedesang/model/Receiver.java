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
    private String bloodGroup;
    private String needType; // NORMAL, URGENT, CRITIQUE

    private String phone;
    private String cin;
    private String birthday;
    private String sexe;

    @Enumerated(EnumType.STRING)
    private ReceiverState state = ReceiverState.EN_ATTENTE;

    @ManyToMany
    @JoinTable(
            name = "receiver_donor",
            joinColumns = @JoinColumn(name = "receiver_id"),
            inverseJoinColumns = @JoinColumn(name = "donor_id")
    )
    private List<Donor> donors = new ArrayList<>();

    // ===== Constructeurs =====
    public Receiver() {}

    public Receiver(String firstName, String lastName, String bloodGroup, String needType) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.bloodGroup = bloodGroup;
        this.needType = needType;
    }

    // ===== Getters / Setters =====
    public Long getId() { return id; }
    public void setId(Long id) { this.id = id; }

    public String getFirstName() { return firstName; }
    public void setFirstName(String firstName) { this.firstName = firstName; }

    public String getLastName() { return lastName; }
    public void setLastName(String lastName) { this.lastName = lastName; }

    public String getBloodGroup() { return bloodGroup; }
    public void setBloodGroup(String bloodGroup) { this.bloodGroup = bloodGroup; }

    public String getNeedType() { return needType; }
    public void setNeedType(String needType) { this.needType = needType; }

    public String getPhone() { return phone; }
    public void setPhone(String phone) { this.phone = phone; }

    public String getCin() { return cin; }
    public void setCin(String cin) { this.cin = cin; }

    public String getBirthday() { return birthday; }
    public void setBirthday(String birthday) { this.birthday = birthday; }

    public String getSexe() { return sexe; }
    public void setSexe(String sexe) { this.sexe = sexe; }

    public ReceiverState getState() { return state; }

    // Méthode pour compatibilité avec setSatisfied(boolean)
    public void setSatisfied(boolean satisfied) {
        this.state = satisfied ? ReceiverState.SATISFAIT : ReceiverState.EN_ATTENTE;
    }

    public List<Donor> getDonors() { return donors; }

    // ===== Méthodes métiers =====
    public void addDonor(Donor donor) {
        if (!donors.contains(donor) && !isSatisfied()) {
            donors.add(donor);
            if (donors.size() >= getRequiredPouches()) {
                state = ReceiverState.SATISFAIT;
            }
        }
    }

    public boolean isSatisfied() {
        return state == ReceiverState.SATISFAIT;
    }

    // Retourne le nombre de poches nécessaires selon le besoin
    public int getRequiredPouches() {
        return switch (needType) {
            case "CRITIQUE" -> 4;
            case "URGENT" -> 3;
            default -> 1;
        };
    }

    public Donor getDonor() {
        if (!donors.isEmpty()) {
            return donors.get(0);
        }
        return null;
    }

    // ===== Enum pour l'état =====
    public enum ReceiverState {
        EN_ATTENTE,
        SATISFAIT
    }
}