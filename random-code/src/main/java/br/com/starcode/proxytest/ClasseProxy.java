package br.com.starcode.proxytest;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;

public class ClasseProxy implements InvocationHandler {
    Object pc;
    public ClasseProxy(Object original) {
        this.pc = original;
    }

    public Object invocaFuncao(Method m, Object[] args) {
        try {
            return pc.getClass().getMethod(m.getName(), m.getParameterTypes()).invoke(pc, args);
        } catch (Exception ex) {
            ex.printStackTrace();
        }
        return null;
    }

    @Override
    public Object invoke(Object proxy, Method m, Object[] args) throws Throwable {
        if(m.getName().equalsIgnoreCase("teste")) {
            System.out.println("Metodo teste foi executado.");
        } else {
            return invocaFuncao(m, args);
        }
        return null;
    }
}