package br.edu.cesarschool.cc.poo.ac.passagem.gui;

import java.awt.EventQueue;
import javax.swing.JFrame;
import javax.swing.JTextField;
import java.awt.BorderLayout;
import javax.swing.JComboBox;
import javax.swing.text.*;

// Importações do mediator de voo
import br.edu.cesarschool.cc.poo.ac.passagem.VooMediator;
import javax.swing.JPanel;
import javax.swing.JLabel;
import com.jgoodies.forms.factories.DefaultComponentFactory;
import javax.swing.JSeparator;
import javax.swing.JRadioButton;
import javax.swing.JRadioButtonMenuItem;
import javax.swing.JButton;

public class TelaGuiBilhete {

    private JFrame frame;
    private JTextField textFieldCpf;

    // Referência ao mediator de voo
    private VooMediator vooMediator;
    private JTextField textFieldPreço;
    private JTextField textFieldPagamentoEmPontos;
    private JTextField textFieldDataeHora;

    /**
     * Launch the application.
     */
    public static void main(String[] args) {
        EventQueue.invokeLater(new Runnable() {
            public void run() {
                try {
                    TelaGuiBilhete window = new TelaGuiBilhete();
                    window.frame.setVisible(true);
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        });
    }

    /**
     * Create the application.
     */
    public TelaGuiBilhete() {
        // Inicializar o mediator de voo
        vooMediator = VooMediator.obterInstancia();
        initialize();
    }

    /**
     * Initialize the contents of the frame.
     */
    private void initialize() {
        frame = new JFrame();
        frame.setBounds(100, 100, 450, 300);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.getContentPane().setLayout(null);
        
        JLabel CPF = DefaultComponentFactory.getInstance().createLabel("CPF:");
        CPF.setBounds(5, 7, 120, 16);
        frame.getContentPane().add(CPF);

        // CAMPO DE CPF
        textFieldCpf = new JTextField();
        CPF.setLabelFor(textFieldCpf);
        textFieldCpf.setBounds(40, 2, 181, 26);
        frame.getContentPane().add(textFieldCpf);
        textFieldCpf.setColumns(10);

        // Aplicar o filtro de documento para o campo de CPF
        ((AbstractDocument) textFieldCpf.getDocument()).setDocumentFilter(new DocumentFilter() {
            @Override
            public void replace(FilterBypass fb, int offset, int length, String text, AttributeSet attrs) throws BadLocationException {
                String newText = fb.getDocument().getText(0, fb.getDocument().getLength()) + text;
                if (newText.matches("\\d{0,3}[\\.]?\\d{0,3}[\\.]?\\d{0,3}-?\\d{0,2}")) {
                    super.replace(fb, offset, length, text, attrs);
                }
            }

            @Override
            public void insertString(FilterBypass fb, int offset, String string, AttributeSet attr) throws BadLocationException {
                String newText = fb.getDocument().getText(0, fb.getDocument().getLength()) + string;
                if (newText.matches("\\d{0,3}[\\.]?\\d{0,3}[\\.]?\\d{0,3}-?\\d{0,2}")) {
                    super.insertString(fb, offset, string, attr);
                }
            }
        });
        
        JLabel CIA = DefaultComponentFactory.getInstance().createLabel("CIA:");
        CIA.setBounds(5, 40, 120, 16);
        frame.getContentPane().add(CIA);

        // SELECIONAR CIA AEREA
        JComboBox<String> comboBoxCompanhia = new JComboBox<String>();
        CIA.setLabelFor(comboBoxCompanhia);
        comboBoxCompanhia.setToolTipText("");
        comboBoxCompanhia.setEditable(false);
        comboBoxCompanhia.setBounds(30, -11, 191, 120);
        frame.getContentPane().add(comboBoxCompanhia);
        
        JLabel VOO = DefaultComponentFactory.getInstance().createLabel("VOO:");
        VOO.setBounds(5, 72, 120, 16);
        frame.getContentPane().add(VOO);
        
        JComboBox comboBoxVoo = new JComboBox();
        VOO.setLabelFor(comboBoxVoo);
        comboBoxVoo.setBounds(48, 72, 125, 16);
        frame.getContentPane().add(comboBoxVoo);
        
        JLabel PREÇO = DefaultComponentFactory.getInstance().createLabel("PREÇO:");
        PREÇO.setBounds(5, 105, 120, 16);
        frame.getContentPane().add(PREÇO);
        
        textFieldPreço = new JTextField();
        PREÇO.setLabelFor(textFieldPreço);
        textFieldPreço.setBounds(58, 100, 92, 21);
        frame.getContentPane().add(textFieldPreço);
        textFieldPreço.setColumns(10);
        
        JLabel PagamentoEmPontos = DefaultComponentFactory.getInstance().createLabel("PAGAMENTO EM PONTOS:");
        PagamentoEmPontos.setBounds(5, 131, 181, 16);
        frame.getContentPane().add(PagamentoEmPontos);
        
        textFieldPagamentoEmPontos = new JTextField();
        PagamentoEmPontos.setLabelFor(textFieldPagamentoEmPontos);
        textFieldPagamentoEmPontos.setBounds(172, 121, 130, 26);
        frame.getContentPane().add(textFieldPagamentoEmPontos);
        textFieldPagamentoEmPontos.setColumns(10);
        
        JLabel DataEHora = DefaultComponentFactory.getInstance().createLabel("DATA E HORA:");
        DataEHora.setBounds(5, 159, 120, 16);
        frame.getContentPane().add(DataEHora);
        
        textFieldDataeHora = new JTextField();
        DataEHora.setLabelFor(textFieldDataeHora);
        textFieldDataeHora.setBounds(102, 154, 130, 26);
        frame.getContentPane().add(textFieldDataeHora);
        textFieldDataeHora.setColumns(10);
        
        JLabel SelecioneOBilhete = DefaultComponentFactory.getInstance().createLabel("Selecione o Bilhete:");
        SelecioneOBilhete.setBounds(4, 187, 146, 16);
        frame.getContentPane().add(SelecioneOBilhete);
        
        JRadioButton rdbtnBilheteComum = new JRadioButton("Bilhete Comum");
        rdbtnBilheteComum.setBounds(5, 204, 141, 23);
        frame.getContentPane().add(rdbtnBilheteComum);
        
        JRadioButton rdbtnNewBilheteVIP = new JRadioButton("Bilhete VIP");
        rdbtnNewBilheteVIP.setBounds(5, 229, 141, 23);
        frame.getContentPane().add(rdbtnNewBilheteVIP);
        
        JButton gerarBilhete = new JButton("Gerar Bilhete");
        gerarBilhete.setBounds(247, 237, 120, 29);
        frame.getContentPane().add(gerarBilhete);
        
        JButton btnNewButton_1 = new JButton("Limpar");
        btnNewButton_1.setBounds(158, 238, 81, 26);
        frame.getContentPane().add(btnNewButton_1);
    }
}
