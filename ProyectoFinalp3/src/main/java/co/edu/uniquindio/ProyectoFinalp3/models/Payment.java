package co.edu.uniquindio.ProyectoFinalp3.models;

import jakarta.persistence.*;

import java.math.BigDecimal;
import java.util.Date;

@Entity
@Table(name = "payments")
public class Payment {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private BigDecimal amount;
    private Date paymentDate;

    @ManyToOne
    @JoinColumn(name = "order_id")
    private Order order;

    // Constructor vacío
    public Payment() {}

    // Constructor con parámetros
    public Payment(BigDecimal amount, Date paymentDate, Order order) {
        this.amount = amount;
        this.paymentDate = paymentDate;
        this.order = order;
    }

    // Getter y Setter
    public Long getId() { return id; }
    public void setId(Long id) { this.id = id; }

    public BigDecimal getAmount() { return amount; }
    public void setAmount(BigDecimal amount) { this.amount = amount; }

    public Date getPaymentDate() { return paymentDate; }
    public void setPaymentDate(Date paymentDate) { this.paymentDate = paymentDate; }

    public Order getOrder() { return order; }
    public void setOrder(Order order) { this.order = order; }
}

