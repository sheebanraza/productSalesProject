package service;

import model.Product;
import model.Sales;
import org.springframework.stereotype.Component;

import java.util.List;

/**
 * Created by SHEEBAN on 27-11-2014.
 */

public interface SalesService {

    public List<Sales> addSalesData(List<Sales> sales);

    public List<Product> addProductData(List<Product> productList);

    public List<Object[]> getSalesData();
}
