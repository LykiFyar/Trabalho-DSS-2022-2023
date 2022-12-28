package business.carros.classes;

import business.campeonatos.Piloto;
import business.carros.Classe;

import java.util.Random;

public class GT extends Classe {

    private float taxaDeterioracao;
    
    public GT(int minCilindrada, int maxCilindrada, int fiabilidade, float taxaDeterioracao) {
        super(minCilindrada, maxCilindrada,fiabilidade);
        this.taxaDeterioracao = taxaDeterioracao;
    }

    public float getTaxaDeterioracao() {
        return taxaDeterioracao;
    }

    @Override
    public boolean equals(Object o) {
        if (o == this)
            return true;
        if (o.getClass() != GT.class) {
            return false;
        }
        GT gt = (GT) o;
        return gt.getMinCilindrada() == super.getMinCilindrada() &&
               gt.getMaxCilindrada() == super.getMaxCilindrada() &&
               gt.getFiabilidade() == super.getFiabilidade() &&
               gt.taxaDeterioracao == taxaDeterioracao;
    }

    @Override
    public Classe clone() {
        return new GT(super.getMinCilindrada(),super.getMaxCilindrada(),super.getFiabilidade(),taxaDeterioracao);
    }

    @Override
    public int hashCode() {
        Float td = taxaDeterioracao;
        return super.hashCode() + td.hashCode();
    }

    public int getPotenciaMotorEletrico() {
        return 0;
    }

    @Override
    public String toString() {
        return "GT";
    }

    @Override
    public boolean dnf(int cilindrada, int volta, Piloto piloto) {
        Random rand=new Random();
        int x=rand.nextInt(80);
        int fiabilidade = (int)((100000/cilindrada)*2.55);
        int desgaste = (int)((volta)*this.taxaDeterioracao); //taxDeterioracao a cada volta
        return (x > (fiabilidade - desgaste));
    }

    @Override
    public boolean isHibrid() {
        return false;
    }


}
