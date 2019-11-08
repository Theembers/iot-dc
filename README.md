## IoT-DC framework

> a IoT data collector framework and power by springboot+netty+rabbitmq/kafka
>
> 一个基于 springboot+netty+rabbitmq 实现的 物联网设备(IoT) 数据接入的项目

> **src**: https://github.com/Theembers/com.theembers.iot-dc
>
> **thanks for star! :) ⭐⭐⭐**

**the obsolete version (branch:s-0.1) will be not update yet (except bug fix), the master will be rebuild as new one. thanks follow!**

**旧版本以封版（分支s-0.1）不再更新（除了bug修改），master 分支将启用新的设计，敬请期待！**


[branch:s-0.1](https://github.com/Theembers/com.theembers.iot-dc/tree/s-0.1)  


## IoT platform framework (IoT平台总体架构设计)

![设备总体接入架构](https://image-1257148187.cos.ap-chengdu.myqcloud.com/picgo_img/20190926173357.jpg)

## about the new IoT-DC Framework (关于新版 IoT-DC Framework)

抽离了netty的实现逻辑，保留了基础框架

- iot-framework-dc 抽象了基础的接入逻辑模型。如下图：（待补全）
![新版框架图](https://image-1257148187.cos.ap-chengdu.myqcloud.com/picgo_img/20191107181542.jpg)

- router 模型 & device shadow 模型
![router 模型](https://image-1257148187.cos.ap-chengdu.myqcloud.com/picgo_img/20191108180557.jpg)



- iot-dc-netty-server 是之前的netty实现，目前（2019-11-07）只是迁移了老模块的代码，后期会修改结构，所以暂不做架构说明

