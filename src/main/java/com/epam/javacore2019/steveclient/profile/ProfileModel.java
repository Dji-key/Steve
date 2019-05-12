package com.epam.javacore2019.steveclient.profile;

import java.util.Date;

public class ProfileModel {
    private int id;
    private String firstName;
    private String lastName;
    private String nickname;
    private int criminalFamilyId;
    private Date dateOfBirth;
    private boolean deceased;
    private Date dateOfDeath;
    private int numberOfCrimes;



    public ProfileModel() {}

    public ProfileModel(int id, String firstName, String lastName, String nickname, int criminalFamilyId, Date dateOfBirth, boolean deceased, Date dateOfDeath, int numberOfCrimes) {
        this.id = id;
        this.firstName = firstName;
        this.lastName = lastName;
        this.nickname = nickname;
        this.criminalFamilyId = criminalFamilyId;
        this.dateOfBirth = dateOfBirth;
        this.deceased = deceased;
        this.dateOfDeath = dateOfDeath;
        this.numberOfCrimes = numberOfCrimes;
    }



    public int getId() {
        return id;
    }

    public ProfileModel setId(int id) {
        this.id = id;
        return this;
    }

    public String getFirstName() {
        return firstName;
    }

    public ProfileModel setFirstName(String firstName) {
        this.firstName = firstName;
        return this;
    }

    public String getLastName() {
        return lastName;
    }

    public ProfileModel setLastName(String lastName) {
        this.lastName = lastName;
        return this;
    }

    public String getNickname() {
        return nickname;
    }

    public ProfileModel setNickname(String nickname) {
        this.nickname = nickname;
        return this;
    }

    public int getCriminalFamilyId() {
        return criminalFamilyId;
    }

    public ProfileModel setCriminalFamilyId(int criminalFamilyId) {
        this.criminalFamilyId = criminalFamilyId;
        return this;
    }

    public Date getBirthday() {
        return dateOfBirth;
    }

    public ProfileModel setBirthday(Date dateOfBirth) {
        this.dateOfBirth = dateOfBirth;
        return this;
    }

    public boolean isDeceased() {
        return deceased;
    }

    public ProfileModel setDeceased(boolean deceased) {
        this.deceased = deceased;
        return this;
    }

    public Date getDateOfDeath() {
        return dateOfDeath;
    }

    public ProfileModel setDateOfDeath(Date dateOfDeath) {
        this.dateOfDeath = dateOfDeath;
        return this;
    }

    public int getNumberOfCrimes() {
        return numberOfCrimes;
    }

    public ProfileModel setNumberOfCrimes(int numberOfCrimes) {
        this.numberOfCrimes = numberOfCrimes;
        return this;
    }
}
