package com.loop.componentdemo.ui

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import androidx.lifecycle.ViewModelProviders
import com.loop.componentdemo.Injection
import com.loop.componentdemo.R
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.schedulers.Schedulers
import kotlinx.android.synthetic.main.activity_user.*

class UserActivity : AppCompatActivity() {


    private lateinit var viewModelFactory: UserViewModelFactory

    private lateinit var viewModel: UserViewModel

    private val disposable = CompositeDisposable()



    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_user)

        viewModelFactory=Injection.provideViewModelFactory(this)

        viewModel= ViewModelProviders.of(this,viewModelFactory)[UserViewModel::class.java]

        //大体来看，一个activity创建一个管理viewModel
        // 的工厂，其用来创建一个个viewModel
        //viewModelFactory人如其名，使用了工厂模式，并且管理一个viewModel的集合，一般通过类作为键来索引

        //viewModel创建了一个用来给activity的提供的一个独立的，与android应用层不相关的逻辑，比如生命周期的内容不会由viewModel来控制
        //一个viewModel可以在多个fragment中共享，达到复用
        //viewModel只是实现了一个onClear的方法，在创建一个viewModel时，通过of(context)来选择这个model的活动范围，并在超出范围之后销毁


        user_btn.setOnClickListener { updateUsername() }

    }

    private fun updateUsername() {
        user_btn.isEnabled=false
        disposable.add(viewModel.updateUser(user_et.text.toString())
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe({
                user_btn.isEnabled=true
            },{
                Log.e("wolf","Unable to update username")
            }))
    }

    override fun onStart() {
        super.onStart()

        disposable.add(viewModel.userName()
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe({
                user_tv.text=it
            }, {
                Log.e("wolf","Unable to get a user")
            })

        )
    }

    override fun onStop() {
        super.onStop()
        //在活动不可见的时清理这个监听资源`
        disposable.clear()
    }
}
