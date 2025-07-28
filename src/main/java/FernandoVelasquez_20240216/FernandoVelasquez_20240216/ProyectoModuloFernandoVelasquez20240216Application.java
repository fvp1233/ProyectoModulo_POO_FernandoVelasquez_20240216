package FernandoVelasquez_20240216.FernandoVelasquez_20240216;

import io.github.cdimascio.dotenv.Dotenv;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class ProyectoModuloFernandoVelasquez20240216Application {

	public static void main(String[] args) {

		//Cargar mi archivo .env sobre .application.properties
		Dotenv dotenv = Dotenv.configure().ignoreIfMissing().load();
		dotenv.entries().forEach(entry ->
				System.setProperty(entry.getKey(), entry.getValue())
				);
		//Carga el proyectp
		SpringApplication.run(ProyectoModuloFernandoVelasquez20240216Application.class, args);
	}

}
