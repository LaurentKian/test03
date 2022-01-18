import java.util.Scanner;

public class mainBody {
    public static void main(String[] args) {
//        getCityData.getCityDataWebByName("北京");
//        getCityData.getCityDataWebByName("上海");
//        getCityData.getCityDataWebByName("福州");
//        getWea("北京");
//        getWea("上海");
//        getWea("福州");
        work();
    }

    //一个判断输入的方法
    public static void work() {
        Scanner sc = new Scanner(System.in);
        boolean res = true;
        while (res) {
            System.out.println();
            System.out.println("请选择指令：");
            System.out.println("根据名称查询城市信息请输入1");
            System.out.println("根据城市id查询城市信息请输入2");
            System.out.println("根据城市名称删除城市信息请输入3");
            System.out.println("查询三日天气请输入4");
            System.out.println("更新三日天气请输入5");
            System.out.println("退出服务请输入0");
            String option = sc.nextLine();

            if (option.equals("1")) {
                System.out.println("请输入城市中文名称");
                String name = sc.nextLine();
                getCityInfoByName(name);
                getCityData.fetchCityDataByName(name);
            } else if (option.equals("2")) {
                System.out.println("请输入城市id");
                int id = sc.nextInt();
                getCityData.getCityDataWebById(id);
                getCityData.fetchCityDataById(id);
            } else if(option.equals("3")){
                System.out.println("请输入城市中文名称");
                String name = sc.nextLine();
                getCityData.deleteCity(name);
            }
            else if (option.equals("4")) {
                System.out.println("请输入城市中文名称");
                String name = sc.nextLine();
                getWea(name);
            } else if (option.equals("5")) {
                System.out.println("请输入城市中文名称");
                String name = sc.nextLine();
                updateWea(name);
            } else if (option.equals("0")) {
                res = false;
            } else System.out.println("请重新输入");
        }
        sc.close();
    }

    //从数据库获得信息的方法
    public static void getWea(String name) {
        updateWea(name);
        getWeatherData.find(name);
    }

    //用城市的名称获得城市经纬度的方法
    public static void getCityInfoByName(String name) {
        getCityData.getCityDataWebByName(name);
//        getCityData.fetchCityDataByName(name);
    }

    //用城市的id获取城市经纬度的方法
    public static void getCityInfoById(int id) {
        getCityData.getCityDataWebById(id);
        getCityData.fetchCityDataById(id);
    }

    //更新城市天气信息的方法
    public static void updateWea(String name) {
        int id = getCityData.getCityDataWebByName(name);
        getWeatherData.getWeather(id);
    }

    public static void updateWea(int id) {
        getWeatherData.getWeather(id);
    }


}
