package business.utilizadores;

import java.util.Map;
import data.UtilizadoresDAO;

public class SSUtilizadoresFacade implements ISSUtilizadores {
    Map<String,Utilizador> utilizadores;

    public SSUtilizadoresFacade(){
        this.utilizadores = UtilizadoresDAO.getInstance();
    }

    @Override
    public boolean existeUtilizador(String codUtilizador) {
        return this.utilizadores.containsKey(codUtilizador);
    }

    @Override
    public void registaUtilizador(String username, String password) {
        this.utilizadores.put(username,new Utilizador(username,password));
    }

    @Override
    public boolean validaUserPassword(String username, String password) {
        return this.utilizadores.get(username).getPassword().compareTo(password) == 0;
    }
    
}
