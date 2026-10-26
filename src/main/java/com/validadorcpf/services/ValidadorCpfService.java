package com.validadorcpf.services;

import com.validadorcpf.utils.CpfUtils;

public class ValidadorCpfService {

    private static final int QTD_DIGITOS_BASE = 9;
    private static final int PESO_INICIAL_PRIMEIRO_DIGITO = 10;
    private static final int PESO_INICIAL_SEGUNDO_DIGITO = 11;

    public enum ResultadoValidacao {
        VALIDO("CPF válido."),
        FORMATO_INVALIDO("Formato inválido: o CPF deve conter exatamente 11 números."),
        SEQUENCIA_REPETIDA("CPF inválido: todos os dígitos são iguais."),
        DIGITOS_VERIFICADORES_INVALIDOS("CPF inválido: os dígitos verificadores não conferem.");

        private final String mensagem;

        ResultadoValidacao(String mensagem) {
            this.mensagem = mensagem;
        }

        public String getMensagem() {
            return mensagem;
        }

        public boolean isValido() {
            return this == VALIDO;
        }
    }

    public ResultadoValidacao validar(String cpfInformado) {
        String cpf = CpfUtils.limparCpf(cpfInformado);

        if (!CpfUtils.possuiFormatoValido(cpf)) {
            return ResultadoValidacao.FORMATO_INVALIDO;
        }
        if (isSequenciaRepetida(cpf)) {
            return ResultadoValidacao.SEQUENCIA_REPETIDA;
        }
        if (!possuiDigitosVerificadoresValidos(cpf)) {
            return ResultadoValidacao.DIGITOS_VERIFICADORES_INVALIDOS;
        }
        return ResultadoValidacao.VALIDO;
    }

    public boolean isSequenciaRepetida(String cpf) {
        char primeiro = cpf.charAt(0);
        for (int i = 1; i < cpf.length(); i++) {
            if (cpf.charAt(i) != primeiro) {
                return false;
            }
        }
        return true;
    }

    public boolean possuiDigitosVerificadoresValidos(String cpf) {
        String base = cpf.substring(0, QTD_DIGITOS_BASE);

        int primeiroDigito = calcularPrimeiroDigito(base);
        int segundoDigito = calcularSegundoDigito(base, primeiroDigito);

        int primeiroInformado = CpfUtils.paraDigito(cpf.charAt(9));
        int segundoInformado = CpfUtils.paraDigito(cpf.charAt(10));

        return primeiroDigito == primeiroInformado
            && segundoDigito == segundoInformado;
    }

    public int calcularPrimeiroDigito(String base) {
        return calcularDigito(base, PESO_INICIAL_PRIMEIRO_DIGITO);
    }

    public int calcularSegundoDigito(String base, int primeiroDigito) {
        return calcularDigito(base + primeiroDigito, PESO_INICIAL_SEGUNDO_DIGITO);
    }

    private int calcularDigito(String numeros, int pesoInicial) {
        int soma = 0;
        int peso = pesoInicial;

        for (int i = 0; i < numeros.length(); i++) {
            soma += CpfUtils.paraDigito(numeros.charAt(i)) * peso;
            peso--;
        }

        int resto = soma % 11;
        return (resto < 2) ? 0 : 11 - resto;
    }
}
