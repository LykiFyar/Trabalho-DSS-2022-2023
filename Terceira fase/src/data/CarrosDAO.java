package data;

import java.sql.*;
import java.util.*;
import java.util.stream.Collectors;

import business.carros.Carro;
import business.carros.Classe;

public class CarrosDAO implements Map<Integer,Carro> {

    private static CarrosDAO singleton = null;
    private static Map<String,Classe> classes; 

    private CarrosDAO() {
        try (Connection conn = DriverManager.getConnection(DAOconfig.URL, DAOconfig.USERNAME, DAOconfig.PASSWORD);
             Statement stm = conn.createStatement()) {
            String sql = "CREATE TABLE IF NOT EXISTS `Simulação`.`Carros` (" +
                         "Id INT NOT NULL AUTO_INCREMENT," +
                         "Marca VARCHAR(255) NOT NULL," +
                         "Modelo VARCHAR(255) NOT NULL," +
                         "Classe VARCHAR(255) NOT NULL," +
                         "FOREIGN KEY (Classe) REFERENCES ClassesCarros(Id)," +
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
            CarrosDAO.classes = ClassesCarrosDAO.getInstance();
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
             ResultSet rs = stm.executeQuery("SELECT count(*) FROM `Simulação`.`Carros`;")) {
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


    public boolean containsKey(Object key) {
        boolean r;
        try (Connection conn = DriverManager.getConnection(DAOconfig.URL, DAOconfig.USERNAME, DAOconfig.PASSWORD);
             Statement stm = conn.createStatement();
             ResultSet rs =
                     stm.executeQuery("SELECT Id FROM `Simulação`.`Carros` WHERE Id='"+key+"'")) {
            r = rs.next();
        } catch (SQLException e) {
            // Database error!
            e.printStackTrace();
            throw new NullPointerException(e.getMessage());
        }
        return r;
    }

    //Método que devolve se uma dada um dado carro existe na base de dados
   // @Override
    public boolean containsValue(Object value) {
        Carro c = (Carro) value;
        return this.containsKey(c.getId());
    }

 
    @Override
    public Carro get(Object key) {
        Carro r = null;
        try (Connection conn = DriverManager.getConnection(DAOconfig.URL, DAOconfig.USERNAME, DAOconfig.PASSWORD);
             Statement stm = conn.createStatement();
             ResultSet rs =
                     stm.executeQuery("SELECT * FROM `Simulação`.`Carros` WHERE Id='"+key+"'")) {
            // TODO: ATENÇÃO: VER O QUE FAZER COM OS VALORES: PNEU, PAC, MOTORICE E CLASSE
            Classe c = classes.get(rs.getString("Classe"));
            r = new Carro(rs.getInt("Id"), rs.getString("Marca"), rs.getString("Modelo"), 0, 0, null, c);
        } catch (SQLException e) {
            // Database error!
            e.printStackTrace();
            throw new NullPointerException(e.getMessage());
        }
        return r;
    }
    @Override
    public Carro put(Integer key, Carro value) {
        try (Connection conn = DriverManager.getConnection(DAOconfig.URL, DAOconfig.USERNAME, DAOconfig.PASSWORD);
             Statement stm = conn.createStatement()) {
            try (PreparedStatement pstm = conn.prepareStatement("INSERT INTO Carros (Marca,Modelo,Classe) VALUES ('?', '?')")){
                pstm.setString(0,value.getMarca());
                pstm.setString(1,value.getMarca());
                pstm.setString(3, value.getClasse().toString());
                pstm.executeUpdate(); 
            }
        }catch (SQLException e) {
            // Database error!
            e.printStackTrace();
            throw new NullPointerException(e.getMessage());
        }
        return value;
    }

    @Override
    public void putAll(Map<? extends Integer, ? extends Carro> m) {
        m.keySet().forEach(i -> this.put(i, m.get(i)));
    }

    @Override
    public Set<Integer> keySet() {
        Set<Integer> res = new HashSet<>();
        try (Connection conn = DriverManager.getConnection(DAOconfig.URL, DAOconfig.USERNAME, DAOconfig.PASSWORD);
             Statement stm = conn.createStatement();
             ResultSet rs = stm.executeQuery("SELECT Id FROM Carros")) {
            while (rs.next()) {
                int idc = rs.getInt("Id");
                res.add(idc);
            }
        } catch (Exception e) {
            // Database error!
            e.printStackTrace();
            throw new NullPointerException(e.getMessage());
        }
        return res;
    }
    @Override
    public Collection<Carro> values() {
        return (this.keySet().stream().map(this::get).collect(Collectors.toList()));
    }

    @Override
    public Set<Entry<Integer, Carro>> entrySet() {
        throw new RuntimeException("public Set<Entry<Integer, Carro>> entrySet() not implemented");
    }

    @Override
    public Carro remove(Object key) {
        Carro value = null;
        try (Connection conn = DriverManager.getConnection(DAOconfig.URL, DAOconfig.USERNAME, DAOconfig.PASSWORD);
             Statement stm = conn.createStatement()) {
            try (PreparedStatement pstm = conn.prepareStatement("DELETE FROM Carros WHERE Id = '?'")){
                value = this.get(key);
                pstm.setInt(1,(Integer)key);
                pstm.executeUpdate(); 
            }
        }catch (SQLException e) {
            // Database error!
            e.printStackTrace();
            throw new NullPointerException(e.getMessage());
        }
        return value;
    }

    @Override
    public void clear() {
        try (Connection conn = DriverManager.getConnection(DAOconfig.URL, DAOconfig.USERNAME, DAOconfig.PASSWORD);
             Statement stm = conn.createStatement()) {
            stm.executeUpdate("TRUNCATE Carros");
        } catch (SQLException e) {
            // Database error!
            e.printStackTrace();
            throw new NullPointerException(e.getMessage());
        }
    }
    
}
