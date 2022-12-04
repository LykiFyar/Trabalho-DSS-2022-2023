package business.carros.classes;

import business.carros.Hibrido;

public class C1H extends C1 implements Hibrido {
    private int motorEletrico;

    public C1H(int minCilindrada, int maxCilindrada, int fiabilidade, int potMotorEletrico){
        super(minCilindrada, maxCilindrada,fiabilidade);
        this.motorEletrico = potMotorEletrico;
    }

    @Override
    public int getPotenciaMotorEletrico() {
        return motorEletrico;
    }

    @Override
    public void setPotenciaMotorEletrico(int motorEletrico) {
        this.motorEletrico = motorEletrico;
    }


    @Override
    public boolean equals(Object o) {
        if (o == this){
            return true;
        }
        if (o.getClass() != C1H.class) {
            return false;
        }

        C1H c1H = (C1H) o;
        return this.getMinCilindrada() == c1H.getMinCilindrada() &&
               this.getMaxCilindrada() == c1H.getMaxCilindrada() && 
               this.getFiabilidade() == c1H.getFiabilidade() && 
               this.motorEletrico == c1H.motorEletrico;
    }

    public C1H clone(){
        return new C1H(super.getMinCilindrada(),super.getMaxCilindrada(),super.getFiabilidade(),this.motorEletrico);
    }

    @Override
    public int hashCode() {
        Integer me = motorEletrico;
        return super.hashCode() + me.hashCode();
    }
    
}
