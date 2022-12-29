package data;

import java.sql.*;
import java.util.Collection;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

import business.campeonatos.Piloto;

public class PilotosDAO implements Map<String,Piloto> {
    private static PilotosDAO singleton = null;

    private PilotosDAO() {
        try (Connection conn = DriverManager.getConnection(DAOconfig.URL, DAOconfig.USERNAME, DAOconfig.PASSWORD);
             Statement stm = conn.createStatement()) {
            String sql = "CREATE TABLE IF NOT EXISTS `Simulação`.`Pilotos` (" +
                    "Id INT NOT NULL PRIMARY KEY," +
                    "Nome varchar(255) NOT NULL" +
                    "CTS float(2,1) NOT NULL" +
                    "SVA float(2,1) NOT NULL)";
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
        // TODO Auto-generated method stub
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
        // TODO Auto-generated method stub
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
        // TODO Auto-generated method stub
        Piloto t = (Piloto) value;
        return this.containsKey(t.getId());
    }

    @Override
    public Set<Entry<String, Piloto>> entrySet() {
        // TODO Auto-generated method stub
        throw new NullPointerException("public Set<Map.Entry<String,Piloto>> entrySet() not implemented!");
    }

    @Override
    public Piloto get(Object key) {
        // TODO Auto-generated method stub
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
    public Set<String> keySet() {
        // TODO Auto-generated method stub
        throw new NullPointerException("Not implemented!");
    }

    @Override
    public Piloto put(String key, Piloto p) {
        // TODO Auto-generated method stub
        Piloto res = null;
        try (Connection conn = DriverManager.getConnection(DAOconfig.URL, DAOconfig.USERNAME, DAOconfig.PASSWORD);
             Statement stm = conn.createStatement()) {

            // Actualizar a piloto
            try (PreparedStatement pstm = conn.prepareStatement("INSERT INTO Pilotos VALUES ('?', '?', '?', '?')")){
                pstm.setInt(0,p.getId());
                pstm.setString(1,p.getNome());
                pstm.setFloat(2,p.getCts());
                pstm.setFloat(3,p.getId());
            }

        } catch (SQLException e) {
            // Database error!
            e.printStackTrace();
            throw new NullPointerException(e.getMessage());
        }
        return res;
    }

    @Override
    public void putAll(Map<? extends String, ? extends Piloto> pilotos) {
        // TODO Auto-generated method stub
        for(Piloto p : pilotos.values()) {
            this.put(p.getNome(), p);
        }
    }

    @Override
    public Piloto remove(Object key) {
        // TODO Auto-generated method stub
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
        // TODO Auto-generated method stub
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
        // TODO Auto-generated method stub
        Collection<Piloto> res = new HashSet<>();
        try (Connection conn = DriverManager.getConnection(DAOconfig.URL, DAOconfig.USERNAME, DAOconfig.PASSWORD);
             Statement stm = conn.createStatement();
             ResultSet rs = stm.executeQuery("SELECT Id FROM Pilotos")) {
            while (rs.next()) {
                String idp = rs.getString("Id");
                Piloto p = this.get(idp);
                res.add(p);
            }
        } catch (Exception e) {
            // Database error!
            e.printStackTrace();
            throw new NullPointerException(e.getMessage());
        }
        return res;
    }
    
}
