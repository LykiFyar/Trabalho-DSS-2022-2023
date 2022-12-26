package business.utilizadores;

public interface ISSUtilizadores {
    public boolean existeUtilizador(String codUtilizador);

    public void registaUtilizador(String username, String password);
    
    public boolean validaUserPassword(String username, String password);

    public Utilizador getUtilizador(String username);

}
