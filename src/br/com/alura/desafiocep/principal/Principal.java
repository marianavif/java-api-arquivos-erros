package br.com.alura.desafiocep.principal;

import br.com.alura.desafiocep.conexao.Conexao;
import br.com.alura.desafiocep.modelos.Endereco;
import br.com.alura.desafiocep.modelos.EnderecoCEP;
import br.com.alura.desafiocep.modelos.ErroDeNumeroDeDigitosException;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.InputMismatchException;
import java.util.List;
import java.util.Scanner;

public class Principal {
    public static void main(String[] args) throws IOException, InterruptedException {
        Scanner leitura = new Scanner(System.in); // objeto para leitura da entrada do teclado
        boolean encerrarMenu = false; // variável responsável pelo menu
        int cep; //receberá a entrada do teclado
        // inicializando a lista dos locais pesquisados de tipo Endereco
        List<Endereco> locaisPesquisados = new ArrayList<>();

        Gson gson = new GsonBuilder()
                .setPrettyPrinting()
                .create();              // inicializando o objeto gson com o qual trabalha-se o arquivo .json

        while (!encerrarMenu) {
            try {
                System.out.println("Digite um CEP (8 dígitos) para busca: ");
                cep = leitura.nextInt();
                if (Integer.toString(cep).length() != 8) {
                    // cep com número de dígitos errado provoca exceção
                    throw new ErroDeNumeroDeDigitosException("CEP inserido não possui 8 dígitos");
                }
                // inicializando o objeto de conexão com a API
                Conexao conexao = new Conexao();
                conexao.setUrl(cep); // setando a chave do valor da API desejado

                // string json recebe o valor da API no formato de json
                String json = conexao.conectar().body();

                // criando objeto do record EnderecoCEP a partir do json fornecido pela API
                EnderecoCEP enderecoViaCep = gson.fromJson(json, EnderecoCEP.class);
                // criando objeto Endereco a partir do objeto EnderecoCEP
                Endereco endereco = new Endereco(enderecoViaCep);

                // adicionando informação do cep desejado à lista de locais pesquisados
                locaisPesquisados.add(endereco);
                System.out.println("Endereço adicionado!");

            } catch (ErroDeNumeroDeDigitosException e) {
                // exibição da mensagem de erro de número de dígitos
                System.out.println("Ocorreu um erro: " + e.getMessage());
            } catch (InputMismatchException e) {
                // exibição da mensagem de erro de tipo de entrada no teclado
                System.out.println("Ocorreu um erro: Não foram digitados números");
                leitura.nextLine(); // recuperando a entrada do teclado após o erro
            }

            System.out.println("Deseja fazer nova busca? (1) Sim; (0) Não");
            if (leitura.nextInt() == 1) {
                encerrarMenu = true; // encerrando o menu
            }
        }
        // exibição da lista de locais pesquisados
        System.out.println("Lista de CEPs pesquisados:");
        System.out.println(locaisPesquisados);

        // escrita do arquivo em .json a partir da lista de objetos Endereco
        FileWriter escrita = new FileWriter("pesquisaCep.json");
        escrita.write(gson.toJson(locaisPesquisados));
        escrita.close();

        System.out.println("Programa finalizado!");
    }
}