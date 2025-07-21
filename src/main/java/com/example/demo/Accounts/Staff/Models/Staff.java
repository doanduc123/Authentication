package com.example.demo.Accounts.Staff.Models;

import com.example.demo.Accounts.Abstracts.AbstractAccount;
import jakarta.persistence.Entity;
import jakarta.persistence.Table;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Entity
@Table(name = "abstract_account")
public class Staff extends AbstractAccount {
    public Staff() {
        this.setUserType("STAFF");
    }
}
