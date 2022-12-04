package business.carros.classes;

import business.carros.Classe;

public class C1 extends Classe {
    
    public C1(int minCilindrada, int maxCilindrada, int fiabilidade){
        super(minCilindrada, maxCilindrada,fiabilidade);
    }

    @Override
    public Classe clone() {
        return new C1(super.getMinCilindrada(),super.getMaxCilindrada(),super.getFiabilidade());
    }

    @Override
    public boolean equals(Object o) {
        if (o == this)
            return true;
        if (o.getClass() != C1.class) {
            return false;
        }
        C1 c1 = (C1) o;
        return c1.getMinCilindrada() == super.getMinCilindrada() &&
               c1.getMaxCilindrada() == super.getMaxCilindrada() &&
               c1.getFiabilidade() == super.getFiabilidade();
    }

    @Override
    public int hashCode() {
        return super.hashCode();
    }
    
}
