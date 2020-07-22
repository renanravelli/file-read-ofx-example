package br.com.renanravelli.ofx.read;

import br.com.renanravelli.ofx.dto.TransactionDTO;

import java.util.List;

public interface FileOFX {

    List<TransactionDTO> parse(String directory);
}
