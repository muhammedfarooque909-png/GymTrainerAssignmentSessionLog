
package com.gymtrainer.model;

public class Member {
    private int memberId;
    private String name;
    private int age;
    private String gender;
    private String phone;
    private String joinDate;

    public Member(int memberId, String name, int age,
                  String gender, String phone, String joinDate) {
        this.memberId = memberId;
        this.name = name;
        this.age = age;
        this.gender = gender;
        this.phone = phone;
        this.joinDate = joinDate;
    }

    public int getMemberId() {
        return memberId;
    }

    public String getName() {
        return name;
    }

    public int getAge() {
        return age;
    }

    public String getGender() {
        return gender;
    }

    public String getPhone() {
        return phone;
    }

    public String getJoinDate() {
        return joinDate;
    }
}
