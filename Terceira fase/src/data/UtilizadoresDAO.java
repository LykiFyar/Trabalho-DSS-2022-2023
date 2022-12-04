package data;

import java.util.Collection;
import java.util.Map;
import java.util.Set;

import business.utilizadores.Utilizador;

public class UtilizadoresDAO implements Map<String,Utilizador> {
    private static UtilizadoresDAO singleton = null;

    private UtilizadoresDAO(){}

    public static UtilizadoresDAO getInstance(){
        if(UtilizadoresDAO.singleton==null){
            UtilizadoresDAO.singleton = new UtilizadoresDAO();
        }
        return UtilizadoresDAO.singleton;
    }

    @Override
    public void clear() {
        // TODO Auto-generated method stub
        
    }

    @Override
    public boolean containsKey(Object key) {
        // TODO Auto-generated method stub
        return false;
    }

    @Override
    public boolean containsValue(Object value) {
        // TODO Auto-generated method stub
        return false;
    }

    @Override
    public Set<Entry<String, Utilizador>> entrySet() {
        // TODO Auto-generated method stub
        return null;
    }

    @Override
    public Utilizador get(Object key) {
        // TODO Auto-generated method stub
        return null;
    }

    @Override
    public boolean isEmpty() {
        return this.size() == 0;
    }

    @Override
    public Set<String> keySet() {
        // TODO Auto-generated method stub
        return null;
    }

    @Override
    public Utilizador put(String arg0, Utilizador arg1) {
        // TODO Auto-generated method stub
        return null;
    }

    @Override
    public void putAll(Map<? extends String, ? extends Utilizador> m) {
        // TODO Auto-generated method stub
        
    }

    @Override
    public Utilizador remove(Object key) {
        // TODO Auto-generated method stub
        return null;
    }

    @Override
    public int size() {
        // TODO Auto-generated method stub
        return 0;
    }

    @Override
    public Collection<Utilizador> values() {
        // TODO Auto-generated method stub
        return null;
    }
}
