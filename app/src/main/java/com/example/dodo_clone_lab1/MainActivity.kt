package com.example.dodo_clone_lab1

import ItemsAdapter
import android.content.ClipData
import android.graphics.Rect
import android.media.RouteListingPreference
import android.os.Bundle
import android.view.TouchDelegate
import android.view.View
import android.widget.Button
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.dodo_clone_lab1.ui.theme.Dodoclonelab1Theme
import android.widget.SearchView
import com.example.dodo_clone_lab1.enums.ItemType


class MainActivity : ComponentActivity() {

    private lateinit var adapter: ItemsAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.products_main)

        val itemsList: RecyclerView = findViewById(R.id.itemsList)
        val items = arrayListOf<Pizza>()
// increase the touchable area for button, since it should be 48dp
//        val button = findViewById<Button>(R.id.pizza_list_price)
//        button.post {
//            val delegateArea = Rect()
//            button.getHitRect(delegateArea)
//            delegateArea.top -= 16 // Adjust as needed
//            delegateArea.bottom += 16 // Adjust as needed
//            delegateArea.left -= 16 // Adjust as needed
//            delegateArea.right += 16 // Adjust as needed
//            val touchDelegate = TouchDelegate(delegateArea, button)
//            if (View::class.java.isInstance(button.parent)) {
//                (button.parent as View).touchDelegate = touchDelegate
//            }
//        }

        items.add(Pizza(1, "waw_kebab", "Wow! Kebab", "Beef kebab, ranch sauce, cheddar cheese, sweet pepper, tomatoes, red onion, marinara sauce", "2,900", "30, traditional тесто 30, 530 g",
            ItemType.PIZZA
        ))
        items.add(Pizza(2, "meal_from_3900", "Meal from 3900 ₸", "Treat yourself! Small pizza, Dodster, a drink and a sauce. Pizza in a combo can be changed", "3,900", "300 g", ItemType.COMBO))
        items.add(Pizza(3, "julienne", "Julienne", "Chicken, mushrooms, rich mushroom sauce, red onion, garlic, mozzarella cheese, cheddar cheese, parmesan cheese, Alfredo sauce", "2,700", "30, traditional тесто 30, 640 g", ItemType.PIZZA))
        items.add(Pizza(4, "cheesy", "Cheesy", "Mozzarella cheese, cheddar cheese, parmesan cheese, Alfredo sauce", "1,900", "30, traditional тесто 30, 490 g", ItemType.PIZZA))
        items.add(Pizza(5, "double_chicken", "Double Chicken", "Double chicken, mozzarella cheese, Alfredo sauce", "2,100", "30, traditional тесто 30, 520 g", ItemType.PIZZA))
        items.add(Pizza(6, "kikoriki_combo", "KikoRiki Combo", "Approved by cartoon characters: small pizza of any flavor and young gardener's kit Combo price depends on the selected pizzas and may change.", "2,300","300 g", ItemType.COMBO))
        items.add(Pizza(7, "ham_and_cheese", "Ham & Cheese", "Chicken ham, mozzarella cheese, Alfredo sauce", "2,000", "30, traditional тесто 30, 490 g", ItemType.PIZZA))
        items.add(Pizza(8, "peperoni_fresh", "Pepperoni Fresh", "Chicken pepperoni, extra mozzarella cheese, tomatoes, marinara sauce", "1,900", "30, traditional тесто 30, 620 g", ItemType.PIZZA))

        items.add(Pizza(9, "chorizo_fresh", "Chorizo fresh", "Spicy chorizo, sweet pepper, mozzarella cheese, marinara sauce", "1,900", "30, traditional тесто 30, 490 g", ItemType.PIZZA))



        adapter = ItemsAdapter(items, this)

        itemsList.layoutManager = LinearLayoutManager(this)
        itemsList.adapter = adapter
        val searchView: SearchView = findViewById(R.id.searchView)
        searchView.setOnQueryTextListener(object : SearchView.OnQueryTextListener {
            override fun onQueryTextSubmit(query: String?): Boolean {
                return false
            }

            override fun onQueryTextChange(newText: String?): Boolean {
                adapter.filter.filter(newText)
                return true
            }
        })

    }
}

