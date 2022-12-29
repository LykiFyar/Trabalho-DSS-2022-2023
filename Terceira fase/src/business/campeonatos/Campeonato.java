package business.campeonatos;

import business.carros.Carro;
import business.carros.MotorICE;
import business.carros.classes.C1;

import business.carros.SSCarrosFacade;
import business.utilizadores.Utilizador;

import java.util.*;

import static business.util.printMapSortedByValue;
import static business.util.sortMap;

public class Campeonato {
    private String nome;
    private List<Circuito> circuitos;
    private List<Jogador> jogadores;
    private Map<String,Integer> classificacao; // classificação para jogadores de carros normais.
    private Map<String,Integer> classificacaoH; // classificação para jogadores de carros híbridos.

    public Campeonato(){
        this.nome = "Campeonato";
        /*
        Jogador j1 = new Jogador(new Piloto("Ham", 0.5F, 0.5F),new Carro("Mercedes","AMG",0,0.5F,new MotorICE(6000,1000,1),new C1()));
        Jogador j2 = new Jogador(new Piloto("Max", 0.5F, 0.5F),new Carro("Red Bull","RB-18",0,0.5F,new MotorICE(6000,1000,1),new C1()));
        List<Jogador> jogadores= new ArrayList<>();
        jogadores.add(j1); jogadores.add(j2);
        this.jogadores = jogadores;
         */
        this.jogadores = new ArrayList<>();
        Circuito c1 = new Circuito("C1",3,true,10);
        Circuito c2 = new Circuito("C2",3,true,20);
        Circuito c3 = new Circuito("C3",3,false,30);
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

    public int pontuacao(int posicao){
        int pontos = 0;
        int[] pontuacoes = new int[]{12,10,8,7,6,5,4,3,2,1}; //Pontuações
        if (posicao<pontuacoes.length){
            pontos = pontuacoes[posicao];
        }
        return pontos;
    }

    public String SimularCampeonato(int corrida){
        Circuito c = this.circuitos.get(corrida);
        String result = c.SimularCorrida(jogadores);
        int e=0, h=0;
        for (Jogador j:jogadores){
            if (j.isHibrid()){
                this.classificacaoH.put(j.toString(), j.addPontuacao(pontuacao(h)));
                h++;
            }
            else{
                this.classificacao.put(j.toString(), j.addPontuacao(pontuacao(e)));
                e++;
            }
        }
        return result;
    }

    /**
     * Esta função imprime os resultados do campeonato e também adiciona as pontuações dos jogadores aos respetivos utilizadores.
     * @return Resultados do campeonato.
     */
    public String printResultados(){
        StringBuilder sb = new StringBuilder();
        if (!this.classificacao.isEmpty()){
            sb.append("Classificação dos carros ICE!\n");
            Map<String, Integer> classificacao = new HashMap<>(this.classificacao);
            sb.append(printMapSortedByValue(classificacao));
        }
        if (!this.classificacaoH.isEmpty()){
            sb.append("Classificação dos carros Hibrido!\n");
            Map<String, Integer> classificacaoH = new HashMap<>(this.classificacao);
            sb.append(printMapSortedByValue(classificacaoH));
        }
        List<String> resultados = new ArrayList<>();
        resultados.addAll(sortMap(this.classificacao).keySet());
        resultados.addAll(sortMap(this.classificacaoH).keySet());

        List<Jogador> ordemJogadores = new ArrayList<>();
        for (String s:resultados){
            for (Jogador j:this.jogadores){
                if (j.toString().equals(s)){
                    ordemJogadores.add(j);
                    break;
                }
            }
        }

        int e=0, h=0;
        for (Jogador j:ordemJogadores){
            if (j.isHibrid()){
                if (j.getClass().equals(JogadorAutenticado.class)){
                    ((JogadorAutenticado) j).addPontuacaoInPlayer(pontuacao(h));
                }
                h++;
            }
            else{
                if (j.getClass().equals(JogadorAutenticado.class)){
                    ((JogadorAutenticado) j).addPontuacaoInPlayer(pontuacao(e));
                }
                e++;
            }
        }
        return sb.toString();
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

    public String printCarro(int nJogador){
        return this.jogadores.get(nJogador).printCarro();
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


    public boolean addJogador(String nome, Utilizador utilizador, Piloto piloto, Carro carro){
        if (piloto == null || carro == null){
            return false;
        }
        Jogador j = null;
        if (utilizador != null){
            j = new JogadorAutenticado(nome,piloto,carro,utilizador);
        }
        else j = new JogadorAnonimo(nome,piloto,carro);
        this.jogadores.add(j);
        return true;
    }

    public void reset(){
        this.jogadores = new ArrayList<>();
        for (Circuito c:this.circuitos){
            c.reset();
        }
        this.classificacao = new HashMap<>();
        this.classificacaoH = new HashMap<>();
    }

    public String getNome(){
        return this.nome;
    }

    public List<Circuito> getCircuitos(){
        return this.circuitos;
    }

    // TODO: Completar esta classe
}
