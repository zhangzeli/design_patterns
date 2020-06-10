# chain of responsibility 责任链模式


# 业务场景
```text
1.在论坛发布文章
2.后台要经过处理才可以发表或者存入数据库
3.每条链都需要有权限去判断还继不继续往下执行
4.servlet filter实现
```

#v1 

#v2
```text
定义Filter接口
```

#v3
```text
单独抽象FilterChain，让他去处理整条链
```

#v4
```text
小技巧在添加FilterChain 的addfilter方法返回this,这样可以链式调用

FilterChain也需要实现Filter接口，然后可以addFilter不同的责任链
```

#v5 
```text
doFilter返回bool
```