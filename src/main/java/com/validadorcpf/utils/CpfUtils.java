package com.validadorcpf.utils;

public final class CpfUtils {

    public static final int TAMANHO_CPF = 11;

    private CpfUtils() {
    }

    public static String limparCpf(String cpf) {
        if (cpf == null) {
            return "";
        }
        return cpf.trim()
                  .replace(".", "")
                  .replace("-", "");
    }

    public static boolean possuiFormatoValido(String cpfLimpo) {
        return cpfLimpo != null && cpfLimpo.matches("\\d{" + TAMANHO_CPF + "}");
    }

    public static int paraDigito(char caractere) {
        return caractere - '0';
    }

    public static String formatarCpf(String cpfLimpo) {
        if (!possuiFormatoValido(cpfLimpo)) {
            return cpfLimpo;
        }
        return cpfLimpo.substring(0, 3) + "."
             + cpfLimpo.substring(3, 6) + "."
             + cpfLimpo.substring(6, 9) + "-"
             + cpfLimpo.substring(9, 11);
    }
}
