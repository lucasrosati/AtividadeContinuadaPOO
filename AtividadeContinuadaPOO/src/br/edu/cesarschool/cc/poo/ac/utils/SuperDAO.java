package br.edu.cesarschool.cc.poo.ac.utils;

public abstract class SuperDAO<T extends Registro> {
    protected abstract Class<T> obterTipo();
}
