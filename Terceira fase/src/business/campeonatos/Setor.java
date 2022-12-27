package business.campeonatos;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Iterator;
import java.util.List;

public class Setor {
    private int dificuldade; //0-facil | 1-dificil | 2-impossivel
    private String tipoSetor;

    private Setor(){}

    public Setor(int dificuldade, String tipoSetor){
        this.dificuldade = dificuldade;
        this.tipoSetor = tipoSetor;
    }


    public int getDificuldade() {
        return this.dificuldade;
    }

    public void setDificuldade(int dificuldade) {
        this.dificuldade = dificuldade;
    }

    public String getTipoSetor() {
        return this.tipoSetor;
    }

    public void setTipoSetor(String tipoSetor) {
        this.tipoSetor = tipoSetor;
    }

    private static <T> void swap(List<T> list, int i, int j) {
        T temp = list.get(i);
        list.set(i,list.get(j));
        list.set(j,temp);
    }

    private boolean checkOvertake(float t1, float t2){
        switch (this.dificuldade){
            case 0: // facil
                return t2 - t1 > 1; // se o carro for 1 segundo mais rapido ultrapassa
            case 1: // dificil
                return t2 - t1 > 2; // se o carro for 2 segundo mais rapido ultrapassa
            case 2: // impossivel
                return false; // inicialmente retorna false, mas poderá ser possivel tendo em conta o enunciado
            default:
                return false;
        }
    }

    /**
     * Função que simula as ultrapassagens nos setores
     * @param jogadores Lista dos jogadores ativos na corrida
     * @param clima True-chuva | False-seco
     * @param halfDistance Indica se a simulação está a decorrer na primeira ou na segunda metade da corrida
     */
    public String SimularSetor(List<Jogador> jogadores, boolean clima, boolean halfDistance, int nSetor){
        StringBuilder sb = new StringBuilder();
        List<Float> tempos = new ArrayList<>();
        for (Jogador j:jogadores){
            float tempo = j.calcularTempo(tipoSetor,clima, halfDistance);
            tempos.add(tempo);
        }
        // dependendo da dificuldade de ultrapassagem e dos tempos dos carros decidir se existe ultrapassagem ou não.
        for (int i=0; i<tempos.size()-1; i++){
            int j = i+1;
            float t1 = tempos.get(i);
            float t2 = tempos.get(j);
            if(checkOvertake(t1,t2)){
                sb.append("O jogador ").append(jogadores.get(j));
                sb.append(" ultrapassou o jogador ").append(jogadores.get(i));
                sb.append(" no ").append(nSetor).append("º setor!\n");
                swap(jogadores,i,j);
                swap(tempos,i,j);
            }
        }
        return sb.toString();
    }
}
