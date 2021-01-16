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

    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        DigitalSignature dSignature = (DigitalSignature) o;
        return signature.equals(dSignature);
    }

    public int hashCode() {
        return signature.hashCode();
    }

    public String toString() {
        return "HealthCardID{" + "personal code='" + Arrays.toString(signature) + '\'' + '}';
    }


}
