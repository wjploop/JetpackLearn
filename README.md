# JetpackLearn
学习Android JetPack系列笔记
]

遇到了几个问题：
1. 在处理Room依赖时，需要引入kotlin-kapt插件，用来处理Kotlin中的注解
比如在Room中，有各种注解

2. 主要学习了ViewModel

viewModel 主要ViewModelFactory创建，即使用了工厂模式
并储存在viewModelProviders中，一般使用ViewModel具体实现的类class作为key来索引viewModel
在使用viewModel时，由ViewModelProviders来提供，由此，ViewModelProviders也管理viewModel的生命周期，通过指定它的context

