
### 使用了project范围的版本依赖，各个module版本一致

### private val IO_EXECUTOR = Executors.newSingleThreadExecutor()在有任务需要后台运行的场景下，可以构建一个工具类，如本项目中的Executor中的
```kotlin

private val IO_EXECUTOR = Executors.newSingleThreadExecutor()

fun ioThread(f:()-> Unit){
    IO_EXECUTOR.execute(f)
}
```
使用了一个单线程阻塞的线程池来管理处理任务，通过传入一个runnable对象，在kotlin中即传入一个方法既可
使用时，就可以很方便的提交一个后台任务

通过在RoomDatabase创建时，传入一个回调，可以在数据库创建和打开时做一些额外实务
比如在创建数据库后，初始化初始的数据


Paging是官方多页加载的框架，通过它可以实现加载更多的操作

一步步Demo中的操作

1. 数据源
这里是通过数据库加载的，Room通过查询返回一个数据源，通过这个数据源可以获取到LiveData

```kotlin

    @Query("SELECT * FROM Cheese ORDER BY name COLLATE NOCASE ASC")
    fun allCheesesByName(): DataSource.Factory<Int, Cheese>

```
2. viewModel中配置加载配置

```kotlin
    
    //z这里使用了 -ktx扩展方法，否则还会需要 LivePagedListBuilder(), PagedList.Config.Builder()来构建
    
    val allCheese=dao.allCheeseByName().toLiveData(Config(
        pageSize = 8,
        enablePlaceholders = true,
        maxSize = 200
    ))
    
    //设置分页数，是否使用占位，最大加载的大小
```

3. 在监听到LiveData变化时更新

```kotlin

        //在使用pagedListAdapter中的submitList方法提示数据源变化了，其中使用了一个Diff比较数据集的变化
        //让使用者来实现数据项是否变化具体逻辑，传入一个diffCallback
        //开启后台任务来计算这个变化，最后提交给adapter来更新UI
        viewModel.allCheese.observe(this, Observer(adapter::submitList))

```
