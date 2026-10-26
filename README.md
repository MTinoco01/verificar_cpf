# Validador de CPF

Validador de CPF em Java que roda no terminal. Fiz este projeto para praticar lógica de programação, manipulação de Strings e separação de responsabilidades em camadas.

## Funcionalidades

- Aceita o CPF com pontuação (`111.444.777-35`) ou só com números (`11144477735`)
- Recusa entradas que não tenham exatamente 11 números
- Recusa sequências repetidas (`11111111111`, `22222222222`...)
- Calcula os dois dígitos verificadores pelo módulo 11 e confere com os informados


## Como rodar

É preciso ter o **Java 22 ou superior**. Na raiz do projeto, rode:

```bash
java src/main/java/com/validadorcpf/application/Main.java
```

Digite um CPF e aperte Enter. Para encerrar, digite `sair`.


## Como funciona o cálculo

1. Cada um dos 9 primeiros números é multiplicado por um peso que vai de 10 até 2, e os resultados são somados.
2. Calcula-se o resto da divisão dessa soma por 11. Se o resto for menor que 2, o dígito é `0`; caso contrário, é `11 - resto`.
3. O 2º dígito segue o mesmo processo usando os 9 números mais o 1º dígito, com pesos de 11 até 2.
