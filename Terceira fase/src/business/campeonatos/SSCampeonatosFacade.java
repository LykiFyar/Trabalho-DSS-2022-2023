package business.campeonatos;

import java.util.*;
import java.util.stream.Collectors;

import business.carros.Carro;
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
        this.circuitos.put("C1",new Circuito("C1",3,true,10, null));
        this.circuitos.put("C2",new Circuito("C2",3,true,20, null));
        this.circuitos.put("C3",new Circuito("C3",3,true,30, null));

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
        Circuito c = new Circuito(name,numVoltas,clima,comprimento,null);
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

    @Override
    public void addJogador(String username, Piloto piloto, Carro carro) {
        // TODO: !!!!!!!!!!ESTE MÉTODO TEM PROBLEMAS!!!!!!!!!!1
    }

    public List<String> getCampeonatosNames(){
        return new ArrayList<>(this.campeonatos.keySet());
    }

    @Override
    public String jogarCampeonato(String name, int corrida){
        Campeonato campeonato = campeonatos.get(name);
        return campeonato.SimularCampeonato(corrida);
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

    public boolean alterarPneu(String campeonato, int nJogador, int pneu){
        return this.campeonatos.get(campeonato).alterarPneu(nJogador, pneu);
    }

    public boolean alterarPac(String campeonato, int nJogador, float pac){
        return this.campeonatos.get(campeonato).alterarPac(nJogador, pac);
    }

    public boolean alterarFuncMotor(String campeonato, int nJogador, int m){
        return this.campeonatos.get(campeonato).alterarFuncMotor(nJogador, m);
    }

}
