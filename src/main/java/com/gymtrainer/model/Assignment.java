
package com.gymtrainer.model;

public class Assignment {

    private int assignmentId;
    private int memberId;
    private int trainerId;
    private String startDate;
    private String endDate;

    public Assignment(int assignmentId, int memberId,
                      int trainerId, String startDate,
                      String endDate) {
        this.assignmentId = assignmentId;
        this.memberId = memberId;
        this.trainerId = trainerId;
        this.startDate = startDate;
        this.endDate = endDate;
    }

    public int getAssignmentId() {
        return assignmentId;
    }

    public int getMemberId() {
        return memberId;
    }

    public int getTrainerId() {
        return trainerId;
    }

    public String getStartDate() {
        return startDate;
    }

    public String getEndDate() {
        return endDate;
    }
}
