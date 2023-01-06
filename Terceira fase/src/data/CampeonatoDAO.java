package data;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Collection;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;
import java.util.stream.Collectors;

import business.campeonatos.Campeonato;

public class CampeonatoDAO implements Map<String,Campeonato>{

    private static CampeonatoDAO singleton = null;

    private CampeonatoDAO() {
        try (Connection conn = DriverManager.getConnection(DAOconfig.URL, DAOconfig.USERNAME, DAOconfig.PASSWORD);
             Statement stm = conn.createStatement()) {
            String sql = "CREATE TABLE IF NOT EXISTS `Simulação`.`Campeonatos` (" +
                        "Nome VARCHAR(255) NOT NULL," +
                        "PRIMARY KEY (Nome));";
            stm.executeUpdate(sql);
        } catch (SQLException e) {
            // Erro a criar tabela...
            e.printStackTrace();
            throw new NullPointerException(e.getMessage());
        }
    }
    //Método que cria uma instância única desta classe
    public static CampeonatoDAO getInstance(){
        if(CampeonatoDAO.singleton == null){
            CampeonatoDAO.singleton = new CampeonatoDAO();
        }
        return CampeonatoDAO.singleton;
    }

    //Método que elimina todas as entradas na tabela.
    @Override
    public void clear() {
          try (Connection conn = DriverManager.getConnection(DAOconfig.URL, DAOconfig.USERNAME, DAOconfig.PASSWORD);
          Statement stm = conn.createStatement()) {
         stm.executeUpdate("TRUNCATE `Simulação`.`Campeonatos`");
     } catch (SQLException e) {
         // Database error!
         e.printStackTrace();
         throw new NullPointerException(e.getMessage());
     }
 }

    //Método que devolve se um dado id de um Campeonato se encontra registado na Base de dados
    @Override
    public boolean containsKey(Object key) {
        boolean b;
        try (Connection conn = DriverManager.getConnection(DAOconfig.URL, DAOconfig.USERNAME, DAOconfig.PASSWORD);
            Statement s = conn.createStatement();
            ResultSet r=
                        s.executeQuery("SELECT Nome FROM `Simulação`.`Campeonatos` WHERE Nome ='" + key + "'")){
            b = r.next();
        }catch (SQLException e){
            e.printStackTrace();
            throw new NullPointerException(e.getMessage());
        }
        return b;
    }

    //Método que devolve se um dado campeonato se encontra registado na nossa base de dados
    @Override
    public boolean containsValue(Object value) {
        Campeonato c = (Campeonato) value;
        return this.containsKey(c.getNome());
    }

    @Override
    public Set<Entry<String, Campeonato>> entrySet() {
        return this.keySet().stream().map(k -> Map.entry(k, this.get(k))).collect(Collectors.toSet());
    }

    @Override
    public Campeonato get(Object key) {
        Campeonato r = null;
        try (Connection conn = DriverManager.getConnection(DAOconfig.URL, DAOconfig.USERNAME, DAOconfig.PASSWORD);
             Statement stm = conn.createStatement();
             ResultSet rs =
                     stm.executeQuery("SELECT * FROM `Simulação`.`Campeonatos` WHERE Nome='"+key+"';")) {
            r = rs.next() ? new Campeonato(rs.getString("Nome")) : null;
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
    public Campeonato put(String key, Campeonato value) {
        try (Connection conn = DriverManager.getConnection(DAOconfig.URL, DAOconfig.USERNAME, DAOconfig.PASSWORD);
             Statement stm = conn.createStatement()) {
            try (PreparedStatement pstm = conn.prepareStatement("INSERT INTO Campeonatos (Nome) VALUES (?)")){
                pstm.setString(1, value.getNome());
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
    public void putAll(Map<? extends String, ? extends Campeonato> m) {
        m.keySet().forEach(i -> this.put(i, m.get(i)));
    }

    @Override
    public Campeonato remove(Object key) {
        Campeonato c = this.get(key);
        try (Connection conn = DriverManager.getConnection(DAOconfig.URL, DAOconfig.USERNAME, DAOconfig.PASSWORD);
             Statement s = conn.createStatement()) {
            s.executeUpdate("DELETE FROM `Simulação`.`Campeonatos` WHERE Nome ='"+key+"'");
        } catch (Exception e) {
            e.printStackTrace();
            throw new NullPointerException(e.getMessage());
        }
        return c;
    }

    @Override
    public int size() {
        int i = 0;
        try (Connection conn = DriverManager.getConnection(DAOconfig.URL, DAOconfig.USERNAME, DAOconfig.PASSWORD);
             Statement stm = conn.createStatement();
             ResultSet rs = stm.executeQuery("SELECT count(*) FROM `Simulação`.`Campeonatos`")) {
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
    public Collection<Campeonato> values() {
        return this.keySet().stream().map(this::get).collect(Collectors.toList());
    }

    @Override
    public Set<String> keySet() {
        Set<String> res = new HashSet<>();
        try (Connection conn = DriverManager.getConnection(DAOconfig.URL, DAOconfig.USERNAME, DAOconfig.PASSWORD);
             Statement stm = conn.createStatement();
             ResultSet rs = stm.executeQuery("SELECT Nome FROM Campeonatos")) {
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
    
}
