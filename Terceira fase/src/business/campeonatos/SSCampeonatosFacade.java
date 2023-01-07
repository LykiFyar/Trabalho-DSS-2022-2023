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

    private Campeonato campeonato;

    public SSCampeonatosFacade(){

        this.pilotos = PilotosDAO.getInstance();
        this.campeonatos = CampeonatoDAO.getInstance();
        this.circuitos = CircuitoDAO.getInstance();

        this.campeonato = new Campeonato();

        /*
        this.pilotos = new HashMap<>();
        this.pilotos.put("Ham", new Piloto(0,"Ham",0.5f,0.5f));
        this.pilotos.put("Max", new Piloto(1,"Max",0.5f,0.5f));

        this.circuitos = new HashMap<>();
        this.circuitos.put("C1",new Circuito("C1",3,true,10));
        this.circuitos.put("C2",new Circuito("C2",3,true,20));
        this.circuitos.put("C3",new Circuito("C3",3,true,30));

        this.campeonatos = new HashMap<>();
        this.campeonatos.put("Campeonato",new Campeonato());
         */
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
        Piloto newPiloto = new Piloto( name, cts, sva);
        if(this.pilotos.containsKey(name)){ // se o piloto já existir na BD, deve ser actualizado
            this.pilotos.remove(name);
        }
        this.pilotos.put(name, newPiloto);
    }

    @Override
    public boolean validarCircuito(String name) {
        return this.circuitos.containsKey(name);
    }

    @Override
    public void adicionaCircuito(String name, int numVoltas, List<Setor> setores, int comprimento, int nSetores) {
        boolean clima = new Random().nextInt(2) == 0;
        Circuito c = new Circuito(name,numVoltas,clima,comprimento, nSetores);
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


    public boolean addJogador(String nome, Utilizador utilizador, Piloto piloto, Carro carro) {
        return this.campeonato.addJogador(nome,utilizador,piloto,carro);
    }

    public void reset(){this.campeonato.reset();}

    public List<String> getCampeonatosNames(){
        return new ArrayList<>(this.campeonatos.keySet());
    }

    public List<String> getCircuitosNames(){
        return new ArrayList<>(this.circuitos.keySet());
    }

    @Override
    public String jogarCampeonato(int corrida){
        return this.campeonato.SimularCampeonato(corrida);
    }

    public String printResultados(){
        return this.campeonato.printResultados();
    }

    public int numCorridas(){
        return this.campeonato.numCorridas();
    }

    public String printCorrida(int nCorrida){
        return this.campeonato.printCorrida(nCorrida);
    }

    public int numJogadores(){
        return this.campeonato.numJogadores();
    }

    public String printJogador(int nJogador){
        return this.campeonato.printJogador(nJogador);
    }

    public String printCarro(int nJogador){
        return this.campeonato.printCarro(nJogador);
    }

    public boolean alterarPneu(int nJogador, int pneu){
        return this.campeonato.alterarPneu(nJogador, pneu);
    }

    public boolean alterarPac(int nJogador, float pac){
        return this.campeonato.alterarPac(nJogador, pac);
    }

    public boolean alterarFuncMotor(int nJogador, int m){
        return this.campeonato.alterarFuncMotor(nJogador, m);
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

    public void prepararCampeonato(String camp){
        this.campeonato = this.campeonatos.get(camp);
    }

    public void addCircuito(String circuito){
        Circuito c = this.circuitos.get(circuito);
        this.campeonato.addCircuito(c);
    }

}
