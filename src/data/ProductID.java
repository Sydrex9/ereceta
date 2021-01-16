package data;

import exceptions.NullProductIDException;
import exceptions.InvalidProductIDException;

public class ProductID {

    private final String UPC;

    public ProductID(String code) throws NullProductIDException, InvalidProductIDException {

        errorIDControl(code);

        this.UPC = code;
    }

    public void errorIDControl(String code) throws NullProductIDException, InvalidProductIDException {
        if (code.equals(""))
            throw new NullProductIDException("Empty code");

        if (!code.matches("[0-9]+") || code.length() != 12)
            throw new InvalidProductIDException("Invalid code");
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        ProductID pID = (ProductID) o;
        return UPC.equals(pID);
    }

    public int hashCode() {
        return UPC.hashCode();
    }

    public String toString() {
        return "ProductID{" + "product code='" + UPC + '\'' + '}';
    }

    public String getUPC() {
        return this.UPC;
    }
}