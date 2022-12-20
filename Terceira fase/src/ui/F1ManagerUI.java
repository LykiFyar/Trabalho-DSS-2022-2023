package ui;

import business.campeonatos.Campeonato;
import business.campeonatos.ISSCampeonados;
import business.carros.ISSCarros;
import business.utilizadores.ISSUtilizadores;

import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;

public class F1ManagerUI{
    private ISSCampeonados campeonados;

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

        scin = new Scanner(System.in);
    }


    public void run() {
        this.menu.run();
        System.out.println("Até breve!...");
    }

    private void jogarCampeonato() {
        try{
            Campeonato c = new Campeonato();
            c.SimularCampeonato();
        }
        catch (NullPointerException e) {
            System.out.println(e.getMessage());
        }
    }
}