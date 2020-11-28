package com.loguito.clase5.viewholders

import android.view.View
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.loguito.clase5.R
import com.loguito.clase5.callbacks.MailClickListener
import com.loguito.clase5.models.Mail
import io.reactivex.rxjava3.core.Observer
import kotlinx.android.synthetic.main.mail_item_view_holder.view.*

// TODO 2: Crear logica asociada al view holder. -> Encargado de pintar los valores para cada celda

class MailViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
    // TODO 5 Crear funcion encargada de pintar o popular los datos
    fun bind(mail: Mail, listener: Observer<Mail>) {
        itemView.nameTextView.text = mail.name
        itemView.emailTextView.text = mail.email

        Glide.with(itemView.context)
            .load(mail.pictureUrl)
            .circleCrop()
            .into(itemView.contactImageView)

        val isFavoriteIcon = if (mail.isFavorite) R.drawable.ic_baseline_star_24 else R.drawable.ic_baseline_star_border_24
        itemView.favoriteImageView.setImageResource(isFavoriteIcon)
        itemView.setOnClickListener {
            listener.onNext(mail)
        }
//        Glide.with(itemView.context)
//            .load(isFavoriteIcon)
//            .into(itemView.favoriteImageView)

    }
}