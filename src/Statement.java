import java.util.Date;

/**
 * @projectName: bookkeeping
 * @package: PACKAGE_NAME
 * @className: Statement
 * @author: xieyuxi
 * @description: The statement records the deatail of income and expense.
 * @date: 2024/2/5 17:24
 * @version: 1.0
 */
public class Statement {

    /**
     * Transaction Date
     */
    private int id;
    private Date date;

    /**
     * Transaction Type
     */
    private TransactionType type;

    /**
     * Transaction Description
     */
    private String desc;

    /**
     * Transaction Amount
     */
    private double amount;

    public Statement(int id, Date date, TransactionType type, String desc, double amount) {
        this.id = id;
        this.date = date;
        this.type = type;
        this.desc = desc;
        this.amount = amount;
    }

    public int getId() {
        return id;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    public TransactionType getType() {
        return type;
    }

    public void setType(TransactionType type) {
        this.type = type;
    }

    public String getDesc() {
        return desc;
    }

    public void setDesc(String desc) {
        this.desc = desc;
    }

    public double getAmount() {
        return amount;
    }

    public void setAmount(double amount) {
        this.amount = amount;
    }
}
