package edu.bionic.domain;

import com.google.common.collect.ImmutableList;
import org.hibernate.validator.constraints.Email;
import org.hibernate.validator.constraints.NotBlank;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

public class Order {
    private Integer id;
    private LocalDateTime dateTime;
    private BigDecimal totalAmount;
    private List<Product> products;

    @NotBlank(message = "Имя не должно быть пустым")
    private String name;

    @NotBlank(message = "Это поле должно быть заполнено")
    @Email(message = "Поле должно содержать правильный email")
    private String email;

    private String phone;

    @NotBlank(message = "Это поле должно быть заполнено")
    private String address;


    public Order() {
        this.products = new ArrayList<>();
    }

    public Order(LocalDateTime dateTime, BigDecimal totalAmount, List<Product> products) {
        this.dateTime = dateTime;
        this.totalAmount = totalAmount;
        this.products = new ArrayList<>(products);
    }

    public Order(Integer id, LocalDateTime dateTime, BigDecimal totalAmount, List<Product> products, String name, String email, String phone, String address) {
        this.id = id;
        this.dateTime = dateTime;
        this.totalAmount = totalAmount.setScale(2, BigDecimal.ROUND_HALF_UP);
        this.products = products;
        this.name = name;
        this.email = email;
        this.phone = phone;
        this.address = address;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Order order = (Order) o;

        if (id != null ? !id.equals(order.id) : order.id != null) return false;
        if (dateTime != null ? !dateTime.equals(order.dateTime) : order.dateTime != null) return false;
        return totalAmount != null ? totalAmount.equals(order.totalAmount) : order.totalAmount == null;
    }

    @Override
    public int hashCode() {
        int result = id != null ? id.hashCode() : 0;
        result = 31 * result + (dateTime != null ? dateTime.hashCode() : 0);
        result = 31 * result + (totalAmount != null ? totalAmount.hashCode() : 0);
        return result;
    }

    @Override
    public String toString() {
        return "Order{" +
                "id=" + id +
                ", dateTime=" + dateTime +
                ", totalAmount=" + totalAmount +
                ", products=" + products +
                ", name='" + name + '\'' +
                ", email='" + email + '\'' +
                ", phone='" + phone + '\'' +
                ", address='" + address + '\'' +
                '}';
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public LocalDateTime getDateTime() {
        return dateTime;
    }

    public void setDateTime(LocalDateTime dateTime) {
        this.dateTime = dateTime;
    }

    public BigDecimal getTotalAmount() {
        return totalAmount;
    }

    public void setTotalAmount(BigDecimal totalAmount) {
        this.totalAmount = totalAmount.setScale(2, BigDecimal.ROUND_HALF_UP);
    }

    public List<Product> getProducts() {
        return ImmutableList.copyOf(products);
    }

    public void setProducts(List<Product> products) {
        this.products = products;
    }

    public void addProduct(Product product) {
        products.add(product);
    }

    public void removeProduct(Product product) {
        products.remove(product);
    }

    public void removeProduct(int index) {
        products.remove(index);
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }
}
