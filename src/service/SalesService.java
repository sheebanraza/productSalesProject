package service;

import model.Product;
import model.Sales;
import org.springframework.stereotype.Component;

import java.util.List;

/**
 * Created by SHEEBAN on 27-11-2014.
 */

public interface SalesService {
    /**
    *   This method adds the sales data and return persisted list of sales data.
    *   @Param List<Sales>
    *   @return List<Sales>  - updated copy
    */
    public List<Sales> addSalesData(List<Sales> sales);

    /**
     *   This method adds the sales data and return persisted list of product data.
     *   @Param List<Product>
     *   @return List<Product>  - updated copy
     */
    public List<Product> addProductData(List<Product> productList);

    /**
     *   This method gets the total sales data from the database.
     *   @Param
     *   @return List  - result list
     */
    public List<Object[]> getSalesData();
}
