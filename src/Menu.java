import java.util.Scanner;

/**
 * @projectName: bookkeeping
 * @package: PACKAGE_NAME
 * @className: Menu
 * @author: xieyuxi
 * @description: TODO
 * @date: 2024/2/5 23:52
 * @version: 1.0
 */
public class Menu {
    /**
     * @param :
     * @return void
     * @author xieyuxi
     * @description Display the 1-level menu
     * @date 2024/2/5 23:55
     */
    public static void run() {

        boolean isFlag = true;

        System.out.println("**********欢迎使用大富翁记账系统**********");

        StatementOperator statementOperator = new StatementOperator();

        Scanner scanner = new Scanner(System.in);
        while (isFlag) {
            System.out.println("------- 请输入操作编号：【0】~【5】-------");
            System.out.println("-------------【1】添加账务--------------");
            System.out.println("-------------【2】删除账务--------------");
            System.out.println("-------------【3】修改账务--------------");
            System.out.println("-------------【4】查询账务--------------");
            System.out.println("-------------【0】退出系统--------------");
            if (scanner.hasNextInt()) {
                int option = scanner.nextInt();
                switch (option) {
                    case 0:
                        System.out.println("系统正在退出！");
                        isFlag = false;
                        break;
                    case 1:
                        statementOperator.addStatement();
                        break;
                    case 2:
                        statementOperator.delStatement();
                        break;
                    case 3:
                        statementOperator.modifyStatement();
                        break;
                    case 4:
                        statementOperator.queryStatement();
                        break;
                    default:
                        System.out.println("输入有误，请重新输入：");
                }
            } else {
                System.out.println("输入有误，请重新输入：");
                scanner.next();
            }
        }
    }
}
