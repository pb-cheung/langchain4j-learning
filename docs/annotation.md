
## @Bean

标记一个方法的返回值对象，这个对象作为一个Bean被Spring的管理。

## @Configuration

标记一个配置类，和`@Bean`结合使用，相当于是个容器：

```xml
<Beans>
    <Bean id="methodName" class="path"></Bean>
</Beans>
```