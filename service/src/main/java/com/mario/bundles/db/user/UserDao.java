package com.mario.bundles.db.user;

import org.jdbi.v3.sqlobject.config.RegisterBeanMapper;
import org.jdbi.v3.sqlobject.customizer.Bind;
import org.jdbi.v3.sqlobject.statement.SqlQuery;
import org.jdbi.v3.sqlobject.statement.SqlUpdate;

import java.util.List;
import java.util.Optional;

public interface UserDao {

    @SqlUpdate("insert into users (fullName, jobTitle) values (:fullName, :jobTitle)")
    void insert(@Bind("fullName") String name, @Bind("jobTitle") String jobTitle);

    @SqlQuery("select * from users")
    @RegisterBeanMapper(User.class)
    List<User> getUsers();

    @SqlQuery("select * from users where id=:id")
    @RegisterBeanMapper(User.class)
    Optional<User> getUserById(@Bind("id") int id);
}
