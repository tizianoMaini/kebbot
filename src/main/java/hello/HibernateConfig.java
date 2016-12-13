package hello;

/**
 * Created by tizio on 03/11/16.
 */

import org.apache.commons.dbcp2.BasicDataSource;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.orm.hibernate4.HibernateTransactionManager;
import org.springframework.orm.hibernate4.LocalSessionFactoryBean;
import org.springframework.transaction.annotation.EnableTransactionManagement;

import javax.sql.DataSource;
import java.util.Properties;


@Configuration
@EnableTransactionManagement
public class HibernateConfig {
    private static final String[] modelEntitiesPackages = {"hello.model"};


    @Value("${DATABASE_URL}")
    private String databaseUrl;


    @Bean
    public DataSource getDataSource() {

        BasicDataSource ds = new BasicDataSource();
        ds.setDriverClassName("org.postgresql.Driver");

        StringBuilder url = new StringBuilder();
        url.append("postgresql://");
        databaseUrl = databaseUrl.replace("postgres://", "");
        String[] split1 = databaseUrl.split(":");
        String[] split2 = split1[1].split("@");
        url.append(split2[1] + ":" + split1[2]);
        ds.setUrl("jdbc:" + url.toString());
        ds.setUsername(split1[0]);
        ds.setPassword(split2[0]);
        ds.setInitialSize(5);
        ds.setMaxTotal(10);


        return ds;
    }


    @Bean
    public LocalSessionFactoryBean sessionFactory() {
        LocalSessionFactoryBean sessionFactoryBean = new LocalSessionFactoryBean();

        sessionFactoryBean.setDataSource(getDataSource());
        sessionFactoryBean.setPackagesToScan(modelEntitiesPackages);
        sessionFactoryBean.setHibernateProperties(getHibernateProperties());
        return sessionFactoryBean;
    }

    @Bean
    public HibernateTransactionManager transactionManager() {
        HibernateTransactionManager transactionManager =
                new HibernateTransactionManager();
        transactionManager.setSessionFactory(sessionFactory().getObject());
        return transactionManager;
    }


    private Properties getHibernateProperties() {
        Properties properties = new Properties();
        properties.setProperty("hibernate.hbm2ddl.auto", "update");
        properties.setProperty("hibernate.connection.release_mode", "after_transaction");
        properties.setProperty("hibernate.max_fetch_depth", "3");
        properties.setProperty("default_batch_fetch_size", "16");
        properties.setProperty("hibernate.order_updates.hbm2ddl.auto", "true");
        properties.setProperty("hibernate.use_sql_comments", "false");
        properties.setProperty("hibernate.show_sql", "false");
        properties.setProperty("hibernate.format_sql", "true");
        //properties.setProperty("hibernate.dialect", "org.hibernate.dialect.PostgreSQLDialect");
        return properties;
    }


}

