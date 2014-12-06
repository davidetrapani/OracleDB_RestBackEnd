package it.padova.sanita.restbackend.dao;

import it.padova.sanita.restbackend.model.Contact;

import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import javax.transaction.Transactional;

import oracle.jdbc.internal.OracleTypes;

import org.hibernate.HibernateException;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.internal.SessionFactoryImpl;

@Transactional()
public class ContactDAO extends GenericHibernateDao<Contact, Long> {

	public ContactDAO(){
		super(Contact.class);
	}

	@SuppressWarnings("unchecked")
	public List<Contact> findByName(String name) throws Exception
	{
		try
		{
			StringBuilder strQuery = new StringBuilder("from Contact p where p.name = :name");
			Query hqlQuery = getSession().createQuery(strQuery.toString());
			hqlQuery.setString("name", name);

			return (ArrayList<Contact>) hqlQuery.list(); 
		}
		catch (HibernateException ex)
		{
			throw new Exception(ex); 
		}
	}

	@SuppressWarnings("unchecked")
	public void saveOrUpdateViaStoredPro(Contact contact) throws Exception
	{
		CallableStatement callableStatement = null;
		try{
			callableStatement = getConnection().prepareCall("call INSERTCONTACT(?,?,?,?)");
			callableStatement.setString(1,contact.getEmail());
			callableStatement.setString(2,contact.getName());
			callableStatement.setString(3, contact.getPhoneNumber());
			//callableStatement.registerOutParameter(4, OracleTypes.CURSOR);
			callableStatement.registerOutParameter(4, java.sql.Types.VARCHAR);

			callableStatement.execute();
			String result = callableStatement.getString(4);
			System.out.println(result);
			//ResultSet resultSet=(ResultSet) callableStatement.getObject(1);

		}catch(SQLException e){
			e.printStackTrace();
		}
	}

	private Connection getConnection(){
		Session session = getSession();
		Connection connection = null;
		SessionFactoryImpl sessionFactory = (SessionFactoryImpl) session.getSessionFactory();
		try{
			connection = sessionFactory.getConnectionProvider().getConnection();
		}
		catch (HibernateException | SQLException e)
		{
			e.printStackTrace();
		}
		return connection;
	}
}
