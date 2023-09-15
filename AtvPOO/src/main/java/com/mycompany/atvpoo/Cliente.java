package com.mycompany.atvpoo;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Scanner;
import java.util.HashMap;
import java.util.Map;
import java.util.List;
import java.util.ArrayList;

public class Cliente {
    private String nome;
    private Date horaChegada;
    private Date horaSaida;
    private double conta;
    private List<Pedido> comanda;
    
    
    
    public Cliente(String nome, Date horaChegada) {
        this.nome = nome;
        this.horaChegada = horaChegada;
        this.horaSaida = null;
        this.conta = 0.0;
        this.comanda = new ArrayList<>();
    }
 
    public void registrarSaida(Date horaSaida) {
        this.horaSaida = horaSaida;
    }
    
    public String calcularTempo() {
        if (horaSaida != null) {
            long diferencaMillis = this.horaSaida.getTime() - this.horaChegada.getTime();
            long diferencaSegundos = diferencaMillis / 1000;
            long horas = diferencaSegundos / 3600;
            long minutos = (diferencaSegundos % 3600) / 60;
            return horas + " horas e " + minutos + " minutos";
        } else {
            return "Cliente ainda não saiu.";
        }
    }
    
    public void adicionarValor(double valor) {
        this.conta += valor;
    }
    
    public void adicionarPedido(String nomePedido, double valorPedido) {
        Pedido pedido = new Pedido(nomePedido, valorPedido);
        this.comanda.add(pedido);
    }
    
    public List<Pedido> getComanda() {
        return comanda;
    }
    
    public double getConta() {
        return conta;
    }

    public String getNome() {
        return nome;
    }
    
}

class Pedido {
    private String nome;
    private double valor;

    public Pedido(String nome, double valor) {
        this.nome = nome;
        this.valor = valor;
    }

    public String getNome() {
        return nome;
    }

    public double getValor() {
        return valor;
    }
}