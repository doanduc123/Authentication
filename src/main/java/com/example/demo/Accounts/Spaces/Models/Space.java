package com.example.demo.Accounts.Spaces.Models;

import com.example.demo.Accounts.Abstracts.AbstractAccount;
import jakarta.persistence.Entity;
import jakarta.persistence.Table;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Entity
@Table(name = "abstract_account")
public class Space extends AbstractAccount {
    public Space() {
        this.setUserType("SPACE");
    }
}