package business.carros;

import java.util.Collection;
import data.CarrosDAO;

public class SSCarrosFacade implements ISSCarros{

    private Collection<Carro> carros;

    public SSCarrosFacade(){
        this.carros = CarrosDAO.getInstance();
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
    
}
