package business.campeonatos;

import java.util.Random;

public class Piloto {
    private int id;
    private String nome;
    private float cts; // perto de 0 é melhor em chuva e perto de 1 melhor em tempo seco
    private float sva; // perto de 0 o piloto conduz de forma mais segura e perto de 1 ultrapassa mais facilmente

    private Piloto(){}

    public Piloto(int id, String nome, float cts, float sva){
        this.id = id;
        this.nome = nome;
        this.cts = cts;
        this.sva = sva;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    /**
     * @return String return the nome
     */
    public String getNome() {
        return nome;
    }

    /**
     * @param nome the nome to set
     */
    public void setNome(String nome) {
        this.nome = nome;
    }

    /**
     * @return float return the cts
     */
    public float getCts() {
        return cts;
    }

    /**
     * @param cts the cts to set
     */
    public void setCts(float cts) {
        this.cts = cts;
    }

    /**
     * @return float return the sva
     */
    public float getSva() {
        return sva;
    }

    /**
     * @param sva the sva to set
     */
    public void setSva(float sva) {
        this.sva = sva;
    }

    public Piloto clone(){
        return new Piloto(id, nome, cts, sva);
    }

    public int hashCode(){
        Float c = cts, s = sva;
        return nome.hashCode() + c.hashCode() + s.hashCode(); 
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("Piloto - ").append(this.nome);
        /*
        sb.append("CTS:").append(this.cts).append("\n");
        sb.append("SVA:").append(this.sva).append("\n");
         */
        return sb.toString();
    }

    // função que verifica se um piloto sai da pista ou não
    public boolean dnf(){
        Random rand = new Random();
        int random = rand.nextInt(100);
        return this.sva * (float) random > 65;
        //return true;
    }

}
