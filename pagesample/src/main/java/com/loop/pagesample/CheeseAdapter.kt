package com.loop.pagesample

import android.view.ViewGroup
import androidx.paging.PagedListAdapter
import androidx.recyclerview.widget.DiffUtil

/**
 *  PagedListAdapter request new pages as use scrolls, and handles new pagedLists by computing list difference on a
 *  background thread, and dispatching minimal, efficient updates to the RecyclerView to ensure minimal UI thread work.
 * 根据滑动来请求加载更多的数据，并通过diff在后台比较更新前后数据集的不同，确保UI处理数据负担最小
 *
 *  If you want to use your own Adapter base class, try using a PagedListAdapterHelper inside your adapter instead.
 *
 */
class CheeseAdapter :PagedListAdapter<Cheese,CheeseViewHolder>(diffCallback){
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CheeseViewHolder {
        return CheeseViewHolder(parent)
    }

    override fun onBindViewHolder(holder: CheeseViewHolder, position: Int) {
        holder.bind(getItem(position))
    }

    companion object {
        /**
         *  The diff callback informs the PageListAdapter how to compute list difference when new pageLists arrive.
         *
         *  <p>
         *      when you add a cheese with a "Add" button, the pageListAdapter use diffCallback to detect there's
         *      only a single item difference from before, so it only needs to animate and rebind a single view.
         */
        private val diffCallback=object: DiffUtil.ItemCallback<Cheese>(){
            override fun areItemsTheSame(oldItem: Cheese, newItem: Cheese) =
                oldItem.id==newItem.id

            override fun areContentsTheSame(oldItem: Cheese, newItem: Cheese) =
                oldItem==newItem
        }

    }
}