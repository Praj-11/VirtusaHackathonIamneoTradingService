import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/stocks")
public class StockController {
    private final StockService stockService;
    private final TransactionService transactionService;

    public StockController(StockService stockService, TransactionService transactionService) {
        this.stockService = stockService;
        this.transactionService = transactionService;
    }

    @GetMapping("/{symbol}")
    public Stock getStock(@PathVariable String symbol) {
        return stockService.getStockBySymbol(symbol);
    }

    @PostMapping("/transaction")
    public Transaction makeTransaction(@RequestBody Transaction transaction) {
        return transactionService.createTransaction(transaction);
    }

    // Additional endpoints if needed
}
