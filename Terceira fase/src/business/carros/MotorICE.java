package business.carros;

public class MotorICE {
    private int cilindrada; // este valor parece influenciar apenas a fiabilidade do motor e não a sua performance
    private int potencia; // este valor influencia diretamente a performance do carro
    private int funcionamentoMotor; // 0-conservador, 1-normal, 2-agressivo. influencia DNFs e velocidade

    private MotorICE(){}

    public MotorICE(int cilindrada, int potencia, int func){
        this.cilindrada = cilindrada;
        this.potencia = potencia;
        this.funcionamentoMotor = func;
    }

    /**
     * @return int return the cilindrada
     */
    public int getCilindrada() {
        return cilindrada;
    }

    /**
     * @param cilindrada the cilindrada to set
     */
    public void setCilindrada(int cilindrada) {
        this.cilindrada = cilindrada;
    }

    /**
     * @return int return the potencia
     */
    public int getPotencia() {
        return potencia;
    }

    /**
     * @param potencia the potencia to set
     */
    public void setPotencia(int potencia) {
        this.potencia = potencia;
    }

    /**
     * @return int return the funcionamentoMotor
     */
    public int getFuncionamentoMotor() {
        return funcionamentoMotor;
    }

    /**
     * @param funcionamentoMotor the funcionamentoMotor to set
     */
    public void setFuncionamentoMotor(int funcionamentoMotor) {
        this.funcionamentoMotor = funcionamentoMotor;
    }


    @Override
    public int hashCode() {
        Integer cil = cilindrada, pot = potencia, func = funcionamentoMotor;
        return cil.hashCode() + pot.hashCode() + func.hashCode();
    }


    @Override
    public boolean equals(Object o) {
        if (o == this){
            return true;
        }
        if (o.getClass() != MotorICE.class) {
            return false;
        }

        MotorICE m = (MotorICE) o;
        return cilindrada == m.cilindrada && 
               potencia == m.potencia && 
               funcionamentoMotor == m.funcionamentoMotor;
    }

    private String getFuncionamento(){
        if (this.funcionamentoMotor == 0){
            return "Conservador";
        } else if (this.funcionamentoMotor == 1) {
            return "Normal";
        }
        else return "Agressivo";
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append(" Cilindrada:").append(this.cilindrada);
        sb.append(" | Potência:").append(this.potencia);
        sb.append(" | Funcionamento:").append(this.getFuncionamento());
        return sb.toString();
    }

    public MotorICE clone(){
        return new MotorICE(cilindrada, potencia, funcionamentoMotor);
    }

}
