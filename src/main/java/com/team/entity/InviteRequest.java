package com.team.entity;

import lombok.*;

import javax.persistence.*;

@Entity
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "invitiation_request")
public class InviteRequest {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "sender_user")
    private User sender;

    @ManyToOne
    @JoinColumn(name = "receiver_user")
    private User receiver;

    @Column(name = "status")
    @Enumerated(EnumType.STRING)
    private Status status;




}
