
import model.Product;
import model.Sales;
import org.springframework.cglib.beans.ImmutableBean;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import service.SalesService;

import javax.sound.midi.SysexMessage;
import java.util.*;

import static com.google.common.collect.Lists.newArrayList;
import static java.lang.Math.*;

/**
 * Created by SHEEBAN on 27-11-2014.
 */
public class MainClass {

    public static void main(String args[]) {
        ApplicationContext applicationContext = new ClassPathXmlApplicationContext("spring-config.xml");

        SalesService salesService = applicationContext.getBean(SalesService.class);

        List<Product> productList = newArrayList();
        for(int i=0;i<10;i++){
            productList.add(new Product("Product"+ i));     //add 10 products
        }
        List<Product> persistedProducts = salesService.addProductData(productList);

        Sales sales1 = getSales(new Date(), 100.0f);
        Sales sales2 = getSales(new Date(-21,2,21),150.3f);
        Sales sales3 = getSales(new Date(-20, 2, 21), 300.1f);
        Sales sales4 = getSales(new Date(-18, 2, 6), 400.4f);
        Sales sales5 = getSales(new Date(-20, 5, 4), 500.6f);
        Sales sales6 = getSales(new Date(-19, 6, 12), 600.7f);
        Sales sales7 = getSales(new Date(-19, 6, 1), 600.7f);
        Sales sales8 = getSales(new Date(-19, 5, 2), 600.7f);
        Sales sales9 = getSales(new Date(-19, 7, 22), 600.7f);
        Sales sales10 = getSales(new Date(-19, 8, 21), 600.7f);

        List<Sales> listOfSales = newArrayList(sales1,sales2,sales3,sales4,sales5,sales6,sales7,sales8,sales9,sales10);
        for(Product product: persistedProducts) {
            for (Sales sales : listOfSales) {           // add sales for every product
                sales.setSalesAmount((float) (sales.getSalesAmount()*Math.random())); //for getting randon Sales amount
                sales.setProduct(product);
            }
            salesService.addSalesData(listOfSales);
        }

        List<Object[]> products = salesService.getSalesData();
        System.out.println("Product\tTotal#Sold\tTotal Amount");
        for(Object[] objects: products){
            System.out.println("---------------------------------------------");
            System.out.println(objects[0]+"\t"+objects[1]+"\t\t "+objects[2]);
        }

    }

    private static Sales getSales( Date date,float price) {
        return new Sales(date,price);
    }
}
