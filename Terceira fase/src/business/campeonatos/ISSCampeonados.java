package business.campeonatos;

import java.util.Collection;
import java.util.List;

import business.carros.Carro;

public interface ISSCampeonados {

    public boolean validaPiloto(String name);

    public void addPiloto(String name, float cts, float sva);

    public boolean validarCircuito(String name);

    public void adicionaCircuito(String name, int numVoltas, List<Setor> setores, int comprimento);

    public boolean campeonatoValido(String name);

    public void adicionaCampeonato(String name, Collection<Circuito> circuitos);

    public void addJogador(String username, Piloto piloto, Carro carro);

    public void jogarCampeonato(String name);

}
