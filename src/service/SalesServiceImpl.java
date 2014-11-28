package service;

import model.Product;
import model.Sales;
import org.omg.CORBA.*;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.lang.Object;
import java.util.ArrayList;
import java.util.List;

import static com.google.common.collect.Lists.newArrayList;

/**
 * Created by SHEEBAN on 27-11-2014.
 */

@Repository
public class SalesServiceImpl implements SalesService {

    @PersistenceContext
    private EntityManager entityManager;

    @Override
    @Transactional
    public List<Sales> addSalesData(List<Sales> salesPerProduct) {
        List<Sales> updatedCopy = newArrayList();
        for(Sales sales: salesPerProduct) {
            updatedCopy.add(entityManager.merge(sales));
        }
        return updatedCopy;
    }

    @Transactional
    public List<Object[]> getSalesData() {
         List<Object[]> list =  entityManager.createQuery("select p.productName, count (*), SUM(s.salesAmount) " +
                 "from Product p JOIN p.sales s group by p.productName ").getResultList();
        return list;
    }

    @Transactional
    public List<Product> addProductData(List<Product> product){
        List<Product> updatedProductList  = newArrayList();
        for(Product p : product) {
            updatedProductList.add(entityManager.merge(p));
        }
        return updatedProductList;
    }
}
