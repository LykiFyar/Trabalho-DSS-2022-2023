package business.campeonatos;

import java.util.Collection;
import java.util.List;
import java.util.Map;
import java.util.Random;

import business.carros.Carro;
import data.PilotosDAO;
import data.CampeonatoDAO;
import data.CircuitoDAO;

public class SSCampeonatosFacade implements ISSCampeonados{

    private Map<String,Piloto> pilotos;
    private Map<String,Circuito> circuitos;
    private Map<String,Campeonato> campeonatos;    

    public SSCampeonatosFacade(){
        this.pilotos = PilotosDAO.getInstance();
        this.campeonatos = CampeonatoDAO.getInstance();
        this.circuitos = CircuitoDAO.getInstance();
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
        boolean clima = new Random().nextInt(1) == 0;
        Circuito c = new Circuito(name,numVoltas,clima,comprimento);
        this.circuitos.put(name, c);
    }

    @Override
    public boolean campeonatoValido(String name) {
        return !this.campeonatos.containsKey(name);
    }

    @Override
    public void adicionaCampeonato(String name, Collection<Circuito> circuitos) {
        Campeonato c = new Campeonato(name, circuitos);
        this.campeonatos.put(name,c);
    }

    @Override
    public void addJogador(String username, Piloto piloto, Carro carro) {
        // TODO: !!!!!!!!!!ESTE MÉTODO TEM PROBLEMAS!!!!!!!!!!1
    }
    
}
