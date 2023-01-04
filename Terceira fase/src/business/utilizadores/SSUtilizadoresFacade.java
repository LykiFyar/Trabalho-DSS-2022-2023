package business.utilizadores;

import java.util.HashMap;
import java.util.Map;
import data.UtilizadoresDAO;

import static business.util.printMapSortedByValue;

public class SSUtilizadoresFacade implements ISSUtilizadores {
    Map<String,Utilizador> utilizadores;

    public SSUtilizadoresFacade(){
        this.utilizadores = UtilizadoresDAO.getInstance();

        /*
        this.utilizadores = new HashMap<>();
        this.utilizadores.put("anarita", new Utilizador("anarita", "ritagrupo30"));
        this.utilizadores.put("joão", new Utilizador("joão", "joãogrupo30"));
        this.utilizadores.put("miguel", new Utilizador("miguel", "miguelgrupo30"));
        this.utilizadores.put("orlando", new Utilizador("orlando", "orlandogrupo30"));
        this.utilizadores.put("pedro", new Utilizador("pedro", "pedrogrupo30"));
         */
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

    public Utilizador getUtilizador(String username){
        return this.utilizadores.get(username);
    }

    @Override
    public String classificacaoGlobal() {
        StringBuilder sb = new StringBuilder();
        Map<String,Integer> utilizadores = new HashMap<>();
        for (Map.Entry<String, Utilizador> entry : this.utilizadores.entrySet()){
            utilizadores.put(entry.getKey(), entry.getValue().getPontuacaoGeral());
        }
        sb.append(printMapSortedByValue(utilizadores));
        return sb.toString();
    }
}
