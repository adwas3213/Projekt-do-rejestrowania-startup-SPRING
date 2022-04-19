package com.example.registerstartupproject.entity;

import lombok.*;
import org.hibernate.Hibernate;

import javax.persistence.Entity;
import javax.persistence.Id;
import java.util.Objects;

@Entity
@Getter
@Setter
@ToString
@RequiredArgsConstructor
public class TokenToRegistry {
    @Id
    private String token;

    public TokenToRegistry(String token) {
        this.token = token;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || Hibernate.getClass(this) != Hibernate.getClass(o)) return false;
        TokenToRegistry that = (TokenToRegistry) o;
        return token != null && Objects.equals(token, that.token);
    }
    @Override
    public int hashCode() {
        return getClass().hashCode();
    }
}
