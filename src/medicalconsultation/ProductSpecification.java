package medicalconsultation;

import data.ProductID;
import exceptions.InvalidProductSpecifyException;
import exceptions.NullProductIDException;
import exceptions.InvalidProductIDException;

import java.math.BigDecimal;

public class ProductSpecification {

    private ProductID UPCCode;
    private String description;
    private BigDecimal price;

    public ProductSpecification(ProductID UPCCode, String description, BigDecimal price) throws InvalidProductSpecifyException, NullProductIDException, InvalidProductIDException{

        UPCCode.errorIDControl(UPCCode.getUPC());
        if(description.length() == 0){
            throw new InvalidProductSpecifyException("Empty description");
        }
        if(price.equals(new BigDecimal(0))){
            throw new InvalidProductSpecifyException("No price");
        }

        this.UPCCode = UPCCode;
        this.description = description;
        this.price = price;
    }

    public ProductID getUPCCode(){
        return this.UPCCode;
    }

    public String getDescription(){
        return this.description;
    }

    public BigDecimal getPrice(){
        return this.price;
    }
}

