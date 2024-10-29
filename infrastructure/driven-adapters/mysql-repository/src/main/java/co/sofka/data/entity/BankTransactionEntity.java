package co.sofka.data.entity;



import jakarta.persistence.*;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

@Data
@Entity
@Table(name = "bank_transaction")
@NoArgsConstructor
public class BankTransactionEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @ManyToOne
    @JoinColumn(name = "origin_account_id", nullable = false)
    private AccountEntity originAccount;

    @ManyToOne
    @JoinColumn(name = "destination_account_id", nullable = false)
    private AccountEntity destinationAccount;

    @Column(nullable = false)
    private Double amount;

    @ManyToOne
    @JoinColumn(name = "type_transaction_id", nullable = false)
    private TypeTransactionEntity typeTransaction;

    @Column(name = "created_at", nullable = false)
    private Date createdAt;
}
