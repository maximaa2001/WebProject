package by.bsuir.entity;

public class Admin extends Account {
    public Admin(long id, String login, String password, String number) {
        super(id, login, password, number);
    }

    public Admin() {
    }

    @Override
    public String toString() {
        return "Admin{}" + super.toString();
    }
}
