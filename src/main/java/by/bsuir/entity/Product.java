package by.bsuir.entity;

import java.util.Objects;

public class Product extends BaseEntity {
    private String name;
    private int price;
    private User user;

    public Product(long id, String name, int price, User user) {
        super(id);
        this.name = name;
        this.price = price;
        this.user = user;
    }



    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        if (!super.equals(o)) return false;
        Product product = (Product) o;
        return price == product.price && name.equals(product.name) && user.equals(product.user);
    }

    @Override
    public int hashCode() {
        return Objects.hash(super.hashCode(), name, price, user);
    }

    @Override
    public String toString() {
        return "Product{" +
                "name='" + name + '\'' +
                ", price=" + price +
                ", user=" + user +
                '}' + super.toString();
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getPrice() {
        return price;
    }

    public void setPrice(int price) {
        this.price = price;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }
}
