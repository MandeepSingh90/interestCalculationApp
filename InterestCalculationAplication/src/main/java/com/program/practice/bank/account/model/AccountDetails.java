package com.program.practice.bank.account.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;
import java.util.Date;
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@JsonInclude(JsonInclude.Include.NON_NULL)
public class AccountDetails implements Serializable {
    private String bsb;
    private Long identification;
    private Date openingDate;
    private  Date updatedAt;
    private Double balance;
    private Double monthlyInterest;
    private String  message;
}
