package top.onektas.stompserver.tools;

import java.sql.ResultSet;
import java.sql.SQLException;

/**
 * ResultSet值转换String
 *
 * @onektas
 */
public class ResultSetTranslator {

    public String resultsetTranslator(ResultSet resultSet) {
        String result = "";
        String buwei = null;
        String xuewei = null;
        String bingzheng = null;
        while (true) {
            try {
                if (!resultSet.next()) break;
            } catch (SQLException e) {
                e.printStackTrace();
            }

            try {
                buwei = resultSet.getString("buwei");
            } catch (SQLException e) {
                e.printStackTrace();
            }
            try {
                xuewei = resultSet.getString("xuewei");
            } catch (SQLException e) {
                e.printStackTrace();
            }
            try {
                bingzheng = resultSet.getString("bingzheng");
            } catch (SQLException e) {
                e.printStackTrace();
            }

            result += "buwei: " + buwei + ", xuewei: " + xuewei + ", bingzheng: " + bingzheng + ", ";
        }
        result = result.replaceAll(", +$", "");
        return result;
    }

}
