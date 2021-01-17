package medicalconsultation;

public class TakingGuideline { // Represents the taking guidelines of a medicine
    private dayMoment dMoment;
    private float duration;
    private String instructions;
    private Posology posology;

    public TakingGuideline(dayMoment dM, float du, String i, float d, float f, FqUnit u){ // Initializes attributes
        this.dMoment=dM;
        this.duration=du;
        this.instructions=i;
        this.posology=new Posology(d,f,u);
    }

    // the getters and setters
    public void setdMoment(dayMoment dM){
        this.dMoment = dM;
    }

    public dayMoment getdMoment() {
        return dMoment;
    }

    public float getDuration() {
        return duration;
    }

    public void setDuration(float duration) {
        this.duration = duration;
    }

    public String getInstructions() {
        return instructions;
    }

    public void setInstructions(String instructions) {
        this.instructions = instructions;
    }

    public Posology getPosology() {
        return posology;
    }

    public void setPosology(Posology posology) {
        this.posology = posology;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        TakingGuideline tg = (TakingGuideline) o;
        return this.dMoment == tg.dMoment && this.duration == tg.duration && this.instructions.equals(tg.instructions) && this.posology.equals(tg.posology);
    }

}
