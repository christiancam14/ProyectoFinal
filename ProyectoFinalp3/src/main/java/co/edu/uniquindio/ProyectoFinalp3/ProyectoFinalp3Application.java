package co.edu.uniquindio.ProyectoFinalp3;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

@SpringBootApplication(scanBasePackages = "package co.edu.uniquindio.ProyectoFinalp3")
public class ProyectoFinalp3Application {

	public static void main(String[] args) {
		SpringApplication.run(ProyectoFinalp3Application.class, args);
		PasswordEncoder passwordEncoder = new BCryptPasswordEncoder();
		String rawPassword = "tu_contraseña";
		String encodedPassword = passwordEncoder.encode(rawPassword);
		System.out.println(encodedPassword);
	}

}
