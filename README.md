# 电影后台管理系统

## 项目采用技术

- [x] Spring+struts2+Hibernate整合
- [x] 前端全部采用easyui搭建
- [ ] Maven管理jar包
- [x] baseDao定义泛型抽象出基本dao操作

## 导入项目

### 导入SQL文件

进入到db文件夹，将ssh.sql文件导入到数据库中

### 修改数据库密码

修改hibernate.cfg.xml文件
```xml
<property name="hibernate.connection.username">root</property>
<property name="hibernate.connection.password">123456</property>
```


