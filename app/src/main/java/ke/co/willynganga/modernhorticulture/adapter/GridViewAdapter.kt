package ke.co.willynganga.modernhorticulture.adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.BaseAdapter
import android.widget.ImageView
import android.widget.TextView
import ke.co.willynganga.modernhorticulture.R
import ke.co.willynganga.modernhorticulture.model.GridItem

class GridViewAdapter(
    private val context: Context,
    private val list: List<GridItem>
) : BaseAdapter() {

    override fun getCount(): Int {
        return list.size
    }

    override fun getItem(position: Int): Any {
        return list[position]
    }

    override fun getItemId(position: Int): Long {
        return 0
    }

    override fun getView(position: Int, convertView: View?, parent: ViewGroup?): View? {
        var gridItemView = convertView
        val layoutInflater = context.getSystemService(Context.LAYOUT_INFLATER_SERVICE) as LayoutInflater

        if (convertView == null) {
            gridItemView = layoutInflater.inflate(R.layout.fruit_upload_item, null)
        }

        val image = gridItemView?.findViewById<ImageView>(R.id.fruit_image)
        val fruitName = gridItemView?.findViewById<TextView>(R.id.fruit_name)
        val quantity = gridItemView?.findViewById<TextView>(R.id.quantity_of_fruits)
        val location = gridItemView?.findViewById<TextView>(R.id.location_of_fruit)

        fruitName?.text = list[position].typeOfFruit
        quantity?.text = list[position].quantity
        location?.text = list[position].location

        return gridItemView
    }
}