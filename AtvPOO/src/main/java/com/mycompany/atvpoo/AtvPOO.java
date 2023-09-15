package com.mycompany.atvpoo;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Scanner;
import java.util.HashMap;
import java.util.Map;
import java.util.ArrayList;
import java.util.List;

public class AtvPOO {

    public static void main(String[] args) throws Exception {
        Scanner scanner = new Scanner(System.in);
        SimpleDateFormat formatoHora = new SimpleDateFormat("HH:mm");

        List<Cliente> clientes = new ArrayList<>();
        Map<String, Double> cardapio = new HashMap<>();
        cardapio.put("F", 30.0); // Frango
        cardapio.put("C", 40.0); // Carne
        cardapio.put("M", 25.0); // Macarrão

        boolean sair = false;

        while (!sair) {
            System.out.println("Menu Principal:");
            System.out.println("1 - Cadastrar Cliente");
            System.out.println("2 - Pedir Conta");
            System.out.println("3 - Fazer Pedido");
            System.out.println("4 - Consultar Comanda");
            System.out.println("5 - Sair");
            System.out.print("Escolha uma opção: ");

            int opcao = scanner.nextInt();

            switch (opcao) {
                case 1:
                    System.out.print("Digite o nome do cliente: ");
                    String nome = scanner.next();
                    System.out.print("Digite a hora de chegada (HH:mm): ");
                    String horaChegadaStr = scanner.next();

                    Date horaChegada = formatoHora.parse(horaChegadaStr);
                    Cliente cliente = new Cliente(nome, horaChegada);
                    clientes.add(cliente);
                    System.out.println("Cliente cadastrado com sucesso!");
                    break;
                case 2:
                    if (!clientes.isEmpty()) {
                        System.out.println("Clientes cadastrados:");
                        for (Cliente c : clientes) {
                            System.out.println("- " + c.getNome());
                        }
                        System.out.print("Digite o nome do cliente para ver a conta: ");
                        String nomeClienteConta = scanner.next();
                        
                        Cliente clienteConta = null;
                        for (Cliente c : clientes) {
                            if (c.getNome().equalsIgnoreCase(nomeClienteConta)) {
                                clienteConta = c;
                                break;
                            }
                        }

                        if (clienteConta != null) {
                            System.out.println("Conta de " + clienteConta.getNome() + ": R$" + clienteConta.getConta());
                            System.out.print("Deseja pagar a conta (S/N)? ");
                            String opcaoPagamento = scanner.next().toUpperCase();

                            if (opcaoPagamento.equals("S")) {
                                System.out.println("Obrigado! Conta paga.");
                                System.out.print("Digite a hora de saída (HH:mm): ");
                                String horaSaidaStr = scanner.next();
                                Date horaSaida = formatoHora.parse(horaSaidaStr);
                                clienteConta.registrarSaida(horaSaida);
                                System.out.println("Tempo que o cliente ficou: " + clienteConta.calcularTempo());
                                
                                clientes.remove(clienteConta);
                            } else if (opcaoPagamento.equals("N")) {
                                System.out.println("Conta não paga.");
                            } else {
                                System.out.println("Opção inválida.");
                            }
                        } else {
                            System.out.println("Cliente não encontrado.");
                        }
                    } else {
                        System.out.println("Nenhum cliente cadastrado.");
                    }
                    break;
                case 3:
                    if (!clientes.isEmpty()) {
                        System.out.print("Digite o nome do cliente: ");
                        String nomeClientePedido = scanner.next();
                        
                        Cliente clientePedido = null;
                        for (Cliente c : clientes) {
                            if (c.getNome().equalsIgnoreCase(nomeClientePedido)) {
                                clientePedido = c;
                                break;
                            }
                        }

                        if (clientePedido != null) {
                            System.out.println("Cardápio:");
                            System.out.println("F - Frango (R$30.00)");
                            System.out.println("C - Carne (R$40.00)");
                            System.out.println("M - Macarrão (R$25.00)");
                            System.out.print("Escolha uma opção do cardápio: ");
                            String escolha = scanner.next().toUpperCase();

                            if (cardapio.containsKey(escolha)) {
                                double valorPedido = cardapio.get(escolha);
                                clientePedido.adicionarValor(valorPedido);
                                clientePedido.adicionarPedido(escolha, valorPedido);
                                System.out.println("Pedido de " + clientePedido.getNome() + " registrado. Valor: R$" + valorPedido);
                            } else {
                                System.out.println("Opção de cardápio inválida.");
                            }
                        } else {
                            System.out.println("Cliente não encontrado.");
                        }
                    } else {
                        System.out.println("Nenhum cliente cadastrado.");
                    }
                    break;
                case 4:
                    if (!clientes.isEmpty()) {
                        System.out.println("Comanda:");
                        for (Cliente c : clientes) {
                            System.out.println("Cliente: " + c.getNome());
                            for (Pedido pedido : c.getComanda()) {
                                System.out.println("- " + pedido.getNome() + " (R$" + pedido.getValor() + ")");
                            }
                        }
                    } else {
                        System.out.println("Nenhum cliente cadastrado.");
                    }
                    break;
                case 5:
                    sair = true;
                    break;
                default:
                    System.out.println("Opção inválida. Tente novamente.");
                   break;
                
            }
        }
    }

}

