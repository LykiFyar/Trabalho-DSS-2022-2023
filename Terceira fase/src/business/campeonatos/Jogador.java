package business.campeonatos;

import business.carros.Carro;

import java.util.Random;

public class Jogador {
    private int pontuacao;
    private Piloto piloto; // !Composição!
    private Carro carro; // !Composição!

    public Jogador(Piloto p,Carro c){
        this.pontuacao = 0;
        this.carro = c.clone();
        this.piloto = p.clone();
    } 

    /**
     * @return int return the pontuacao
     */
    public int getPontuacao() {
        return pontuacao;
    }

    /**
     * @param pontuacao the pontuacao to set
     */
    public void setPontuacao(int pontuacao) {
        this.pontuacao = pontuacao;
    }

    public int hashCode() {
        Integer p = pontuacao;
        return p.hashCode() + piloto.hashCode() + carro.hashCode();
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append(carro.toString()).append("\n");
        sb.append(piloto.toString()).append("\n");
        return sb.toString();
    }

    /**
     * Esta função calcula o tempo que um jogador irá demorar em cada setor.
     * @param tipoSetor Indica qual é o tipo de setor (reta,curva ou chicane)
     * @param clima True-chuva | False-seco
     * @return Tempo estimado que o jogador irá demorar em cada setor
     */
    public float calcularTempo(String tipoSetor, boolean clima){
        int potencia = this.carro.getMotorICE().getPotencia(); // valor na casa das centenas possivelmente 1000cv
        int funcionamentoMotor = this.carro.getMotorICE().getFuncionamentoMotor(); // 0-1-2 maior o numero mais rápido
        int pneu = this.carro.getPneu(); // macio-duro-chuva | macio+rapido | duro+resistente?? | chuva melhor em chuva
        float pac = this.carro.getPac(); // 0-1 perto de 0 melhor em reta | 1 melhor em curva (depende do tipoSetor)
        int potenciaH = this.carro.getClasse().getPotenciaMotorEletrico(); // adicionamos à potencia base

        float cts = this.piloto.getCts(); // 0-1 | 0 melhor em chuva | 1 melhor em seco
        float sva = this.piloto.getSva(); // 0-1 | 0 o tempo será menor | 1 o tempo será maior

        // valor do perfil aerodinamico
        float pacE = 1;
        if (tipoSetor.equals("reta") && pac!=0){
            pacE = pacE/(pac*5); // pac menor derá um número maior
        }
        else pacE = pacE*pac;

        long fator_sorte = Double.valueOf(Math.random()*(5)).intValue();
        //System.out.println(fator_sorte);
        float tempo_estimado = 50;  // tempo inicial para cada setor que irá ser influenciado pelos valores acima
                                    // devemos também utilizar alguma aleatoriadade no calculo do tempo

        if(clima){
            // calcular o tempo em chuva
            // valor do pneu
            int pneuE;
            if(pneu == 2){
                pneuE = 1;
            }
            else pneuE = -5;

            tempo_estimado = tempo_estimado - ((potencia+potenciaH)/100f) - funcionamentoMotor - pneuE - pacE - (1/cts*10) - (1*sva) - fator_sorte;
        }
        else{
            // calcular o tempo em tempo seco
            // valor do pneu
            int pneuE;
            if(pneu == 0){
                pneuE = 2;
            } else if (pneu == 1) {
                pneuE = 1;
            } else pneuE = -3;

            tempo_estimado = tempo_estimado - ((potencia+potenciaH)/100f) - funcionamentoMotor - pneuE - pacE - (1/cts*10) - (1*sva) - fator_sorte;
        }
        //System.out.println(tempo_estimado);
        return tempo_estimado;
    }
}
