package business.campeonatos;

import java.util.Collection;
import java.util.List;
import java.util.Map;

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
    public void adicionaCircuito(String name, int numVoltas, List<Setor> setores) {
        // TODO Auto-generated method stub
        
    }

    @Override
    public boolean campeonatoValido(String name) {
        // TODO Auto-generated method stub
        return false;
    }

    @Override
    public void adicionaCampeonato(String name, Collection<Circuito> circuitos) {
        // TODO Auto-generated method stub
        
    }

    @Override
    public void addJogador(String username, Piloto piloto, Carro carro) {
        // TODO Auto-generated method stub
        
    }
    
}
