package br.com.coop.poc.conciliacao;

import br.com.coop.poc.conciliacao.dto.TransactionDTO;
import br.com.coop.poc.conciliacao.read.FileOFX;
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
