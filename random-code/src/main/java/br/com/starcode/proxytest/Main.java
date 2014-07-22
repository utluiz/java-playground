package br.com.starcode.proxytest;

import java.lang.reflect.Field;
import java.lang.reflect.Proxy;

/**
 * http://pt.stackoverflow.com/questions/25098/setando-um-field-para-uma-proxy
 */
public class Main {

    public static void main(String[] args) throws Exception {
        
        Classe2 obj = new Classe2();
        IClasse pc = (IClasse)Proxy.newProxyInstance(Main.class.getClassLoader(),
                new Class[] {IClasse.class},
                new ClasseProxy(obj));
        
        ClasseAlvo alvo = new ClasseAlvo();
        Field f = alvo.getClass().getDeclaredField("con");
        f.setAccessible(true);
        f.set(alvo, pc); // <--- O PROBLEMA

    }

}
