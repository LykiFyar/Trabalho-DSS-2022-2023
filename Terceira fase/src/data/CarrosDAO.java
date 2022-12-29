package data;

import java.sql.*;
import java.util.*;
import business.carros.Carro;

public class CarrosDAO implements Set<Carro> {

    private static CarrosDAO singleton = null;

    private CarrosDAO() {
        try (Connection conn = DriverManager.getConnection(DAOconfig.URL, DAOconfig.USERNAME, DAOconfig.PASSWORD);
             Statement stm = conn.createStatement()) {
            String sql = "CREATE TABLE IF NOT EXISTS `Simulação`.`Carros` (" +
                    " Id INT NOT NULL," +
                    " Marca VARCHAR(255) NOT NULL," +
                    " Modelo VARCHAR(255) NOT NULL," +
                    "PRIMARY KEY (Id));";
            stm.executeUpdate(sql);

        } catch (SQLException e) { // erro ao criarmos a tabela
            e.printStackTrace();
            throw new NullPointerException(e.getMessage());
        }
    }
    //Método que devolve a instância única desta classe no padrão Singleton.
    public static CarrosDAO getInstance(){
        if(CarrosDAO.singleton==null){
            CarrosDAO.singleton = new CarrosDAO();
        }
        return CarrosDAO.singleton;
    }

    //Método que devolve o número de entradas na tabela da base de dados.
    @Override
    public int size() {
        int i = 0;
        try (Connection conn = DriverManager.getConnection(DAOconfig.URL, DAOconfig.USERNAME, DAOconfig.PASSWORD);
             Statement stm = conn.createStatement();
             ResultSet rs = stm.executeQuery("SELECT count(*) FROM `Simulação`.`Carros`")) {
            if(rs.next()) {
                i = rs.getInt(1);
            }
        }
        catch (Exception e) {
            e.printStackTrace();
            throw new NullPointerException(e.getMessage());
        }
        return i;
    }
    
    //Método que devolve se a tabela está vazia.
    @Override
    public boolean isEmpty() {
        return this.size() == 0;
    }

    //Método que devolve se uma dada um dado carro existe na base de dados
    /*@Override
    public boolean containsValue(Object value) {
        Carro c = (Carro) value;
        return this.containsKey(c.getId());
    }*/


    @Override
    public boolean add(Carro e) {
        // TODO Auto-generated method stub
        return false;
    }

    @Override
    public boolean addAll(Collection<? extends Carro> c) {
        // TODO Auto-generated method stub
        return false;
    }

    @Override
    public void clear() {
        // TODO Auto-generated method stub
    }

    @Override
    public boolean contains(Object o) {
        // TODO Auto-generated method stub
        return false;
    }

    @Override
    public boolean containsAll(Collection<?> c) {
        // TODO Auto-generated method stub
        return false;
    }

 

    @Override
    public Iterator<Carro> iterator() {
        // TODO Auto-generated method stub
        return null;
    }

    @Override
    public boolean remove(Object o) {
        // TODO Auto-generated method stub
        return false;
    }

    @Override
    public boolean removeAll(Collection<?> c) {
        // TODO Auto-generated method stub
        return false;
    }

    @Override
    public boolean retainAll(Collection<?> c) {
        // TODO Auto-generated method stub
        return false;
    }

    @Override
    public Object[] toArray() {
        // TODO Auto-generated method stub
        return null;
    }

    @Override
    public <T> T[] toArray(T[] a) {
        // TODO Auto-generated method stub
        return null;
    }
    
}
