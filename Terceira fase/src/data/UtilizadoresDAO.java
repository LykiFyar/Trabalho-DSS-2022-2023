package data;

import java.util.Collection;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;
import java.sql.*;
import java.util.stream.Collectors;

import business.utilizadores.Utilizador;

public class UtilizadoresDAO implements Map<String,Utilizador> {
    private static UtilizadoresDAO singleton = null;

    private UtilizadoresDAO(){
        try (Connection conn = DriverManager.getConnection(DAOconfig.URL, DAOconfig.USERNAME, DAOconfig.PASSWORD);
             Statement stm = conn.createStatement()) {
            String sql = "CREATE TABLE IF NOT EXISTS `Simulação`.`Utilizadores` (" +
                "user VARCHAR(255) NOT NULL," +
                "pass VARCHAR(255) NOT NULL," +
                "pontuaçãoGeral INT NOT NULL," +
                "isAdmin INT NOT NULL," +
                "PRIMARY KEY (user))";
            stm.executeUpdate(sql);

        } catch (SQLException e) { // erro ao criarmos a tabela
            e.printStackTrace();
            throw new NullPointerException(e.getMessage());
        }
    }

    public static UtilizadoresDAO getInstance(){
        if(UtilizadoresDAO.singleton==null){
            UtilizadoresDAO.singleton = new UtilizadoresDAO();
        }
        return UtilizadoresDAO.singleton;
    }

    @Override
    public void clear() {
        try (Connection conn = DriverManager.getConnection(DAOconfig.URL, DAOconfig.USERNAME, DAOconfig.PASSWORD);
             Statement stm = conn.createStatement()) {
            stm.executeUpdate("TRUNCATE Utilizadores");
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
                     stm.executeQuery("SELECT user FROM `Simulação`.`Utilizadores` WHERE user='"+key+"'")) {
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
        Utilizador c = (Utilizador) value;
        return this.containsKey(c.getUsername());
    }

    @Override
    public Set<Entry<String, Utilizador>> entrySet() {
        return this.keySet().stream().map(k -> Map.entry(k, this.get(k))).collect(Collectors.toSet());
    }

    @Override
    public Utilizador get(Object key) {
        Utilizador r = null;
        try (Connection conn = DriverManager.getConnection(DAOconfig.URL, DAOconfig.USERNAME, DAOconfig.PASSWORD);
             Statement stm = conn.createStatement();
             ResultSet rs =
                     stm.executeQuery("SELECT * FROM Utilizadores WHERE user='"+key+"'")) {
            r = rs.next() ? new Utilizador(rs.getString("user"), rs.getString("pass"), rs.getInt("pontuaçãoGeral"), rs.getInt("isAdmin") == 1 ? true : false) : null;
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
             ResultSet rs = stm.executeQuery("SELECT user FROM Utilizadores")) {
            while (rs.next()) {
                String idu = rs.getString("user");
                res.add(idu);
            }
        } catch (Exception e) {
            // Database error!
            e.printStackTrace();
            throw new NullPointerException(e.getMessage());
        }
        return res;
    }

    @Override
    public Utilizador put(String key, Utilizador value) {
        try (Connection conn = DriverManager.getConnection(DAOconfig.URL, DAOconfig.USERNAME, DAOconfig.PASSWORD);
             Statement stm = conn.createStatement()) {
            if (this.containsKey(key)){
                try (PreparedStatement pstm1 = conn.prepareStatement("UPDATE Utilizadores SET pontuaçãoGeral = ? where user = ?")){
                    pstm1.setInt(1,value.getPontuacaoGeral());
                    pstm1.setString(2,key);
                    pstm1.executeUpdate();
                }
            }
            else {
                try (PreparedStatement pstm2 = conn.prepareStatement("INSERT INTO Utilizadores (user,pass,pontuaçãoGeral,isAdmin) VALUES ('?','?','?','?')")){
                    pstm2.setString(1, value.getUsername());
                    pstm2.setString(2, value.getPassword());
                    pstm2.setInt(3, value.getPontuacaoGeral());
                    pstm2.setInt(4, value.isAdmin() ? 1 : 0);
                    pstm2.executeUpdate();
                }
            }
        }catch (SQLException e) {
            // Database error!
            e.printStackTrace();
            throw new NullPointerException(e.getMessage());
        }
        return value;
    }

    @Override
    public void putAll(Map<? extends String, ? extends Utilizador> m) {
        m.keySet().forEach(i -> this.put(i, m.get(i)));
    }

    @Override
    public Utilizador remove(Object key) {
        Utilizador value = null;
        try (Connection conn = DriverManager.getConnection(DAOconfig.URL, DAOconfig.USERNAME, DAOconfig.PASSWORD);
             Statement stm = conn.createStatement()) {
            try (PreparedStatement pstm = conn.prepareStatement("DELETE FROM Utilizadores WHERE user = '?'")){
                value = this.get(key);
                pstm.setString(1,(String)key);
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
             ResultSet rs = stm.executeQuery("SELECT count(*) FROM `Simulação`.`Utilizadores`;")) {
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
    public Collection<Utilizador> values() {
        return this.keySet().stream().map(this::get).collect(Collectors.toList());
    }
}
