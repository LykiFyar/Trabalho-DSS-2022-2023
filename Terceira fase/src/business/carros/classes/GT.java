package business.carros.classes;

import business.carros.Classe;

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
}
