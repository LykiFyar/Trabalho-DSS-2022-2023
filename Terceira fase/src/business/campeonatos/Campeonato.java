package business.campeonatos;

import business.carros.Carro;
import business.carros.Classe;
import business.carros.MotorICE;
import business.carros.classes.C1;

import java.util.*;

public class Campeonato {
    private String nome;
    private List<Circuito> circuitos;
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
        Circuito c1 = new Circuito("C1",3,true,10, this.jogadores);
        Circuito c2 = new Circuito("C2",3,true,20, this.jogadores);
        Circuito c3 = new Circuito("C3",3,false,30, this.jogadores);
        List<Circuito> circuitos = new ArrayList<>();
        circuitos.add(c1); circuitos.add(c2); circuitos.add(c3);
        this.circuitos = circuitos;
        this.classificacao = new HashMap<String,Integer>();
        this.classificacaoH = new HashMap<String,Integer>();
    }

    public Campeonato(String name, List<Circuito> circuitos){
        this.nome = name;
        this.circuitos = circuitos; // PODE TER QUE SER ALTERADO
        this.jogadores = new ArrayList<Jogador>(); // PODE TER QUE SER ALTERADO
        this.classificacao = new HashMap<String,Integer>();
        this.classificacaoH = new HashMap<String,Integer>();
    }


    @Override
    public String toString() {
        return this.nome;
    }

    public int numCorridas(){
        return this.circuitos.size();
    }

    public String SimularCampeonato(int corrida){
        Circuito c = this.circuitos.get(corrida);
        return c.SimularCorrida(jogadores);
    }

    public String printCorrida(int nCorrida){
        return this.circuitos.get(nCorrida).toString();
    }

    public int numJogadores(){
        return this.jogadores.size();
    }

    public String printJogador(int nJogador){
        return this.jogadores.get(nJogador).toString();
    }

    public boolean alterarPneu(int nJogador, int pneu){
        return this.jogadores.get(nJogador).alterarPneu(pneu);
    }

    public boolean alterarPac(int nJogador, float pac){
        return this.jogadores.get(nJogador).alterarPac(pac);
    }

    public boolean alterarFuncMotor(int nJogador, int m){
        return this.jogadores.get(nJogador).alterarFuncMotor(m);
    }

    // TODO: Completar esta classe
}
