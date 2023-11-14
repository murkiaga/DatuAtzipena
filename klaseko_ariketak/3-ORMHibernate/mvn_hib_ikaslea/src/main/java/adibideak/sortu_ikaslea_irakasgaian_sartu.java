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
import entitateak.Helbidea;
import entitateak.Telefonoa;

public class sortu_ikaslea_irakasgaian_sartu {

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
			System.out.println("Ikaslea sortu");
			Ikaslea ikaslea = new Ikaslea("Joseba", "Martin");
			NortasunAgiria nan = new NortasunAgiria("18926212L", "1995/02/44", "2031/06/19");
			Helbidea helbidea = new Helbidea("Lekeitio", "48280", "Kale 2", "1D");
			Telefonoa telf = new Telefonoa("Fijoa", "94 684 24 12");
			ikaslea.setHelbidea(helbidea);
			ikaslea.getTelefonoak().add(telf);
			telf = new Telefonoa("Mugikorra", "699 281 123");
			ikaslea.getTelefonoak().add(telf);
			
			Irakasgaia irakasgaia = session.get( Irakasgaia.class, "DA");
			
			session.beginTransaction();
			
			ikaslea.setNan(nan); //@OneToOne-en Cascade ALL jarri dugu eta beraz automatiko gordeko da NANa
			ikaslea.getIrakasgaiak().add(irakasgaia);
			
			
			System.out.println("Gorde ikaslea");
			session.persist(ikaslea);
			
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
