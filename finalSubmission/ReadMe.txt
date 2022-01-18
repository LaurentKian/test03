	1.关于数据库的设计：
根据题目的要求和数据库设计的三大范式，我选择设计成两个表格（city_info,weather)，这样在后续的操作中更加的便捷
1）一个存放城市的信息（city_info），设计id，name，lat，lon四个字段，因为城市id具有唯一性和不变性，所以我选择id为主键
2）另一个存放天气信息（weather），设计city_id,fxDate,tempMax,tempMin,textDay，五个字段。
因为我选择使用一个表格存放三天的信息，（city_id,fxDate)联合起来具有唯一性，所以我使用（city_id,fxDate)作为联合主键
根据要求我需要更新后两天的天气信息，所以我使用了SQL语句中的更新指令（replace),自动刷新已经存在的信息
	2.关于JDBC的设计：
因为考试周确实任务繁重，并没有做太多的相关准备，所以只能使用JDBC完成题目要求。spring、spring MVC、spring boot框架会更加的简洁，不需要再重复手动连接数据库，
SQL语句也会更加独立和清楚，我也在加紧学习中！分页查询也已经实现，但是因为查询数据很少，添加与否对结果没有影响；关于事务的功能也已经实现，并且已经开启
	3.两个类（city，weather）
两个类设立了相关的信息和set，get方法，为后续处理从API获取的信息打基础。
	4.关于城市信息的相关方法：
在getCItyData类中：
1）我设计可以根据城市的名称（getCityDataWebByName）或者城市的id（getCityDataWebById），来进行从处理从API获取的信息，通过方法（inputCItyData），将信息传入表中；
2）我设计可以根据城市的名称（fetchCityDataByName）或者城市的id（fetchCityDataById），来进行城市经纬度的查询，代码中已经有将Java对象转换成JSON格式，但是输出打印是注解状态，可以直接打开；
在mainBody类（即我们要操作的类）中：
1）为了防止我要查询的城市信息不存在，我把从网络抓取入库和查询的方法集成到一个方法中，形成（getCityInfoByName）和（getCityInfoById）
	5.关于城市天气的相关方法：
和上述获取城市信息方法类似，我用一致的方法根据城市的id从API抓取相关信息并且入库，唯一不同的是我在mainBody类中多了一个updateWea方法来更新对应的城市天气信息，城市的名称和id都可以，
代码中已经有将Java对象转换成JSON格式，但是输出打印是注解状态，可以直接打开
	6.关于mainBody类
这是工作类，集成了所有方法，并且通过while循环持续接受指令进行工作，直到输入0，结束工作。使用者只需要在这个类中操作即可

