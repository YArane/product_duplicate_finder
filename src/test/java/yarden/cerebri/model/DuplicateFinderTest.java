package yarden.cerebri.model;

import org.junit.Assert;
import org.junit.Test;

import java.util.ArrayList;

public class DuplicateFinderTest {

    private static final ArrayList<Product> EMPTY_LIST = new ArrayList<>();

    @Test
    public void testEmptyList() {
        Assert.assertEquals(EMPTY_LIST, DuplicateFinder.findDuplicates(new Product[]{}));
    }

    @Test
    public void testSingleElementList() {
        Product product1 = new Product(1, "a10");
        Product[] products = new Product[]{product1};
        Assert.assertEquals(EMPTY_LIST, DuplicateFinder.findDuplicates(products));
    }

    @Test
    public void testDoubleElementListUnique() {
        Product product1 = new Product(1, "a10");
        Product product2 = new Product(2, "a11");
        Product[] products = new Product[]{product1, product2};
        Assert.assertEquals(EMPTY_LIST, DuplicateFinder.findDuplicates(products));
    }

    @Test
    public void testDoubleElementListDuplicate() {
        Product product1 = new Product(1, "a10");
        Product product2 = new Product(2, "a10");
        Product[] products = new Product[]{product1, product2};
        ArrayList<Product> productsList = new ArrayList<>();
        productsList.add(product1);
        productsList.add(product2);
        Assert.assertEquals(productsList, DuplicateFinder.findDuplicates(products));
    }

    @Test
    public void testTripleElementListDuplicate() {
        Product product1 = new Product(1, "a10");
        Product product2 = new Product(2, "a10");
        Product product3 = new Product(3, "a10");
        Product[] products = new Product[]{product1, product2, product3};
        ArrayList<Product> productsList = new ArrayList<>();
        productsList.add(product1);
        productsList.add(product2);
        productsList.add(product3);
        Assert.assertEquals(productsList, DuplicateFinder.findDuplicates(products));
    }

    @Test
    public void testMultipleDistinctDuplicates() {
        Product product1 = new Product(1, "a10");
        Product product2 = new Product(2, "a11");
        Product product3 = new Product(3, "a12");
        Product product4 = new Product(4, "a10");
        Product product5 = new Product(5, "a11");
        Product product6 = new Product(6, "a13");
        Product[] products = new Product[]{product1, product2, product3, product4, product5, product6};
        ArrayList<Product> productsList = new ArrayList<>();
        productsList.add(product2);
        productsList.add(product5);
        productsList.add(product1);
        productsList.add(product4);
        Assert.assertEquals(productsList, DuplicateFinder.findDuplicates(products));
    }


}
