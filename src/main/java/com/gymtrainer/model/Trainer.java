
package com.gymtrainer.model;

public class Trainer {

    private int trainerId;
    private String name;
    private String specialization;
    private String phone;

    public Trainer(int trainerId, String name,
                   String specialization, String phone) {
        this.trainerId = trainerId;
        this.name = name;
        this.specialization = specialization;
        this.phone = phone;
    }

    public int getTrainerId() {
        return trainerId;
    }

    public String getName() {
        return name;
    }

    public String getSpecialization() {
        return specialization;
    }

    public String getPhone() {
        return phone;
    }
}
