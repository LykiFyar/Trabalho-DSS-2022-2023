package business.carros.classes;

import business.campeonatos.Piloto;
import business.carros.Hibrido;

import java.util.Random;

public class GTH extends GT implements Hibrido {
    private int motorEletrico;

    public GTH(int minCilindrada, int maxCilindrada, int fiabilidade, float taxaDeterioracao, int potMotorEletrico){
        super(minCilindrada, maxCilindrada,fiabilidade,taxaDeterioracao);
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
        if (o.getClass() != GTH.class) {
            return false;
        }

        GTH gth = (GTH) o;
        return this.getMinCilindrada() == gth.getMinCilindrada() &&
               this.getMaxCilindrada() == gth.getMaxCilindrada() && 
               this.getFiabilidade() == gth.getFiabilidade() && 
               this.getTaxaDeterioracao() == gth.getTaxaDeterioracao() &&
               this.motorEletrico == gth.motorEletrico;
    }
    
    @Override
    public int hashCode() {
        Integer me = motorEletrico;
        return super.hashCode() + me.hashCode();
    }

    public GTH clone(){
        return new GTH(super.getMinCilindrada(),super.getMaxCilindrada(),super.getFiabilidade(),super.getTaxaDeterioracao(),this.motorEletrico);
    }

    @Override
    public String toString() {
        return "GTH";
    }

    @Override
    public boolean dnf(int cilindrada, int volta, Piloto piloto) {
        Random rand=new Random();
        int x=rand.nextInt(80);
        int fiabilidade = (int)((100000/cilindrada)*2.55);
        int desgaste = (int)((volta)*this.getTaxaDeterioracao()); //taxDeterioracao a cada volta
        int motorH = this.getPotenciaMotorEletrico()/20;
        return (x > (fiabilidade - desgaste - motorH));
    }
}
