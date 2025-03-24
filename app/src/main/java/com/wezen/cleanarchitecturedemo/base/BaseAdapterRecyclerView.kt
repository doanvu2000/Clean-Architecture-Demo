package com.wezen.cleanarchitecturedemo.base

import android.annotation.SuppressLint
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import androidx.viewbinding.ViewBinding
import com.wezen.cleanarchitecturedemo.util.ex.tryCatch

abstract class BaseAdapterRecyclerView<T, VB : ViewBinding>
@JvmOverloads constructor(dataList: MutableList<T>? = null) :
    RecyclerView.Adapter<BaseViewHolder<VB>>() {

    private var binding: VB? = null

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): BaseViewHolder<VB> {
        binding = inflateBinding(LayoutInflater.from(parent.context), parent, viewType)
        return BaseViewHolder(requireNotNull(binding)).apply {
            bindViewClick(this, viewType)
        }
    }

    fun setOnClickItem(listener: ((item: T?, position: Int) -> Unit)? = null) {
        setOnClickItem = listener
    }

    private var setOnClickItem: ((item: T?, position: Int) -> Unit)? = null
    open fun bindViewClick(viewHolder: BaseViewHolder<VB>, viewType: Int) {
        viewHolder.itemView.setOnClickListener {
            val position = viewHolder.bindingAdapterPosition
            if (position == RecyclerView.NO_POSITION) {
                return@setOnClickListener
            }
            setOnClickItem?.invoke(dataList.getOrNull(position), position)
        }
    }

    var dataList: MutableList<T> = dataList ?: arrayListOf()
        internal set

    override fun onBindViewHolder(holder: BaseViewHolder<VB>, position: Int) {
        bindData(holder.binding, dataList[position], position)
    }

    override fun onBindViewHolder(
        holder: BaseViewHolder<VB>,
        position: Int,
        payloads: MutableList<Any>
    ) {
        if (payloads.isEmpty()) {
            onBindViewHolder(holder, position)
            return
        }
        bindData(holder.binding, dataList[position], position)
    }

    override fun getItemCount(): Int {
        return dataList.size
    }

    protected abstract fun inflateBinding(
        inflater: LayoutInflater,
        parent: ViewGroup,
        viewType: Int
    ): VB

    protected abstract fun bindData(binding: VB, item: T, position: Int)

    @SuppressLint("NotifyDataSetChanged")
    open fun setDataList(data: Collection<T>) {
        dataList.clear()
        dataList.addAll(data)
        notifyDataSetChanged()
    }

    /**
     * add more data in last of base list and notify, using for load more
     * @author doanvv
     * @sample adapter.addDataList(list)
     * @param data: list data insert
     *
     * */
    open fun addDataList(data: Collection<T>) {
        dataList.addAll(data)
        notifyItemRangeInserted(dataList.size, data.size)
    }

    open fun addItem(item: T, index: Int) {
        tryCatch {
            dataList.add(index, item)
            notifyItemInserted(index)
        }
    }

}