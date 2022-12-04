package business.campeonatos;

import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Circuito {
    private String nome;
    private int numVoltas;
    private boolean clima;
    private int comprimento;
    private List<Setor> setores; // acho que faz sentido ter uma lista porque há uma noção de ordem nos setores.
    private Collection<Jogador> participantes;
    private Map<Jogador,Integer> dnf;
    
    private Circuito(){}

    public Circuito(String nome, int numVoltas, boolean clima, int comprimento){
        this.nome = nome;
        this.numVoltas = numVoltas;
        this.clima = clima;
        this.comprimento = comprimento;
        this.setores = new ArrayList<Setor>();
        this.participantes = new ArrayList<Jogador>();
        this.dnf = new HashMap<Jogador,Integer>();
    }

    // TODO: Completar esta classe

}
