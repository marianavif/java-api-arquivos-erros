package br.com.alura.desafiocep.modelos;

public class Endereco {
    // atributos do objeto endereco
    private int cep;
    private String lugar;
    private String complemento;
    private String bairro;
    private String cidade;
    private String uf;

    // construtor do objeto Endereco levando em conta apenas o atributo int cep
    public Endereco(int cep) {
        this.cep = cep;
    }

    //construtor do objeto Endereco a partir do objeto record do tipo EnderecoCEP
    public Endereco(EnderecoCEP enderecoViaCep){
        this.cep = Integer.valueOf(enderecoViaCep.cep().replace("-",""));
        this.lugar = enderecoViaCep.logradouro();
        this.complemento = enderecoViaCep.complemento();
        this.bairro = enderecoViaCep.bairro();
        this.cidade = enderecoViaCep.localidade();
        this.uf = enderecoViaCep.uf();
    }

    // getters e setters
    public int getCep() {
        return cep;
    }

    public void setCep(int cep) {
        this.cep = cep;
    }

    public String getLugar() {
        return lugar;
    }

    public String getComplemento() {
        return complemento;
    }

    public String getBairro() {
        return bairro;
    }

    public String getCidade() {
        return cidade;
    }

    public String getUf() {
        return uf;
    }

    // override do método toString() para formar uma string com os atributos do objeto Endereco
    @Override
    public String toString() {
        return "Endereco {" +
                "cep = " + cep +
                ", lugar = " + lugar +
                ", complemento = " + complemento +
                ", bairro = " + bairro +
                ", cidade = " + cidade +
                ", uf= " + uf +
                '}';
    }
}
