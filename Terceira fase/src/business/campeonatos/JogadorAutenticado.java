package business.campeonatos;

import business.carros.Carro;
import business.utilizadores.Utilizador;
import data.UtilizadoresDAO;

public class JogadorAutenticado extends Jogador {
    private Utilizador player; // !Agregação!

    public JogadorAutenticado(String n,Piloto p,Carro c,Utilizador u){
        super(n,p,c);
        this.player = u;
    }

    public void addPontuacaoInPlayer(int pontuacao){
        int newPont = pontuacao + player.getPontuacaoGeral();
        player.setPontuacaoGeral(newPont);
        UtilizadoresDAO.getInstance().put(player.getUsername(), player);
    }
}
