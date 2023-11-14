package adibideak;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.boot.Metadata;
import org.hibernate.boot.MetadataSources;
import org.hibernate.boot.registry.StandardServiceRegistry;
import org.hibernate.boot.registry.StandardServiceRegistryBuilder;

import entitateak.Irakasgaia;
import entitateak.Ikaslea;
import entitateak.NortasunAgiria;
import entitateak.Nota;

public class sortu_irakasgaia {

	public static void main(String[] args) {
		StandardServiceRegistry standardRegistry = new StandardServiceRegistryBuilder()
				.configure( "hibernate.cfg.xml" )
			    .build();

		Metadata metadata = new MetadataSources( standardRegistry )
				.addAnnotatedClass( Irakasgaia.class )
				.addAnnotatedClass( Ikaslea.class )
				.addAnnotatedClass( NortasunAgiria.class )
				.addAnnotatedClass( Nota.class )
			    .getMetadataBuilder()
			    .build();

		SessionFactory sessionFactory = metadata.getSessionFactoryBuilder()
				.build();    
		
		Session session = sessionFactory.openSession();
		
		try {			
			System.out.println("Irakasgaia sortzen...");
			Irakasgaia irakasgaia = new Irakasgaia("DA", "Datu Atzipena");
			
			session.beginTransaction();
			
			System.out.println("Gorde irakasgaia");
			session.persist(irakasgaia);
			
			session.getTransaction().commit();
			System.out.println("Eginda!");
		}
		catch ( Exception e ) {
			System.out.println("Realizando Rollback");
			session.getTransaction().rollback();
			e.printStackTrace();
		}
		finally {
			session.close();
			sessionFactory.close();
		}

	}

}
