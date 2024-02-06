import java.util.*;

/**
 * @projectName: bookkeeping
 * @package: PACKAGE_NAME
 * @className: StatementOperator
 * @author: xieyuxi
 * @description: 交易明细的处理类
 * @date: 2024/2/5 23:36
 * @version: 1.0
 */
public class StatementOperator {

    /**
     * 定义一个全局数组列表变量，用于存储交易明细
     */
    private static ArrayList<Statement> statementList = new ArrayList<>();

    /**
     * @param :
     * @return void
     * @author xieyuxi
     * @description 记一笔明细
     * @date 2024/2/6 12:41
     */
    public void addStatement() {

        TransactionType type = null;

        Scanner scanner = new Scanner(System.in);
        System.out.println("请输入交易日期，注意格式【yyyy-mm-dd】，返回请输入【0】：");
        String dateStr = scanner.next();
        if (dateStr.equals("0")) {
            return;
        }
        Date date = DateUtil.YmdStringToDate(dateStr);
        System.out.println("请输入交易类型的编号：【1】收入, 【2】支出，【0】返回");
        switch (scanner.nextInt()) {
            case 0:
                break;
            case 1:
                type = TransactionType.INCOME;
                break;
            case 2:
                type = TransactionType.EXPENSE;
                break;
            case 3:
                System.out.println("您输入的类型有误！");
        }
        System.out.println("请输入交易摘要：");
        String desc = scanner.next();
        System.out.println("请输入交易金额：");
        double amount = scanner.nextDouble();

        Statement statement = new Statement(statementList.size() + 1, date, type, desc, amount);

        if (statementList.add(statement)) {
            System.out.println("添加成功");
        } else {
            System.out.println("删除失败");
        }

    }


    /**
     * @param :
     * @return void
     * @author xieyuxi
     * @description 展示所有交易明细
     * @date 2024/2/6 14:35
     */
    public boolean showAllList() {
        if (!statementList.isEmpty()) {
            printDetails(statementList);
            return true;
        } else {
            System.out.println("目前没有明细");
            return false;
        }
    }

    /**
     * @param :
     * @return void
     * @author xieyuxi
     * @description 删除交易明细
     * @date 2024/2/6 15:14
     */
    public void delStatement() {

        int option;
        if (!showAllList()) {
            return;
        }

        Scanner scanner = new Scanner(System.in);
        Statement statement;

        while (true) {
            System.out.println("请输入您要删除的明细的ID号，返回请输入【0】：");
            option = scanner.nextInt();
            if (option == 0) {
                return;
            }

            statement = getStatementByID(option);
            if (statement != null) {
                System.out.println("是否确定删除ID为 " + option + " 的明细，请输入【是】或【否】");
                if (scanner.next().equals("是")) {
                    if (statementList.remove(statement)) {
                        System.out.println("删除成功！");
                        break;
                    } else {
                        System.out.println("删除失败！");
                    }
                } else {
                    System.out.println("序号不存在，请重新输入：");
                }
            }
        }
    }

    public void modifyStatement() {

        boolean isFlag = true;
        int option;
        if (!showAllList()) {
            return;
        }

        Scanner scanner = new Scanner(System.in);
        Statement statement;

        while (isFlag) {
            System.out.println("请输入您要修改的明细的ID号，返回请输入【0】：");
            option = scanner.nextInt();
            if (option == 0) {
                return;
            }
            statement = getStatementByID(option);
            if (statement != null) {
                System.out.println("请输入您要修改内容的编号：【1】日期，【2】类型，【3】描述，【4】金额，【0】返回");
                option = scanner.nextInt();
                switch (option) {
                    case 0:
                        isFlag = false;
                        break;
                    case 1:
                        modifyStatementDate(statement);
                        isFlag = false;
                        break;
                    case 2:
                        modifyStatementType(statement);
                        isFlag = false;
                        break;
                    case 3:
                        modifyStatementDesc(statement);
                        isFlag = false;
                        break;
                    case 4:
                        modifyStatementAmount(statement);
                        isFlag = false;
                        break;
                    default:
                        System.out.println("您的选择有误！");
                }
            } else {
                System.out.println("序号不存在，请重新输入：");
            }
            System.out.println("修改成功！");
        }
    }

    private void modifyStatementAmount(Statement statement) {
        Scanner scanner = new Scanner(System.in);
        System.out.println("请输入新的交易金额：");
        statement.setAmount(scanner.nextDouble());
    }

    private void modifyStatementDate(Statement statement) {
        Scanner scanner = new Scanner(System.in);
        System.out.println("请输入新的交易日期，注意格式【yyyy-mm-dd】：");
        Date date = DateUtil.YmdStringToDate(scanner.next());
        statement.setDate(date);
    }

    private void modifyStatementType(Statement statement) {
        Scanner scanner = new Scanner(System.in);
        System.out.println("请输入新的交易类型编号：【1】收入，【2】支出，【0】返回");
        switch (scanner.nextInt()) {
            case 0:
                break;
            case 1:
                statement.setType(TransactionType.INCOME);
                break;
            case 2:
                statement.setType(TransactionType.EXPENSE);
                break;
            default:
                System.out.println("您输入的类型有误！");
        }
    }

    private void modifyStatementDesc(Statement statement) {
        Scanner scanner = new Scanner(System.in);
        System.out.println("请输入新的交易描述：");
        String newDesc = scanner.next();
        System.out.println(newDesc);
        statement.setDesc(newDesc);
        System.out.println(statement.getDesc());
    }

    public Statement getStatementByID(int id) {
        for (Statement statement : statementList) {
            if (statement.getId() == id) {
                return statement;
            } else {
                System.out.println("不存在ID号为 " + id + " 的明细！");
            }
        }
        return null;
    }

    public void getStatementByType(TransactionType type) {
        ArrayList<Statement> queryList = new ArrayList<>();
        for (Statement statement : statementList) {
            if (statement.getType().equals(type)) {
                queryList.add(statement);
            }
        }
        printDetails(queryList);
    }

    /**
     * @param statementList:
     * @return void
     * @author xieyuxi
     * @description 打印传入列表的明细
     * @date 2024/2/6 14:37
     */
    public void printDetails(ArrayList<Statement> statementList) {

        double incomeSum = 0.0;
        double expenseSum = 0.0;

        System.out.println("------------------------账单明细------------------------------");
        System.out.println("ID\t日期\t\t\t\t类型\t\t\t描述\t\t\t\t\t金额\t\t");
        System.out.println("-------------------------------------------------------------");
        for (Statement state : statementList) {
            System.out.printf("%-4d%-16s%-11s%-15s%8s\n", state.getId(), DateUtil.YmdDateToString(state.getDate()),
                    state.getType().getName(), state.getDesc(), state.getAmount());
            if (state.getType().equals(TransactionType.INCOME)) {
                incomeSum = incomeSum + state.getAmount();
            } else {
                expenseSum = expenseSum + state.getAmount();
            }
        }
        System.out.println("-------------------------------------------------------------");
        System.out.println("总收入: " + incomeSum + "\t\t\t\t\t\t总支出: " + expenseSum);
        System.out.println();
    }


    public void queryByDate() {

        Scanner sc = new Scanner(System.in);
        System.out.println("请输入查询的起始日期，注意格式【yyyy-mm-dd】：");
        Date startDate = DateUtil.YmdStringToDate(sc.next());

        System.out.println("请输入查询的结束日期，注意格式【yyyy-mm-dd】：");
        Date endDate = DateUtil.YmdStringToDate(sc.next());

        ArrayList<Statement> queryList = new ArrayList<>();
        for (Statement statement : statementList) {
            if (statement.getDate().after(startDate) && statement.getDate().before(endDate)) {
                queryList.add(statement);
            }
        }
        printDetails(queryList);
    }


    public void queryStatement() {
        Scanner scanner = new Scanner(System.in);
        System.out.println("请输入查询方式编号：【1】查询所有明细，【2】按日期区间查询，【3】按交易类型查询，【0】返回");
        switch (scanner.nextInt()) {
            case 0:
                break;
            case 1:
                showAllList();
                break;
            case 2:
                queryByDate();
                break;
            case 3:
                queryByType();
                break;
            case 4:
                System.out.println("您输入的类型有误！");
        }

    }

    private void queryByType() {
        Scanner scanner = new Scanner(System.in);
        System.out.println("请输入要查询类型的编号：【1】按收入，【2】按支出，【0】返回");
        switch (scanner.nextInt()) {
            case 0:
                break;
            case 1:
                getStatementByType(TransactionType.INCOME);
                break;
            case 2:
                getStatementByType(TransactionType.EXPENSE);
                break;
            default:
                System.out.println("您输入的类型有误！");
        }
    }
}
