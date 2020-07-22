package br.com.coop.poc.conciliacao.read;

import br.com.coop.poc.conciliacao.dto.TransactionDTO;

import java.util.List;

public interface FileOFX {

    List<TransactionDTO> parse(String directory);
}
