package by.bsuir.entity;

import java.util.Objects;

public class Account extends BaseEntity {
    private String login;
    private String password;
    private String number;

    public Account(long id, String login, String password, String number) {
        super(id);
        this.login = login;
        this.password = password;
        this.number = number;
    }


    public Account() {
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        if (!super.equals(o)) return false;
        Account account = (Account) o;
        return login.equals(account.login) && password.equals(account.password) && number.equals(account.number);
    }

    @Override
    public int hashCode() {
        return Objects.hash(super.hashCode(), login, password, number);
    }

    @Override
    public String toString() {
        return "Account{" +
                "login =" + login + '\'' +
                ", password = " + password + '\'' +
                "number = " + number +
                '}' + super.toString();
    }

    public String getLogin() {
        return login;
    }

    public void setLogin(String login) {
        this.login = login;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getNumber() {
        return number;
    }

    public void setNumber(String number) {
        this.number = number;
    }
}
