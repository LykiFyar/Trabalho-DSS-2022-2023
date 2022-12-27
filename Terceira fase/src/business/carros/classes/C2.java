package business.carros.classes;

import business.campeonatos.Piloto;
import business.carros.Classe;

import java.util.Random;

public class C2 extends Classe {
    
    public C2(int minCilindrada, int maxCilindrada, int fiabilidade){
        super(minCilindrada, maxCilindrada,fiabilidade);
    }

    @Override
    public boolean equals(Object o) {
        if (o == this)
            return true;
        if (o.getClass() != C2.class) {
            return false;
        }
        C2 c2 = (C2) o;
        return c2.getMinCilindrada() == super.getMinCilindrada() &&
               c2.getMaxCilindrada() == super.getMaxCilindrada() &&
               c2.getFiabilidade() == super.getFiabilidade();
    }

    @Override
    public Classe clone() {
        return new C2(super.getMinCilindrada(),super.getMaxCilindrada(),super.getFiabilidade());
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
        return "C2";
    }

    @Override
    public boolean dnf(int cilindrada, int volta, Piloto piloto) {
        Random rand=new Random();
        int x=rand.nextInt(90);
        return (x > super.getFiabilidade() + (cilindrada/1200));
        //return false;
    }
}
