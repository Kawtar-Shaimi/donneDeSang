package com.donnedesang.model;

import jakarta.persistence.*;

@Entity
public class Donor {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String firstName;
    private String lastName;
    private String cin;
    private String phone;
    private String bloodGroup;
    private int age;
    private double weight;
    private boolean hasContraindications;
    private String status;

    // --- Getters & Setters ---
    public Long getId() { return id; }

    public String getFirstName() { return firstName; }
    public void setFirstName(String firstName) { this.firstName = firstName; }

    public String getLastName() { return lastName; }
    public void setLastName(String lastName) { this.lastName = lastName; }

    public String getCin() { return cin; }
    public void setCin(String cin) { this.cin = cin; }

    public String getPhone() { return phone; }
    public void setPhone(String phone) { this.phone = phone; }

    public String getBloodGroup() { return bloodGroup; }
    public void setBloodGroup(String bloodGroup) { this.bloodGroup = bloodGroup; }

    public int getAge() { return age; }
    public void setAge(int age) { this.age = age; }

    public double getWeight() { return weight; }
    public void setWeight(double weight) { this.weight = weight; }

    public boolean isHasContraindications() { return hasContraindications; }
    public void setHasContraindications(boolean hasContraindications) { this.hasContraindications = hasContraindications; }

    public String getStatus() { return status; }
    public void setStatus(String status) { this.status = status; }
}
