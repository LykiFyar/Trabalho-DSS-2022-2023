package business.campeonatos;

import business.carros.Carro;
import business.carros.Classe;
import business.carros.MotorICE;
import business.carros.classes.C1;

import java.util.*;

public class Campeonato {
    private String nome;
    private Collection<Circuito> circuitos;
    private List<Jogador> jogadores;
    private Map<String,Integer> classificacao; // classificação para jogadores de carros normais.
    private Map<String,Integer> classificacaoH; // classificação para jogadores de carros híbridos.

    public Campeonato(){
        this.nome = "Campeonato";
        Jogador j1 = new Jogador(new Piloto("Ham", 0.5F, 0.5F),new Carro("Mercedes","AMG",0,0.5F,new MotorICE(6000,1000,1),new C1()));
        Jogador j2 = new Jogador(new Piloto("Max", 0.5F, 0.5F),new Carro("Red Bull","RB-18",0,0.5F,new MotorICE(6000,1000,1),new C1()));
        List<Jogador> jogadores= new ArrayList<>();
        jogadores.add(j1); jogadores.add(j2);
        this.jogadores = jogadores;
        Circuito c1 = new Circuito("c1",3,true,10, this.jogadores);
        Circuito c2 = new Circuito("c2",3,true,20, this.jogadores);
        Circuito c3 = new Circuito("c3",3,false,30, this.jogadores);
        Collection<Circuito> circuitos = new ArrayList<>();
        circuitos.add(c1); circuitos.add(c2); circuitos.add(c3);
        this.circuitos = circuitos;
        this.classificacao = new HashMap<String,Integer>();
        this.classificacaoH = new HashMap<String,Integer>();
    }

    public Campeonato(String name, Collection<Circuito> circuitos){
        this.nome = name;
        this.circuitos = circuitos; // PODE TER QUE SER ALTERADO
        this.jogadores = new ArrayList<Jogador>(); // PODE TER QUE SER ALTERADO
        this.classificacao = new HashMap<String,Integer>(); 
        this.classificacaoH = new HashMap<String,Integer>();
    }

    public void SimularCampeonato(){
        int i = 1;
        for(Circuito c: circuitos){
            System.out.println("||||| Corrida: " + c.getNome() + " |||||");
            c.SimularCorrida(jogadores);
            i++;

        }
    }

    // TODO: Completar esta classe
}
