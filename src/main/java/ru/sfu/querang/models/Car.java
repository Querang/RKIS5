package ru.sfu.querang.models;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.Size;

/**
 * Класс автомобиля
 */
@Entity
@Table(name = "car_table")
public class Car {

    /**
     * id Автомобиля
     */
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private int id;

    /**
     * Название автомобиля
     */
    @Column(name = "name")
    @Size(min = 2, max = 25, message = "len from 2 to 25")
    private String name;

    /**
     * Производитель автомобиля
     */
    @Column(name = "brand")
    @Size(min = 2, max = 25, message = "len from 2 to 25")
    private String brand;

    /**
     * Страна производитель автомобиля
     */
    @Column(name = "country")
    @Size(min = 2, max = 25, message = "len from 2 to 25")
    private String country;


    /**
     * Цена автомобиля
     */
    @Column(name = "price")
    @Min(value = 0, message = "so small")
    private Float price;

    /**
     * Лошадиные силы автомобиля
     */
    @Column(name = "horsepower")
    @Min(value = 0, message = "so small")
    private int horsepower;


    public Car() {
    }


    public Car(String name, String brand, String country, Float price, int horsepower) {
        this.name = name;
        this.brand = brand;
        this.country = country;
        this.price = price;
        this.horsepower = horsepower;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getBrand() {
        return brand;
    }

    public void setBrand(String brand) {
        this.brand = brand;
    }

    public String getCountry() {
        return country;
    }

    public void setCountry(String country) {
        this.country = country;
    }

    public Float getPrice() {
        return price;
    }

    public void setPrice(Float price) {
        this.price = price;
    }

    public int getHorsepower() {
        return horsepower;
    }

    public void setHorsepower(int horsepower) {
        this.horsepower = horsepower;
    }

    @Override
    public String toString() {
        return "---Автомобиль id: " + id + "---\n" +
                "Название автомобиля: " + name + '\n' +
                "Производитель автомобиля: " + brand + '\n' +
                "Страна производителя автомобиля: " + country + '\n' +
                "Цена автомобиля: " + price + " Руб.\n" +
                "Лошадиные силы автомобиля: " + horsepower + "\n";
    }
}