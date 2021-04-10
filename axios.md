# Axios 

### 1.Axios的引言

~~~markdown
# axios
	Axios是一个异步请求技术
# 异步请求
	基于XMLHttpRequest对象发起都是异步请求
# 异步请求特点
	请求之后页面不动，响应回来更新的是页面的局部，多个请求之间互不影响，并行执行

# Axios在系统架构上的应用
	前后端分离架构系统   ----异步请求技术---->  Vue全家桶系列 前端技术端  Vue 淘汰了Jquery
~~~

### 2.Axios基本入门

#### 2.1 下载Axios 

下载地址 ：

https://unpkg.com/axios/dist/axios.min.js

使用：

```html
<script src="https://unpkg.com/axios/dist/axios.min.js"></script>
```

#### 2.2 Axios案例

##### 2.2.1 没有参数的请求

~~~java
@RestController
@RequestMapping("axios")
public class AxiosController{
    
    @GetMapping("findAll")
    @CrossOrigin          //允许请求跨域
    public List<User> findAll(){
        
        List<User> users = new ArrayList<>();
        users.add(new User("21","xiaoxiao",23,new Date()));
        users.add(new User("21","xiaoxiao",23,new Date()));  
        users.add(new User("21","xiaoxiao",23,new Date()));
        
        return users;
    }
}
~~~

~~~html
<script src="https://unpkg.com/axios/dist/axios.min.js"></script>
<script >
	//get方法请求
    axios.get("http://localhost:8888/axios/findAll")//发送请求的url
    	.then(function(response){
        	console.log(response.data);
    })//响应回来触发的回调函数
    	.catch(function(err){
        	console.log(err);
    })
</script>
~~~

---

##### 2.2.2 带有的参数的请求

~~~java
@RestController
@RequestMapping("axios")
public class AxiosController{
    
    @GetMapping("findAll")
    @CrossOrigin          //允许请求跨域
    public List<User> findAll(String username){
        
        List<User> users = new ArrayList<>();
        users.add(new User("21","xiaoxiao",23,new Date()));
        users.add(new User("21","xiaoxiao",23,new Date()));  
        users.add(new User("21","xiaoxiao",23,new Date()));
        
        return users;
    }
}
~~~

~~~html
<script src="https://unpkg.com/axios/dist/axios.min.js"></script>
<script >
	//get方法请求
    //发送请求的url
    axios.get("http://localhost:8888/axios/findAll?username=zhangsan")
    	.then(function(response){
        	console.log(response.data);
    })//响应回来触发的回调函数
    	.catch(function(err){
        	console.log(err);
    })
</script>
~~~

---

~~~markdown
# 总结
	1. axios在发送post方式的请求是传递的参数如果为对象类型，axios会自动将对象转为json格式的字符串使用 application/json  的请求向后端服务端口传递参数
	请求头必须是: application/www-x-form-urlencoded
	如果请求头是: appliaction/json 没有办法直接请求参数
	2. axios的post请求传递参数的两种方式
		第一种:使用字符串进行参数传递   "name=zhangsan&age=23"
		第二种:使用后端接口使用@ReuestBody注解形式接受参数
~~~

---

### 3.axios并发请求

~~~html
<script src="https://unpkg.com/axios/dist/axios.min.js"></script>
<script >
    //并发请求:在同一时间发送多个不同请求到后端服务，最后同一处理不同服务的响应结果
    
	function findall(){
        return axios.get("http://localhost:8888/axios/findAll");
    }
    
    function save(){
        return axios.post("http://localhost:8888/axios/save?username=zhangsan")
    }
    axios.all([findAll(),save()]).then(
    	axios.spread(function(result1,result2){  //用来统一处理多个并发请求的执行结果
            conslle.log(result1.data)
        })     
    )
</script>
~~~

~~~markdown
# 总结
	1. 针对于并发请求需要用到axios.all()函数来完成并发请求的处理
	2. 针对于并发请求的结果需要使用axios.apread()函数统一汇总请求结果
~~~

----

### 4. Axios的restful风格的API

~~~markdown
# Axios的API的总结
	axios.request(config)
	axios.delete(url[,config])
	axios.head(url[,config])
	axios.get(url[,config])
	axios.post(url[,data[,config]])
	axios.put(url[,data[,config]])
	axios.patch(url[,data[,config]])
	
# 
	在使用别名方法时，url，method，data这些属性都不必在配置中指定
~~~



