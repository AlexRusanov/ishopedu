package edu.bionic.dao;

import org.junit.runner.RunWith;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.jdbc.Sql;
import org.springframework.test.context.jdbc.SqlConfig;
import org.springframework.test.context.junit4.SpringRunner;

import static edu.bionic.config.Profiles.*;

@RunWith(SpringRunner.class)
@ContextConfiguration("classpath:spring/spring-app.xml")
@ActiveProfiles({HSQLDB, JPA})
@Sql(scripts = "classpath:db/fillDB.sql", config = @SqlConfig(encoding = "UTF-8"))
public abstract class BaseDaoTest {
}
