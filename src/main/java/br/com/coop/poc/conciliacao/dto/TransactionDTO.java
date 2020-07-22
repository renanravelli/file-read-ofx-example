package br.com.coop.poc.conciliacao.dto;


import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.math.BigDecimal;
import java.util.Date;

@Getter
@Setter
@ToString
public class TransactionDTO {

    private TransactionType transactionType;
    private Date datePosted;
    private Date dateInitiated;
    private Date dateAvailable;
    private BigDecimal amount;
    private String id;
    private String correctionId;
    private String tempId;
    private String checkNumber;
    private String referenceNumber;
    private String standardIndustrialCode;
    private String payeeId;
    private String name;
    private String memo;

}
