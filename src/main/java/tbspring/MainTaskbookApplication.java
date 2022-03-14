package tbspring;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.jdbc.DataSourceAutoConfiguration;
import org.springframework.context.annotation.ComponentScan;

@SpringBootApplication (/*exclude = {DataSourceAutoConfiguration.class}*/)
//@ComponentScan(basePackages = "tbspring")
public class MainTaskbookApplication {

    public static void main(String[] args) {
        SpringApplication.run(MainTaskbookApplication.class, args);
    }

}
