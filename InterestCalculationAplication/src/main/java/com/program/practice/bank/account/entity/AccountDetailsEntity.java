package com.program.practice.bank.account.entity;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Date;

@Data
@Entity
@Builder
@Table(name = "account")
@NoArgsConstructor
@AllArgsConstructor
@JsonInclude(JsonInclude.Include.NON_NULL)
public class AccountDetailsEntity implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;

    @Column(name = "bsb", nullable = false)
    private String bsb;

    @Column(name = "identification", nullable = false)
    private Long identification;

    @Column(name = "opening_date", nullable = false)
    private Date openingDate;

    @Column(name = "updated_at", nullable = false)
    private Date updated_at;

    @Column(name = "balance", nullable = false)
    double balance;

}
