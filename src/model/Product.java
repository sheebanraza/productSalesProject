package model;

import org.springframework.stereotype.Component;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by SHEEBAN on 27-11-2014.
 */

@Entity
@Table(name = "PRODUCTS")
public class Product {

    @Id
    @GeneratedValue
    @Column(name = "PRODUCT_ID")
    private Long productId;

    @Column
    private String productName;

    @OneToMany(mappedBy = "product")
    private List<Sales> sales = new ArrayList<Sales>();

    public Product(){ // for Hibernate
    }

    public Product(String productName) {
        this.productName = productName;
    }

    public void setProductId(Long productId) {
        this.productId = productId;
    }

    public Long getProductId() {
        return productId;
    }

    public String getProductName() {
        return productName;
    }

    @Override
    public String toString() {
        return "Product{" +
                "productId=" + productId +
                ", productName='" + productName + '\'' +
                ", sales=" + sales +
                '}';
    }
}
