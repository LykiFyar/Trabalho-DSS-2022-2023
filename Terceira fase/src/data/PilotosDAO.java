package data;

import java.sql.*;
import java.util.Collection;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

import business.campeonatos.Piloto;

public class PilotosDAO implements Map<Integer,Piloto> {
    private static PilotosDAO singleton = null;

    private PilotosDAO() {
        try (Connection conn = DriverManager.getConnection(DAOconfig.URL, DAOconfig.USERNAME, DAOconfig.PASSWORD);
             Statement stm = conn.createStatement()) {
            String sql = "CREATE TABLE IF NOT EXISTS `Simulação`.`Pilotos` (" +
                         "Id INT NOT NULL AUTO_INCREMENT," +
                         "Nome VARCHAR(255) NOT NULL," +
                         "CTS DECIMAL (5,2)," + 
                         "SVA DECIMAL (5,2)," +
                         "PRIMARY KEY (Id));";
            stm.executeUpdate(sql);
        } catch (SQLException e) {
            // Erro a criar tabela...
            e.printStackTrace();
            throw new NullPointerException(e.getMessage());
        }
    }

    public static PilotosDAO getInstance() {
        if(PilotosDAO.singleton == null){
            PilotosDAO.singleton = new PilotosDAO();
        }
        return PilotosDAO.singleton;
    }

    @Override
    public void clear() {
        try (Connection conn = DriverManager.getConnection(DAOconfig.URL, DAOconfig.USERNAME, DAOconfig.PASSWORD);
             Statement stm = conn.createStatement()) {
            stm.executeUpdate("TRUNCATE Pilotos");
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
                     stm.executeQuery("SELECT Id FROM Pilotos WHERE Id='"+key+"'")) {
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
        Piloto t = (Piloto) value;
        return this.containsKey(t.getId());
    }

    @Override
    public Set<Entry<Integer, Piloto>> entrySet() {
        throw new NullPointerException("public Set<Map.Entry<String,Piloto>> entrySet() not implemented!");
    }

    @Override
    public Piloto get(Object key) {
        Piloto p = null;
        try (Connection conn = DriverManager.getConnection(DAOconfig.URL, DAOconfig.USERNAME, DAOconfig.PASSWORD);
             Statement stm = conn.createStatement();
             ResultSet rs = stm.executeQuery("SELECT * FROM Pilotos WHERE Id='"+key+"'")) {
            if (rs.next()) {  // A chave existe na tabela
                p = new Piloto(rs.getInt("Id"), rs.getString("Name"), rs.getFloat("CTS"), rs.getFloat("SVA"));
            }
        } catch (SQLException e) {
            // Database error!
            e.printStackTrace();
            throw new NullPointerException(e.getMessage());
        }
        return p;
    }

    @Override
    public boolean isEmpty() {
        return this.size() == 0;
    }

    @Override
    public Set<Integer> keySet() {
        Set<Integer> res = new HashSet<>();
        try (Connection conn = DriverManager.getConnection(DAOconfig.URL, DAOconfig.USERNAME, DAOconfig.PASSWORD);
             Statement stm = conn.createStatement();
             ResultSet rs = stm.executeQuery("SELECT Id FROM Pilotos")) {
            while (rs.next()) {
                int idp = rs.getInt("Id");
                res.add(idp);
            }
        } catch (Exception e) {
            // Database error!
            e.printStackTrace();
            throw new NullPointerException(e.getMessage());
        }
        return res;
    }

    @Override
    public Piloto put(Integer key, Piloto p) {
        Piloto res = null;
        try (Connection conn = DriverManager.getConnection(DAOconfig.URL, DAOconfig.USERNAME, DAOconfig.PASSWORD);
             Statement stm = conn.createStatement()) {

            // Actualizar a piloto
            try (PreparedStatement pstm = conn.prepareStatement("INSERT INTO Pilotos (Nome,CTS,SVA) VALUES ('?', '?', '?')")){
                pstm.setString(1,p.getNome());
                pstm.setFloat(2,p.getCts());
                pstm.setFloat(3,p.getSva());
                pstm.executeUpdate(); // acho que faltava isto.
            }

        } catch (SQLException e) {
            // Database error!
            e.printStackTrace();
            throw new NullPointerException(e.getMessage());
        }
        return res;
    }

    @Override
    public void putAll(Map<? extends Integer, ? extends Piloto> m) {
        m.keySet().forEach(i -> this.put(i, m.get(i)));
    }

    @Override
    public Piloto remove(Object key) {
        Piloto p = this.get(key);
        try (Connection conn = DriverManager.getConnection(DAOconfig.URL, DAOconfig.USERNAME, DAOconfig.PASSWORD);
             Statement stm = conn.createStatement()){
             ResultSet rs = stm.executeQuery("DELETE FROM Piloto WHERE Id='"+key+"'");
        } catch (Exception e) {
            // Database error!
            e.printStackTrace();
            throw new NullPointerException(e.getMessage());
        }
        return p;
    }

    @Override
    public int size() {
        int i = 0;
        try (Connection conn = DriverManager.getConnection(DAOconfig.URL, DAOconfig.USERNAME, DAOconfig.PASSWORD);
             Statement stm = conn.createStatement();
             ResultSet rs = stm.executeQuery("SELECT count(*) FROM Pilotos")) {
            if(rs.next()) {
                i = rs.getInt(1);
            }
        }
        catch (Exception e) {
            // Erro a criar tabela...
            e.printStackTrace();
            throw new NullPointerException(e.getMessage());
        }
        return i;
    }

    @Override
    public Collection<Piloto> values() {
        return new HashSet<>(this.keySet().stream().map(key -> this.get(key)).toList());
    }
    
}
