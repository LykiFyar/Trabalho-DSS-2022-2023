package business.carros;

public interface ISSCarros {
    public boolean validaCarro(Carro carro);

    public void addCarro(Carro carro);

    public boolean validarCilindrada(int cilindrada, Classe classe);

    public String printCarros();

    public Carro getCarro(int carro);

}