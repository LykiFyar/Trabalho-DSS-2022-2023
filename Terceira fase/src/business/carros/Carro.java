package business.carros;

import business.campeonatos.Circuito;
import business.campeonatos.Piloto;

import java.util.Map;

public class Carro {
    private int id;
    private String marca;
    private String modelo;
    private int pneu; // 0-Macio, 1-duro, 2-chuva
    private float pac; // float entre 0 e 1 (perto de 0 é melhor nas retas e 1 é melhor nas curvas)
    private MotorICE motorICE;
    private Classe classe;

    private Carro(){}

    public Carro(String marca, String modelo, int pneu, float pac, MotorICE motorICE, Classe classe){
        this.id = -1; 
        this.marca = marca;
        this.modelo = modelo;
        this.pneu = pneu;
        this.pac = pac;
        this.motorICE = motorICE.clone();
        this.classe = classe.clone();
    }

    public Carro(int id, String marca, String modelo, int pneu, float pac, MotorICE motorICE, Classe classe){
        this.id = id; 
        this.marca = marca;
        this.modelo = modelo;
        this.pneu = pneu;
        this.pac = pac;
        this.motorICE = motorICE.clone();
        this.classe = classe.clone();
    }

    /**
     * @return String return the ID
     */

    public int getId() {
        return id;
    }

    /**
     * @param id the id to set
     */
    public void setId(int id) {
        this.id = id;
    }

    /**
     * @return String return the marca
     */
    public String getMarca() {
        return marca;
    }

    /**
     * @param marca the marca to set
     */
    public void setMarca(String marca) {
        this.marca = marca;
    }

    /**
     * @return String return the modelo
     */
    public String getModelo() {
        return modelo;
    }

    /**
     * @param modelo the modelo to set
     */
    public void setModelo(String modelo) {
        this.modelo = modelo;
    }

    /**
     * @return int return the pneu
     */
    public int getPneu() {
        return pneu;
    }

    /**
     * @param pneu the pneu to set
     */
    public void setPneu(int pneu) {
        this.pneu = pneu;
    }

    /**
     * @return float return the pac
     */
    public float getPac() {
        return pac;
    }

    /**
     * @param pac the pac to set
     */
    public void setPac(float pac) {
        this.pac = pac;
    }

    /**
     * @return MotorICE return the motorICE
     */
    public MotorICE getMotorICE() {
        return motorICE.clone();
    }

    /**
     * @param motorICE the motorICE to set
     */
    public void setMotorICE(MotorICE motorICE) {
        this.motorICE = motorICE.clone();
    }

    /**
     * @return Classe return the classe
     */
    public Classe getClasse() {
        return classe.clone();
    }

    /**
     * @param classe the classe to set
     */
    public void setClasse(Classe classe) {
        this.classe = classe.clone();
    }

    public void setFuncMotor(int m) {this.motorICE.setFuncionamentoMotor(m);}

    @Override
    public boolean equals(Object o) {
        if (o == this)
            return true;
        if (!(o instanceof Carro)) {
            return false;
        }
        Carro c = (Carro) o;
        return marca == c.marca &&
               modelo == c.modelo &&
               pneu == c.pneu &&
               pac == c.pac &&
               motorICE.equals(c.motorICE) &&
               classe.equals(c.classe);
    }

    @Override
    public int hashCode() {
        Integer p = pneu;
        Float pac_ = pac;
        return marca.hashCode() + modelo.hashCode() + p.hashCode() + pac_.hashCode() + motorICE.hashCode() + classe.hashCode();
    } 

    public Carro clone(){
        return new Carro(marca, modelo, pneu, pac, motorICE, classe);
    }

    private String getPneuS(){
        if (this.pneu == 0){
            return "Macio";
        } else if (this.pneu == 1) {
            return "Duro";
        }
        else return "Chuva";
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append(this.marca).append("-").append(this.modelo);
        sb.append("\n       Pneu:").append(this.getPneuS());
        sb.append("\n       Pac:").append(this.pac);
        sb.append("\n       MotorICE:").append(this.motorICE.toString());
        sb.append("\n       Classe:").append(this.classe.toString());
        return sb.toString();
    }

    public boolean dnf(int volta, Piloto piloto){
        return this.classe.dnf(this.getMotorICE().getCilindrada(), volta, piloto);
    }

    public boolean isHibrid(){
        return this.classe.isHibrid();
    }
}
