package business.carros.classes;

import business.campeonatos.Piloto;
import business.carros.Classe;

import java.util.Random;

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

    public int getPotenciaMotorEletrico() {
        return 0;
    }

    @Override
    public String toString() {
        return "SC";
    }

    @Override
    public boolean dnf(int cilindrada, int volta, Piloto piloto) {
        Random rand=new Random();
        int x=rand.nextInt(65);
        float sva = -(piloto.getSva() * 10);
        int fiabilidade = (int)(sva*0.75) + (int)((cilindrada/10)*0.25);
        //System.out.println("Fiabilidade: "+fiabilidade);
        //System.out.println("Random: "+x);
        return (x > fiabilidade);
    }

    @Override
    public boolean isHibrid() {
        return false;
    }
}
