package com.alura.desafio.consultaprecos;

import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import java.util.List;

@SpringBootApplication
public class ConsultaprecosApplication implements CommandLineRunner {

	public static void main(String[] args) {
		SpringApplication.run(ConsultaprecosApplication.class, args);
	}

	@Override
	public void run(String... args) throws Exception {
		System.out.println("Consulta de preços de veículos segundo a tabela FIPE");
		Menu menu = new Menu();
		menu.exibeMenu();

	}
}
