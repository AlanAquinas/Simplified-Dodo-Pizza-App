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
import com.example.dodo_clone_lab1.ComboActivity
import com.example.dodo_clone_lab1.Pizza
import com.example.dodo_clone_lab1.PizzaActivity
import com.example.dodo_clone_lab1.R
import com.example.dodo_clone_lab1.enums.ItemType
import java.util.*

class ItemsAdapter(private var items: List<Pizza>, private val context: Context) : RecyclerView.Adapter<RecyclerView.ViewHolder>(),
    Filterable {

    // Original list of items
    private var originalItems: List<Pizza> = items
    private val VIEW_PIZZA = 0
    private val VIEW_COMBO = 1

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

    inner class PizzaViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        val image: ImageView = view.findViewById(R.id.pizza_list_image)
        val title: TextView = view.findViewById(R.id.pizza_list_title)
        val description: TextView = view.findViewById(R.id.pizza_list_desc)
        val price: Button = view.findViewById(R.id.pizza_list_price)
        val button: FrameLayout = view.findViewById(R.id.pizza_container)
    }

    inner class ComboViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        val image: ImageView = view.findViewById(R.id.combo_list_image)
        val title: TextView = view.findViewById(R.id.combo_list_title)
        val description: TextView = view.findViewById(R.id.combo_list_desc)
        val price: Button = view.findViewById(R.id.combo_list_price)
        val button: FrameLayout = view.findViewById(R.id.combo_container)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
        if (viewType == VIEW_PIZZA) {
            val view =
                LayoutInflater.from(parent.context).inflate(R.layout.pizza_in_list, parent, false)
            return PizzaViewHolder(view)
        } else {
            val view =
                LayoutInflater.from(parent.context).inflate(R.layout.combo_offer_in_list, parent, false)
            return ComboViewHolder(view)
        }
    }

    override fun getItemCount(): Int {
        return items.size
    }

    override fun getItemViewType(position: Int): Int {
        val pos = items[position]
        return if (pos.type == ItemType.COMBO) {
            VIEW_COMBO
        } else {
            VIEW_PIZZA
        }

    }

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        when (holder.itemViewType) {
            VIEW_PIZZA -> {
                val currentItem = items[position] as Pizza
                val pizzaHolder = holder as PizzaViewHolder
                pizzaHolder.title.text = currentItem.title
                pizzaHolder.description.text = currentItem.desc
                pizzaHolder.price.text = "from ${currentItem.price} KZT"

                val imageId = context.resources.getIdentifier(
                    currentItem.image,
                    "drawable",
                    context.packageName
                )
                pizzaHolder.image.setImageResource(imageId)

                pizzaHolder.button.setOnClickListener {
                    val intent = Intent(context, PizzaActivity::class.java)
                    intent.putExtra("pizzaTitle", currentItem.title)
                    intent.putExtra("pizzaDesc", currentItem.desc)
                    intent.putExtra("sizeDesc", currentItem.sizeDesc)
                    intent.putExtra("imageId", imageId)

                    context.startActivity(intent)
                }
            }
            VIEW_COMBO -> {
                val currentItem = items[position] as Pizza
                val comboHolder = holder as ComboViewHolder
                comboHolder.title.text = currentItem.title
                comboHolder.description.text = currentItem.desc
                comboHolder.price.text = "${currentItem.price} KZT"

                val imageId = context.resources.getIdentifier(
                    currentItem.image,
                    "drawable",
                    context.packageName
                )

                comboHolder.image.setImageResource(imageId)

                comboHolder.button.setOnClickListener {
                    val intent = Intent(context, ComboActivity::class.java)
                    intent.putExtra("comboTitle", currentItem.title)
                    intent.putExtra("comboDesc", currentItem.desc)
                    intent.putExtra("comboPrice", currentItem.price)
                    intent.putExtra("sizeDesc", currentItem.sizeDesc)
                    intent.putExtra("imageId", imageId)
                    context.startActivity(intent)
                }
            }
        }
    }

    override fun getFilter(): Filter {
        return filter
    }
}
