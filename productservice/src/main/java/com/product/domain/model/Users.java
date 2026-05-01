package com.product.domain.model;

import io.quarkus.hibernate.orm.panache.PanacheEntity;
import jakarta.persistence.*;

@Entity
@Table(name = "users")
public class Users extends PanacheEntity {
    public String name;
    public String email;
    public String status;
    public String createdAt;

}