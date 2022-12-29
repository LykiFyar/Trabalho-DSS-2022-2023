package data;
//Nesta classe definimos o utilizador, a palavra-passe, o driver e a base de dados
public class DAOconfig {
    static final String USERNAME = "grupo30";                   
    static final String PASSWORD = "Grupo30#pass!";                    
    private static final String DATABASE = "Simulação";         
    //private static final String DRIVER = "jdbc:mariadb";        // Usar para MariaDB
    private static final String DRIVER = "jdbc:mysql";        // Usar para MySQL
    static final String URL = DRIVER+"://localhost:3307/"+DATABASE;
}
