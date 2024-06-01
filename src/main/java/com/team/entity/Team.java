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
@Table(name = "teams")
public class Team {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false)
    private Long id;

    @Column(name = "nr_members")
    private int nrMembers;

    @Column(name = "project_name")
    private String projectName;

    @ManyToOne
    @JoinColumn(name = "theme_id")
    private TeamTheme theme;

    @ManyToOne
    @JoinColumn(name = "user_id")
    private User user;



}
