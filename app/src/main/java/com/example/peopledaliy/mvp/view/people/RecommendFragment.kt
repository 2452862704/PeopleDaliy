package com.example.peopledaliy.mvp.view.people
import android.view.View
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.chad.library.adapter.base.BaseQuickAdapter
import com.chad.library.adapter.base.listener.OnItemChildClickListener
import com.example.peopledaliy.R
import com.example.peopledaliy.mvp.base.model.BaseEntity
import com.example.peopledaliy.mvp.base.view.BaseFragment
import com.example.peopledaliy.mvp.contract.LREContract
import com.example.peopledaliy.mvp.model.HttpCode
import com.example.peopledaliy.mvp.model.LREModel
import com.example.peopledaliy.mvp.model.entity.RecommendEntity
import com.example.peopledaliy.mvp.presenter.LREPresenter
import com.example.peoplenews.mvp.view.people.adapter.RecommendAdapter


class RecommendFragment : BaseFragment<LREPresenter>(), LREContract.ILREView,
    OnItemChildClickListener{

    private var recommend_rv: RecyclerView? = null
    private var manager: LinearLayoutManager? = null
    private var adapter: RecommendAdapter? = null

    override fun onPause() {
        super.onPause()
        adapter!!.pause()
    }
    override fun bindLayout(): Int {
        return R.layout.fragment_recommend
    }

    override fun initView() {
        recommend_rv = f(R.id.recommend_rv)
        manager = LinearLayoutManager(context)
        manager!!.orientation = LinearLayoutManager.VERTICAL
        recommend_rv!!.setLayoutManager(manager)

    }

    override fun initData() {
        var map = HashMap<String, Any>()
        map.put("code", HttpCode.RECOMMENDNEWSCODE)
        p!!.requestAll(map)
    }

    override fun inject() {
        p = LREPresenter(LREModel(), this)
    }

    override fun refreshAll(entity: BaseEntity?) {
        if (entity is RecommendEntity) {
            adapter = entity.getValues()?.let { RecommendAdapter(it as ArrayList<RecommendEntity.Values>) }
            recommend_rv!!.adapter = adapter
        }
    }

    override fun refreshRecyckerView(entity: BaseEntity?) {
    }

    override fun loadMoreRecyclerView(entity: BaseEntity?) {
    }

    override fun onItemChildClick(adapter: BaseQuickAdapter<*, *>, view: View, position: Int) {

    }

}