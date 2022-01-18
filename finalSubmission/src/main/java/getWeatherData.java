import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class getWeatherData {

    public static void getWeather(int code) {

        String json1 = tool.doGet("https://devapi.qweather.com/v7/weather/3d?key=2a76e9d329e1499eadfce566522980d2&location=" + code);
        String json2 = json1;
        JSONObject jo1 = JSONObject.parseObject(json2);
        JSONArray weatherArray = (JSONArray) jo1.get("daily");
        int num = 0;
        weather[] array = new weather[3];
        while (num < 3) {
            JSONObject jo = (JSONObject) weatherArray.get(num);
            weather desired = new weather();
            desired.setId(Integer.toString(code));
            desired.setFxDate(jo.getString("fxDate"));
            desired.setTempMax(jo.getDouble("tempMax"));
            desired.setTempMin(jo.getDouble("tempMin"));
            desired.setTextDay(jo.getString("textDay"));
            array[num] = desired;
            num++;
        }
        inputWeatherData(array);
    }

    public static void inputWeatherData(weather[] desired) {
        Connection conn = tool.conn();
        PreparedStatement ps = null;
        ResultSet rs = null;
        try {
//                        将事务设计成手动提交
            conn.setAutoCommit(false);
            for (int i = 0; i < desired.length; i++) {
                String sql = "REPLACE INTO weather (city_id,fxDate,tempMax,tempMin,textDay) VALUES (?,?,?,?,?)";
//                System.out.println(conn);
                ps = conn.prepareStatement(sql);
                ps.setString(1, desired[i].getId());
                ps.setString(2, desired[i].getFxDate());
                ps.setDouble(3, desired[i].getTempMax());
                ps.setDouble(4, desired[i].getTempMin());
                ps.setString(5, desired[i].getTextDay());
                int a = ps.executeUpdate();
//                System.out.print("第"+(i+1)+"天 天气");
                System.out.println(a == 1 ? "成功入库" : "入库失败");
//                System.out.println("入库成功");
            }
//            手动提交事务
            conn.commit();

        } catch (SQLException e) {
//                        如果失败就选择回滚
            if(conn!=null){
                try {
                    conn.rollback();
                } catch (SQLException ex) {
                    ex.printStackTrace();
                }
            }
            e.printStackTrace();
        } finally {
            if (ps != null) {
                try {
                    ps.close();
                } catch (SQLException e) {
                    e.printStackTrace();
                }
            }
            if (conn != null) {
                try {
                    conn.close();
                } catch (SQLException e) {
                    e.printStackTrace();
                }
            }
        }
    }

    public static void find(String name) {
        Connection conn = tool.conn();
        PreparedStatement ps = null;
        ResultSet rs = null;
        System.out.println(" 城市名称：" + name);
        try {
            String sql = " select c.id,c.name,w.tempMax,w.tempMin,w.fxDate,w.textDay from city_info c join weather w on c.id=w.city_id and c.name=? limit 3 offset 0";
            ps = conn.prepareStatement(sql);
            ps.setString(1, name);
            rs = ps.executeQuery();
            List<weather> listWea = new ArrayList<weather>();
            int num = 0;
            while (rs.next()) {
                System.out.println("第" + (++num) + "天 城市代码：" + rs.getString("id") + " 最高气温：" + rs.getDouble("tempMax") + " 最低气温：" + rs.getDouble("tempMin"));
                hint(rs.getDouble("tempMax"), rs.getDouble("tempMin"));
                //将读取的数据存储到一个临时对象中
                weather temp = new weather(rs.getString("id"), rs.getString("fxDate"), rs.getDouble("tempMax"), rs.getDouble("tempMin"), rs.getString("textDay"));
                //将临时对象存到List中
                listWea.add(temp);
            }
            //将java对象转换成JSON格式
            String jsonOutput = JSON.toJSONString(listWea);
//            System.out.println(jsonOutput);
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            if (ps != null) {
                try {
                    ps.close();
                } catch (SQLException e) {
                    e.printStackTrace();
                }
            }
            if (conn != null) {
                try {
                    conn.close();
                } catch (SQLException e) {
                    e.printStackTrace();
                }
            }
        }
    }

    public static void hint(double tempMax, double tempMin) {
        if (tempMax > 25) System.out.print("温度很高！还不在空调屋里卷？");
        else if (tempMin < 5) System.out.print("温度很低，还不缩在暖气屋里卷？");
        if (tempMax - tempMin >= 10) {
            System.out.println("温差巨大，注意保暖嗷！");
        } else if (tempMax - tempMin <= 5) {
            System.out.println("温差较小，适合活动！ ");
        } else {
            System.out.println("温差适中");
        }
    }

}
