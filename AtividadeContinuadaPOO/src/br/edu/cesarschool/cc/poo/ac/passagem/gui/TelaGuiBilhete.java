package br.edu.cesarschool.cc.poo.ac.passagem.gui;

import br.edu.cesarschool.cc.poo.ac.passagem.BilheteMediator;
import br.edu.cesarschool.cc.poo.ac.passagem.Voo;
import br.edu.cesarschool.cc.poo.ac.passagem.VooMediator;
import br.edu.cesarschool.cc.poo.ac.passagem.ResultadoGeracaoBilhete;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
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
    private JTextField dataTextField; // Changed to dataTextField
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
        // Initialize GUI components here
        cpfTextField = new JTextField(15);
        companhiaAereaComboBox = new JComboBox<>();
        numeroVooComboBox = new JComboBox<>();
        precoTextField = new JTextField(10);
        pagamentoPontosTextField = new JTextField(10);
        dataTextField = new JTextField(10); // TextField for date input
        bilheteNormalRadioButton = new JRadioButton("Normal");
        bilheteVipRadioButton = new JRadioButton("VIP");
        bonusPontuacaoTextField = new JTextField(10);
        // Layout and other GUI components initialization...
    }

    private void initListeners() {
        // Add listeners for buttons
    }

    private void initDropdownLists() {
        List<Voo> voos = vooMediator.buscarTodos();
        for (Voo voo : voos) {
            companhiaAereaComboBox.addItem(voo.getCompanhiaAerea());
            numeroVooComboBox.addItem(voo.getNumeroVoo());
        }
    }

    private void clearFields() {
        // Clear all text fields
    }

    private void gerarBilhete() {
        String cpf = cpfTextField.getText();
        String ciaAerea = (String) companhiaAereaComboBox.getSelectedItem();
        int numeroVoo = (int) numeroVooComboBox.getSelectedItem();
        double preco = Double.parseDouble(precoTextField.getText());
        double pagamentoEmPontos = Double.parseDouble(pagamentoPontosTextField.getText());
        LocalDate data = LocalDate.parse(dataTextField.getText(), DateTimeFormatter.ofPattern("dd/MM/yyyy"));
        Voo selectedVoo = vooMediator.buscar(ciaAerea + numeroVoo); // Assuming this method fetches the correct Voo
        LocalTime hora = selectedVoo != null ? selectedVoo.getHora() : LocalTime.MIDNIGHT;
        LocalDateTime dataHora = LocalDateTime.of(data, hora);

        // Continue with the rest of the bilhete generation logic...
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            TelaGuiBilhete tela = new TelaGuiBilhete(BilheteMediator.obterInstancia(), VooMediator.obterInstancia());
            tela.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
            tela.pack();
            tela.setVisible(true);
        });
    }
}
