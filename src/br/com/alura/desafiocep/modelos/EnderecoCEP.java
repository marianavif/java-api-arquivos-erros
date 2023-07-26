package br.com.alura.desafiocep.modelos;

public record EnderecoCEP(String cep, String logradouro, String complemento, String bairro,
                          String localidade, String uf) {
}
