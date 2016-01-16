import java.util.function.Consumer;

public class SessionTemplate {

    public void salvar(Usuario usuario) {
        execute(session -> session.save(usuario));
    }

    public void execute(Consumer<Session> consumer) {
        try {
            Session sessao = HibernateUtil.getSessionFactory().openSession();
            try {
                Transaction transacao = sessao.beginTransaction();
                try {
                    consumer.accept(sessao);
                    transacao.commit();
                } finally {
                    transacao.close();
                }
            } finally {
                sessao.close();
            }
        } catch (Throwable e) {
            trataErro(e);
        }
    }

    private void trataErro(Throwable e) {
    }

    private static class SessionFactory {
        public Session openSession() {
            return null;
        }
    }


    private class Session {
        public Transaction beginTransaction() {
            return null;
        }

        public void close() {

        }

        public void save(Object obj) {
        }

    }

    private static class HibernateUtil {
        public static SessionFactory getSessionFactory() {
            return null;
        }
    }

    private class Transaction {
        public void commit() {

        }

        public void close() {
            throw new UnsupportedOperationException("Not implemented");
        }
    }

    private class Usuario {
    }
}
