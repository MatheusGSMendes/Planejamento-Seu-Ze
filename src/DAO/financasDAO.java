/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package DAO;

/**
 *
 * @author gustavo
 */
import java.sql.*;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.GregorianCalendar;
import java.util.List;
import model.financasModel;

public class financasDAO {

    private final Connection connection;

    public financasDAO(Connection connection) {
        this.connection = connection;
    }

    
    public void setFinancas(String nome, double valor, String classificacao, Date datarealizada) throws SQLException{
        String sql = "INSERT INTO seuze(nome, valor, classificacao, datarealizado, datacadastrado)"
                + "values(?,?,?,?,?)";
        Calendar c = new GregorianCalendar();
        
        try(PreparedStatement stmt = connection.prepareStatement(sql)) {
            stmt.setString(1, nome);
            stmt.setDouble(2, valor);
            stmt.setString(3, classificacao);
            stmt.setDate(4, datarealizada);
            stmt.setDate(5, new java.sql.Date(c.getTime().getTime()));
            
            stmt.execute();
               
        }
        
    }
    
    //Consegue todos os registros do banco de dados
    public List<financasModel> getFinancasTotal() {
        String sql = "SELECT * FROM seuze";
        List<financasModel> financas = new ArrayList<>();

        try (PreparedStatement stmt = connection.prepareStatement(sql)) {
            ResultSet rs = stmt.executeQuery();

            while (rs.next()) {
                financas.add(new financasModel(
                        rs.getInt("idValor"),
                        rs.getString("nome"),
                        rs.getString("classificacao"),
                        rs.getDate("dataRealizado"),
                        rs.getDate("dataCadastrado"),
                        rs.getDouble("valor")
                ));
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }

        return financas;
    }

    //Limita os registros a datas fornecidas
    public List<financasModel> getFinancasTimeStamp(Date comeco, Date fim) throws SQLException {
        String sql = "SELECT * FROM seuze WHERE datarealizado BETWEEN ? AND ?";
        List<financasModel> financas = new ArrayList<>();

        try (PreparedStatement stmt = connection.prepareStatement(sql)) {
            stmt.setDate(1, comeco);
            stmt.setDate(2, fim);

            try (ResultSet rs = stmt.executeQuery()) {
                while (rs.next()) {
                    financas.add(new financasModel(
                            rs.getInt("idValor"),
                            rs.getString("nome"),
                            rs.getString("classificacao"),
                            rs.getDate("dataRealizado"),
                            rs.getDate("dataCadastrado"),
                            rs.getDouble("valor")
                    )
                    );
                }
            }

        }
        return financas;
    }

    //Limita os registros ao mes atual
    public List<financasModel> getFinancasMesAtual() throws SQLException {
        String sql = "SELECT * FROM seuze WHERE EXTRACT(MONTH FROM datarealizado) = ?";
        List<financasModel> financas = new ArrayList<>();
        Calendar c = new GregorianCalendar();

        try (PreparedStatement stmt = connection.prepareStatement(sql)) {
            stmt.setInt(1, c.get(Calendar.MONTH) + 1);
            try (ResultSet rs = stmt.executeQuery()) {
                while (rs.next()) {
                    financas.add(new financasModel(
                            rs.getInt("idValor"),
                            rs.getString("nome"),
                            rs.getString("classificacao"),
                            rs.getDate("dataRealizado"),
                            rs.getDate("dataCadastrado"),
                            rs.getDouble("valor")
                    ));
                }

            }
        }

        return financas;
    }
    
    //deleta linha da tabela com base no id do registro
    public void deleteFinanca(int id) throws SQLException{
        String sql = "DELETE FROM seuze WHERE idvalor = ?";
        
        try (PreparedStatement stmt = connection.prepareStatement(sql)) {
            stmt.setInt(1, id);
            
            stmt.execute();
        }
        
        
    }

}
