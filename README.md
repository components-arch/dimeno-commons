# dimeno-commons

> 公共类库

1. **@DoubleClick**防连击注解
    ``` java
    @Target(ElementType.METHOD)
    @Retention(RetentionPolicy.RUNTIME)
    public @interface DoubleClick {
        int value() default 500;
    }
    ```
2. xxx
