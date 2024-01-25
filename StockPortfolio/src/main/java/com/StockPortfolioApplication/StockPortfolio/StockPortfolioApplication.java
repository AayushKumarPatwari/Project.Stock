package com.StockPortfolioApplication.StockPortfolio;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cache.annotation.EnableCaching;

@SpringBootApplication
//@EnableCaching
public class StockPortfolioApplication {

	public static void main(String[] args) {
		SpringApplication.run(StockPortfolioApplication.class, args);
	}

}
