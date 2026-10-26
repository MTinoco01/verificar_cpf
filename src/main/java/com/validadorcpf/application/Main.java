package com.validadorcpf.application;

import com.validadorcpf.services.ValidadorCpfService;
import com.validadorcpf.services.ValidadorCpfService.ResultadoValidacao;
import com.validadorcpf.utils.CpfUtils;

import java.util.Scanner;


public class Main {

    private static final String COMANDO_SAIR = "sair";

    public static void main(String[] args) {
        ValidadorCpfService validador = new ValidadorCpfService();

        try (Scanner scanner = new Scanner(System.in)) {
            exibirCabecalho();

            while (true) {
                System.out.print("\nDigite o CPF (ou '" + COMANDO_SAIR + "'): ");
                if (!scanner.hasNextLine()) {
                    break;
                }

                String entrada = scanner.nextLine().trim();
                if (entrada.equalsIgnoreCase(COMANDO_SAIR)) {
                    break;
                }

                exibirResultado(entrada, validador.validar(entrada));
            }
        }

        System.out.println("\nEncerrando o validador. Até logo!");
    }

    private static void exibirCabecalho() {
        System.out.println("=================================");
        System.out.println("        VALIDADOR DE CPF         ");
        System.out.println("=================================");
        System.out.println("Aceita 111.222.333-44 ou 11122233344");
    }

    private static void exibirResultado(String entrada, ResultadoValidacao resultado) {
        String cpfExibicao = CpfUtils.formatarCpf(CpfUtils.limparCpf(entrada));
        String status = resultado.isValido() ? "[OK]" : "[X]";
        System.out.println(status + " " + cpfExibicao + " -> " + resultado.getMensagem());
    }
}
