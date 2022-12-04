package business.campeonatos;

import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.Map;

public class Campeonato {
    private String nome;
    private Collection<Circuito> circuitos;
    private Collection<Jogador> jogadores; 
    private Map<String,Integer> classificacao; // classificação para jogadores de carros normais.
    private Map<String,Integer> classificacaoH; // classificação para jogadores de carros híbridos.

    private Campeonato(){}

    public Campeonato(String name, Collection<Circuito> circuitos){
        this.nome = name;
        this.circuitos = circuitos; // PODE TER QUE SER ALTERADO
        this.jogadores = new ArrayList<Jogador>(); // PODE TER QUE SER ALTERADO
        this.classificacao = new HashMap<String,Integer>(); 
        this.classificacaoH = new HashMap<String,Integer>();
    }

    // TODO: Completar esta classe
}
