package business.carros.classes;

import business.carros.Classe;

import java.util.Random;

public class C1 extends Classe {

    public C1(){
        super(6000,6000,95);
    }
    
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

    public int getPotenciaMotorEletrico() {
        return 0;
    }

    /**
     * Os carros de classe C1 têm uma avaria se tiverem 95 ou menos de fiabilidade.
     * Random entre 0-100.
     * @return Boolean
     */
    public boolean DNF() {
        Random rand=new Random();
        int x=rand.nextInt(100);
        return (x > super.getFiabilidade());
        //return false;
    }

}
