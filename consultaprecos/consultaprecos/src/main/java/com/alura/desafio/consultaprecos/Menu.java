package com.alura.desafio.consultaprecos;


import com.alura.desafio.modelos.Dados;
import com.alura.desafio.modelos.DadosAnos;
import com.alura.desafio.modelos.DadosModelos;
import com.alura.desafio.servicos.ConsumoAPI;
import com.alura.desafio.servicos.ConverteDados;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Menu {
    Scanner scanner = new Scanner(System.in);
    private String veiculo;
    private String uriTipo;
    private String codigoMarca;
    private boolean continuar = true;
    private ConsumoAPI consumo = new ConsumoAPI();
    private ConverteDados conversor = new ConverteDados();

    public void exibeMenu() {
        while (continuar) {
            System.out.println("Para realizar a consulta de preços, primeiro informe o tipo de veículo:");
            System.out.println("Digite 1 para CARRO, 2 para MOTO, 3 para CAMINHÃO ou 4 para SAIR:");
            String opcao = scanner.nextLine();
            if (opcao.equals("4")) {
                System.out.println("Obrigado! Até a próxima!");
                continuar = false;
            }
            if (validaOpcao(opcao)) {
                try {
                    uriTipo = retornaUriTipo(opcao);
                    System.out.println("Abaixo, a lista de marcas em ordem alfabética: ");
                    List<Dados> listaDados = retornaLista(uriTipo, Dados.class);
                    listaDados.forEach(d -> System.out.println(d));
                    System.out.println("Agora, digite o código da marca do seu veículo: ");
                    codigoMarca = scanner.nextLine();
                    String uriMarca = uriTipo + "/" + codigoMarca + "/modelos";
                    DadosModelos modelos = retornaDados(uriMarca, DadosModelos.class);
                    System.out.println(modelos);
                    System.out.println("Por fim, digite o código do modelo do veículo: ");
                    String codigoModelo = scanner.nextLine();
                    String uriAnos = uriMarca + "/" + codigoModelo + "/anos/";
                    List<Dados> anos = retornaLista(uriAnos, Dados.class);
                    System.out.println("Abaixo, a lista de preços conforme o ano do veículo selecionado:");
                    exibeValorPorAno(anos, uriAnos);
                }
                catch (RuntimeException e) {
                    System.out.println("Ops, algo deu errado!");
                    System.out.println("O código que você digitou pode ser inválido, ou a pesquisa está fora do ar!");
                }
                continuar = desejaContinuar();
            } else {
                System.out.println("Tente novamente!");
            }
        }
    }

    public boolean validaOpcao(String opcao) {
        if (!opcao.equals("1") && !opcao.equals("2") && !opcao.equals("3")) {
            System.out.println("Opção inválida!");
            return false;
        } else {
            return true;
        }
    }

    public String retornaUriTipo(String opcao) {
        if (opcao.equals("1")) {
            veiculo = "carros";
        } else if (opcao.equals("2")) {
            veiculo = "motos";
        } else {
            veiculo = "caminhoes";
        }
        return "https://parallelum.com.br/fipe/api/v1/" + veiculo + "/marcas";
    }

    public <T> T retornaDados(String uriModelos, Class<T> classe) {
        T objeto;
        try {
            String json = consumo.consomeApi(uriModelos);
            objeto = conversor.converterDados(json, classe);
            if (objeto == null) {
                throw new NullPointerException("Ocorreu um erro na busca! O código que você digitou pode ser inválido!");
            }
            return objeto;
        }
        catch (Exception e) {
            return null;
        }
    }

    public <T> List<T> retornaLista(String uriTipo, Class<T> classe) {
        String json = consumo.consomeApi(uriTipo);
        List<T> listaTipos = conversor.converterListas(json, classe);
        return listaTipos;
    }

    public void exibeValorPorAno(List<Dados> anos, String uriAnos) {
        List<DadosAnos> valorPorAno = new ArrayList<>();
        anos.forEach(a -> {
            DadosAnos dadosAnos = retornaDados(uriAnos + a.codigo(), DadosAnos.class);
            valorPorAno.add(dadosAnos);
        });
        valorPorAno.forEach(v -> System.out.println(v));
    }

    public boolean desejaContinuar() {
        while (true) {
            System.out.println("Deseja realizar outra consulta? Digite 'S' para Sim, 'N' para Não: ");
            String resposta = scanner.nextLine();
            if (resposta.equalsIgnoreCase("s")) {
                return true;
            } else if (resposta.equalsIgnoreCase("n")) {
                System.out.println("Obrigado! Até a próxima!");
                return false;
            } else {
                System.out.println("Resposta inválida, tente novamente!");
            }
        }
    }
}