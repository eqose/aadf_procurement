package com.team.entity;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;

@Getter
@Setter
@Entity
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "user_details")
public class UserInformation {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false)
    private Long id;

    @Column(name = "age")
    private int age;

    @Column(name = "gender")
    private String gender;

    @Column(name = "qualification")
    private String qualification;

    @Column(name = "education")
    private String education;

    @Column(name = "profession")
    private String profession;

    @Column(name = "position")
    private String position;

    @Column(name = "company")
    private String company;

    @Column(name = "experience")
    private int experience;

    @Column(name = "location")
    private String location;

    @ManyToOne
    @JoinColumn(name = "user_id")
    private User user;

}
