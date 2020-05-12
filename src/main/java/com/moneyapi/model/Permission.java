package com.moneyapi.model;

import javax.persistence.*;
import javax.validation.constraints.NotNull;

@Entity
@Table(name =  "permission")
public class Permission {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotNull
    private String description;

}
