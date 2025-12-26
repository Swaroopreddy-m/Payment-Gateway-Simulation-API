@PostMapping("/refunds/{id}")
public ResponseEntity<?> refund(@PathVariable Long id) {

    Transaction txn = transactionRepository.findById(id)
            .orElseThrow(() -> new RuntimeException("Transaction not found"));

    if (!txn.getStatus().equals("SUCCESS")) {
        return ResponseEntity.badRequest().body("Refund not allowed");
    }

    Transaction refund = new Transaction();
    refund.setAmount(txn.getAmount());
    refund.setStatus("REFUNDED");
    refund.setTransactionType("REFUND");

    transactionRepository.save(refund);
    return ResponseEntity.ok(refund);
}
