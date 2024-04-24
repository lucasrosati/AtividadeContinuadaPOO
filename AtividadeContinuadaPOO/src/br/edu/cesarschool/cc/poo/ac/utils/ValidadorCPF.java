package br.edu.cesarschool.cc.poo.ac.utils;

public class ValidadorCPF {

    private ValidadorCPF() {
   
    }

    public static boolean isCpfValido(String cpf) {
        if (cpf == null || !cpf.matches("\\d{11}")) return false;

        int[] pesosCPF = {11, 10, 9, 8, 7, 6, 5, 4, 3, 2};
        int soma = 0;
        int numero;
        int resto;

        
        for (int i = 0; i < 9; i++) {
            numero = (int)(cpf.charAt(i) - '0');
            soma += (numero * pesosCPF[i + 1]);
        }
        resto = soma % 11;
        if (resto < 2) {
            if (cpf.charAt(9) != '0') return false;
        } else {
            if (cpf.charAt(9) != (char)('0' + (11 - resto))) return false;
        }

        soma = 0;
        for (int i = 0; i < 10; i++) {
            numero = (int)(cpf.charAt(i) - '0');
            soma += (numero * pesosCPF[i]);
        }
        resto = soma % 11;
        if (resto < 2) {
            if (cpf.charAt(10) != '0') return false;
        } else {
            if (cpf.charAt(10) != (char)('0' + (11 - resto))) return false;
        }

        return true;
    }
}
