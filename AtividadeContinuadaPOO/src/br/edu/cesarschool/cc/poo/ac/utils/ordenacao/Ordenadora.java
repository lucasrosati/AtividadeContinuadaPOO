package br.edu.cesarschool.cc.poo.ac.utils.ordenacao;

public class Ordenadora {
    public static void ordenar(Object[] lista, Comparador comp) {
        for (int i = 0; i < lista.length - 1; i++) {
            for (int j = 0; j < lista.length - i - 1; j++) {
                if (comp.comparar(lista[j], lista[j + 1]) > 0) {
                    Object temp = lista[j];
                    lista[j] = lista[j + 1];
                    lista[j + 1] = temp;
                }
            }
        }
    }

    public static void ordenar(Comparavel[] lista) {
        for (int i = 0; i < lista.length - 1; i++) {
            for (int j = 0; j < lista.length - i - 1; j++) {
                if (lista[j].comparar(lista[j + 1]) > 0) {
                    Comparavel temp = lista[j];
                    lista[j] = lista[j + 1];
                    lista[j + 1] = temp;
                }
            }
        }
    }
}
