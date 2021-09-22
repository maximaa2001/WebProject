package by.bsuir.entity;

public class Admin extends Account{
    public Admin(long id, String login, String password) {
        super(id, login, password);
    }

    @Override
    public String toString() {
        return "Admin{}" + super.toString();
    }
}
