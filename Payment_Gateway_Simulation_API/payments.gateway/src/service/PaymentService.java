

@Service
public class PaymentService {

    public Transaction processPayment(PaymentRequest request, User user) {

        Transaction txn = new Transaction();
        txn.setUser(user);
        txn.setAmount(request.getAmount());
        txn.setTransactionType("PAYMENT");

        if (!LuhnValidator.isValid(request.getCardNumber())) {
            txn.setStatus("FAILED");
        } else {
            String[] statuses = {"SUCCESS", "PENDING", "FAILED"};
            txn.setStatus(statuses[new Random().nextInt(statuses.length)]);
        }

        return transactionRepository.save(txn);
    }
}
