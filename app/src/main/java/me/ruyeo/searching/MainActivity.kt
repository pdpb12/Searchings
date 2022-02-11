package me.ruyeo.searching

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import android.widget.EditText
import androidx.recyclerview.widget.RecyclerView

class MainActivity : AppCompatActivity() {

    private val adapter by lazy { ToDoAdapter() }
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        var searchEt = findViewById<EditText>(R.id.searchEt)
        var todoRV = findViewById<RecyclerView>(R.id.todo_rv)
        setData()
        todoRV.adapter = adapter

        searchEt.addTextChangedListener(object : TextWatcher{
            override fun beforeTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {

            }

            override fun onTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {
                adapter.filter.filter(p0)
            }

            override fun afterTextChanged(p0: Editable?) {

            }
        })
    }

    private fun setData() {
        var todoList: ArrayList<ToDo> = ArrayList()
        todoList.add(ToDo("some"))
        todoList.add(ToDo("hello"))
        todoList.add(ToDo("world"))
        todoList.add(ToDo("something"))
        todoList.add(ToDo("heliii"))
        todoList.add(ToDo("nimadir"))

        adapter.setData(todoList)
    }

}