package model;

import javax.persistence.*;
import java.util.Date;

/**
 * Created by SHEEBAN on 27-11-2014.
 */

@Entity
@Table(name = "SALESS")
public class Sales {

    @Id
    @GeneratedValue
    private Long salesId;

    @Column
    private Date salesDate;

    @Column
    private Float salesAmount;

    @ManyToOne
    @JoinColumn(name="PRODUCT_ID")
    private Product product;

    protected Sales() {
    }

    public void setSalesId(Long salesId) {
        this.salesId = salesId;
    }

    public Product getProduct() {
        return product;
    }

    public void setProduct(Product product) {
        this.product = product;
    }

    public Long getSalesId() {
        return salesId;
    }

    public Date getSalesDate() {
        return salesDate;
    }

    public Float getSalesAmount() {
        return salesAmount;
    }

    public Sales(Date salesDate, Float salesAmount) {
        this.salesDate = salesDate;
        this.salesAmount = salesAmount;
    }

    public void setSalesAmount(Float salesAmount) {
        this.salesAmount = salesAmount;
    }

    @Override
    public String toString() {
        return "Sales{" +
                "salesId=" + salesId +
                ", salesDate=" + salesDate +
                ", salesAmount=" + salesAmount +
                '}';
    }
}
