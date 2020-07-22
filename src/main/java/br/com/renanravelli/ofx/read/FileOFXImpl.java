package br.com.renanravelli.ofx.read;

import br.com.renanravelli.ofx.dto.TransactionDTO;
import br.com.renanravelli.ofx.mapper.TransactionMapper;
import com.webcohesion.ofx4j.domain.data.MessageSetType;
import com.webcohesion.ofx4j.domain.data.ResponseEnvelope;
import com.webcohesion.ofx4j.domain.data.banking.BankStatementResponseTransaction;
import com.webcohesion.ofx4j.domain.data.banking.BankingResponseMessageSet;
import com.webcohesion.ofx4j.domain.data.common.StatementResponse;
import com.webcohesion.ofx4j.domain.data.common.Transaction;
import com.webcohesion.ofx4j.domain.data.common.TransactionList;
import com.webcohesion.ofx4j.io.AggregateUnmarshaller;
import lombok.RequiredArgsConstructor;
import lombok.SneakyThrows;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

import java.io.FileInputStream;
import java.util.Collection;
import java.util.List;
import java.util.stream.Collectors;

@Slf4j
@Component
@RequiredArgsConstructor
public class FileOFXImpl implements FileOFX {

    private final TransactionMapper transactionMapper;

    @Override
    @SneakyThrows
    public List<TransactionDTO> parse(String directory) {
        var arquivo = new FileInputStream(directory);
        var unmarshaller = new AggregateUnmarshaller<>(ResponseEnvelope.class);
        var envelope = unmarshaller.unmarshal(arquivo);
        arquivo.close();

        var response = (BankingResponseMessageSet) envelope.getMessageSet(MessageSetType.banking);
        List<Transaction> transactions = response.getStatementResponses().stream()
                .map(BankStatementResponseTransaction::getMessage)
                .map(StatementResponse::getTransactionList)
                .map(TransactionList::getTransactions)
                .flatMap(Collection::stream)
                .collect(Collectors.toList());
        return this.transactionMapper.toDTO(transactions);
    }
}
