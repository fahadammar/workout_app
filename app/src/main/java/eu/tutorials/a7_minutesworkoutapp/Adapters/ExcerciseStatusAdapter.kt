package eu.tutorials.a7_minutesworkoutapp.Adapters

import android.graphics.Color
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.core.content.ContextCompat
import androidx.recyclerview.widget.RecyclerView
import eu.tutorials.a7_minutesworkoutapp.Model.ExcerciseModel
import eu.tutorials.a7_minutesworkoutapp.R
import eu.tutorials.a7_minutesworkoutapp.databinding.ItemExerciseStatusBinding

class ExcerciseStatusAdapter(val items : ArrayList<ExcerciseModel>) :
RecyclerView.Adapter<ExcerciseStatusAdapter.ViewHolder>() {

    /**
     * Inflates the item view which is designed in xml layout file
     *
     * create a new
     * {@link ViewHolder} and initializes some private fields to be used by RecyclerView.
     */
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        return ViewHolder(
            ItemExerciseStatusBinding.inflate(
                LayoutInflater.from(parent.context), parent, false )
        )
    }

    /**
     * Binds each item in the ArrayList to a view
     *
     * Called when RecyclerView needs a new {@link ViewHolder} of the given type to represent
     * an item.
     *
     * This new ViewHolder should be constructed with a new View that can represent the items
     * of the given type. You can either create a new View manually or inflate it from an XML
     * layout file.
     */
    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        var modelItem : ExcerciseModel = items[position]
        holder.tvItem.text = modelItem.getId().toString()

        // Updating the background and text color according to the flags that is in the list.
        // A link to set text color programmatically and same way we can set the drawable background also instead of color.
        // https://stackoverflow.com/questions/8472349/how-to-set-text-color-to-a-text-view-programmatically
        when{
            modelItem.getIsCompleted() -> {
                holder.tvItem.background = ContextCompat.getDrawable(
                    holder.itemView.context,
                    R.drawable.item_circular_color_accent_background
                )
                holder.tvItem.setTextColor(Color.parseColor("#FFFFFF"))
            }

            modelItem.getIsSelected() -> {
                holder.tvItem.background = ContextCompat.getDrawable(
                    holder.itemView.context,
                    R.drawable.item_circular_thin_color_accent_border
                )
                // Parse the color string, and return the corresponding color-int.
                holder.tvItem.setTextColor(Color.parseColor("#212121"))
            }

            else -> {
                holder.tvItem.background = ContextCompat.getDrawable(
                    holder.itemView.context,
                    R.drawable.item_circular_color_gray_background
                )
                holder.tvItem.setTextColor(Color.parseColor("#212121"))
            }
        }
    }

    /**
     * Gets the number of items in the list
     */
    override fun getItemCount(): Int {
            return items.size
    }

    /**
     * A ViewHolder describes an item view and metadata about its place within the RecyclerView.
     * Here in the binding we are taking the item_excercise_status xml
     */
    class ViewHolder(binding : ItemExerciseStatusBinding) : RecyclerView.ViewHolder(binding.root) {
        val tvItem = binding.tvItem
    }
}