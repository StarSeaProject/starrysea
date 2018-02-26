package top.starrysea;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cache.annotation.EnableCaching;
import org.springframework.context.annotation.Bean;

import top.starrysea.kql.facede.KumaSqlDao;
import top.starrysea.kql.facede.KumaSqlDaoImpl;

@SpringBootApplication
@EnableCaching
public class StarrtseaApplication {

	public static void main(String[] args) {
		SpringApplication.run(StarrtseaApplication.class, args);
	}

	@Bean
	public KumaSqlDao getKumaSqlDao() {
		return new KumaSqlDaoImpl();
	}
}
