package bankingApp.Transactions;

import org.hibernate.annotations.GenericGenerator;
import org.hibernate.annotations.Type;

import javax.persistence.*;
import java.time.ZonedDateTime;
import java.time.format.DateTimeFormatter;
import java.util.UUID;

@Entity
@Table(name = "transactions")
public class Transaction {

    @Id
    @GeneratedValue(generator = "UUID")
    @GenericGenerator(name = "UUID", strategy = "org.hibernate.id.UUIDGenerator")
    @Column(name = "transaction_id", updatable = false, nullable = false)
    @Type(type="pg-uuid")
    private UUID id;

    @Column(name = "sending_account_id")
    private UUID sendingAccount;

    @Column(name = "receiving_account_id")
    private UUID receivingAccount;

    @Enumerated(EnumType.STRING)
    @Column(name = "transfer_type")
    private TransactionUtils.TransferType transferType;

    private double amount;

    @Column(name = "transaction_time")
    private ZonedDateTime transactionTime;

    @Enumerated(EnumType.STRING)
    @Column(name = "transaction_location")
    private TransactionUtils.Country transactionLocation;

    @Enumerated(EnumType.STRING)
    @Column(name = "device_type")
    private TransactionUtils.DeviceType device;

    @Enumerated(EnumType.STRING)
    @Column(name = "payment_method")
    private TransactionUtils.PaymentMethod paymentMethod;


    public Transaction() {}

    public Transaction(TransactionUtils.TransferType type, double amount, UUID sendingAccount,
                       UUID receivingAccount, ZonedDateTime transactionTime,
                       TransactionUtils.Country transactionLocation, TransactionUtils.DeviceType device,
                       TransactionUtils.PaymentMethod paymentMethod) {
        this.sendingAccount = sendingAccount;
        this.receivingAccount = receivingAccount;
        this.transferType = type;
        this.amount = amount;
        this.transactionTime = transactionTime;
        this.transactionLocation = transactionLocation;
        this.device = device;
        this.paymentMethod = paymentMethod;
    }


    // Getters

    public TransactionUtils.TransferType getTransferType() {
        return transferType;
    }

    public double getAmount() {
        return amount;
    }

    public UUID getReceivingAccount() { return receivingAccount; }

    public ZonedDateTime getTransactionTime() {
        return transactionTime;
    }

    public TransactionUtils.Country getTransactionLocation() {
        return transactionLocation;
    }

    public TransactionUtils.DeviceType getDevice() {
        return device;
    }

    public TransactionUtils.PaymentMethod getPaymentMethod() {
        return paymentMethod;
    }

    public UUID getId() { return id; }

    public void setId(UUID id) { this.id = id; }


    // Setters

    public void setTransactionTime(ZonedDateTime transactionTime) {
        this.transactionTime = transactionTime;
    }
    public void setTransferType(TransactionUtils.TransferType transferType) {
        this.transferType = transferType;
    }

    public void setAmount(double amount) {
        this.amount = amount;
    }

    public void setReceivingAccount(UUID destinationAccount) {
        this.receivingAccount = receivingAccount;
    }

    public void setTransactionLocation(TransactionUtils.Country transactionLocation) {
        this.transactionLocation = transactionLocation;
    }

    public void setDevice(TransactionUtils.DeviceType device) {
        this.device = device;
    }

    public void setPaymentMethod(TransactionUtils.PaymentMethod paymentMethod) {
        this.paymentMethod = paymentMethod;
    }


    @Override
    public String toString() {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd MMM uuuu HH:mm:ss z");
        String formattedTime = transactionTime.format(formatter);
        return "Transaction Details:\n" +
                "-------------------\n" +
                "Sending Account: " + sendingAccount + "\n" + // Corrected from Account Number
                "Receiving Account: " + receivingAccount + "\n" + // Corrected from Destination Account
                "Type: " + transferType + "\n" +
                "Amount: $" + String.format("%.2f", amount) + "\n" +
                "Transaction Time: " + formattedTime + "\n" +
                "Transaction Location: " + transactionLocation + "\n" +
                "Device: " + device + "\n" +
                "Payment Method: " + paymentMethod + "\n";
    }

}
