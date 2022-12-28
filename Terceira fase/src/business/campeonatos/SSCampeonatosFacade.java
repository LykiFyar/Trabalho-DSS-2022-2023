package business.campeonatos;

import java.util.*;

import business.carros.Carro;
import business.utilizadores.Utilizador;
import data.PilotosDAO;
import data.CampeonatoDAO;
import data.CircuitoDAO;

public class SSCampeonatosFacade implements ISSCampeonados{

    private Map<String,Piloto> pilotos;
    private Map<String,Circuito> circuitos;
    private Map<String,Campeonato> campeonatos;    

    public SSCampeonatosFacade(){
        /*
        this.pilotos = PilotosDAO.getInstance();
        this.campeonatos = CampeonatoDAO.getInstance();
        this.circuitos = CircuitoDAO.getInstance();
         */

        this.pilotos = new HashMap<>();
        this.pilotos.put("Ham", new Piloto("Ham",0.5f,0.5f));
        this.pilotos.put("Max", new Piloto("Max",0.5f,0.5f));

        this.circuitos = new HashMap<>();
        this.circuitos.put("C1",new Circuito("C1",3,true,10));
        this.circuitos.put("C2",new Circuito("C2",3,true,20));
        this.circuitos.put("C3",new Circuito("C3",3,true,30));

        this.campeonatos = new HashMap<>();
        this.campeonatos.put("Campeonato",new Campeonato());
    }

    public Map<String, Piloto> getPilotos() {
        return pilotos;
    }

    public Map<String, Circuito> getCircuitos() {
        return circuitos;
    }

    public Map<String, Campeonato> getCampeonatos() {
        return campeonatos;
    }

    @Override
    public boolean validaPiloto(String name) {
        return this.pilotos.containsKey(name);
    }

    @Override
    public void addPiloto(String name, float cts, float sva) {
        Piloto newPiloto = new Piloto(name, cts, sva);
        this.pilotos.put(name, newPiloto);
    }

    @Override
    public boolean validarCircuito(String name) {
        return this.circuitos.containsKey(name);
    }

    @Override
    public void adicionaCircuito(String name, int numVoltas, List<Setor> setores, int comprimento) {
        boolean clima = new Random().nextInt(2) == 0;
        Circuito c = new Circuito(name,numVoltas,clima,comprimento);
        this.circuitos.put(name, c);
    }

    @Override
    public boolean campeonatoValido(String name) {
        return !this.campeonatos.containsKey(name);
    }

    @Override
    public void adicionaCampeonato(String name, List<Circuito> circuitos) {
        Campeonato c = new Campeonato(name, circuitos);
        this.campeonatos.put(name,c);
    }


    public boolean addJogador(String campeonato, String nome, Utilizador utilizador, Piloto piloto, Carro carro) {
        return this.campeonatos.get(campeonato).addJogador(nome,utilizador,piloto,carro);
    }

    public void reset(String campeonato){
        this.campeonatos.get(campeonato).reset();
    }

    public List<String> getCampeonatosNames(){
        return new ArrayList<>(this.campeonatos.keySet());
    }

    @Override
    public String jogarCampeonato(String name, int corrida){
        Campeonato campeonato = campeonatos.get(name);
        return campeonato.SimularCampeonato(corrida);
    }

    public String printResultados(String campeonato){
        return this.campeonatos.get(campeonato).printResultados();
    }

    public int numCorridas(String name){
        return this.campeonatos.get(name).numCorridas();
    }

    public String printCorrida(String campeonato, int nCorrida){
        return this.campeonatos.get(campeonato).printCorrida(nCorrida);
    }

    public int numJogadores(String name){
        return this.campeonatos.get(name).numJogadores();
    }

    public String printJogador(String campeonato, int nJogador){
        return this.campeonatos.get(campeonato).printJogador(nJogador);
    }

    public String printCarro(String campeonato, int nJogador){
        return this.campeonatos.get(campeonato).printCarro(nJogador);
    }

    public boolean alterarPneu(String campeonato, int nJogador, int pneu){
        return this.campeonatos.get(campeonato).alterarPneu(nJogador, pneu);
    }

    public boolean alterarPac(String campeonato, int nJogador, float pac){
        return this.campeonatos.get(campeonato).alterarPac(nJogador, pac);
    }

    public boolean alterarFuncMotor(String campeonato, int nJogador, int m){
        return this.campeonatos.get(campeonato).alterarFuncMotor(nJogador, m);
    }

    public String printPilotos(){
        StringBuilder sb = new StringBuilder();
        int i = 1;
        for (Piloto p:this.pilotos.values()){
            sb.append(i).append(" ---> ").append(p.toString()).append("\n");
            i++;
        }
        return sb.toString();
    }

    public List<String> getPilotosNames(){return new ArrayList<>(this.pilotos.keySet());}

    public Piloto getPiloto(String piloto){
        return this.pilotos.get(piloto).clone();
    }

}
