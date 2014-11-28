package test;

import model.Product;
import model.Sales;
import org.junit.Before;
import org.junit.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.test.util.ReflectionTestUtils;
import service.SalesService;
import service.SalesServiceImpl;

import javax.persistence.EntityManager;
import javax.persistence.Query;
import java.util.Date;
import java.util.List;

import static com.google.common.collect.Lists.newArrayList;
import static java.lang.Float.*;
import static org.junit.Assert.*;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

/**
 * Created by SHEEBAN on 27-11-2014.
 */

public class SalesServiceTest {

    @Mock
    private EntityManager entityManager;
    @InjectMocks
    private SalesService salesService;

    private final Long ID = 1l;

    @Before
    public void init() {
        salesService = new SalesServiceImpl();
        MockitoAnnotations.initMocks(this);
        ReflectionTestUtils.setField(salesService,"entityManager",entityManager);
    }

    @Test
    public void testNothing(){
    }

    @Test
    public void testWithProducts(){
        Product product = new Product("testProd");
        product.setProductId(ID);       //setter is exposed bcoz entityManager is Mocked and wont be able to produce ids
        when(entityManager.merge(product)).thenReturn(product);

        List<Product> updatedProducts = salesService.addProductData(newArrayList(product));
        assertNotNull(updatedProducts.get(0).getProductId());
        assertEquals(ID,updatedProducts.get(0).getProductId());
        assertEquals(updatedProducts.get(0).getProductName(),"testProd");
    }

    @Test
    public void testAddSales(){
        Sales salesForProd1 = new Sales(new Date(),16f);
        Product product = new Product("Prod1");
        product.setProductId(ID);
        when(entityManager.merge(salesForProd1)).thenReturn(salesForProd1);
        salesForProd1.setSalesId(ID);
        salesForProd1.setProduct(product);

        List<Sales> updatedSalesList = salesService.addSalesData(newArrayList(salesForProd1));
        assertNotNull(String.valueOf(ID), updatedSalesList.get(0).getSalesId());
        assertEquals(valueOf(16f),salesForProd1.getSalesAmount());
    }

    @Test
    public void testGetSalesDataQuery(){
        final Query mockedQuery = mock(Query.class);
        Product product = new Product("Prod1");
        product.setProductId(ID);
        Sales sales = new Sales(new Date(),115F);
        sales.setSalesId(ID);
        sales.setProduct(product);

        Object[] objects = new Object[3];
        objects[0] = "Prod1";
        objects[1] = "1";
        objects[2] = "115";
        when(entityManager.createQuery("select p.productName, count (*), SUM(s.salesAmount) " +
                "from Product p JOIN p.sales s group by p.productName ")).thenReturn(mockedQuery);
        assertNotNull(mockedQuery);
        assertEquals(objects.length,3);
    }
}



