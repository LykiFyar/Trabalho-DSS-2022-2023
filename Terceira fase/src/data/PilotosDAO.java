package data;

import java.util.Collection;
import java.util.Map;
import java.util.Set;

import business.campeonatos.Piloto;

public class PilotosDAO implements Map<String,Piloto> {
    private static PilotosDAO singleton = null;

    private PilotosDAO() {}

    public static PilotosDAO getInstance() {
        if(PilotosDAO.singleton == null){
            PilotosDAO.singleton = new PilotosDAO();
        }
        return PilotosDAO.singleton;
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
    public Set<Entry<String, Piloto>> entrySet() {
        // TODO Auto-generated method stub
        return null;
    }

    @Override
    public Piloto get(Object key) {
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
    public Piloto put(String arg0, Piloto arg1) {
        // TODO Auto-generated method stub
        return null;
    }

    @Override
    public void putAll(Map<? extends String, ? extends Piloto> m) {
        // TODO Auto-generated method stub
        
    }

    @Override
    public Piloto remove(Object key) {
        // TODO Auto-generated method stub
        return null;
    }

    @Override
    public int size() {
        // TODO Auto-generated method stub
        return 0;
    }

    @Override
    public Collection<Piloto> values() {
        // TODO Auto-generated method stub
        return null;
    }
    
}
