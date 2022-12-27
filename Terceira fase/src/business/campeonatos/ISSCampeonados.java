package business.campeonatos;

import java.util.Collection;
import java.util.List;
import java.util.Map;

import business.carros.Carro;
import business.utilizadores.Utilizador;

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

    //public void addJogador(String username, Piloto piloto, Carro carro); esta função foi mal pensadae é preciso trocar.

    public boolean addJogador(String campeonato, String nome, Utilizador utilizador, Piloto piloto, Carro carro);

    public void reset(String campeonato);

    public List<String> getCampeonatosNames();

    public String jogarCampeonato(String name, int corrida);

    public int numCorridas(String name);

    public String printCorrida(String campeonato, int nCorrida);

    public int numJogadores(String name);

    public String printJogador(String campeonato, int nJogador);

    public String printCarro(String campeonato, int nJogador);

    public boolean alterarPneu(String campeonato, int nJogador, int pneu);

    public boolean alterarPac(String campeonato, int nJogador, float pac);

    public boolean alterarFuncMotor(String campeonato, int nJogador, int m);

    public String printPilotos();

    public List<String> getPilotosNames();

    public Piloto getPiloto(String piloto);

}
