package business.carros;

import java.util.ArrayList;
import java.util.List;

import business.carros.classes.C1;
import data.CarrosDAO;

public class SSCarrosFacade implements ISSCarros{

    private List<Carro> carros;

    public SSCarrosFacade(){
        /*
        this.carros = CarrosDAO.getInstance();
        */
        this.carros = new ArrayList<>();
        this.carros.add(new Carro("Mercedes","AMG",0,0.5F,new MotorICE(6000,1000,1),new C1()));
        this.carros.add(new Carro("Red Bull","RB-18",0,0.5F,new MotorICE(6000,1000,1),new C1()));
    }

    @Override
    public boolean validaCarro(Carro carro) {
        return this.carros.contains(carro);
    }

    @Override
    public void addCarro(Carro carro) {
        this.carros.add(carro);
    }

    @Override
    public boolean validarCilindrada(int cilindrada, Classe classe) {
        return cilindrada >= classe.getMinCilindrada() && 
               cilindrada <= classe.getMaxCilindrada();
    }

    public String printCarros(){
        StringBuilder sb = new StringBuilder();
        int i = 1;
        for (Carro c:this.carros){
            sb.append(i).append(" ---> ").append(c.toString()).append("\n");
            i++;
        }
        return sb.toString();
    }

    public Carro getCarro(int carro){
        return this.carros.get(carro).clone();
    }
}
