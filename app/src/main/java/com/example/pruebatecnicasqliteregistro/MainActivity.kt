package com.example.pruebatecnicasqliteregistro

import android.annotation.SuppressLint
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.LinearLayout
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.pruebatecnicasqliteregistro.adapter.TaskListAdapter
import com.example.pruebatecnicasqliteregistro.database.DatabaseHelper
import com.example.pruebatecnicasqliteregistro.model.TaskListModel

class MainActivity : AppCompatActivity() {

    lateinit var recycler_task : RecyclerView
    lateinit var btn_add : Button
    var tasklistAdapter : TaskListAdapter ?= null
    var dbHandler : DatabaseHelper ?= null
    var taskList : List<TaskListModel> = ArrayList<TaskListModel>()
    var linearLayoutManager : LinearLayoutManager ?= null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        recycler_task = findViewById(R.id.rv_list)
        btn_add = findViewById(R.id.bt_add_items)

        dbHandler = DatabaseHelper(this)
        fetchlist()

        btn_add.setOnClickListener {
            val i = Intent(applicationContext , AddTask::class.java)
            startActivity(i)
        }
    }

    @SuppressLint("NotifyDataSetChanged")
    private fun fetchlist(){
        taskList = dbHandler!!.getAllTask()
        tasklistAdapter = TaskListAdapter(taskList,applicationContext)
        linearLayoutManager = LinearLayoutManager(applicationContext)
        recycler_task.layoutManager = linearLayoutManager
        recycler_task.adapter = tasklistAdapter
        tasklistAdapter?.notifyDataSetChanged()

    }
}