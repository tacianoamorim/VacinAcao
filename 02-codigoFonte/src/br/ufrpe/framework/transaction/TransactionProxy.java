package br.ufrpe.framework.transaction;

import java.lang.reflect.Method;
import java.sql.Connection;

import net.sf.cglib.proxy.Enhancer;
import net.sf.cglib.proxy.MethodInterceptor;
import net.sf.cglib.proxy.MethodProxy;

public class TransactionProxy implements MethodInterceptor {
    /**
     * Cria uma nova instancia do proxy e pluga ela no objeto de negocio.
     */
    public static Object getInstance(Class<?> classeNegocio) {
        //cria o objeto do CGLIB que efetivamente pluga o proxy
        Enhancer e = new Enhancer();
        //configura o proxy para interceptar o objeto de negocio
        e.setSuperclass(classeNegocio);
        //cria uma nova instancia do print proxy para receptar as chamadas
        e.setCallback(new TransactionProxy());
        //cria uma nova instancia do objeto de negocio com o proxy plugado nela
        return e.create();
    }

    /**
     * Intercepta a chamada para qualquer objeto.
     */
    public Object intercept(Object obj, Method method, Object[] args, MethodProxy proxy) throws
        Throwable, Exception {
        //resultado da execucao do metodo interceptado
        Object result = null;
        TransactionManager transactionManager= new TransactionManager();
        Connection con = transactionManager.getConnection();
        boolean resultTransaction= true;
        
        try {
            //fazer um start Transaction
        	transactionManager.getTransaction(con);
            //executar o metodo interceptado
            result = proxy.invokeSuper(obj, args);
            
        } catch (Throwable ex) {
            //fazer um set roll back only
        	resultTransaction= false;
            throw ex;
        } finally {
        	transactionManager.doFinishTransaction(con, resultTransaction);
        }
        //chama o metodo do objeto interceptado
        return result;
    }

}