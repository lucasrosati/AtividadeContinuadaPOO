package br.edu.cesarschool.cc.poo.ac.passagem.gui;

import br.edu.cesarschool.cc.poo.ac.passagem.BilheteMediator;
import br.edu.cesarschool.cc.poo.ac.passagem.Voo;
import br.edu.cesarschool.cc.poo.ac.passagem.VooMediator;
import br.edu.cesarschool.cc.poo.ac.passagem.ResultadoGeracaoBilhete;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.List;

public class TelaGuiBilhete extends JFrame {

    private BilheteMediator bilheteMediator;
    private VooMediator vooMediator;

    private JTextField cpfTextField;
    private JComboBox<String> companhiaAereaComboBox;
    private JComboBox<Integer> numeroVooComboBox;
    private JTextField precoTextField;
    private JTextField pagamentoPontosTextField;
    private JTextField dataHoraTextField;
    private JRadioButton bilheteNormalRadioButton;
    private JRadioButton bilheteVipRadioButton;
    private JTextField bonusPontuacaoTextField;

    public TelaGuiBilhete(BilheteMediator bilheteMediator, VooMediator vooMediator) {
        this.bilheteMediator = bilheteMediator;
        this.vooMediator = vooMediator;
        initComponents();
        initListeners();
        initDropdownLists();
        clearFields();
    }

    private void initComponents() {
        // Inicializar os componentes da GUI
    }

    private void initListeners() {
        // Adicionar os listeners para os botões
    }

    private void initDropdownLists() {
        List<Voo> voos = vooMediator.buscarTodos();
        // Preencher os dropdown lists com os voos obtidos
    }

    private void clearFields() {
        // Limpar todos os campos da tela
    }

    private void gerarBilhete() {
        String cpf = cpfTextField.getText();
        String ciaAerea = (String) companhiaAereaComboBox.getSelectedItem();
        int numeroVoo = (int) numeroVooComboBox.getSelectedItem();
        double preco = Double.parseDouble(precoTextField.getText());
        double pagamentoEmPontos = Double.parseDouble(pagamentoPontosTextField.getText());
        LocalDateTime dataHora = LocalDateTime.parse(dataHoraTextField.getText(), DateTimeFormatter.ofPattern("dd/MM/yyyy HH:mm"));

        if (bilheteNormalRadioButton.isSelected()) {
            ResultadoGeracaoBilhete resultado = bilheteMediator.gerarBilhete(cpf, ciaAerea, numeroVoo, preco, pagamentoEmPontos, dataHora);
            if (resultado.getMensagemErro() != null) {
                JOptionPane.showMessageDialog(this, resultado.getMensagemErro(), "Erro ao gerar bilhete", JOptionPane.ERROR_MESSAGE);
            } else {
                String numeroBilhete = resultado.getBilhete() != null ? "Número do bilhete: " + resultado.getBilhete().getBilhete() : "Número do bilhete: (N/A)";
                JOptionPane.showMessageDialog(this, "Bilhete gerado com sucesso!\n" + numeroBilhete +
                        "\nSaldo do cliente em pontos: " + resultado.getBilhete().getCliente().getSaldoPontos(), "Bilhete gerado", JOptionPane.INFORMATION_MESSAGE);
                clearFields();
            }
        } else if (bilheteVipRadioButton.isSelected()) {
            double bonusPontuacao = Double.parseDouble(bonusPontuacaoTextField.getText());
            ResultadoGeracaoBilhete resultado = bilheteMediator.gerarBilheteVip(cpf, ciaAerea, numeroVoo, preco, pagamentoEmPontos, dataHora, bonusPontuacao);
            if (resultado.getMensagemErro() != null) {
                JOptionPane.showMessageDialog(this, resultado.getMensagemErro(), "Erro ao gerar bilhete VIP", JOptionPane.ERROR_MESSAGE);
            } else {
                String numeroBilhete = resultado.getBilheteVip() != null ? "Número do bilhete: " + resultado.getBilheteVip().getNumeroBilhete() : "Número do bilhete: (N/A)";
                JOptionPane.showMessageDialog(this, "Bilhete VIP gerado com sucesso!\n" + numeroBilhete +
                        "\nSaldo do cliente em pontos: " + resultado.getBilheteVip().getCliente().getSaldoPontos(), "Bilhete VIP gerado", JOptionPane.INFORMATION_MESSAGE);
                clearFields();
            }
        } else {
            JOptionPane.showMessageDialog(this, "Selecione o tipo de bilhete (Normal ou VIP)", "Erro", JOptionPane.ERROR_MESSAGE);
        }
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            // Inicializar os mediators
            BilheteMediator bilheteMediator = BilheteMediator.obterInstancia();
            VooMediator vooMediator = VooMediator.obterInstancia();
            TelaGuiBilhete tela = new TelaGuiBilhete(bilheteMediator, vooMediator);
            tela.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
            tela.pack();
            tela.setVisible(true);
        });
    }
}
// corrigir tudo