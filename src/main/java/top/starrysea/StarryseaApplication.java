package top.starrysea;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cache.annotation.EnableCaching;
import org.springframework.context.annotation.Bean;
import org.springframework.scheduling.annotation.EnableScheduling;

import top.starrysea.kql.facede.KumaRedisDao;
import top.starrysea.kql.facede.KumaRedisDaoImpl;
import top.starrysea.kql.facede.KumaSqlDao;
import top.starrysea.kql.facede.KumaSqlDaoImpl;

@SpringBootApplication
@EnableCaching
@EnableScheduling
public class StarryseaApplication {

	public static void main(String[] args) {
		SpringApplication.run(StarryseaApplication.class, args);
	}

	@Bean
	public KumaSqlDao getKumaSqlDao() {
		return new KumaSqlDaoImpl();
	}
	
	@Bean
	public KumaRedisDao getKumaRedisDao() {
		return new KumaRedisDaoImpl();
	}
}
