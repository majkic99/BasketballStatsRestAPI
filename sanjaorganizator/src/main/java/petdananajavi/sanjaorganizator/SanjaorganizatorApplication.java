package petdananajavi.sanjaorganizator;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import petdananajavi.sanjaorganizator.model.Storage;

import java.io.FileNotFoundException;

@SpringBootApplication
public class SanjaorganizatorApplication {

    public static void main(String[] args) throws FileNotFoundException {
        Storage.getInstance().loadEverything();
        SpringApplication.run(SanjaorganizatorApplication.class, args);
    }

}
