package com.os360.enterprise.entity;

import jakarta.persistence.*;
import lombok.*;

import java.time.OffsetDateTime;
import java.util.UUID;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "user_role_assignment")
public class UserRoleAssignment {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private UUID id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_id", nullable = false)
    private User user;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "role_id", nullable = false)
    private UserRole role;

    @Column(nullable = false)
    private OffsetDateTime assignedAt = OffsetDateTime.now();

    @Column
    private OffsetDateTime expiresAt; // null → permanent

    @Column(length = 255)
    private String assignedBy; // e.g., admin username
}
