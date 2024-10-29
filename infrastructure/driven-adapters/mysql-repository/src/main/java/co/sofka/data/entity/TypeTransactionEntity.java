package co.sofka.data.entity;

import jakarta.persistence.*;
import lombok.Data;

@Data
@Entity
@Table(name = "type_transaction")
public class TypeTransactionEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column(nullable = false, unique = true)
    private String name;

    @Column(nullable = false)
    private String description;

}
