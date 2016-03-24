/**
 * http://pt.stackoverflow.com/q/119616/227
 */
public class OverrideReturnInInterfaceExample {

    interface IGeneralController<E> {
        //...
    }
    interface IReturningAppenderController<E> {
        E adicionar(E Element);
    }
    interface IAppenderController<E> {
        void adicionar(E Element);
    }

    public static class Entidade {}

    public static class Controller implements IGeneralController<Entidade>, IReturningAppenderController<Entidade> {
        @Override
        public Entidade adicionar(Entidade entidade) {
            //insere no banco
            return entidade;
        }
    }
    public static class OtherController implements IGeneralController<Entidade>, IAppenderController<Entidade> {
        @Override
        public void adicionar(Entidade entidade) {
            //insere no banco
        }
    }

}
