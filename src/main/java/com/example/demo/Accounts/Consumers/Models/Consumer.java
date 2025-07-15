package com.example.demo.Accounts.Consumers.Models;

import com.example.demo.Accounts.Abstracts.AbstractAccount;
import jakarta.persistence.Entity;
import jakarta.persistence.Table;
import lombok.Getter;
import lombok.Setter;


@Getter
@Setter
@Entity
@Table(name = "account_consumers")
public class Consumer extends AbstractAccount {
    public Consumer() {
        this.setUserType("consumer");
    }
}
