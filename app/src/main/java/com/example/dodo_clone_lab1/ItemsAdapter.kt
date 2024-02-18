import android.content.Context
import android.content.Intent
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.Filter
import android.widget.Filterable
import android.widget.FrameLayout
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.dodo_clone_lab1.Pizza
import com.example.dodo_clone_lab1.PizzaActivity
import com.example.dodo_clone_lab1.R
import java.util.*

class ItemsAdapter(private var items: List<Pizza>, private val context: Context) : RecyclerView.Adapter<ItemsAdapter.MyViewHolder>(),
    Filterable {

    // Original list of items
    private var originalItems: List<Pizza> = items

    // Filter
    private var filter: Filter = object : Filter() {
        override fun performFiltering(constraint: CharSequence?): FilterResults {
            val filteredList = mutableListOf<Pizza>()
            if (constraint.isNullOrBlank()) {
                filteredList.addAll(originalItems)
            } else {
                val filterPattern = constraint.toString().toLowerCase(Locale.getDefault()).trim()
                for (item in originalItems) {
                    if (item.title.toLowerCase(Locale.getDefault()).contains(filterPattern)) {
                        filteredList.add(item)
                    }
                }
            }
            val results = FilterResults()
            results.values = filteredList
            return results
        }

        override fun publishResults(constraint: CharSequence?, results: FilterResults?) {
            items = results?.values as List<Pizza>
            notifyDataSetChanged()
        }
    }

    class MyViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        val image: ImageView = view.findViewById(R.id.pizza_list_image)
        val title: TextView = view.findViewById(R.id.pizza_list_title)
        val description: TextView = view.findViewById(R.id.pizza_list_desc)
        val price: Button = view.findViewById(R.id.pizza_list_price)
        val button: FrameLayout = view.findViewById(R.id.item_container)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.pizza_in_list, parent, false)
        return MyViewHolder(view)
    }

    override fun getItemCount(): Int {
        return items.size
    }

    override fun onBindViewHolder(holder: MyViewHolder, position: Int) {
        val currentItem = items[position]
        holder.title.text = currentItem.title
        holder.description.text = currentItem.desc
        holder.price.text = "from ${currentItem.price} KZT"

        val imageId = context.resources.getIdentifier(
            currentItem.image,
            "drawable",
            context.packageName
        )
        holder.image.setImageResource(imageId)

        holder.button.setOnClickListener {
            val intent = Intent(context, PizzaActivity::class.java)
            intent.putExtra("pizzaTitle", currentItem.title)
            intent.putExtra("pizzaDesc", currentItem.desc)
            intent.putExtra("sizeDesc", currentItem.sizeDesc)
            context.startActivity(intent)
        }
    }

    override fun getFilter(): Filter {
        return filter
    }
}
