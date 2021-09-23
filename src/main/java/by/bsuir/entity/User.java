package by.bsuir.entity;

import java.util.Objects;
import java.util.Set;

public class User extends Account {
    private boolean isApproved;
    private Set<Product> products;

    public User(long id, String login, String password, boolean isApproved, Set<Product> products) {
        super(id, login, password);
        this.isApproved = isApproved;
        this.products = products;
    }

    public User() {
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        if (!super.equals(o)) return false;
        User user = (User) o;
        return isApproved == user.isApproved && products.equals(user.products);
    }

    @Override
    public int hashCode() {
        return Objects.hash(super.hashCode(), isApproved, products);
    }

    @Override
    public String toString() {
        return "User{" +
                "isApproved=" + isApproved +
                ", products=" + products +
                '}' + super.toString();
    }

    public boolean isApproved() {
        return isApproved;
    }

    public void setApproved(boolean approved) {
        isApproved = approved;
    }

    public Set<Product> getProducts() {
        return products;
    }

    public void setProducts(Set<Product> products) {
        this.products = products;
    }

}
