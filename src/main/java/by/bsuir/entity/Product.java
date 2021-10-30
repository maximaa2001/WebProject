package by.bsuir.entity;

import java.util.Objects;

public class Product extends BaseEntity {
    private String name;
    private String description;
    private int date;
    private int run;
    private float volumeEngine;
    private String typeTransmission;
    private String imagePath;
    private int price;
    private User user;

    public Product(long id, String name, String description,int date, int run, float volumeEngine, String typeTransmission, String imagePath, int price) {
        super(id);
        this.name = name;
        this.description = description;
        this.date = date;
        this.run = run;
        this.volumeEngine = volumeEngine;
        this.typeTransmission = typeTransmission;
        this.imagePath = imagePath;
        this.price = price;
    }

    public Product(long id, String name, String imagePath) {
        super(id);
        this.name = name;
        this.imagePath = imagePath;
    }

    public Product() {
    }


    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        if (!super.equals(o)) return false;
        Product product = (Product) o;
        return date == product.date && run == product.run && Float.compare(product.volumeEngine, volumeEngine) == 0 && typeTransmission.equals(product.typeTransmission) && price == product.price && name.equals(product.name) && description.equals(product.description) && imagePath.equals(product.imagePath) && user.equals(product.user);
    }

    @Override
    public int hashCode() {
        return Objects.hash(super.hashCode(), name, description, date, run, volumeEngine, typeTransmission, imagePath, price, user);
    }

    @Override
    public String toString() {
        return "Product{" +
                "name = " + name  +
                ", description = " + description  +
                ", date = " + date  +
                ", run = " + run  +
                ", volumeEngine = " + volumeEngine  +
                ", typeTransmission = " + typeTransmission  +
                ", imagePath = " + imagePath  +
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

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public int getDate() {
        return date;
    }

    public void setDate(int date) {
        this.date = date;
    }

    public int getRun() {
        return run;
    }

    public void setRun(int run) {
        this.run = run;
    }

    public float getVolumeEngine() {
        return volumeEngine;
    }

    public void setVolumeEngine(float volumeEngine) {
        this.volumeEngine = volumeEngine;
    }

    public String getTypeTransmission() {
        return typeTransmission;
    }

    public void setTypeTransmission(String typeTransmission) {
        this.typeTransmission = typeTransmission;
    }

    public String getImagePath() {
        return imagePath;
    }

    public void setImagePath(String imagePath) {
        this.imagePath = imagePath;
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
