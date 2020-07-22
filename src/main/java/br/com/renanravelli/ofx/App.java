package br.com.renanravelli.ofx;

import br.com.renanravelli.ofx.dto.TransactionDTO;
import br.com.renanravelli.ofx.read.FileOFX;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import java.util.List;
import java.util.Objects;

@Slf4j
@SpringBootApplication
@RequiredArgsConstructor
public class App implements CommandLineRunner {

    private final FileOFX fileOFX;

    public static void main(String[] args) {
        SpringApplication.run(App.class, args);
    }

    @Override
    public void run(String... args) throws Exception {
        var directory = "./directory-of-file.ofx";
        List<TransactionDTO> transactions = fileOFX.parse(directory);
        transactions.stream()
                .map(Objects::toString)
                .forEach(log::info);
    }
}
