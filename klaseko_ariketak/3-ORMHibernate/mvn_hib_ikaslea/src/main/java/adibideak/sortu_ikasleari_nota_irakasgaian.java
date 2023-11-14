package adibideak;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.boot.Metadata;
import org.hibernate.boot.MetadataSources;
import org.hibernate.boot.registry.StandardServiceRegistry;
import org.hibernate.boot.registry.StandardServiceRegistryBuilder;

import entitateak.Ikaslea;
import entitateak.Irakasgaia;
import entitateak.NortasunAgiria;
import entitateak.Nota;

public class sortu_ikasleari_nota_irakasgaian {

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
			Irakasgaia irakasgaia = new Irakasgaia("SEIN", "Segurtasun Informatikoa");
			Ikaslea ikaslea = session.get(Ikaslea.class, 1); //ID=1 duen ikaslea
			
			System.out.println("Nota sortzen...");
			Nota nota = new Nota(ikaslea, irakasgaia, 6);
			
			session.beginTransaction();
			
			System.out.println("Gorde Nota");
			session.persist(nota);
			
			ikaslea.getIrakasgaiak().add(irakasgaia);
			
			session.getTransaction().commit();
			System.out.println("Eginda!");
			
			System.out.println("Kontsultatu ikasle eta bere notak.");
			ikaslea = session.get(Ikaslea.class, 1); //ID=1 duen ikaslea
			System.out.println(ikaslea.getNotak());
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
