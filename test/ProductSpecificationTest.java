import data.ProductID;
import exceptions.InvalidProductIDException;
import exceptions.InvalidProductSpecifyException;
import exceptions.NullProductIDException;
import medicalconsultation.ProductSpecification;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.math.BigDecimal;

import static org.junit.jupiter.api.Assertions.*;

class ProductSpecificationTest {

    ProductSpecification productSpecification;
    ProductID productID;
    String description;
    BigDecimal value;

    @BeforeEach
    void setUp() throws InvalidProductIDException, NullProductIDException {

        description = "Medicina";
        value = new BigDecimal(10);
        productID = new ProductID("123456789257");


    }

    @Test
    void validProductSpecificationTest() throws NullProductIDException, InvalidProductIDException, InvalidProductSpecifyException {

        getPriceTest();
        getDescriptionTest();
        getUPCCodeTest();

    }

    @Test
    void getUPCCodeTest() throws NullProductIDException, InvalidProductIDException, InvalidProductSpecifyException {

        productSpecification = new ProductSpecification(productID,description,value);
        assertEquals(productID, productSpecification.getUPCCode());
    }

    @Test
    void getDescriptionTest() throws NullProductIDException, InvalidProductIDException, InvalidProductSpecifyException {

        productSpecification = new ProductSpecification(productID,description,value);
        assertEquals(description, productSpecification.getDescription());
    }

    @Test
    void getPriceTest() throws NullProductIDException, InvalidProductIDException, InvalidProductSpecifyException {

        productSpecification = new ProductSpecification(productID,description,value);
        assertEquals(value, productSpecification.getPrice());
    }




}