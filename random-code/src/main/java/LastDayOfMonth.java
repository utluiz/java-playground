import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.time.temporal.TemporalAdjusters;
import java.util.Calendar;
import java.util.Date;
import java.util.Locale;
import java.util.TimeZone;

public class LastDayOfMonth {
    public static void main(String[] args) throws ParseException {

        Locale.setDefault(new Locale("pt", "BR"));
        TimeZone.setDefault(TimeZone.getTimeZone("GMT"));
        Date entrada = new SimpleDateFormat("dd/MM/yyyy").parse("01/03/2016");
        Calendar calendar = Calendar.getInstance(TimeZone.getTimeZone("America/Sao_Paulo"));
        calendar.setTime(entrada);
        calendar.add(Calendar.MONTH, -1);
        System.out.println(new SimpleDateFormat("dd/MM/yyyy HH:mm:ss").format(calendar.getTime()));

//        testTimezone();
//
//        System.out.printf("Timezone: %s%n", TimeZone.getDefault().getDisplayName());
//        System.out.printf("Locale: %s%n", Locale.getDefault());
//
//        SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy HH:mm:ss");
//        Date original = new SimpleDateFormat("dd/MM/yyyy").parse("01/03/2016");
//        System.out.println(sdf.format(original));
//        Date resultado = ultimoDiaDoMesAnterior(original);
//        System.out.println(sdf.format(resultado));
    }

    static Date ultimoDiaDoMesAnterior(Date date) {
        Calendar c = Calendar.getInstance(TimeZone.getTimeZone("America/Sao_Paulo"));
        c.setTime(date);
        c.add(Calendar.MONTH, -1);
        c.set(Calendar.DAY_OF_MONTH, c.getActualMaximum(Calendar.DAY_OF_MONTH));
        return c.getTime();
    }

    static void testTimezone() throws ParseException {
        Date date = new SimpleDateFormat("dd/MM/yyyy").parse("01/03/2016");
        Calendar c = Calendar.getInstance(new Locale("pt", "BR"));
        //Calendar c = Calendar.getInstance(TimeZone.getTimeZone("America/Sao_Paulo"));
        c.setTime(date);
        for (int i = 0; i < 36; i++) {
            System.out.println(new SimpleDateFormat("dd/MM/yyyy HH:mm:ss").format(c.getTime()));
            c.add(Calendar.MONTH, -1);
        }
    }

    static void printLastDays() {
        LocalDateTime data = LocalDateTime.now();
        LocalDateTime ultimoDiaDoMesAnterior = data.minusMonths(1).with(TemporalAdjusters.lastDayOfMonth());
        System.out.println(ultimoDiaDoMesAnterior.format(DateTimeFormatter.ofPattern("dd/MM/yyyy")));

        Date date = new Date();
        Calendar c = Calendar.getInstance(TimeZone.getTimeZone("America/Sao_Paulo"));
        c.setTime(date);
        c.add(Calendar.MONTH, -1);
        c.set(Calendar.DAY_OF_MONTH, c.getActualMaximum(Calendar.DAY_OF_MONTH));
        System.out.println(new SimpleDateFormat("dd/MM/yyyy").format(c.getTime()));

        c = Calendar.getInstance(TimeZone.getTimeZone("America/Sao_Paulo"));
        c.setTime(date);
        c.set(Calendar.DAY_OF_MONTH, 1);
        c.add(Calendar.DAY_OF_MONTH, -1);
        System.out.println(new SimpleDateFormat("dd/MM/yyyy").format(c.getTime()));
    }
}
