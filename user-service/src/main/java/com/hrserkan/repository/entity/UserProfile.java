package com.hrserkan.repository.entity;

import com.hrserkan.repository.enums.EStatus;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.SuperBuilder;

import javax.persistence.*;
import java.time.LocalDate;

@Data
@AllArgsConstructor
@NoArgsConstructor
@SuperBuilder
@Entity
public class UserProfile extends BaseEntity{
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private Long authId;
    private String username;
    private String name;
    private String surName;
    private String email;
    private String phone;
    private String address;
    private LocalDate birthDate;
    private String avatar;//yüklenen foto nun adını tutacaz galiba resimleri cloud'ta tutabiliriz
    private String about;
    private Double salary;
    private Integer leave; // todo enumları ekle
    @Enumerated(EnumType.STRING)
    @Builder.Default
    private EStatus status=EStatus.PENDING;
}
