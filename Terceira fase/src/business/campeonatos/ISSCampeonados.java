package business.campeonatos;

import java.util.Collection;
import java.util.List;
import java.util.Map;

import business.carros.Carro;

public interface ISSCampeonados {

    public Map<String, Piloto> getPilotos();

    public Map<String, Circuito> getCircuitos();

    public Map<String, Campeonato> getCampeonatos();

    public boolean validaPiloto(String name);

    public void addPiloto(String name, float cts, float sva);

    public boolean validarCircuito(String name);

    public void adicionaCircuito(String name, int numVoltas, List<Setor> setores, int comprimento);

    public boolean campeonatoValido(String name);

    public void adicionaCampeonato(String name, List<Circuito> circuitos);

    public void addJogador(String username, Piloto piloto, Carro carro);

    public List<String> getCampeonatosNames();

    public String jogarCampeonato(String name, int corrida);

    public int numCorridas(String name);

    public String printCorrida(String campeonato, int nCorrida);

    public int numJogadores(String name);

    public String printJogador(String campeonato, int nJogador);

    public boolean alterarPneu(String campeonato, int nJogador, int pneu);

    public boolean alterarPac(String campeonato, int nJogador, float pac);

    public boolean alterarFuncMotor(String campeonato, int nJogador, int m);

}
