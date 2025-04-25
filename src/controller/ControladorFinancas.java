package controller;

import DAO.financasDAO;
import javax.swing.JOptionPane;
import model.financasModel;
import view.TelaFinanancas;
import java.sql.*;

public class ControladorFinancas {

    private TelaFinanancas tela;
    private financasDAO dao;

    //constructor
    public ControladorFinancas(TelaFinanancas tela) {
        this.tela = tela;
        this.dao = new financasDAO();
    }

    public ControladorFinancas() {
        
    }
    
    //método de exclusão
    public void excluirFinanca(financasModel financa){
            try {
            dao.deleteFinanca(financa.getIdValor());
            tela.listagemTabela();
                JOptionPane.showMessageDialog(tela, "Item removido com sucesso!");
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(tela, "Erro ao deletar: " + e.getMessage());
        }
    }
}
