package DAO;

import connectionFactory.Conexao;
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

    public financasDAO() {
        this.connection = Conexao.getConnection();
    }

    public void setFinancas(String nome, double valor, String classificacao, Date datarealizada) throws SQLException {
        String sql = "INSERT INTO seuze(nome, valor, classificacao, datarealizado, datacadastrado)"
                + "values(?,?,?,?,?)";
        Calendar c = new GregorianCalendar();

        try (PreparedStatement stmt = connection.prepareStatement(sql)) {
            stmt.setString(1, nome);
            stmt.setDouble(2, valor);
            stmt.setString(3, classificacao);
            stmt.setDate(4, datarealizada);
            stmt.setDate(5, new java.sql.Date(c.getTime().getTime()));

            stmt.execute();

        }

    }

    public void setFinancas(financasModel f) throws SQLException {
        String sql = "INSERT INTO seuze(nome, valor, classificacao, datarealizado, datacadastrado)"
                + "values(?,?,?,?,?)";
        Calendar c = new GregorianCalendar();

        try (PreparedStatement stmt = connection.prepareStatement(sql)) {
            stmt.setString(1, f.getNome());
            stmt.setDouble(2, f.getValor());
            stmt.setString(3, f.getClassificacao());
            stmt.setDate(4, f.getDataRealizado());
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
        String sql = "SELECT * FROM seuze WHERE EXTRACT(MONTH FROM datarealizado) = ? and EXTRACT(YEAR FROM datarealizado) = ?";
        List<financasModel> financas = new ArrayList<>();
        Calendar c = new GregorianCalendar();

        try (PreparedStatement stmt = connection.prepareStatement(sql)) {
            stmt.setInt(1, c.get(Calendar.MONTH) + 1);
            stmt.setInt(2, c.get(Calendar.YEAR));
            
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

    //busca no banco pelo id
    private financasModel getFinancasAtID(int id) throws SQLException {
        String sql = "SELECT * FROM seuze WHERE idValor = ?";
        financasModel f;
        try (PreparedStatement stmt = connection.prepareStatement(sql)) {
            stmt.setInt(1, id);

            try (ResultSet rs = stmt.executeQuery()) {
                rs.next();
                f = new financasModel(
                        rs.getInt("idValor"),
                        rs.getString("nome"),
                        rs.getString("classificacao"),
                        rs.getDate("dataRealizado"),
                        rs.getDate("dataCadastrado"),
                        rs.getDouble("valor")
                );
            }
        }
        return f;
    }

    //deleta linha da tabela com base no id do registro
    public void deleteFinanca(int id) throws SQLException {
        
        //Armazena o registro antes de exclui-lo
        financasModel f = getFinancasAtID(id);
        
        
        //Executa a query de DELETE pelo id fornecido
        String sql = "DELETE FROM seuze WHERE idvalor = ?";

        try (PreparedStatement stmt = connection.prepareStatement(sql)) {
            stmt.setInt(1, id);
            stmt.execute();
        }

        
        //Armazena no banco de backup
        sql = "INSERT INTO refazerexclusao (nome, classificacao, dataRealizado, dataCadastrado, valor, idValor)"
                + "VALUES (?,?,?,?,?,?);";

        try (PreparedStatement stmt = connection.prepareStatement(sql)) {
            stmt.setString(1, f.getNome());
            stmt.setString(2, f.getClassificacao());
            stmt.setDate(3, f.getDataRealizado());
            stmt.setDate(4, f.getDataCadastrado());
            stmt.setDouble(5, f.getValor());
            stmt.setInt(6, f.getIdValor());

            stmt.execute();
        }

    }

    public void refazerExclusao() throws SQLException {
        String sql = "SELECT * FROM refazerexclusao ORDER BY ordemdeadicao DESC LIMIT 1";
        financasModel f = new financasModel();
        try (PreparedStatement stmt = connection.prepareStatement(sql)) {
            ResultSet rs = stmt.executeQuery();
            while (rs.next()) {
                f.setIdValor(rs.getInt("idValor"));
                f.setNome(rs.getString("nome"));
                f.setClassificacao(rs.getString("classificacao"));
                f.setDataRealizado(rs.getDate("dataRealizado"));
                f.setDataCadastrado(rs.getDate("dataCadastrado"));
                f.setValor(rs.getDouble("valor"));
            }

        }

        sql = "INSERT INTO seuze (nome, classificacao, dataRealizado, dataCadastrado, valor, idValor)"
                + "VALUES (?,?,?,?,?,?);"
                + "DELETE FROM refazerexclusao WHERE idValor = ?";

        try (PreparedStatement stmt = connection.prepareStatement(sql)) {
            stmt.setString(1, f.getNome());
            stmt.setString(2, f.getClassificacao());
            stmt.setDate(3, f.getDataRealizado());
            stmt.setDate(4, f.getDataCadastrado());
            stmt.setDouble(5, f.getValor());
            stmt.setInt(6, f.getIdValor());
            stmt.setInt(7, f.getIdValor());

            stmt.execute();
        }

    }

}
