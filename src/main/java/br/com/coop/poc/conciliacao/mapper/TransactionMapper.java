package br.com.coop.poc.conciliacao.mapper;

import br.com.coop.poc.conciliacao.dto.TransactionDTO;
import com.webcohesion.ofx4j.domain.data.common.Transaction;
import org.mapstruct.Mapper;

import java.util.List;

@Mapper(componentModel = "spring")
public interface TransactionMapper {

    TransactionDTO toDTO(Transaction transaction);

    List<TransactionDTO> toDTO(List<Transaction> transactions);
}
