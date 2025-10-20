package Point1;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

public class Config {
    @Configuration
    public class Config {
        public Config() {
        }
        @Bean({"ConfigurationService1"})
        public Service1 service1() {
            return new Service1();
        }
        @Bean({"ConfigurationService2"})
        public Service2 service2() {
            return new Service2();
        }
    }

}
