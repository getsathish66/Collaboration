package com.niit.back.config;
import java.util.Properties;

import javax.sql.DataSource;

import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.jdbc.datasource.DriverManagerDataSource;
import org.springframework.orm.hibernate4.HibernateTransactionManager;
import org.springframework.orm.hibernate4.LocalSessionFactoryBuilder;
import org.springframework.transaction.annotation.EnableTransactionManagement;

import com.niit.back.dao.AppliedjobsDAO;
import com.niit.back.dao.BlogDAO;
import com.niit.back.dao.CommentDAO;
import com.niit.back.dao.EventDAO;
import com.niit.back.dao.ForumDAO;
import com.niit.back.dao.FriendDAO;
import com.niit.back.dao.JobDAO;
import com.niit.back.dao.UserDAO;
import com.niit.back.daoimpl.AppliedjobsDAOImpl;
import com.niit.back.daoimpl.BlogDAOImpl;
import com.niit.back.daoimpl.CommentDAOImpl;
import com.niit.back.daoimpl.EventDAOImpl;
import com.niit.back.daoimpl.ForumDAOImpl;
import com.niit.back.daoimpl.FriendDAOImpl;
import com.niit.back.daoimpl.JobDAOImpl;
import com.niit.back.daoimpl.UserDAOImpl;
import com.niit.back.model.Appliedjobs;
import com.niit.back.model.Blog;
import com.niit.back.model.Chat;
import com.niit.back.model.Comment;
import com.niit.back.model.Event;
import com.niit.back.model.Forum;
import com.niit.back.model.Friend;
import com.niit.back.model.Job;
import com.niit.back.model.User;



@Configuration
@ComponentScan("com.niit.back")
@EnableTransactionManagement
public class ApplicationContextConfig {
	
	@Autowired
	@Bean(name = "dataSource")
	public DataSource getOracleDataSource() {

		DriverManagerDataSource dataSource = new DriverManagerDataSource();

		dataSource.setDriverClassName("oracle.jdbc.driver.OracleDriver");
		dataSource.setUrl("jdbc:oracle:thin:@localhost:1521:XE");

		dataSource.setUsername("colab");
		dataSource.setPassword("sa");

		return dataSource;
	}

	private Properties getHibernateProperties() {
		Properties properties = new Properties();

		properties.put("hibernate.dialect", "org.hibernate.dialect.OracleDialect");
		properties.put("hibernate.hbm2ddl.auto", "update");

		properties.put("hibernate.show_sql", "true");
		properties.put("hibernate.format_sql", "true");
		return properties;
	}

	@Autowired
	@Bean(name = "sessionFactory")
	public SessionFactory getSessionFactory(DataSource dataSource) {

		LocalSessionFactoryBuilder sessionBuilder = new LocalSessionFactoryBuilder(dataSource);
		sessionBuilder.addProperties(getHibernateProperties());

		sessionBuilder.addAnnotatedClass(User.class);
		sessionBuilder.addAnnotatedClass(Blog.class);
		sessionBuilder.addAnnotatedClass(Forum.class);
		sessionBuilder.addAnnotatedClass(Job.class);
		sessionBuilder.addAnnotatedClass(Friend.class);
		sessionBuilder.addAnnotatedClass(Chat.class);
		sessionBuilder.addAnnotatedClass(Comment.class);
		sessionBuilder.addAnnotatedClass(Event.class);
		sessionBuilder.addAnnotatedClass(Appliedjobs.class);


		return sessionBuilder.buildSessionFactory();
	}

	@Autowired
	@Bean(name = "transactionManager")
	public HibernateTransactionManager getTransactionManager(SessionFactory sessionFactory) {
		HibernateTransactionManager transactionManager = new HibernateTransactionManager(sessionFactory);
		return transactionManager;
	}

	@Autowired(required = true)
	@Bean(name = "userDAO")
	public UserDAO getUserDAO(SessionFactory sessionFactory) {
		return new UserDAOImpl(sessionFactory);
	}
	@Autowired(required = true)
	@Bean(name = "BlogDAO")
	public BlogDAO getBlogDAO(SessionFactory sessionFactory) {
		return new BlogDAOImpl(sessionFactory);
	}
	
	@Autowired(required = true)
	@Bean(name = "ForumDAO")
	public ForumDAO getForumDAO(SessionFactory sessionFactory) {
		return new ForumDAOImpl(sessionFactory);
	}
	
	
	@Autowired(required = true)
	@Bean(name = "JobDAO")
	public JobDAO getJobDAO(SessionFactory sessionFactory) {
		return new JobDAOImpl(sessionFactory);
	}
	
	@Autowired(required = true)
	@Bean(name = "FriendDAO")
	public FriendDAO getFriendDAO(SessionFactory sessionFactory) {
		return new FriendDAOImpl(sessionFactory);
	}
 
	/*@Autowired(required = true)
	@Bean(name = "MychatDAO")
	public MychatDAO getMychatDAO(SessionFactory sessionFactory) {
		return new MychatDAOImpl(sessionFactory);
	}*/
	@Autowired(required = true)
	@Bean(name = "CommentDAO")
	public CommentDAO getCommentDAO(SessionFactory sessionFactory) {
		return new CommentDAOImpl(sessionFactory);
	}
	@Autowired(required = true)
	@Bean(name = "EventDAO")
	public EventDAO getEventDAO(SessionFactory sessionFactory) {
		return new EventDAOImpl(sessionFactory);
	}
	@Autowired(required = true)
	@Bean(name = "AppliedjobsDAO")
	public AppliedjobsDAO getAppliedjobsDAO(SessionFactory sessionFactory) {
		return new AppliedjobsDAOImpl(sessionFactory);
	}
}
