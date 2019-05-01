package com.yibo.springbootjdbc.repository;

import com.yibo.springbootjdbc.domain.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.dao.DataAccessException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.PreparedStatementCallback;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.PlatformTransactionManager;
import org.springframework.transaction.TransactionException;
import org.springframework.transaction.TransactionStatus;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.transaction.support.DefaultTransactionDefinition;

import javax.sql.DataSource;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.Collection;
import java.util.Collections;

/**
 * @author: huangyibo
 * @Date: 2018/12/6 23:41
 * @Description:
 *
 * @{link User} 用户的仓储（SQL、或NOSQL、或内存型）
 */

@Repository
public class UserRepository {

    private final DataSource dataSource;

    private final DataSource masterDataSource;

    private final DataSource salveDataSource;

    private final JdbcTemplate jdbcTemplate;

    private final PlatformTransactionManager platformTransactionManager;

    @Autowired
    public UserRepository(DataSource dataSource,
                          @Qualifier("masterDataSource") DataSource masterDataSource,
                          @Qualifier("salveDataSource") DataSource salveDataSource,
                          JdbcTemplate jdbcTemplate,
                          PlatformTransactionManager platformTransactionManager) {
        this.dataSource = dataSource;
        this.masterDataSource = masterDataSource;
        this.salveDataSource = salveDataSource;
        this.jdbcTemplate = jdbcTemplate;
        this.platformTransactionManager = platformTransactionManager;
    }


    public boolean save(User user){
        System.out.printf("[Thread : %s] save user :%s\n",Thread.currentThread().getName(),user);
        boolean success = false;
        TransactionStatus transactionStatus = null;

        try {
            DefaultTransactionDefinition transactionDefinition = new DefaultTransactionDefinition();
            //开始事物
            transactionStatus = platformTransactionManager.getTransaction(transactionDefinition);

            success = jdbcTemplate.execute("INSERT INTO user(name) VALUES(?)",
                    new PreparedStatementCallback<Boolean>() {

                        @Override
                        public Boolean doInPreparedStatement(PreparedStatement preparedStatement) throws SQLException, DataAccessException {
                            preparedStatement.setString(1,user.getName());
                            return preparedStatement.executeUpdate() > 0;
                        }
                    });
            platformTransactionManager.commit(transactionStatus);
        } catch (Exception e) {
            platformTransactionManager.rollback(transactionStatus);
        }
        return success;
    }

    @Transactional
    public boolean transactionSave(User user){
        boolean success = false;
        success = jdbcTemplate.execute("INSERT INTO user(name) VALUES(?)",
                new PreparedStatementCallback<Boolean>() {

                    @Override
                    public Boolean doInPreparedStatement(PreparedStatement preparedStatement) throws SQLException, DataAccessException {
                        preparedStatement.setString(1,user.getName());
                        return preparedStatement.executeUpdate() > 0;
                    }
                });
        return success;
    }

    private boolean jdbcSave(User user){
        boolean success = false;
        Connection connection = null;
        try{
            connection = dataSource.getConnection();
            connection.setAutoCommit(false);
            PreparedStatement prepared = connection.prepareStatement("INSERT INTO user(name) VALUES(?)");
            prepared.setString(1,user.getName());
            success = prepared.executeUpdate() > 0;
            prepared.close();
            connection.commit();
            return success;
        }catch (Exception e){
            try {
                connection.rollback();
            } catch (SQLException e1) {
                e1.printStackTrace();
            }
            return success;
        }finally {
            if(connection != null){
                try {
                    connection.close();
                } catch (SQLException e) {
                    e.printStackTrace();
                }
            }
        }
    }

    public Collection<User> findAll(){
        return Collections.emptyList();
    }

}
