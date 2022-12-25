package ui;

import business.campeonatos.ISSCampeonados;
import business.campeonatos.SSCampeonatosFacade;
import business.carros.ISSCarros;
import business.carros.SSCarrosFacade;
import business.utilizadores.ISSUtilizadores;
import business.utilizadores.SSUtilizadoresFacade;

import java.util.List;
import java.util.Scanner;

public class F1ManagerUI{
    private ISSCampeonados campeonatos;

    private ISSCarros carros;

    private ISSUtilizadores utilizadores;

    // Menus da aplicação
    private Menu menu;

    // Scanner para leitura
    private Scanner scin;


    public F1ManagerUI(){
        this.menu = new Menu(new String[]{
                "Jogar Campeonato"
        });

        this.menu.setHandler(1,this::jogarCampeonato);

        this.campeonatos = new SSCampeonatosFacade();
        this.carros = new SSCarrosFacade();
        this.utilizadores = new SSUtilizadoresFacade();
        scin = new Scanner(System.in);
    }


    public void run() {
        this.menu.run();
        System.out.println("Até breve!...");
    }

    private void jogarCampeonato() {
        try{
            System.out.println("\n****** JOGAR CAMPEONATO ****** \n");
            System.out.println("Qual campeonato pretente jogar?");
            List<String> campeonatos = this.campeonatos.getCampeonatosNames();
            for (int i=0; i<campeonatos.size();i++){
                System.out.println((i+1) + "º -> " + campeonatos.get(i));
            }
            int nCamp = scin.nextInt();
            scin.nextLine(); //consumir o \n
            String camp = campeonatos.get(nCamp-1);
            try{
                for (int i=0; i<this.campeonatos.numCorridas(camp); i++){
                    configurarCorrida(camp,i);
                    System.out.println(this.campeonatos.jogarCampeonato(camp,i));
                    System.out.println("Prime enter para simular a próxima corrida!\n");
                    String enter = scin.nextLine();
                }
            }
            catch (NullPointerException e){
                System.out.println("O campeonato inserido não existe");
            }
        }
        catch (NullPointerException e){
            System.out.println(e.getMessage());
        }
    }

    private void configurarCorrida(String camp, int nCorrida){
        System.out.println(this.campeonatos.printCorrida(camp,nCorrida));

        for (int i=0; i<this.campeonatos.numJogadores(camp); i++){
            System.out.println(this.campeonatos.printJogador(camp,i));
            System.out.println("Dejesa fazer alterações ao carro? S/N");
            String s = scin.nextLine();
            if (s.equals("S") || s.equals("s")){
                modificarCarro(camp, i);
            }
        }
    }

    private void modificarCarro(String camp, int nJogador){
        boolean flag = true;
        while(flag){
            System.out.println("O que deseja alterar no seu carro?\n" +
                    "1 -> Alterar os pneus\n" +
                    "2 -> Alterar o perfil aerodinâmico do carro\n" +
                    "3 -> Mudar o modo do motor\n" +
                    "0 -> Sair");
            int e = scin.nextInt();
            scin.nextLine(); //consumir o \n
            switch (e){
                case 1:
                    trocarPneu(camp,nJogador);
                    break;
                case 2:
                    trocarPac(camp,nJogador);
                    break;
                case 3:
                    trocarFuncMotor(camp,nJogador);
                    break;
                default:
                    flag = false;
            }
        }
        System.out.println("Modificações efetuadas!");
    }

    private void trocarPneu(String camp, int nJogador){
        System.out.println("Qual pneu deseja utilizar nesta corrida?\n" +
                "1 -> Macio\n" +
                "2 -> Duro\n" +
                "3 -> Chuva");
        int pneu = scin.nextInt();
        scin.nextLine(); //consumir o \n
        if (this.campeonatos.alterarPneu(camp,nJogador,pneu-1)){
            System.out.println("Pneu alterado com sucesso!");
        }
        else System.out.println("Pneu não faz parte dos pneus disponíveis!");
    }

    private void trocarPac(String camp, int nJogador){
        System.out.println("Qual perfil aerodinâmico deseja para o seu carro? Valor entre 0-10");
        int pac = scin.nextInt();
        float pacF = pac/10f;
        if (this.campeonatos.alterarPac(camp,nJogador,pacF)){
            System.out.println("Perfil aerodinâmico alterado com sucesso!");
        }
        else System.out.println("Perfil aerodinâmico inválido!");
    }

    private void trocarFuncMotor(String camp, int nJogador){
        System.out.println("Qual modo de motor deseja utilizar nesta corrida?\n" +
                "1 -> Conservador\n" +
                "2 -> Normal\n" +
                "3 -> Agressivo");
        int m = scin.nextInt();
        scin.nextLine(); //consumir o \n
        if (this.campeonatos.alterarFuncMotor(camp,nJogador,m-1)){
            System.out.println("Modo de motor alterado com sucesso!");
        }
        else System.out.println("Modo de motor inserido inválido!");
    }
}