package business.carros;

import business.campeonatos.Piloto;

public abstract class Classe {
    private int minCilindrada;
    private int maxCilindrada;
    private int fiabilidade;

    private Classe(){/* Não é conveniente disponibilizar construtores vazios */}

    public Classe(int minCilindrada, int maxCilindrada, int fiabilidade){
        this.minCilindrada = minCilindrada;
        this.maxCilindrada = maxCilindrada;
        this.fiabilidade = fiabilidade;
    }

    /**
     * @return int return the minCilindrada
     */
    public int getMinCilindrada() {
        return minCilindrada;
    }

    /**
     * @param minCilindrada the minCilindrada to set
     */
    public void setMinCilindrada(int minCilindrada) {
        this.minCilindrada = minCilindrada;
    }

    /**
     * @return int return the maxCilindrada
     */
    public int getMaxCilindrada() {
        return maxCilindrada;
    }

    /**
     * @param maxCilindrada the maxCilindrada to set
     */
    public void setMaxCilindrada(int maxCilindrada) {
        this.maxCilindrada = maxCilindrada;
    }

    /**
     * @return int return the fiabilidade
     */
    public int getFiabilidade() {
        return fiabilidade;
    }

    /**
     * @param fiabilidade the fiabilidade to set
     */
    public void setFiabilidade(int fiabilidade) {
        this.fiabilidade = fiabilidade;
    }

    public abstract int getPotenciaMotorEletrico();

    @Override
    public int hashCode() {
        Integer minC = minCilindrada, maxC = maxCilindrada, fia = fiabilidade;
        return minC.hashCode() + maxC.hashCode() + fia.hashCode();
    }


    @Override
    public abstract boolean equals(Object o);

    @Override
    public abstract Classe clone();

    public abstract boolean dnf(int cilindrada, int volta, Piloto piloto);

    public abstract boolean isHibrid();

}
