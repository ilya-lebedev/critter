package com.udacity.jdnd.course3.critter.user;

import org.hibernate.annotations.Nationalized;

import javax.persistence.*;
import java.time.DayOfWeek;
import java.util.Set;

@Entity
public class Employee {

    @Id
    @GeneratedValue
    private long id;

    @Nationalized
    private String name;

    @ElementCollection(targetClass = EmployeeSkill.class)
    @JoinTable(name = "employee_skills", joinColumns = @JoinColumn(name = "id"))
    @Column(name = "skills")
    @Enumerated(EnumType.STRING)
    private Set<EmployeeSkill> skills;

    @ElementCollection(targetClass = DayOfWeek.class)
    @JoinTable(name = "days_available", joinColumns = @JoinColumn(name = "id"))
    @Column(name = "days")
    @Enumerated(EnumType.STRING)
    private Set<DayOfWeek> daysAvailable;

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Set<EmployeeSkill> getSkills() {
        return skills;
    }

    public void setSkills(Set<EmployeeSkill> skills) {
        this.skills = skills;
    }

    public Set<DayOfWeek> getDaysAvailable() {
        return daysAvailable;
    }

    public void setDaysAvailable(Set<DayOfWeek> daysAvailable) {
        this.daysAvailable = daysAvailable;
    }

}
