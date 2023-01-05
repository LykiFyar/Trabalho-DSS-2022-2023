package data;

import java.sql.*;
import java.util.Collection;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;
import java.util.stream.Collectors;

import business.campeonatos.Piloto;

public class PilotosDAO implements Map<String,Piloto> {
    private static PilotosDAO singleton = null;

    private PilotosDAO() {
        try (Connection conn = DriverManager.getConnection(DAOconfig.URL, DAOconfig.USERNAME, DAOconfig.PASSWORD);
             Statement stm = conn.createStatement()) {
            String sql = "CREATE TABLE IF NOT EXISTS `Simulação`.`Pilotos` (" +
                         "Nome VARCHAR(255) NOT NULL," +
                         "CTS DECIMAL (5,2), " +
                         "SVA DECIMAL (5,2), " +
                         "PRIMARY KEY (Nome));";
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
                     stm.executeQuery("SELECT Nome FROM Pilotos WHERE Nome='"+key+"'")) {
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
        return this.containsKey(t.getNome());
    }

    @Override
    public Set<Entry<String, Piloto>> entrySet() {
        throw new NullPointerException("public Set<Map.Entry<String,Piloto>> entrySet() not implemented!");
    }

    @Override
    public Piloto get(Object key) {
        Piloto p = null;
        try (Connection conn = DriverManager.getConnection(DAOconfig.URL, DAOconfig.USERNAME, DAOconfig.PASSWORD);
             Statement stm = conn.createStatement();
             ResultSet rs = stm.executeQuery("SELECT * FROM Pilotos WHERE Nome='"+key+"'")) {
            if (rs.next()) {  // A chave existe na tabela
                p = new Piloto(rs.getString("Nome"), rs.getFloat("CTS"), rs.getFloat("SVA"));
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
    public Set<String> keySet() {
        Set<String> res = new HashSet<>();
        try (Connection conn = DriverManager.getConnection(DAOconfig.URL, DAOconfig.USERNAME, DAOconfig.PASSWORD);
             Statement stm = conn.createStatement();
             ResultSet rs = stm.executeQuery("SELECT Nome FROM Pilotos")) {
            while (rs.next()) {
                String idp = rs.getString("Nome");
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
    public Piloto put(String key, Piloto p) {
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
    public void putAll(Map<? extends String, ? extends Piloto> m) {
        m.keySet().forEach(i -> this.put(i, m.get(i)));
    }

    @Override
    public Piloto remove(Object key) {
        Piloto p = this.get(key);
        try (Connection conn = DriverManager.getConnection(DAOconfig.URL, DAOconfig.USERNAME, DAOconfig.PASSWORD);
             Statement stm = conn.createStatement()){
             ResultSet rs = stm.executeQuery("DELETE FROM Piloto WHERE Nome='"+key+"'");
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
        return (this.keySet().stream().map(this::get).collect(Collectors.toList()));
    }
    
}
