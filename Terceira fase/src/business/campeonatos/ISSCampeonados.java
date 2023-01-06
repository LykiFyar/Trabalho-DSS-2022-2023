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

    //public void addPiloto(int id, String name, float cts, float sva);
    public void addPiloto(String name, float cts, float sva);

    public boolean validarCircuito(String name);

    public void adicionaCircuito(String name, int numVoltas, List<Setor> setores, int comprimento, int nSetores);

    public boolean campeonatoValido(String name);

    public void adicionaCampeonato(String name, List<Circuito> circuitos);

    //public void addJogador(String username, Piloto piloto, Carro carro); esta função foi mal pensadae é preciso trocar.

    public boolean addJogador(String nome, Utilizador utilizador, Piloto piloto, Carro carro);

    public void reset();

    public List<String> getCampeonatosNames();

    public List<String> getCircuitosNames();

    public String jogarCampeonato(int corrida);

    public String printResultados();

    public int numCorridas();

    public String printCorrida(int nCorrida);

    public int numJogadores();

    public String printJogador(int nJogador);

    public String printCarro(int nJogador);

    public boolean alterarPneu(int nJogador, int pneu);

    public boolean alterarPac(int nJogador, float pac);

    public boolean alterarFuncMotor(int nJogador, int m);

    public String printPilotos();

    public List<String> getPilotosNames();

    public Piloto getPiloto(String piloto);

    public void prepararCampeonato(String camp);

    public void addCircuito(String circuito);

}
