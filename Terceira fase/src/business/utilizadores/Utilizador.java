package business.utilizadores;

public class Utilizador {
    private String username;   
    private String password;
    private int pontuacaoGeral;
    private boolean isAdmin;

    public Utilizador(String username, String password){
        this.username = username;
        this.password = password;
        this.pontuacaoGeral = 0;
        this.isAdmin = false;
    }

    public Utilizador(String username, String password, int pg, boolean isAdmin){
        this.username = username;
        this.password = password;
        this.pontuacaoGeral = pg;
        this.isAdmin = isAdmin;
    }

    private Utilizador(){}

    /**
     * @return String return the username
     */
    public String getUsername() {
        return username;
    }

    /**
     * @param username the username to set
     */
    public void setUsername(String username) {
        this.username = username;
    }

    /**
     * @return String return the password
     */
    public String getPassword() {
        return password;
    }

    /**
     * @param password the password to set
     */
    public void setPassword(String password) {
        this.password = password;
    }

    /**
     * @return int return the pontuacaoGeral
     */
    public int getPontuacaoGeral() {
        return pontuacaoGeral;
    }

    /**
     * @param pontuacaoGeral the pontuacaoGeral to set
     */
    public void setPontuacaoGeral(int pontuacaoGeral) {
        this.pontuacaoGeral = pontuacaoGeral;
    }

    /**
     * @return boolean return the isAdmin
     */
    public boolean isAdmin() {
        return isAdmin;
    }

    /**
     * @param isAdmin the isAdmin to set
     */
    public void setIsAdmin(boolean isAdmin) {
        this.isAdmin = isAdmin;
    }
}