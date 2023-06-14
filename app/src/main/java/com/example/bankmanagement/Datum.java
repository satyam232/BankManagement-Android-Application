package com.example.bankmanagement;

public class Datum {
    String name;
    String accountno;
    String phoneno;
    String addharno;
    String Balance;

    public Datum(String name, String accountno, String phoneno, String addharno,String Balance) {
        this.name = name;
        this.accountno = accountno;
        this.phoneno = phoneno;
        this.addharno = addharno;
        this.Balance=Balance;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getAccountno() {
        return accountno;
    }

    public void setAccountno(String accountno) {
        this.accountno = accountno;
    }

    public String getPhoneno() {
        return phoneno;
    }

    public void setPhoneno(String phoneno) {
        this.phoneno = phoneno;
    }

    public String getAddharno() {
        return addharno;
    }

    public void setAddharno(String addharno) {
        this.addharno = addharno;
    }

    public String getBalance() {
        return Balance;
    }

    public void setBalance(String balance) {
        this.Balance = balance;
    }
}
