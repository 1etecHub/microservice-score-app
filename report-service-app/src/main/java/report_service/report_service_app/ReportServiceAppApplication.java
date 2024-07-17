package report_service.report_service_app;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;

@SpringBootApplication
@EnableDiscoveryClient
public class ReportServiceAppApplication {

	public static void main(String[] args) {
		SpringApplication.run(ReportServiceAppApplication.class, args);
	}

}
