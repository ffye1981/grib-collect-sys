package com.zgss.grib.gribdata.config;

import org.apache.ibatis.executor.Executor;
import org.apache.ibatis.executor.resultset.ResultSetHandler;
import org.apache.ibatis.executor.statement.StatementHandler;
import org.apache.ibatis.mapping.MappedStatement;
import org.apache.ibatis.plugin.*;

import java.sql.Connection;
import java.sql.Statement;
import java.util.Properties;

@Intercepts(value = {@Signature(type = Executor.class, method = "update", args = { MappedStatement.class, Object.class }) })
public class MbInterceptor implements Interceptor {

    //拦截目标对象
    @Override
    public Object intercept(Invocation invocation) throws Throwable {
        Executor  statementHandler = (Executor) invocation.getTarget();
        statementHandler.getTransaction().getConnection().setAutoCommit(false);
//        System.out.println("拦截的对象是2："+invocation.getTarget());
//        System.out.println("拦截方法是2："+invocation.getMethod().toString());
//        System.out.println("拦截参数是2："+invocation.getArgs().length);
        //执行拦截对象真正的方法
        Object o =invocation.proceed();
        statementHandler.getTransaction().getConnection().commit();
        return o;
    }

    @Override
    public Object plugin(Object target) {
//        System.out.println("插件方法2--将要包装的目标对象2："+target);
        //为当前对象创建代理对象
        Object o= Plugin.wrap(target, this);
        return o;
    }

    @Override
    public void setProperties(Properties properties) {

    }
}
