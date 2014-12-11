package it.padova.sanita.restbackend.dao;

import it.padova.sanita.restbackend.model.Contact;
import it.padova.sanita.restbackend.model.Demo;

import java.sql.CallableStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import javax.transaction.Transactional;

import oracle.jdbc.internal.OracleTypes;

import org.hibernate.HibernateException;
import org.hibernate.Query;

import com.google.gson.Gson;

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
	public String saveOrUpdateViaStoredPro(Contact contact) throws Exception
	{
		//http://stackoverflow.com/questions/13015749/getting-a-result-back-from-a-stored-procedure-in-java
		String ret = "";
		CallableStatement callableStatement = null;
		try{
			callableStatement = getConnection().prepareCall("call INSERTCONTACTRETURNCURSOR(?,?,?,?)");
			callableStatement.setString(1,contact.getEmail());
			callableStatement.setString(2,contact.getName());
			callableStatement.setString(3, contact.getPhoneNumber());

			//callableStatement.registerOutParameter(4, java.sql.Types.VARCHAR);

			callableStatement.registerOutParameter(4, OracleTypes.CURSOR);
			callableStatement.execute();

			//String result = callableStatement.getString(4);
			//System.out.println(result);

			ResultSet rs = (ResultSet)callableStatement.getObject(4);
			ArrayList<Demo> array = new ArrayList<Demo>();
			while(rs.next()){
				Demo demo = new Demo();
				demo.setAnno(rs.getString("ANNO"));
				demo.setMese(rs.getString("MESE"));
				demo.setGiorno(rs.getString("GIORNO"));
				array.add(demo);
			}
			rs.close();
			Gson gson = new Gson();
			ret = gson.toJson(array);

		}catch(SQLException e){
			e.printStackTrace();
		}
		
		return ret;
	}
}
