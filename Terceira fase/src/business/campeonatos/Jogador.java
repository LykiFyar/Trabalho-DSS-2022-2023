package business.campeonatos;

import business.carros.Carro;

public class Jogador {
    private int pontuacao;
    private Piloto piloto; // !Composição!
    private Carro carro; // !Composição!

    public Jogador(Piloto p,Carro c){
        this.pontuacao = 0;
        this.carro = carro.clone();
        this.piloto = piloto.clone();
    } 

    /**
     * @return int return the pontuacao
     */
    public int getPontuacao() {
        return pontuacao;
    }

    /**
     * @param pontuacao the pontuacao to set
     */
    public void setPontuacao(int pontuacao) {
        this.pontuacao = pontuacao;
    }

    public int hashCode() {
        Integer p = pontuacao;
        return p.hashCode() + piloto.hashCode() + carro.hashCode();
    }

}
