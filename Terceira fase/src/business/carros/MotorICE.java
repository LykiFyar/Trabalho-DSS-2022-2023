package business.carros;

public class MotorICE {
    private int cilindrada;
    private int potencia;
    private int funcionamentoMotor;

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


    public MotorICE clone(){
        return new MotorICE(cilindrada, potencia, funcionamentoMotor);
    }

}
