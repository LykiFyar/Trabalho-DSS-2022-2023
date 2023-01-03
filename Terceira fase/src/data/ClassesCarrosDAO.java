package data;

import java.util.Collection;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;
import java.sql.*;

import business.carros.Classe;
import business.carros.classes.*;

public class ClassesCarrosDAO implements Map<String,Classe>{

    private static ClassesCarrosDAO singleton = null;

    private ClassesCarrosDAO() {
        try (Connection conn = DriverManager.getConnection(DAOconfig.URL, DAOconfig.USERNAME, DAOconfig.PASSWORD);
             Statement stm = conn.createStatement()) {
            String sql = "CREATE TABLE IF NOT EXISTS `Simulação`.`ClassesCarros` (" +
                "Id VARCHAR(255) NOT NULL," +
                "minCilindrada INT NOT NULL, " +
                "maxCilindrada INT NOT NULL, " +
                "PRIMARY KEY (Id));";
            stm.executeUpdate(sql);
        }catch(SQLException e){
            e.printStackTrace();
        }
    }

    public static ClassesCarrosDAO getInstance() {
        if(singleton == null){
            singleton = new ClassesCarrosDAO();
        }
        return singleton;
    }

    @Override
    public int size() {
        int i = 0;
        try (Connection conn = DriverManager.getConnection(DAOconfig.URL, DAOconfig.USERNAME, DAOconfig.PASSWORD);
             Statement stm = conn.createStatement();
             ResultSet rs = stm.executeQuery("SELECT count(*) FROM `Simulação`.`ClassesCarros`;")) {
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
    public boolean isEmpty() {
        return this.size() == 0;
    }

    @Override
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

    @Override
    public boolean containsValue(Object value) {
        Classe c = (Classe) value;
        return this.containsKey(c.toString());
    }

    @Override
    public Classe get(Object key) {
        Classe r = null;
        String k = (String) key;
        try (Connection conn = DriverManager.getConnection(DAOconfig.URL, DAOconfig.USERNAME, DAOconfig.PASSWORD);
             Statement stm = conn.createStatement();
             ResultSet rs =
                     stm.executeQuery("SELECT * FROM `Simulação`.`ClassesCarros` WHERE Id='"+key+"'")) {
            if(k.equals("SC")){
                r = new SC(rs.getInt("minCilindrada"), rs.getInt("maxCilindrada"), 0);
            }else if(k.equals("C1")){
                r = new C1(rs.getInt("minCilindrada"), rs.getInt("maxCilindrada"), 0);
            }else if(k.equals("C2")){
                r = new C2(rs.getInt("minCilindrada"), rs.getInt("maxCilindrada"), 0);
            }else if(k.equals("GT")){
                r = new GT(rs.getInt("minCilindrada"), rs.getInt("maxCilindrada"), 0, 0);
            }else if(k.equals("C1H")){
                r = new C1H(rs.getInt("minCilindrada"), rs.getInt("maxCilindrada"), 0, 0);
            }else if(k.equals("C2H")){
                r = new C2H(rs.getInt("minCilindrada"), rs.getInt("maxCilindrada"), 0, 0);
            }else if(k.equals("GTH")){
                r = new GTH(rs.getInt("minCilindrada"), rs.getInt("maxCilindrada"), 0, 0, 0);
            }else{
                throw new RuntimeException("(Linha 104 -> ClassesCarrosDAO.java) Função get do ClassesCarrosDAO: key not in {SC,C1,C2,GT,C1H,C2H,GTH}");
            }
        } catch (SQLException e) {
            // Database error!
            e.printStackTrace();
            throw new NullPointerException(e.getMessage());
        }
        return r;
    }

    @Override
    public Classe put(String key, Classe value) {
        try (Connection conn = DriverManager.getConnection(DAOconfig.URL, DAOconfig.USERNAME, DAOconfig.PASSWORD);
             Statement stm = conn.createStatement()) {
            try (PreparedStatement pstm = conn.prepareStatement("INSERT INTO ClassesCarros (Id,minCilindrada,maxCilindrada) VALUES ('?', '?', '?')")){
                pstm.setString(1, value.toString());
                pstm.setInt(2, value.getMinCilindrada());
                pstm.setInt(3, value.getMaxCilindrada());
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
    public Classe remove(Object key) {
        Classe value = null;
        try (Connection conn = DriverManager.getConnection(DAOconfig.URL, DAOconfig.USERNAME, DAOconfig.PASSWORD);
             Statement stm = conn.createStatement()) {
            try (PreparedStatement pstm = conn.prepareStatement("DELETE FROM ClassesCarross WHERE Id = '?'")){
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
    public void putAll(Map<? extends String, ? extends Classe> m) {
        m.keySet().forEach(i -> this.put(i, m.get(i)));
    }

    @Override
    public void clear() {
        try (Connection conn = DriverManager.getConnection(DAOconfig.URL, DAOconfig.USERNAME, DAOconfig.PASSWORD);
             Statement stm = conn.createStatement()) {
            stm.executeUpdate("TRUNCATE ClassesCarros");
        } catch (SQLException e) {
            // Database error!
            e.printStackTrace();
            throw new NullPointerException(e.getMessage());
        }
    }

    @Override
    public Set<String> keySet() {
        Set<String> res = new HashSet<>();
        try (Connection conn = DriverManager.getConnection(DAOconfig.URL, DAOconfig.USERNAME, DAOconfig.PASSWORD);
             Statement stm = conn.createStatement();
             ResultSet rs = stm.executeQuery("SELECT Id FROM ClassesCarros")) {
            while (rs.next()) {
                String idc = rs.getString("Id");
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
    public Collection<Classe> values() {
        return new HashSet<>(this.keySet().stream().map(key -> this.get(key)).toList());
    }

    @Override
    public Set<Entry<String, Classe>> entrySet() {
        throw new RuntimeException("public Set<Entry<String, Classe>>  entrySet() not implemented");
    }
    
}
