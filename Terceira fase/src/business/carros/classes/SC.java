package business.carros.classes;

import business.carros.Classe;

public class SC extends Classe {
    
    public SC(int minCilindrada, int maxCilindrada, int fiabilidade){
        super(minCilindrada, maxCilindrada,fiabilidade);
    }

    @Override
    public boolean equals(Object o) {
        if (o == this)
            return true;
        if (o.getClass() != SC.class) {
            return false;
        }
        SC sc = (SC) o;
        return sc.getMinCilindrada() == super.getMinCilindrada() &&
               sc.getMaxCilindrada() == super.getMaxCilindrada() &&
               sc.getFiabilidade() == super.getFiabilidade();
    }

    @Override
    public Classe clone() {
        return new SC(super.getMinCilindrada(),super.getMaxCilindrada(),super.getFiabilidade());
    }
    
    @Override
    public int hashCode() {
        return super.hashCode();
    }

}
