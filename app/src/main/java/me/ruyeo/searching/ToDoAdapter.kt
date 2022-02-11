package me.ruyeo.searching

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Filter
import android.widget.Filterable
import android.widget.TextView
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView

class ToDoAdapter : ListAdapter<ToDo, ToDoAdapter.VH>(ITEM_DIF), Filterable {

    var todoList: List<ToDo> = ArrayList()

    inner class VH(var view: View) : RecyclerView.ViewHolder(view) {
        fun bind(toDo: ToDo) {
            var title = view.findViewById<TextView>(R.id.title)
            title.text = toDo.title
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): VH {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.todo_item, parent, false)
        return VH(view)
    }

    override fun onBindViewHolder(holder: VH, position: Int) = holder.bind(getItem(position))

    fun setData(list: List<ToDo>) {
        this.todoList = list
        submitList(list)
    }

    companion object {
        private val ITEM_DIF = object : DiffUtil.ItemCallback<ToDo>() {
            override fun areItemsTheSame(oldItem: ToDo, newItem: ToDo): Boolean =
                oldItem.title == newItem.title

            override fun areContentsTheSame(oldItem: ToDo, newItem: ToDo): Boolean =
                oldItem.title == newItem.title
        }
    }

    override fun getFilter(): Filter {
        return customFilter
    }

    private val customFilter = object : Filter() {
        override fun performFiltering(p0: CharSequence?): FilterResults {
            val filterableList = mutableListOf<ToDo>()
            if (p0 == null || p0.isEmpty()) {
                filterableList.addAll(todoList)
            } else {
                for (item in todoList) {
                    if (item.title.lowercase().contains(p0.toString().lowercase())) {
                        filterableList.add(item)
                    }
                }
            }

            val results = FilterResults()
            results.values = filterableList
            return results
        }

        override fun publishResults(p0: CharSequence?, p1: FilterResults?) {
            submitList(p1?.values as MutableList<ToDo>)
        }

    }
}