package business.campeonatos;

import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Circuito {
    private String nome;
    private int numVoltas;
    private boolean clima; // true-chove | false-seco
    private int comprimento;
    private List<Setor> setores; // acho que faz sentido ter uma lista porque há uma noção de ordem nos setores.
    private List<Jogador> participantes; // terá a ordem dos jogadores na corrida
    private Map<Jogador,Integer> dnf;
    
    private Circuito(){}

    public Circuito(String nome, int numVoltas, boolean clima, int comprimento, List<Jogador> participantes){
        this.nome = nome;
        this.numVoltas = numVoltas;
        this.clima = clima;
        this.comprimento = comprimento;
        Setor s1 = new Setor(0,"reta");
        Setor s2 = new Setor(1,"chicane");
        Setor s3 = new Setor(2,"curva");
        List<Setor> setores = new ArrayList<>();
        setores.add(s1); setores.add(s2); setores.add(s3);
        this.setores = setores;
        this.participantes = participantes;
        this.dnf = new HashMap<Jogador,Integer>();
    }

    public String getNome() {
        return nome;
    }

    public int getNumVoltas() {
        return numVoltas;
    }

    public boolean isClima() {
        return clima;
    }

    public int getComprimento() {
        return comprimento;
    }

    public List<Setor> getSetores() {
        return setores;
    }

    public List<Jogador> getParticipantes() {
        return participantes;
    }

    public Map<Jogador, Integer> getDnf() {
        return dnf;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("||||||||||||| Circuito: ").append(this.nome).append(" |||||||||||||\n");
        sb.append("| Clima:").append(this.clima ? "Chuva" : "Seco");
        sb.append(" | Voltas:").append(this.numVoltas);
        sb.append(" | Comprimento:").append(this.comprimento).append("km |\n");
        return sb.toString();
    }

    private String printJogadores(List<Jogador> jogadores, int volta){
        StringBuilder sb = new StringBuilder();
        sb.append("||||| Posições na volta ").append(volta).append(" |||||\n");

        int i=1;
        for (Jogador j:jogadores){
            sb.append("Jogador na posição ").append(i).append("\n");
            sb.append(j.toString()).append("\n");
            i++;
        }
        return sb.toString();
    }

    /**
     * Método que simula uma corrida simulando todos os setores presentes na mesma.
     * O booleano halfDistance indica se a simulação irá decorrer na primeira metade ou na segunda metade da corrida.
     * False - Primeira Metade | True - Segunda Metade
     * @param jogadores Jogadores participantes na corrida
     * @return String com os resultados da corrida onde contém todas as posições em cada volta da mesma
     */
    public String SimularCorrida(List<Jogador> jogadores){
        StringBuilder result = new StringBuilder();
        boolean halfDistance = false;
        for(int i=1; i<=numVoltas; i++){
            halfDistance = i>numVoltas/2;
            for (Setor s: setores){
                s.SimularSetor(jogadores, this.clima, halfDistance);
            }
            result.append(printJogadores(jogadores,i));
        }
        return result.toString();
    }

    // TODO: Completar esta classe

}
