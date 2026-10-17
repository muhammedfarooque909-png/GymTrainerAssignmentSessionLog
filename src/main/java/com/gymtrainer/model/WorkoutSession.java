
package com.gymtrainer.model;

public class WorkoutSession {

    private int sessionId;
    private int memberId;
    private int trainerId;
    private String sessionDate;
    private int durationMinutes;
    private String workoutDetails;

    public WorkoutSession(int sessionId, int memberId,
                          int trainerId, String sessionDate,
                          int durationMinutes, String workoutDetails) {
        this.sessionId = sessionId;
        this.memberId = memberId;
        this.trainerId = trainerId;
        this.sessionDate = sessionDate;
        this.durationMinutes = durationMinutes;
        this.workoutDetails = workoutDetails;
    }

    public int getSessionId() {
        return sessionId;
    }

    public int getMemberId() {
        return memberId;
    }

    public int getTrainerId() {
        return trainerId;
    }

    public String getSessionDate() {
        return sessionDate;
    }

    public int getDurationMinutes() {
        return durationMinutes;
    }

    public String getWorkoutDetails() {
        return workoutDetails;
    }
}
