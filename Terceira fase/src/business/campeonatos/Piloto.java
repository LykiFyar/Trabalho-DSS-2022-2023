package business.campeonatos;

public class Piloto {
    private String nome;
    private float cts;
    private float sva;

    private Piloto(){}

    public Piloto(String nome, float cts, float sva){
        this.nome = nome;
        this.cts = cts;
        this.sva = sva;
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
        return new Piloto(nome, cts, sva);
    }

    public int hashCode(){
        Float c = cts, s = sva;
        return nome.hashCode() + c.hashCode() + s.hashCode(); 
    }

}
