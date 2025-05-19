package com.example.exam_final_jee;

import com.example.exam_final_jee.entities.Customer;
import com.example.exam_final_jee.repo.CreditRepository;
import com.example.exam_final_jee.repo.CustomerRepository;
import com.example.exam_final_jee.repo.RepaymentRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

import java.util.stream.Stream;

@SpringBootApplication
public class ExamFinalJeeApplication {

	public static void main(String[] args) {
		SpringApplication.run(ExamFinalJeeApplication.class, args);
	}

	@Bean
	CommandLineRunner start(CustomerRepository customerRepository, CreditRepository creditRepository, RepaymentRepository repaymentRepository){
		return args -> {
			Stream.of("Youssef","Mohammed","Adil").forEach(name -> {
				Customer customer = new Customer();
				customer.setName(name);
				customer.setEmail(name+"@gmail.com");
				customerRepository.save(customer);
			});
		};
	}
}
