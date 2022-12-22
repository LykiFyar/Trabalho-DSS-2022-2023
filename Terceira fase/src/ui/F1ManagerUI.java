package ui;

import business.campeonatos.ISSCampeonados;
import business.campeonatos.SSCampeonatosFacade;
import business.carros.ISSCarros;
import business.carros.SSCarrosFacade;
import business.utilizadores.ISSUtilizadores;
import business.utilizadores.SSUtilizadoresFacade;

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
            System.out.println("Qual campeonato pretente jogar?");
            System.out.println(this.campeonatos.printCampeonatos());
            String camp = scin.nextLine();
            try{
                for (int i=0; i<this.campeonatos.numCorridas(camp); i++){
                    System.out.println("||||| Corrida " + (i+1) + " |||||\n");
                    System.out.println(this.campeonatos.jogarCampeonato(camp,i));
                    System.out.println("Prime enter para simular a próxima corrida!");
                    scin.nextLine();
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
}