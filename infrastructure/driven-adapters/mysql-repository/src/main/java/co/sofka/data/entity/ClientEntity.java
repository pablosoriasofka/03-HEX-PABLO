package co.sofka.data.entity;


import co.sofka.data.commons.BaseEntity;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Table;
import lombok.Data;

@Data
@Entity
@Table(name = "client")
public class ClientEntity extends BaseEntity {


    @Column(nullable = false, unique = true)
    private String code;

    @Column(nullable = false)
    private String name;

    @Column(nullable = false)
    private String usuario;

    @Column(nullable = false)
    private String password;

    @Column(nullable = false)
    private String address;

    @Column(nullable = false)
    private String phone;
}
