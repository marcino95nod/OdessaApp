
 
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.LinkedList;
import java.util.List;

import Pracownicy.Stanowisko;

 
public class Datebase {
 
    public static final String DRIVER = "org.sqlite.JDBC";
    public static final String DB_URL = "jdbc:sqlite:biblioteka.db";
 
    private Connection conn;
    private Statement stat;
 
    public Datebase() {
        try {
            Class.forName(Datebase.DRIVER);
        } catch (ClassNotFoundException e) {
            System.err.println("Brak sterownika JDBC");
            e.printStackTrace();
        }
 
        try {
            conn = DriverManager.getConnection(DB_URL);
            stat = conn.createStatement();
        } catch (SQLException e) {
            System.err.println("Problem z otwarciem polaczenia");
            e.printStackTrace();
        }
 
       // createTables();
    }
 
    public boolean createTables()  {
        String createPracownicy = "CREATE TABLE IF NOT EXISTS pracownicy (id_pracownika INTEGER PRIMARY KEY AUTOINCREMENT, imie varchar(255), nazwisko varchar(255), stanowisko varchar(255))";
      //  String createKsiazki = "CREATE TABLE IF NOT EXISTS ksiazki (id_ksiazki INTEGER PRIMARY KEY AUTOINCREMENT, tytul varchar(255), autor varchar(255))";
      //  String createWypozyczenia = "CREATE TABLE IF NOT EXISTS wypozyczenia (id_wypozycz INTEGER PRIMARY KEY AUTOINCREMENT, id_czytelnika int, id_ksiazki int)";
        try {
            stat.execute(createPracownicy);
        //    stat.execute(createKsiazki);
        //    stat.execute(createWypozyczenia);
        } catch (SQLException e) {
            System.err.println("Blad przy tworzeniu tabeli");
            e.printStackTrace();
            return false;
        }
        return true;
    }
 
    public boolean insertPracownik(String imie, String nazwisko, Stanowisko stanowisko) {
        try {
            PreparedStatement prepStmt = conn.prepareStatement(
                    "insert into pracownicy values (NULL, ?, ?, ?);");
            prepStmt.setString(1, imie);
            prepStmt.setString(2, nazwisko);
            prepStmt.setString(3, stanowisko.nazwaStanowiska());
            prepStmt.execute();
        } catch (SQLException e) {
            System.err.println("Blad przy wstawianiu pracownika");
            e.printStackTrace();
            return false;
        }
        return true;
    }
 /*
    public boolean insertKsiazka(String tytul, String autor) {
        try {
            PreparedStatement prepStmt = conn.prepareStatement(
                    "insert into ksiazki values (NULL, ?, ?);");
            prepStmt.setString(1, tytul);
            prepStmt.setString(2, autor);
            prepStmt.execute();
        } catch (SQLException e) {
            System.err.println("Blad przy wypozyczaniu");
            return false;
        }
        return true;
    }
 
    public boolean insertWypozycz(int idCzytelnik, int idKsiazka) {
        try {
            PreparedStatement prepStmt = conn.prepareStatement(
                    "insert into wypozyczenia values (NULL, ?, ?);");
            prepStmt.setInt(1, idCzytelnik);
            prepStmt.setInt(2, idKsiazka);
            prepStmt.execute();
        } catch (SQLException e) {
            System.err.println("Blad przy wypozyczaniu");
            return false;
        }
        return true;
    }
 */
    public List<Pracownicy.Pracownik> selectPracownicy() {
        List<Pracownicy.Pracownik> pracownicy = new LinkedList<Pracownicy.Pracownik>();
        try {
            ResultSet result = stat.executeQuery("SELECT * FROM pracownicy");
            int id;
            String imie, nazwisko;
            Stanowisko stanowisko;
            while(result.next()) {
                id = result.getInt("id_pracownika");
                imie = result.getString("imie");
                nazwisko = result.getString("nazwisko");
                stanowisko = new Stanowisko(result.getString("stanowisko"));
                pracownicy.add(new Pracownicy.Pracownik(id, imie, nazwisko, stanowisko));
            }
        } catch (SQLException e) {
            e.printStackTrace();
            return null;
        }
        return pracownicy;
    }
/* 
    public List<Ksiazka> selectKsiazki() {
        List<Ksiazka> ksiazki = new LinkedList<Ksiazka>();
        try {
            ResultSet result = stat.executeQuery("SELECT * FROM ksiazki");
            int id;
            String tytul, autor;
            while(result.next()) {
                id = result.getInt("id_ksiazki");
                tytul = result.getString("tytul");
                autor = result.getString("autor");
                ksiazki.add(new Ksiazka(id, tytul, autor));
            }
        } catch (SQLException e) {
            e.printStackTrace();
            return null;
        }
        return ksiazki;
    }
 */
    public void closeConnection() {
        try {
            conn.close();
        } catch (SQLException e) {
            System.err.println("Problem z zamknieciem polaczenia");
            e.printStackTrace();
        }
    }
}