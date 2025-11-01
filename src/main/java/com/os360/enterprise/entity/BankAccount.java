package com.os360.enterprise.entity;

import java.time.LocalDate;
import java.util.UUID;

public class BankAccount {
    private UUID id;
    private Party party;


    private Bank bank;


    private String branch;

    private String accountNumber;
    private String iban;
    private String currencyCode;
    private Boolean isDefault ;

    private LocalDate validFrom;
    private LocalDate validTo;
}
