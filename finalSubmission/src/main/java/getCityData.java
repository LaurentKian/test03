import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import org.apache.http.HttpEntity;
import org.apache.http.ParseException;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.util.EntityUtils;

import java.io.IOException;
import java.sql.*;

public class getCityData {

    //从网页根据城市的名称获取城市的信息
    public static int getCityDataWebByName(String name) {
        //和风天气的url
        String json1 = tool.doGet("https://geoapi.qweather.com/v2/city/lookup?key=2a76e9d329e1499eadfce566522980d2&location=" + name);
        //处理json的过程如下
        String json2 = json1;
        JSONObject jo1 = JSONObject.parseObject(json2);
        //拆分从api获取的信息，选择我们需要的信息
        JSONArray cityArray = (JSONArray) jo1.get("location");
        JSONObject jo = (JSONObject) cityArray.get(0);
        city desired = new city();
        desired.setName(jo.getString("name"));
        desired.setId(jo.getInteger("id"));
        desired.setLat(jo.getDouble("lat"));
        desired.setLon(jo.getDouble("lon"));
        inputCItyData(desired);
        //后续需要城市的id进行其他关于天气的操作
        return jo.getInteger("id");
    }

    //从网页根据城市的id获取城市的信息
    public static String getCityDataWebById(int id) {
        String json1 = tool.doGet("https://geoapi.qweather.com/v2/city/lookup?key=2a76e9d329e1499eadfce566522980d2&location=" + id);
        //处理json的过程如下
        String json2 = json1;
        JSONObject jo1 = JSONObject.parseObject(json2);
        //拆分从api获取的信息，选择我们需要的信息
        JSONArray cityArray = (JSONArray) jo1.get("location");
        JSONObject jo = (JSONObject) cityArray.get(0);
        city desired = new city();
        desired.setName(jo.getString("name"));
        desired.setId(jo.getInteger("id"));
        desired.setLat(jo.getDouble("lat"));
        desired.setLon(jo.getDouble("lon"));
        inputCItyData(desired);
        //后续需要城市的id进行其他关于天气的操作
        return jo.getString("name");
    }

    public static void inputCItyData(city desired) {
        //将城市信息传入到数据库中，使用JDBC方法
        Connection conn = tool.conn();
        PreparedStatement ps = null;
        ResultSet rs = null;
        try {
//                        将事务设计成手动提交
            conn.setAutoCommit(false);
//            插入数据的的SQL语句，使用了占位符便于信息的传递和防止SQL注入
            String sql = "replace into city_info (id,name,lat,lon) values (?,?,?,?)";
            ps = conn.prepareStatement(sql);
            ps.setDouble(1, desired.getId());
            ps.setString(2, desired.getName());
            ps.setDouble(3, desired.getLat());
            ps.setDouble(4, desired.getLon());
            int a = ps.executeUpdate();
            System.out.println(a == 1 ? "城市信息成功入库" : "城市信息未入库");
//                        手动提交事务
            conn.commit();
        } catch (SQLException e) {
            //            如果失败就选择回滚
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

    public static void fetchCityDataByName(String name) {
        Connection conn = tool.conn();
        PreparedStatement ps = null;
        ResultSet rs = null;
        try {
            String sql = "select * from city_info where name = ?";
            ps = conn.prepareStatement(sql);
            ps.setString(1, name);
            rs = ps.executeQuery();
            city temp = new city();
            while (rs.next()) {
                //为转换成JSON格式做准备
                temp.setName(rs.getString("name"));
                temp.setId(rs.getInt("id"));
                temp.setLat(rs.getDouble("lat"));
                temp.setLon(rs.getDouble("lon"));
//                输出的操作
                System.out.println("城市名称：" + rs.getString("name") + " 城市id：" + rs.getInt("id") + " 经度：" + rs.getDouble("lat") + " 纬度：" + rs.getDouble("lon"));
            }
//            将Java对象转换成JSON格式
            String jsonOutPut = JSON.toJSONString(temp);
//            System.out.println(jsonOutPut);

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

    //根据城市的id查询城市的信息
    public static void fetchCityDataById(int id) {
        Connection conn = tool.conn();
        PreparedStatement ps = null;
        ResultSet rs = null;
        try {
            String sql = "select * from city_info where id = ? limit 1 offset 0";
            ps = conn.prepareStatement(sql);
            ps.setInt(1, id);
            rs = ps.executeQuery();
            city temp = new city();
            while (rs.next()) {
//                将Java对象转换成JSON格式
                temp.setName(rs.getString("name"));
                temp.setId(rs.getInt("id"));
                temp.setLat(rs.getDouble("lat"));
                temp.setLon(rs.getDouble("lon"));
                //输出的操作
                System.out.println("城市名称：" + rs.getString("name") + " 城市id：" + rs.getInt("id") + " 经度：" + rs.getDouble("lat") + " 纬度：" + rs.getDouble("lon"));
            }
//            将Java对象转换成JSON格式
            String jsonOutPut = JSON.toJSONString(temp);
//            System.out.println(jsonOutPut);
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

    //删除城市信息
    public static void deleteCity(String name) {
        //将城市信息传入到数据库中，使用JDBC方法
        Connection conn = tool.conn();
        PreparedStatement ps = null;
        ResultSet rs = null;
        try {
//                        将事务设计成手动提交
//            conn.setAutoCommit(false);
//            插入数据的的SQL语句，使用了占位符便于信息的传递和防止SQL注入
            String sql = "delete from city_info where name = ?";
            ps = conn.prepareStatement(sql);
            ps.setString(1, name);
            int a = ps.executeUpdate();
            System.out.println(a == 1 ? "删除成功" : "删除失败");
//                        手动提交事务
//            conn.commit();
        } catch (SQLException e) {
            //            如果失败就选择回滚
//            if(conn!=null){
//                try {
//                    conn.rollback();
//                } catch (SQLException ex) {
//                    ex.printStackTrace();
//                }
//            }
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

}
