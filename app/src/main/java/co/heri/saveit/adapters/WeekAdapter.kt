package co.heri.saveit.adapters

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import co.heri.saveit.R
import co.heri.saveit.models.Week
import kotlinx.android.synthetic.main.month_breakdown_view.view.*


class WeekAdapter(private val data : ArrayList<Week>, val context: Context?) : RecyclerView.Adapter<WeekAdapter.WeekViewHolder>() {


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): WeekViewHolder {
        return WeekViewHolder(
            LayoutInflater.from(parent.context)
                .inflate(R.layout.month_breakdown_view, parent, false)
        )
    }

    override fun getItemCount(): Int{
        return data.size
    }

    public  fun recalculate(principal: Int) {
        data.forEach {
            it.startAmount = principal
            this.notifyDataSetChanged()
        }
    }

    override fun onBindViewHolder(holder: WeekViewHolder, position: Int) = holder.bind(data[position])


    class WeekViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        fun bind(item: Week) = with(itemView) {

            val deposit = item.startAmount * item.weekno;

            weekno_input.text = item.weekno.toString()
            deposit_amount.text = "KES " + (deposit).toString()


            val total_amount = (item.startAmount * item.weekno) + deposit

            item.total = total_amount;


            total.text = "KES" + total_amount.toString()



            setOnClickListener {
                // TODO: Handle on click
            }
        }
    }
}