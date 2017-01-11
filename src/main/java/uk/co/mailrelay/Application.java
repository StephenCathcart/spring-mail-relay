package uk.co.mailrelay;

import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.boot.context.web.SpringBootServletInitializer;
import org.springframework.context.annotation.Bean;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

import uk.co.mailrelay.model.Account;
import uk.co.mailrelay.repository.AccountRepository;

/**
 * @author Stephen Cathcart
 *
 */
@SpringBootApplication
public class Application extends SpringBootServletInitializer {

	public static void main(String[] args) {
		SpringApplication.run(Application.class, args);
	}

	@Override
	protected SpringApplicationBuilder configure(SpringApplicationBuilder application) {
		return application.sources(Application.class);
	}

	@Bean
	CommandLineRunner init(final AccountRepository accountRepository) {
		return new CommandLineRunner() {
			@Override
			public void run(String... arg0) throws Exception {
				BCryptPasswordEncoder passwordEncoder = new BCryptPasswordEncoder();
				Account account = new Account("user", passwordEncoder.encode("M@i1R3laY"), true, true, true, true);
				accountRepository.save(account);
			}
		};
	}
}
