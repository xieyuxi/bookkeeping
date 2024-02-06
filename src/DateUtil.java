import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;

/**
 * @projectName: bookkeeping
 * @package: PACKAGE_NAME
 * @className: DateUtil
 * @author: xieyuxi
 * @description: TODO
 * @date: 2024/2/6 0:25
 * @version: 1.0
 */
public class DateUtil {
    public static String YmdDateToString(Date date){
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
        String datestr = sdf.format(date);
        return datestr;
    }

    public static Date YmdStringToDate(String dateStr) {
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
        Date date = null;
        try {
            date = sdf.parse(dateStr);
        } catch (ParseException e) {
            System.out.println(dateStr+"日期格式"+"与yyyy-MM-dd不符");
            throw new RuntimeException(e);
        }
        return date;
    }
}
