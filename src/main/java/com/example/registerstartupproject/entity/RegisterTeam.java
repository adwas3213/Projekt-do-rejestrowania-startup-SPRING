package com.example.registerstartupproject.entity;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.*;
import org.hibernate.Hibernate;
import org.hibernate.annotations.Cascade;
import org.hibernate.annotations.CascadeType;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Objects;
import java.util.Set;

@Entity
@Getter
@Setter
@ToString
@RequiredArgsConstructor
@NoArgsConstructor
@AllArgsConstructor
public class RegisterTeam {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @OneToMany(fetch = FetchType.EAGER)
    @Cascade({CascadeType.PERSIST, CascadeType.DELETE})
    private List<Member> members;
    private String username;
    private String email;
    private String password;
    private boolean isEnabled = false;
    @OneToOne(cascade = javax.persistence.CascadeType.PERSIST, fetch = FetchType.EAGER)
    @JoinColumn(name = "token")
    private TokenToRegistry tokenToRegistry;
    @OneToOne(fetch = FetchType.EAGER)
    private RegisterIdea idea;
    private LocalDateTime dateOfRegistry;
    @ManyToMany(fetch = FetchType.EAGER)
    private Set<Role> roles;
    @JsonProperty
    List roles() {
        return roles.stream().toList();
    }
    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || Hibernate.getClass(this) != Hibernate.getClass(o)) return false;
        RegisterTeam that = (RegisterTeam) o;
        return id != null && Objects.equals(id, that.id);
    }
    @Override
    public int hashCode() {
        return getClass().hashCode();
    }
}
