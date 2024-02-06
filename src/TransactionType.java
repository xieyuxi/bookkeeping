/**
 * @projectName: bookkeeping
 * @package: PACKAGE_NAME
 * @className: TransactionType
 * @author: xieyuxi
 * @description: The transactionType includes income and expense.
 * @date: 2024/2/5 17:24
 * @version: 1.0
 */
public enum TransactionType {
    INCOME("收入"), EXPENSE("支出");

    private String name;

    TransactionType(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
