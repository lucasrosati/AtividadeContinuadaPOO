package br.edu.cesarschool.cc.poo.ac.passagem.gui;

import java.awt.EventQueue;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import javax.swing.*;
import java.util.HashSet;
import java.text.ParseException;
import javax.swing.text.MaskFormatter;

import br.edu.cesarschool.cc.poo.ac.passagem.*;
import br.edu.cesarschool.cc.poo.ac.cliente.ClienteMediator;

public class TelaGuiBilhete {

    private JFrame frame;
    private JTextField textFieldCpf;
    private JTextField textFieldPreco;
    private JTextField textFieldPagamentoEmPontos;
    private JFormattedTextField textFieldDataeHora;
    private JTextField textFieldBonusPontuacao;
    private JComboBox<String> comboBoxCompanhia;
    private JComboBox<String> comboBoxVoo;
    private JRadioButton rdbtnBilheteComum;
    private JRadioButton rdbtnBilheteVip;
    private ButtonGroup grupoBilhetes;
    private JButton btnGerarBilhete;
    private JButton btnLimpar;
    private BilheteMediator bilheteMediator = BilheteMediator.obterInstancia();
    private VooMediator vooMediator = VooMediator.obterInstancia();
    private DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy HH:mm");

    public static void main(String[] args) {
        EventQueue.invokeLater(() -> {
            try {
                TelaGuiBilhete window = new TelaGuiBilhete();
                window.frame.setVisible(true);
            } catch (Exception e) {
                e.printStackTrace();
            }
        });
    }

    public TelaGuiBilhete() {
        initialize();
        preencherCompanhiasEAereas();
        preencherNumerosVoos();
    }

    private void initialize() {
        frame = new JFrame();
        frame.setBounds(100, 100, 500, 400);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.getContentPane().setLayout(null);

        setupComponents();
    }

    private void setupComponents() {
        JLabel lblCpf = new JLabel("CPF:");
        lblCpf.setBounds(10, 10, 80, 25);
        frame.getContentPane().add(lblCpf);

        textFieldCpf = new JTextField();
        textFieldCpf.setBounds(100, 10, 180, 25);
        frame.getContentPane().add(textFieldCpf);

        JLabel lblCompanhia = new JLabel("Companhia:");
        lblCompanhia.setBounds(10, 45, 80, 25);
        frame.getContentPane().add(lblCompanhia);

        comboBoxCompanhia = new JComboBox<>();
        comboBoxCompanhia.setBounds(100, 45, 180, 25);
        frame.getContentPane().add(comboBoxCompanhia);

        JLabel lblVoo = new JLabel("Voo:");
        lblVoo.setBounds(10, 80, 80, 25);
        frame.getContentPane().add(lblVoo);

        comboBoxVoo = new JComboBox<>();
        comboBoxVoo.setBounds(100, 80, 180, 25);
        frame.getContentPane().add(comboBoxVoo);

        JLabel lblPreco = new JLabel("Preço:");
        lblPreco.setBounds(10, 115, 80, 25);
        frame.getContentPane().add(lblPreco);

        textFieldPreco = new JTextField();
        textFieldPreco.setBounds(100, 115, 180, 25);
        frame.getContentPane().add(textFieldPreco);

        JLabel lblPagamentoEmPontos = new JLabel("Pagamento em Pontos:");
        lblPagamentoEmPontos.setBounds(10, 150, 180, 25);
        frame.getContentPane().add(lblPagamentoEmPontos);

        textFieldPagamentoEmPontos = new JTextField();
        textFieldPagamentoEmPontos.setBounds(190, 150, 180, 25);
        frame.getContentPane().add(textFieldPagamentoEmPontos);

        JLabel lblDataeHora = new JLabel("Data e Hora:");
        lblDataeHora.setBounds(10, 185, 80, 25);
        frame.getContentPane().add(lblDataeHora);

        setupDataeHoraField();

        grupoBilhetes = new ButtonGroup();

        rdbtnBilheteComum = new JRadioButton("Bilhete Comum");
        rdbtnBilheteComum.setBounds(10, 220, 180, 25);
        grupoBilhetes.add(rdbtnBilheteComum);
        frame.getContentPane().add(rdbtnBilheteComum);

        rdbtnBilheteVip = new JRadioButton("Bilhete VIP");
        rdbtnBilheteVip.setBounds(200, 220, 180, 25);
        grupoBilhetes.add(rdbtnBilheteVip);
        frame.getContentPane().add(rdbtnBilheteVip);

        JLabel lblBonusPontuacao = new JLabel("Bônus Pontuação:");
        lblBonusPontuacao.setBounds(10, 255, 180, 25);
        frame.getContentPane().add(lblBonusPontuacao);

        textFieldBonusPontuacao = new JTextField();
        textFieldBonusPontuacao.setBounds(190, 255, 180, 25);
        textFieldBonusPontuacao.setEnabled(false);
        frame.getContentPane().add(textFieldBonusPontuacao);

        btnGerarBilhete = new JButton("Gerar Bilhete");
        btnGerarBilhete.setBounds(380, 10, 130, 25);
        frame.getContentPane().add(btnGerarBilhete);

        btnLimpar = new JButton("Limpar");
        btnLimpar.setBounds(380, 45, 130, 25);
        frame.getContentPane().add(btnLimpar);

        setupActions();
    }

    private void setupDataeHoraField() {
        try {
            MaskFormatter maskFormatter = new MaskFormatter("##/##/#### ##:##");
            maskFormatter.setPlaceholderCharacter('_');
            textFieldDataeHora = new JFormattedTextField(maskFormatter);
            textFieldDataeHora.setBounds(100, 185, 180, 25);
            frame.getContentPane().add(textFieldDataeHora);
        } catch (ParseException e) {
            e.printStackTrace();
        }
    }

    private void setupActions() {
        btnGerarBilhete.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                gerarBilhete();
            }
        });

        btnLimpar.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                limparCampos();
            }
        });

        rdbtnBilheteVip.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                textFieldBonusPontuacao.setEnabled(true);
            }
        });

        rdbtnBilheteComum.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                textFieldBonusPontuacao.setEnabled(false);
                textFieldBonusPontuacao.setText("");
            }
        });
    }

    private void preencherCompanhiasEAereas() {
        Voo[] voos = vooMediator.buscarTodos();
        if(voos != null) {
            HashSet<String> companhias = new HashSet<>();
            for (Voo voo : voos) {
                companhias.add(voo.getCompanhiaAerea());
            }
            for (String cia : companhias) {
                comboBoxCompanhia.addItem(cia);
            }
        }
    }

    private void preencherNumerosVoos() {
        Voo[] voos = vooMediator.buscarTodos();
        if(voos != null) {
            HashSet<String> numeros = new HashSet<>();
            for (Voo voo : voos) {
                numeros.add(String.valueOf(voo.getNumeroVoo()));
            }
            for (String num : numeros) {
                comboBoxVoo.addItem(num);
            }
        }
    }

    private void gerarBilhete() {
        try {
            String dataHoraTexto = textFieldDataeHora.getText().replace('_', ' ').trim();
            LocalDateTime dataHora = LocalDateTime.parse(dataHoraTexto, formatter);
            String cpf = textFieldCpf.getText();
            String companhiaAerea = (String) comboBoxCompanhia.getSelectedItem();
            String numeroVooStr = (String) comboBoxVoo.getSelectedItem();
            int numeroVoo = Integer.parseInt(numeroVooStr);
            double preco = Double.parseDouble(textFieldPreco.getText());
            double pagamentoEmPontos = Double.parseDouble(textFieldPagamentoEmPontos.getText());
            if (rdbtnBilheteComum.isSelected()) {
                ResultadoGeracaoBilhete resultado = bilheteMediator.gerarBilhete(cpf, companhiaAerea, numeroVoo, preco, pagamentoEmPontos, dataHora);
                processarResultadoGeracaoBilhete(resultado);
            } else if (rdbtnBilheteVip.isSelected()) {
                double bonusPontuacao = Double.parseDouble(textFieldBonusPontuacao.getText());
                ResultadoGeracaoBilhete resultado = bilheteMediator.gerarBilheteVip(cpf, companhiaAerea, numeroVoo, preco, pagamentoEmPontos, dataHora, bonusPontuacao);
                processarResultadoGeracaoBilhete(resultado);
            }
        } catch (DateTimeParseException e) {
            JOptionPane.showMessageDialog(frame, "Formato de data e hora inválido. Use o formato dd/MM/yyyy HH:mm");
        }
    }

    private void processarResultadoGeracaoBilhete(ResultadoGeracaoBilhete resultado) {
        if (resultado.getMensagemErro() == null) {
            JOptionPane.showMessageDialog(frame, "Bilhete gerado com sucesso!");
            limparCampos();
        } else {
            JOptionPane.showMessageDialog(frame, "Erro: " + resultado.getMensagemErro());
        }
    }

    private void limparCampos() {
        textFieldCpf.setText("");
        comboBoxCompanhia.setSelectedIndex(0);
        comboBoxVoo.setSelectedIndex(0);
        textFieldPreco.setText("");
        textFieldPagamentoEmPontos.setText("");
        textFieldDataeHora.setText("");
        textFieldBonusPontuacao.setText("");
        grupoBilhetes.clearSelection();
        textFieldBonusPontuacao.setEnabled(false);
    }
}
