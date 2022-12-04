package business.campeonatos;

import java.util.Collection;
import java.util.Map;

public class Campeonato {
    private String nome;
    private Collection<Circuito> circuitos;
    private Collection<Jogador> jogadores; 
    private Map<String,Integer> classificacao; // classificação para jogadores de carros normais.
    private Map<String,Integer> classificacaoH; // classificação para jogadores de carros híbridos.

    // TODO: Completar esta classe
}
