# docker
## 1. 基本概念
### 1.1 镜像
* **下载镜像** 

```
#命令：
docker pull [镜像名称]

#eg.：
docker pull hello-world
```

* **查看镜像信息**

```
#命令：
docker images
```
* **搜索镜像**

```
#命令：
docker search [term]
```

* **删除镜像**

```
#命令：
docker rmi
```

* **创建镜像**

* [ ] 基于已有镜像创建

```
#命令：
docker commit

#eg.，test为仓库：
docker commit -m "新增新文件" -a "test author" test
```
* [ ] 基于本地模板导入
* [ ] 基于Dockerfile创建

* **存出镜像**（保存至本地文件）

```
#命令
docker save

#eg.：
docker save -o hello-world.zip hello-world
```

* **载入镜像**（从本地文件导入到本地镜像库）

```
#命令：
docker load < hello-world.zip

#或
docker load --input hello-world.zip
```

* **上传镜像**

```
#命令：
docker push hello-world
```

### 1.2 容器
容器是镜像运行到一个实例

* **新建容器**

```
#命令：
docker create 

#eg. 
docker create -it hello-world

#使用docker create 创建的容器处于停止状态，可以使用docker start命令启动它
#若使用docker run 命令，则等价于：先docker create 再docker start
```

* **终止容器**


```
#命令：
docker stop
```

* **删除容器**


```
#命令：
docker rm
```

* **查看运行中的容器的ip（如运行中的tomcat容器）**


```
#命令：
docker inspect --format='{{range .NetworkSettings.Networks}}{{.IPAddress}}{{end}}' [容器ID]
```

![](media/15391519315476/15395799970954.jpg)

* **宿主机与容器端口映射**

> 为什么有端口映射？
> 通过docker inspect命令获取到IP地址：
> 1. 这些IP地址是容器本地地址，宿主机不在此网段，无法访问
> 2. 这些IP地址在容器每次启动的时候都会改变


```
#命令：
docker run -p [宿主机映射端口]:[容器端口] [镜像]

#eg.
docker run -p 80:8080 tomcat

#宿主机访问地址：localhost:80，即可访问到启动的tomcat
```

### 1.3 仓库
存放镜像的地方

```
#eg.：
dl.dockerpool.com/ubuntu
[注册服务器地址]/[仓库名]
```

### 1.4 使用Dockfile创建镜像

> ***注意：镜像是只读的，因此在构建镜像的过程中不应该放入需要频繁修改的文件和配置***
> 1. 镜像都是分层的，每一个镜像都是基于一个基本镜像（如：Linux某个版本的内核）逐步构建而成
> 2. 在构建自定义镜像的过程中，每一步安装和配置都是在上一个镜像的基础上叠加一层形成新的镜像的过程
> 3. 一个容器的存储空间是有限的，所以并不适合将应用程序的持久化功能放入容器中

* **基本结构**

Dockerfile由一行行的命令语句组成。
Dockerfile分为四部分：基础镜像信息/维护者信息/镜像操作指令/容器启动时执行指令
![](media/15391519315476/15392487770458.jpg)
![](media/15391519315476/15392488092744.jpg)

```
# 基本信息注释

# 第一行指定基于的基础镜像
FROM [基础镜像]

# 维护者信息
MAINTAINER [维护者信息，如bing bing@test.com]

# 镜像操作指令（每运行一条RUN指令，镜像添加新的一层，并提交
RUN [操作镜像的指令，如新建一个文件夹]

# 运行容器时的操作命令
CMD [操作命令，如/home/bing/code/java/bin/java test-spring-boot.jar]
```

* **Dockerfile指令**

> 1. `FROM [image]:[tag]`
> 2. `MAINTAINER [name]`
> 3. `RUN [command]`，命令较长时，用\来换行
> 4. `CMD [command]`，每个Dockerfile只能有一条CMD指令，若多条只会执行最后一条
> 5. `EXPOSE [port][port]...`
> 6. `ENV [key] [value]`，指定一个环境变量，会被后续RUN指令使用，并在容器运行时保持
> 7. `ADD <src> <dest>`，src可以为Dockerfile所在目录的相对路径，也可以是一个URL，还可以是一个tar文件（自动解压为目录），如：`ADD     test.tar.gz /home/bing/test`
> 8. `COPY <src> <dest>`，同ADD，只是不会解压文件
> 9. And so on...

编写Dockerfile之后，可以通过docker build命令来创建镜像。

```
EXPOSE、-P、-p区别
EXPOSE：声明暴露哪些端口
-P：在容器启动时，根据暴露的端口，与宿主机的随机一个端口，做自动映射绑定
-p：在容器启动时，手动指定端口绑定规则
```



## 2. 使用gradle docker插件构建docker化应用
[](https://github.com/TheWalkingPanda/docker-test)https://github.com/TheWalkingPanda/docker-test