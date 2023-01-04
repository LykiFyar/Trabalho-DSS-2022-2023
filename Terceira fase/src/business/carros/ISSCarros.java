package business.carros;

public interface ISSCarros {
    public boolean validaCarro(int id);

    public void addCarro(int id,Carro carro);

    public boolean validarCilindrada(int cilindrada, Classe classe);

    public String printCarros();

    public Carro getCarro(int carro);

}