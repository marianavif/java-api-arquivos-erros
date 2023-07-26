package br.com.alura.desafiocep.modelos;

// Exceção lançada caso o número de dígitos não corresponda ao número de dígitos desejado
public class ErroDeNumeroDeDigitosException extends IllegalArgumentException {
    private String mensagem;

    public ErroDeNumeroDeDigitosException(String mensagem) {
        this.mensagem = mensagem;
    }

    @Override
    public String getMessage() {
        return this.mensagem;
    }
}
