package br.com.starcode.comparadordata;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

/**
 * http://pt.stackoverflow.com/questions/21701/como-fa%C3%A7o-para-obter-os-valores-de-um-date-no-formato-dd-mm-yyyy-hhmm-e-compa
 */
public class ComparadorData {
    
    public static boolean compareByPattern(Date d1, Date d2, String pattern) {
        SimpleDateFormat sdf = new SimpleDateFormat(pattern);
        return sdf.format(d1).equals(sdf.format(d2));
    }
    
    public static void main(String[] args) {
        
        //forma mais adequada para criar uma data especificando ano, mês dia, hora e minuto
        //lembrando que o mês começa com zero (janeiro) e vai até 11 (dezembro)
        Calendar c = Calendar.getInstance();
        c.set(2014, 11, 30, 23, 59); // 30/12/2014 23:59
        Date horaMarcada = c.getTime();
        
        //hora atual
        Date horaAtual = new Date();
        
        //exibe resultado
        System.out.println("São Iguais? " + compareByPattern(horaMarcada, horaAtual, "yyyyMMddHHmm"));

    }

}
