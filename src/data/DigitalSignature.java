package data;

import exceptions.NullSignatureException;

import java.util.Arrays;

public class DigitalSignature {

    private final byte[] signature;

    public DigitalSignature(byte[] s) throws NullSignatureException{

        if (s.length == 0){
            throw new NullSignatureException("Empty signature");
        }

        this.signature = s;
    }

    public byte[] getDigitalSignatureCode() {
        return signature;
    }

    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        DigitalSignature dSignature = (DigitalSignature) o;
        return  Arrays.equals(signature, dSignature.signature);
    }

    public int hashCode() {

        return Arrays.hashCode(signature);
    }

    public String toString() {
        return "DigitalSignature{" + "signature code='" + Arrays.toString(signature) + "'}";
    }

}
