package com.example.neveragain.fragment

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.AdapterView
import android.widget.ArrayAdapter
import androidx.recyclerview.widget.ItemTouchHelper
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.neveragain.data.Menu
import com.example.neveragain.adapter.MyListAdapter
import com.example.neveragain.R
import com.example.neveragain.data.Restaurant
import com.example.neveragain.databinding.FragmentWhiteListBinding

class WhiteListFragment : Fragment() {
    var binding: FragmentWhiteListBinding? = null
    var myListAdapter : MyListAdapter? = null
    val items : ArrayList<Restaurant> = ArrayList()
    val location : ArrayList<String> = ArrayList()
    var flag = false // data 변경여부 확인
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentWhiteListBinding.inflate(layoutInflater,container,false)
        return binding!!.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        initData()
        initRecyclerView()
        initSpinner()
        initLayout()
    }

    private fun initSpinner() {
        val categoryAdapter = ArrayAdapter.createFromResource(requireContext(),
            R.array.category_arrays,android.R.layout.simple_spinner_dropdown_item)
        val tipAdapter = ArrayAdapter.createFromResource(requireContext(),
            R.array.tip_arrays,android.R.layout.simple_spinner_dropdown_item)
        val addressAdapter = ArrayAdapter<String>(requireContext(), android.R.layout.simple_spinner_dropdown_item, location)

        categoryAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item)
        tipAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item)

        binding!!.category.adapter = categoryAdapter
        binding!!.tip.adapter = tipAdapter
        binding!!.address.adapter = addressAdapter

        binding!!.category.onItemSelectedListener = object: AdapterView.OnItemSelectedListener{
            override fun onItemSelected(parent: AdapterView<*>?, p1: View?, position: Int, p3: Long) {
                myListAdapter!!.getFilter().filter(parent?.getItemAtPosition(position).toString()
                        + "," + binding!!.tip.selectedItem.toString()
                        + "," + binding!!.address.selectedItem.toString())
            }
            override fun onNothingSelected(p0: AdapterView<*>?) {

            }

        }
        binding!!.tip.onItemSelectedListener = object: AdapterView.OnItemSelectedListener{
            override fun onItemSelected(parent: AdapterView<*>?, p1: View?, position: Int, p3: Long) {
                myListAdapter!!.getFilter().filter(binding!!.category.selectedItem.toString()
                        + ","+parent?.getItemAtPosition(position).toString()
                        + ","+ binding!!.address.selectedItem.toString())
            }

            override fun onNothingSelected(p0: AdapterView<*>?) {
                //do nothing
            }

        }
        binding!!.address.onItemSelectedListener = object: AdapterView.OnItemSelectedListener{
            override fun onItemSelected(parent: AdapterView<*>?, p1: View?, position: Int, p3: Long) {
                myListAdapter!!.getFilter().filter(binding!!.category.selectedItem.toString()
                        + "," + binding!!.tip.selectedItem.toString()
                        + "," + parent?.getItemAtPosition(position).toString())
            }

            override fun onNothingSelected(p0: AdapterView<*>?) {

            }

        }


    }

    private fun initRecyclerView() {
        binding!!.recyclerView.layoutManager = LinearLayoutManager(requireContext(),
            LinearLayoutManager.VERTICAL,false)
        myListAdapter = MyListAdapter(items, "White")
        myListAdapter!!.itemClickListener = object : MyListAdapter.OnItemClickListener {
            override fun onItemClick(data: Restaurant) {
                /*val i = intent(this, ) 클래스 이름 정해지면
                startActivity(i)
                 */
            }


        }
        binding!!.recyclerView.adapter = myListAdapter
    }

    private fun initData() {
        TODO("쿼리문이랑 데이터 클래스 보고 수정")
        items.add(
            Restaurant(1,"test", arrayListOf(Menu(1,"food",3)), "123",true
            , true, arrayListOf(Pair(3,1000)),"카테",1.1,1.1)
        )
        items.add(
            Restaurant(1,"test1", arrayListOf(Menu(2, "food",3)), "123",true
            , true, arrayListOf(Pair(3,2000)),"야식",1.1,1.1)
        )
        items.add(
            Restaurant(1,"test2", arrayListOf(Menu(3, "food",3)), "123",true
            , false, arrayListOf(Pair(3,3000)),"야식",1.1,1.1)
        )

        location.add("광진구 화양동 건국대")
        location.add("강남구 대치동 ")

    }

    private fun initLayout() {


        /**         swipe       **/
        val simpleCallback = object : ItemTouchHelper.SimpleCallback(
            ItemTouchHelper.UP or ItemTouchHelper.DOWN, ItemTouchHelper.RIGHT){
            override fun onMove(
                recyclerView: RecyclerView,
                viewHolder: RecyclerView.ViewHolder,
                target: RecyclerView.ViewHolder
            ): Boolean {
                myListAdapter!!.moveItem(viewHolder.adapterPosition, target.adapterPosition)
                flag = true
                return true
            }

            override fun onSwiped(viewHolder: RecyclerView.ViewHolder, direction: Int) {
                myListAdapter!!.removeItem(viewHolder.adapterPosition)
                flag = true
            }

        }
        val itemTOuchHelper = ItemTouchHelper(simpleCallback)
        itemTOuchHelper.attachToRecyclerView(binding!!.recyclerView)
    }
    override fun onStop() {
        super.onStop()
        if(flag) {
            TODO("데이터 저장")
        }
    }

}