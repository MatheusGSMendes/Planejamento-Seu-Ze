/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JFrame.java to edit this template
 */
package view;

import DAO.financasDAO;
import java.sql.Date;
import java.sql.SQLException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;
import model.financasModel;

public class TelaFinanancas extends javax.swing.JFrame {

    public TelaFinanancas() {

        initComponents();
        escondeSoma();
        this.listagemTabela();
    }

    private financasDAO daoFinancas = new financasDAO();//Objeto utilizado para acessar os metodos da classe financasDAO
    private int opcao = 1;//É utilizado para definir se o registro foi um gasto ou um ganho
    private double recebidos = 0;//A soma dos registros em que o usuário RECEBEU dinheiro
    private double gastos = 0;//A soma dos registros em que o usuário GASTOU dinheiro
    private double diferenca = 0;//A DIFERENCA entre o dinheiro gasto e recebido

    //sobrescrita: exibe na tabela uma lista fornecida
    public void listagemTabela(List<financasModel> financas) {
        try {
            apagaRegistros();
            DefaultTableModel model = (DefaultTableModel) jTBRegistros.getModel();
            model.setRowCount(0); // Limpa a tabela antes de recarregar os dados

            escondeID();
            if(!financas.isEmpty()){
            for (financasModel financa : financas) {
                model.addRow(new Object[]{
                    financa.getNome(),
                    financa.getClassificacao(),
                    financa.getValor(),
                    financa.getDataRealizado(),
                    financa.getDataCadastrado(),
                    financa.getIdValor()

                });
                somaDosRegistros(financa.getValor());
            }

            exibeSoma();
            }else{
                escondeSoma();
            }
        } catch (NullPointerException ex) {
            JOptionPane.showMessageDialog(null, "Nenhum registro encontrado");
            escondeSoma();
        }

    }

    //Exibe na tabela todos os registros do banco de dados
    public void listagemTabela() {
        try {
            apagaRegistros();
            DefaultTableModel model = (DefaultTableModel) jTBRegistros.getModel();
            model.setRowCount(0); // Limpa a tabela antes de recarregar os dados

            escondeID();

            List<financasModel> financas = daoFinancas.getFinancasTotal();
            if(!financas.isEmpty()){
            for (financasModel financa : financas) {
                model.addRow(new Object[]{
                    financa.getNome(),
                    financa.getClassificacao(),
                    financa.getValor(),
                    financa.getDataRealizado(),
                    financa.getDataCadastrado(),
                    financa.getIdValor()
                });
                somaDosRegistros(financa.getValor());
            }

            exibeSoma();
            }else{
                escondeSoma();
            }
            
        } catch (NullPointerException ex) {
            JOptionPane.showMessageDialog(null, "Nenhum registro encontrado");
            escondeSoma();
        }

    }

    //Mostra os labels da soma dos registros recebidos, gastos e da diferenca
    private void exibeSoma() {
        jLRecebidos.setText("Recebido: " + recebidos);
        jLRecebidos.setVisible(true);
        jLGastos.setText("Gastos: " + gastos);
        jLGastos.setVisible(true);
        jLDiferenca.setText("Diferenca: " + diferenca);
        jLDiferenca.setVisible(true);
    }

    //Esconde os labels da soma dos registros recebidos, gastos e da diferenca
    private void escondeSoma() {
        jLRecebidos.setVisible(false);
        jLGastos.setVisible(false);
        jLDiferenca.setVisible(false);
    }

    //Faz a soma dos registros para exibir para o usuario
    private void somaDosRegistros(double valor) {
        if (valor > 0) {
            recebidos += valor;
        } else {
            gastos += Math.abs(valor);
        }

        diferenca += valor;
    }

    //Torna os valores da soma dos registros recebidos, gastos e da diferenca
    private void apagaRegistros() {
        this.recebidos = 0;
        this.gastos = 0;
        this.diferenca = 0;
    }

    //esconde a coluna do atributo valorID na tabela, que será usado porém não é necessário que o cliente veja
    private void escondeID() {
        jTBRegistros.getColumnModel().getColumn(5).setMinWidth(0);
        jTBRegistros.getColumnModel().getColumn(5).setMaxWidth(0);
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jPanel1 = new javax.swing.JPanel();
        jLabel1 = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();
        txtNome = new javax.swing.JTextField();
        jScrollPane1 = new javax.swing.JScrollPane();
        jTBRegistros = new javax.swing.JTable();
        jLabel3 = new javax.swing.JLabel();
        jLabel4 = new javax.swing.JLabel();
        jLabel5 = new javax.swing.JLabel();
        buttonMesAtual = new javax.swing.JButton();
        txtClassificacao = new javax.swing.JTextField();
        jLabel6 = new javax.swing.JLabel();
        txtValor = new javax.swing.JTextField();
        jLabel7 = new javax.swing.JLabel();
        buttonCadastrar = new javax.swing.JButton();
        buttonExcluir = new javax.swing.JButton();
        jLRecebidos = new javax.swing.JLabel();
        jLGastos = new javax.swing.JLabel();
        jLDiferenca = new javax.swing.JLabel();
        jButton1 = new javax.swing.JButton();
        jButton2 = new javax.swing.JButton();
        jFTFdataentrada = new javax.swing.JFormattedTextField();
        txtDataInicial = new javax.swing.JFormattedTextField();
        txtDataFinal = new javax.swing.JFormattedTextField();
        jBvoltar = new javax.swing.JButton();
        buttonFiltrar = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        jPanel1.setBackground(new java.awt.Color(255, 255, 255));

        jLabel1.setBackground(new java.awt.Color(0, 0, 0));
        jLabel1.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        jLabel1.setText("Nome: ");

        jLabel2.setFont(new java.awt.Font("Segoe UI Black", 0, 24)); // NOI18N
        jLabel2.setText("Finanças Anual Seu José");

        txtNome.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtNomeActionPerformed(evt);
            }
        });

        jTBRegistros.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null, null, null},
                {null, null, null, null, null, null},
                {null, null, null, null, null, null},
                {null, null, null, null, null, null}
            },
            new String [] {
                "Nome", "Classificação", "Valor", "Data", "Cadastro", "idValor"
            }
        ) {
            boolean[] canEdit = new boolean [] {
                false, false, false, false, false, false
            };

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        jScrollPane1.setViewportView(jTBRegistros);
        if (jTBRegistros.getColumnModel().getColumnCount() > 0) {
            jTBRegistros.getColumnModel().getColumn(5).setResizable(false);
            jTBRegistros.getColumnModel().getColumn(5).setPreferredWidth(0);
        }

        jLabel3.setBackground(new java.awt.Color(0, 0, 0));
        jLabel3.setFont(new java.awt.Font("Segoe UI", 2, 14)); // NOI18N
        jLabel3.setText("De:");

        jLabel4.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        jLabel4.setText("Até:");

        jLabel5.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        jLabel5.setText("Classificação:");

        buttonMesAtual.setBackground(new java.awt.Color(51, 255, 51));
        buttonMesAtual.setText("Mês Atual");
        buttonMesAtual.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                buttonMesAtualActionPerformed(evt);
            }
        });

        jLabel6.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        jLabel6.setText("Valor:");

        txtValor.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtValorActionPerformed(evt);
            }
        });

        jLabel7.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        jLabel7.setText("Data Entrada: ");

        buttonCadastrar.setBackground(new java.awt.Color(51, 51, 255));
        buttonCadastrar.setText("CADASTRAR");
        buttonCadastrar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                buttonCadastrarActionPerformed(evt);
            }
        });

        buttonExcluir.setText("Excluir");
        buttonExcluir.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                buttonExcluirActionPerformed(evt);
            }
        });

        jLRecebidos.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N

        jLGastos.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N

        jLDiferenca.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N

        jButton1.setBackground(new java.awt.Color(51, 255, 51));
        jButton1.setText("GANHO+");
        jButton1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton1ActionPerformed(evt);
            }
        });

        jButton2.setBackground(new java.awt.Color(255, 51, 51));
        jButton2.setText("GASTO-");
        jButton2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton2ActionPerformed(evt);
            }
        });

        try {
            jFTFdataentrada.setFormatterFactory(new javax.swing.text.DefaultFormatterFactory(new javax.swing.text.MaskFormatter("##/##/####")));
        } catch (java.text.ParseException ex) {
            ex.printStackTrace();
        }
        jFTFdataentrada.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jFTFdataentradaActionPerformed(evt);
            }
        });

        try {
            txtDataInicial.setFormatterFactory(new javax.swing.text.DefaultFormatterFactory(new javax.swing.text.MaskFormatter("##/##/####")));
        } catch (java.text.ParseException ex) {
            ex.printStackTrace();
        }
        txtDataInicial.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtDataInicialActionPerformed(evt);
            }
        });

        try {
            txtDataFinal.setFormatterFactory(new javax.swing.text.DefaultFormatterFactory(new javax.swing.text.MaskFormatter("##/##/####")));
        } catch (java.text.ParseException ex) {
            ex.printStackTrace();
        }
        txtDataFinal.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtDataFinalActionPerformed(evt);
            }
        });

        jBvoltar.setText("Refazer");
        jBvoltar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jBvoltarActionPerformed(evt);
            }
        });

        buttonFiltrar.setBackground(new java.awt.Color(0, 0, 153));
        buttonFiltrar.setFont(new java.awt.Font("Segoe UI Black", 0, 12)); // NOI18N
        buttonFiltrar.setForeground(new java.awt.Color(255, 255, 255));
        buttonFiltrar.setText("FILTRAR");
        buttonFiltrar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                buttonFiltrarActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(jLabel2)
                .addGap(216, 216, 216))
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addContainerGap()
                        .addComponent(jButton1, javax.swing.GroupLayout.PREFERRED_SIZE, 81, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 53, Short.MAX_VALUE)
                        .addComponent(jButton2, javax.swing.GroupLayout.PREFERRED_SIZE, 86, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18))
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jPanel1Layout.createSequentialGroup()
                                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                        .addComponent(jLabel5)
                                        .addComponent(jLabel1)
                                        .addComponent(jLabel6))
                                    .addComponent(jLabel7, javax.swing.GroupLayout.Alignment.TRAILING))
                                .addGap(16, 16, 16)
                                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(jFTFdataentrada, javax.swing.GroupLayout.PREFERRED_SIZE, 95, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                                        .addComponent(txtNome, javax.swing.GroupLayout.PREFERRED_SIZE, 93, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addComponent(txtClassificacao, javax.swing.GroupLayout.PREFERRED_SIZE, 93, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addComponent(txtValor, javax.swing.GroupLayout.PREFERRED_SIZE, 93, javax.swing.GroupLayout.PREFERRED_SIZE))))
                            .addGroup(jPanel1Layout.createSequentialGroup()
                                .addGap(53, 53, 53)
                                .addComponent(buttonCadastrar, javax.swing.GroupLayout.PREFERRED_SIZE, 124, javax.swing.GroupLayout.PREFERRED_SIZE)))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 46, Short.MAX_VALUE)))
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addComponent(jLRecebidos, javax.swing.GroupLayout.PREFERRED_SIZE, 128, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18)
                        .addComponent(jLGastos, javax.swing.GroupLayout.PREFERRED_SIZE, 140, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18)
                        .addComponent(jLDiferenca, javax.swing.GroupLayout.PREFERRED_SIZE, 148, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addComponent(buttonMesAtual)
                        .addGap(45, 45, 45)
                        .addComponent(jLabel3)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(txtDataInicial, javax.swing.GroupLayout.PREFERRED_SIZE, 86, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(52, 52, 52)
                        .addComponent(jLabel4)
                        .addGap(18, 18, 18)
                        .addComponent(txtDataFinal, javax.swing.GroupLayout.PREFERRED_SIZE, 87, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(buttonFiltrar)
                    .addComponent(buttonExcluir)
                    .addComponent(jBvoltar))
                .addContainerGap(41, Short.MAX_VALUE))
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addComponent(jLabel2, javax.swing.GroupLayout.PREFERRED_SIZE, 56, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel3, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(jLabel4)
                            .addComponent(txtDataInicial, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(txtDataFinal, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(buttonFiltrar))
                        .addGap(15, 15, 15))
                    .addComponent(buttonMesAtual))
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                        .addGroup(jPanel1Layout.createSequentialGroup()
                            .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                .addComponent(jLabel1, javax.swing.GroupLayout.PREFERRED_SIZE, 28, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addComponent(txtNome, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addGap(23, 23, 23)
                            .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                .addComponent(jLabel5)
                                .addComponent(txtClassificacao, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addGap(31, 31, 31)
                            .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                .addComponent(jLabel6)
                                .addComponent(txtValor, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addGap(33, 33, 33)
                            .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                .addComponent(jLabel7)
                                .addComponent(jFTFdataentrada, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addGap(58, 58, 58)
                            .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                .addComponent(jButton1, javax.swing.GroupLayout.PREFERRED_SIZE, 34, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addComponent(jButton2, javax.swing.GroupLayout.PREFERRED_SIZE, 34, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addGap(74, 74, 74)
                            .addComponent(buttonCadastrar, javax.swing.GroupLayout.PREFERRED_SIZE, 43, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGap(166, 166, 166)
                        .addComponent(buttonExcluir)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jBvoltar)))
                .addGap(18, 18, 18)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLDiferenca)
                    .addComponent(jLGastos)
                    .addComponent(jLRecebidos, javax.swing.GroupLayout.PREFERRED_SIZE, 24, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap())
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 0, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 61, Short.MAX_VALUE))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    //exibe os registros do mes atual na tabela
    private void buttonMesAtualActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_buttonMesAtualActionPerformed
        try {
            financasDAO f = new financasDAO();

            listagemTabela(f.getFinancasMesAtual());

        } catch (SQLException ex) {
            Logger.getLogger(TelaFinanancas.class.getName()).log(Level.SEVERE, null, ex);
        }

    }//GEN-LAST:event_buttonMesAtualActionPerformed

    //Exclui um registro da tabela
    private void buttonExcluirActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_buttonExcluirActionPerformed
        int row = jTBRegistros.getSelectedRow();
        //caso não haja um registro selecionado, exibe uma mensagem
        if (row == -1) {
            JOptionPane.showMessageDialog(this, "Selecione uma informação para deletar!");
            return;
        }

        //pede confirmação do usuário para excluir o registro selecionado
        int confirm = JOptionPane.showConfirmDialog(this, "Tem certeza que deseja deletar essa informação?", "Confirmar", JOptionPane.YES_NO_OPTION);

        if (confirm == JOptionPane.YES_OPTION) {

            try {
                daoFinancas.deleteFinanca((int) jTBRegistros.getValueAt(row, 5));
            } catch (SQLException ex) {
                Logger.getLogger(TelaFinanancas.class.getName()).log(Level.SEVERE, null, ex);
            }

            listagemTabela();

        }

    }//GEN-LAST:event_buttonExcluirActionPerformed

    //Botão para cadastrar um novo registro na tabela
    private void buttonCadastrarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_buttonCadastrarActionPerformed
        SimpleDateFormat formato = new SimpleDateFormat("dd/MM/yyyy");
        java.util.Date data = null;

        try {
            financasModel f = new financasModel();

            f.setNome(txtNome.getText());
            f.setClassificacao(txtClassificacao.getText());
            f.setValor((Double.parseDouble(txtValor.getText())) * opcao);
            f.setDataRealizado(new Date(formato.parse(jFTFdataentrada.getText()).getTime()));
            daoFinancas.setFinancas(f);

            JOptionPane.showMessageDialog(this, "Informação adicionada com sucesso!");
            listagemTabela(); // Atualiza a tabela após adicionar

        } catch (SQLException ex) {
            Logger.getLogger(TelaFinanancas.class.getName()).log(Level.SEVERE, null, ex);
        } catch (ParseException ex) {
            Logger.getLogger(TelaFinanancas.class.getName()).log(Level.SEVERE, null, ex);
        }

    }//GEN-LAST:event_buttonCadastrarActionPerformed

    private void txtValorActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtValorActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txtValorActionPerformed

    private void txtDataInicialActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtDataInicialActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txtDataInicialActionPerformed

    //Botão para retornar o ultimo registro apagado para a tabela
    private void jBvoltarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jBvoltarActionPerformed
        try {
            daoFinancas.refazerExclusao();
            listagemTabela();
            // TODO add your handling code here:
        } catch (SQLException ex) {
            Logger.getLogger(TelaFinanancas.class.getName()).log(Level.SEVERE, null, ex);
        }
    }//GEN-LAST:event_jBvoltarActionPerformed

    private void txtNomeActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtNomeActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txtNomeActionPerformed

    private void jFTFdataentradaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jFTFdataentradaActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_jFTFdataentradaActionPerformed

    //define opcao como 1
    private void jButton1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton1ActionPerformed
        // TODO add your handling code here:
        opcao = 1;
    }//GEN-LAST:event_jButton1ActionPerformed

    private void buttonFiltrarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_buttonFiltrarActionPerformed
        try {
            // Primeiro, você pega as datas dos JTextFields
            String dataInicialStr = txtDataInicial.getText();
            String dataFinalStr = txtDataFinal.getText();

            // Converte para java.sql.Date
            SimpleDateFormat formato = new SimpleDateFormat("dd/MM/yyyy"); // ajuste o formato se necessário
            java.util.Date dataInicialUtil = formato.parse(dataInicialStr);
            java.util.Date dataFinalUtil = formato.parse(dataFinalStr);
            java.sql.Date dataInicial = new java.sql.Date(dataInicialUtil.getTime());
            java.sql.Date dataFinal = new java.sql.Date(dataFinalUtil.getTime());

            // Agora chama o DAO para pegar os registros filtrados
            financasDAO dao = new financasDAO(); // Certifique-se de que seu DAO esteja criado corretamente
            List<financasModel> financasFiltradas = dao.getFinancasTimeStamp(dataInicial, dataFinal);

            if (financasFiltradas.isEmpty()) {
                listagemTabela();

                JOptionPane.showMessageDialog(null, "nenhum registro encontrado");

            } else {

                // Atualiza a tabela usando o método listagemTabela
                listagemTabela(financasFiltradas);
            }
        } catch (Exception ex) {
            ex.printStackTrace();
            JOptionPane.showMessageDialog(null, "Tente uma data válida. Erro ao filtrar dados: " + ex.getMessage());
        }
    }//GEN-LAST:event_buttonFiltrarActionPerformed

    private void jButton2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton2ActionPerformed
        // TODO add your handling code here:
        opcao = -1;
    }//GEN-LAST:event_jButton2ActionPerformed

    private void txtDataFinalActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtDataFinalActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txtDataFinalActionPerformed

    /**
     * @param args the command line arguments
     */
    public static void main(String args[]) {
        /* Set the Nimbus look and feel */
        //<editor-fold defaultstate="collapsed" desc=" Look and feel setting code (optional) ">
        /* If Nimbus (introduced in Java SE 6) is not available, stay with the default look and feel.
         * For details see http://download.oracle.com/javase/tutorial/uiswing/lookandfeel/plaf.html 
         */
        try {
            for (javax.swing.UIManager.LookAndFeelInfo info : javax.swing.UIManager.getInstalledLookAndFeels()) {
                if ("Nimbus".equals(info.getName())) {
                    javax.swing.UIManager.setLookAndFeel(info.getClassName());
                    break;
                }
            }
        } catch (ClassNotFoundException ex) {
            java.util.logging.Logger.getLogger(TelaFinanancas.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(TelaFinanancas.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(TelaFinanancas.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(TelaFinanancas.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new TelaFinanancas().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton buttonCadastrar;
    private javax.swing.JButton buttonExcluir;
    private javax.swing.JButton buttonFiltrar;
    private javax.swing.JButton buttonMesAtual;
    private javax.swing.JButton jButton1;
    private javax.swing.JButton jButton2;
    private javax.swing.JButton jBvoltar;
    private javax.swing.JFormattedTextField jFTFdataentrada;
    private javax.swing.JLabel jLDiferenca;
    private javax.swing.JLabel jLGastos;
    private javax.swing.JLabel jLRecebidos;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JTable jTBRegistros;
    private javax.swing.JTextField txtClassificacao;
    private javax.swing.JFormattedTextField txtDataFinal;
    private javax.swing.JFormattedTextField txtDataInicial;
    private javax.swing.JTextField txtNome;
    private javax.swing.JTextField txtValor;
    // End of variables declaration//GEN-END:variables
}
