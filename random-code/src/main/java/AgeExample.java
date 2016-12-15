import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.Period;
import java.time.ZoneOffset;
import java.util.Date;

public class AgeExample {

    public static int idade(final LocalDate aniversario) {
        final LocalDate dataAtual = LocalDate.now();
        final Period periodo = Period.between(aniversario, dataAtual);
        return periodo.getYears();
    }

    public static int idade2(final LocalDate aniversario) {
        return Period.between(aniversario, LocalDate.now()).getYears();
    }

    public static int idade(final int dia, final int mes, final int ano) {
        return idade(LocalDate.of(ano, mes, dia));
    }

    public static int idade(final Date dataAniversario) {
        return idade(LocalDateTime.ofInstant(dataAniversario.toInstant(), ZoneOffset.UTC).toLocalDate());
    }

    public static void main(String[] args) {
        System.out.println(idade(1, 3, 1984));

        final Date aniversario = Date.from(LocalDate.of(1984, 3, 1).atStartOfDay().toInstant(ZoneOffset.UTC));
        System.out.println(idade(aniversario));
    }
}
