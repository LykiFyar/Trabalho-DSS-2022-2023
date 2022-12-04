package business.campeonatos;

import business.carros.Carro;
import business.utilizadores.Utilizador;

public class JogadorAutenticado extends Jogador {
    private Utilizador player; // !Agregação!

    public JogadorAutenticado(Piloto p,Carro c,Utilizador u){
        super(p,c);
        this.player = u;
    }

    public void addPontuacaoInPlayer(){
        int newPont = super.getPontuacao() + player.getPontuacaoGeral();
        player.setPontuacaoGeral(newPont);
    }
}
