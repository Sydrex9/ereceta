package medicalconsultation;

import data.ProductID;

public class MedicalPrescriptionLine {
    private ProductID product;
    private TakingGuideline instructions;

    public MedicalPrescriptionLine(ProductID product,TakingGuideline line){
        this.product = product;
        this.instructions = line;
    }

    public TakingGuideline getInstructions() {
        return instructions;
    }

    public void setInstructions(TakingGuideline instructions) {
        this.instructions = instructions;
    }

    public ProductID getProduct() {
        return product;
    }

    public void setProduct(ProductID product) {
        this.product = product;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        MedicalPrescriptionLine mpl = (MedicalPrescriptionLine) o;
        return this.product.equals(mpl.product) && this.instructions.equals(mpl.instructions);
    }

}
