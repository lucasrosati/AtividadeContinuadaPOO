package br.edu.cesarschool.cc.poo.ac.passagem.gui;

import java.awt.EventQueue;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import javax.swing.*;
import java.text.ParseException;
import javax.swing.text.MaskFormatter;
import java.util.HashSet;

import br.edu.cesarschool.cc.poo.ac.cliente.Cliente;
import br.edu.cesarschool.cc.poo.ac.cliente.ClienteMediator;
import br.edu.cesarschool.cc.poo.ac.passagem.BilheteMediator;
import br.edu.cesarschool.cc.poo.ac.passagem.ResultadoGeracaoBilhete;
import br.edu.cesarschool.cc.poo.ac.passagem.Voo;
import br.edu.cesarschool.cc.poo.ac.passagem.VooMediator;
import br.edu.cesarschool.cc.poo.ac.utils.ValidadorCPF;

public class TelaGuiBilhete {

    private JFrame frame;
    private JFormattedTextField textFieldCpf;
    private JTextField textFieldNome;
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
    private ClienteMediator clienteMediator = ClienteMediator.obterInstancia();
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
        preencherCompanhiasAereas();
        preencherNumerosVoos();
        limparCampos(); // Inicializa a tela no estado desejado
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

        setupCpfField();

        JLabel lblNome = new JLabel("Nome:");
        lblNome.setBounds(10, 45, 80, 25);
        frame.getContentPane().add(lblNome);

        textFieldNome = new JTextField();
        textFieldNome.setBounds(100, 45, 180, 25);
        frame.getContentPane().add(textFieldNome);

        JLabel lblCompanhia = new JLabel("Companhia:");
        lblCompanhia.setBounds(10, 80, 80, 25);
        frame.getContentPane().add(lblCompanhia);

        comboBoxCompanhia = new JComboBox<>();
        comboBoxCompanhia.setBounds(100, 80, 180, 25);
        frame.getContentPane().add(comboBoxCompanhia);

        JLabel lblVoo = new JLabel("Voo:");
        lblVoo.setBounds(10, 115, 80, 25);
        frame.getContentPane().add(lblVoo);

        comboBoxVoo = new JComboBox<>();
        comboBoxVoo.setBounds(100, 115, 180, 25);
        frame.getContentPane().add(comboBoxVoo);

        JLabel lblPreco = new JLabel("Preço:");
        lblPreco.setBounds(10, 150, 80, 25);
        frame.getContentPane().add(lblPreco);

        textFieldPreco = new JTextField();
        textFieldPreco.setBounds(100, 150, 180, 25);
        frame.getContentPane().add(textFieldPreco);

        JLabel lblPagamentoEmPontos = new JLabel("Pagamento em Pontos:");
        lblPagamentoEmPontos.setBounds(10, 185, 180, 25);
        frame.getContentPane().add(lblPagamentoEmPontos);

        textFieldPagamentoEmPontos = new JTextField();
        textFieldPagamentoEmPontos.setBounds(190, 185, 180, 25);
        frame.getContentPane().add(textFieldPagamentoEmPontos);

        JLabel lblDataeHora = new JLabel("Data e Hora:");
        lblDataeHora.setBounds(10, 220, 80, 25);
        frame.getContentPane().add(lblDataeHora);

        setupDataeHoraField();

        grupoBilhetes = new ButtonGroup();

        rdbtnBilheteComum = new JRadioButton("Bilhete Comum");
        rdbtnBilheteComum.setBounds(10, 255, 180, 25);
        grupoBilhetes.add(rdbtnBilheteComum);
        frame.getContentPane().add(rdbtnBilheteComum);

        rdbtnBilheteVip = new JRadioButton("Bilhete VIP");
        rdbtnBilheteVip.setBounds(200, 255, 180, 25);
        grupoBilhetes.add(rdbtnBilheteVip);
        frame.getContentPane().add(rdbtnBilheteVip);

        JLabel lblBonusPontuacao = new JLabel("Bônus Pontuação:");
        lblBonusPontuacao.setBounds(10, 290, 180, 25);
        frame.getContentPane().add(lblBonusPontuacao);

        textFieldBonusPontuacao = new JTextField();
        textFieldBonusPontuacao.setBounds(190, 290, 180, 25);
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

    private void setupCpfField() {
        try {
            MaskFormatter cpfFormatter = new MaskFormatter("###.###.###-##");
            cpfFormatter.setPlaceholderCharacter('_');
            textFieldCpf = new JFormattedTextField(cpfFormatter);
            textFieldCpf.setBounds(100, 10, 180, 25);
            frame.getContentPane().add(textFieldCpf);
        } catch (ParseException e) {
            e.printStackTrace();
        }
    }

    private void setupDataeHoraField() {
        try {
            MaskFormatter dataHoraFormatter = new MaskFormatter("##/##/#### ##:##");
            dataHoraFormatter.setPlaceholderCharacter('_');
            textFieldDataeHora = new JFormattedTextField(dataHoraFormatter);
            textFieldDataeHora.setBounds(100, 220, 180, 25);
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

    private void preencherCompanhiasAereas() {
        Voo[] voos = vooMediator.buscarTodos();
        HashSet<String> companhias = new HashSet<>();
        if (voos != null) {
            for (Voo voo : voos) {
                companhias.add(voo.getCompanhiaAerea());
            }
            for (String cia : companhias) {
                comboBoxCompanhia.addItem(cia);
            }
        }
        if (!companhias.isEmpty()) {
            comboBoxCompanhia.setSelectedIndex(0);
        }
    }

    private void preencherNumerosVoos() {
        Voo[] voos = vooMediator.buscarTodos();
        HashSet<String> numeros = new HashSet<>();
        if (voos != null) {
            for (Voo voo : voos) {
                numeros.add(String.valueOf(voo.getNumeroVoo()));
            }
            for (String num : numeros) {
                comboBoxVoo.addItem(num);
            }
        }
        if (!numeros.isEmpty()) {
            comboBoxVoo.setSelectedIndex(0);
        }
    }

    private void gerarBilhete() {
        try {
            String cpf = textFieldCpf.getText().replace(".", "").replace("-", "").trim();
            if (!ValidadorCPF.isCpfValido(cpf)) {
                JOptionPane.showMessageDialog(frame, "CPF inválido.");
                return;
            }

            Cliente cliente = clienteMediator.buscar(cpf);
            if (cliente == null) {
                String nome = textFieldNome.getText().trim();
                if (nome.isEmpty()) {
                    JOptionPane.showMessageDialog(frame, "Nome do cliente não preenchido.");
                    return;
                }
                cliente = new Cliente(cpf, nome, 0); 
                String resultadoInclusao = clienteMediator.incluir(cliente);
                if (resultadoInclusao != null) {
                    JOptionPane.showMessageDialog(frame, "Erro ao incluir cliente: " + resultadoInclusao);
                    return;
                }
            }

            String dataHoraTexto = textFieldDataeHora.getText().replace('_', ' ').trim();
            LocalDateTime dataHora = LocalDateTime.parse(dataHoraTexto, formatter);
            String companhiaAerea = (String) comboBoxCompanhia.getSelectedItem();
            String numeroVooStr = (String) comboBoxVoo.getSelectedItem();
            int numeroVoo = Integer.parseInt(numeroVooStr);
            double preco = Double.parseDouble(textFieldPreco.getText());
            double pagamentoEmPontos = Double.parseDouble(textFieldPagamentoEmPontos.getText());

            ResultadoGeracaoBilhete resultado;
            if (rdbtnBilheteComum.isSelected()) {
                resultado = bilheteMediator.gerarBilhete(cpf, companhiaAerea, numeroVoo, preco, pagamentoEmPontos, dataHora);
            } else if (rdbtnBilheteVip.isSelected()) {
                double bonusPontuacao = Double.parseDouble(textFieldBonusPontuacao.getText());
                resultado = bilheteMediator.gerarBilheteVip(cpf, companhiaAerea, numeroVoo, preco, pagamentoEmPontos, dataHora, bonusPontuacao);
            } else {
                JOptionPane.showMessageDialog(frame, "Selecione o tipo de bilhete.");
                return;
            }

            if (resultado.getMensagemErro() != null) {
                JOptionPane.showMessageDialog(frame, "Erro ao gerar bilhete: " + resultado.getMensagemErro());
            } else {
                String numeroBilhete = resultado.getBilhete() != null ? resultado.getBilhete().gerarNumero() : resultado.getBilheteVip().gerarNumero();
                double saldoPontos = cliente.getSaldoPontos();
                JOptionPane.showMessageDialog(frame, "Bilhete gerado com sucesso! Número: " + numeroBilhete + ", Saldo de pontos: " + saldoPontos);
                limparCampos();
            }
        } catch (NumberFormatException e) {
            JOptionPane.showMessageDialog(frame, "Erro nos dados numéricos: " + e.getMessage());
        } catch (DateTimeParseException e) {
            JOptionPane.showMessageDialog(frame, "Formato de data e hora inválido. Use o formato dd/MM/yyyy HH:mm");
        }
    }

    private void limparCampos() {
        textFieldCpf.setText("");
        textFieldNome.setText("");
        comboBoxCompanhia.setSelectedIndex(comboBoxCompanhia.getItemCount() > 0 ? 0 : -1);
        comboBoxVoo.setSelectedIndex(comboBoxVoo.getItemCount() > 0 ? 0 : -1);
        textFieldPreco.setText("");
        textFieldPagamentoEmPontos.setText("");
        textFieldDataeHora.setText("");
        textFieldBonusPontuacao.setText("");
        grupoBilhetes.clearSelection();
        rdbtnBilheteComum.setSelected(true); 
        textFieldBonusPontuacao.setEnabled(false);
        textFieldCpf.requestFocusInWindow(); 
    }
}
