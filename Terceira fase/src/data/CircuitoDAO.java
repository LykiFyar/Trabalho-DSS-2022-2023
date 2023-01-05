package data;

import java.util.Collection;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;
import java.sql.*;
import java.util.stream.Collectors;

import business.campeonatos.Circuito;

public class CircuitoDAO implements Map<String,Circuito>{

    private static CircuitoDAO singleton = null;

    private CircuitoDAO() {
        try (Connection conn = DriverManager.getConnection(DAOconfig.URL, DAOconfig.USERNAME, DAOconfig.PASSWORD);
             Statement stm = conn.createStatement()) {
            String sql = "CREATE TABLE IF NOT EXISTS `Simulação`.`Circuitos` (" +
                         "Nome VARCHAR(255) NOT NULL," +
                         "Clima VARCHAR(255) NOT NULL," +
                         "Voltas INT NOT NULL," +
                         "Comprimento INT NOT NULL," +
                         "PRIMARY KEY (Nome));";
            stm.executeUpdate(sql);

        } catch (SQLException e) { // erro ao criarmos a tabela
            e.printStackTrace();
            throw new NullPointerException(e.getMessage());
        }
    }

    public static CircuitoDAO getInstance() {
        if(CircuitoDAO.singleton == null){
            CircuitoDAO.singleton = new CircuitoDAO();
        }
        return CircuitoDAO.singleton;
    }

    @Override
    public void clear() {
        try (Connection conn = DriverManager.getConnection(DAOconfig.URL, DAOconfig.USERNAME, DAOconfig.PASSWORD);
             Statement stm = conn.createStatement()) {
            stm.executeUpdate("TRUNCATE Circuitos");
        } catch (SQLException e) {
            // Database error!
            e.printStackTrace();
            throw new NullPointerException(e.getMessage());
        }
    }

    @Override
    public boolean containsKey(Object key) {
        boolean r;
        try (Connection conn = DriverManager.getConnection(DAOconfig.URL, DAOconfig.USERNAME, DAOconfig.PASSWORD);
             Statement stm = conn.createStatement();
             ResultSet rs =
                     stm.executeQuery("SELECT Nome FROM `Simulação`.`Circuitos` WHERE Nome='"+key+"'")) {
            r = rs.next();
        } catch (SQLException e) {
            // Database error!
            e.printStackTrace();
            throw new NullPointerException(e.getMessage());
        }
        return r;
    }

    @Override
    public boolean containsValue(Object value) {
        Circuito c = (Circuito) value;
        return this.containsKey(c.getNome());
    }

    @Override
    public Set<Entry<String, Circuito>> entrySet() {
        return this.keySet().stream().map(k -> Map.entry(k, this.get(k))).collect(Collectors.toSet());
    }

    @Override
    public Circuito get(Object key) {
        Circuito r = null;
        try (Connection conn = DriverManager.getConnection(DAOconfig.URL, DAOconfig.USERNAME, DAOconfig.PASSWORD);
             Statement stm = conn.createStatement();
             ResultSet rs =
                     stm.executeQuery("SELECT * FROM `Simulação`.`Circuitos` WHERE Nome='"+key+"'")) {
            r = rs.next() ? new Circuito(rs.getString("Nome"),rs.getInt("Voltas"), 
                             rs.getString("Clima").equals("Chuva"), 
                             rs.getInt("Comprimento")) : null;
        } catch (SQLException e) {
            // Database error!
            e.printStackTrace();
            throw new NullPointerException(e.getMessage());
        }
        return r;
    }

    @Override
    public boolean isEmpty() {
        return this.size() == 0;
    }

    @Override
    public Set<String> keySet() {
        Set<String> res = new HashSet<>();
        try (Connection conn = DriverManager.getConnection(DAOconfig.URL, DAOconfig.USERNAME, DAOconfig.PASSWORD);
             Statement stm = conn.createStatement();
             ResultSet rs = stm.executeQuery("SELECT Nome FROM Circuitos")) {
            while (rs.next()) {
                String idc = rs.getString("Nome");
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
    public Circuito put(String key, Circuito value) {
        try (Connection conn = DriverManager.getConnection(DAOconfig.URL, DAOconfig.USERNAME, DAOconfig.PASSWORD);
             Statement stm = conn.createStatement()) {
            try (PreparedStatement pstm = conn.prepareStatement("INSERT INTO Circuitos (Nome,Clima,Voltas,Comprimento) VALUES ('?','?','?','?')")){
                pstm.setString(1, value.getNome());
                pstm.setString(2, value.isClima() ? "Chuva" : "Tempo Seco");
                pstm.setInt(3, value.getNumVoltas());
                pstm.setInt(3, value.getComprimento());
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
    public void putAll(Map<? extends String, ? extends Circuito> m) {
        m.keySet().forEach(i -> this.put(i, m.get(i)));
    }

    @Override
    public Circuito remove(Object key) {
        Circuito value = null;
        try (Connection conn = DriverManager.getConnection(DAOconfig.URL, DAOconfig.USERNAME, DAOconfig.PASSWORD);
             Statement stm = conn.createStatement()) {
            try (PreparedStatement pstm = conn.prepareStatement("DELETE FROM Circuitos WHERE Nome = '?'")){
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
    public int size() {
        int i = 0;
        try (Connection conn = DriverManager.getConnection(DAOconfig.URL, DAOconfig.USERNAME, DAOconfig.PASSWORD);
             Statement stm = conn.createStatement();
             ResultSet rs = stm.executeQuery("SELECT count(*) FROM `Simulação`.`Circuitos`;")) {
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

    @Override
    public Collection<Circuito> values() {
        return (this.keySet().stream().map(this::get).collect(Collectors.toList()));
    }
    
}
