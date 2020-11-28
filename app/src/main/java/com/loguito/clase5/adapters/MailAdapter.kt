package com.loguito.clase5.adapters

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.loguito.clase5.R
import com.loguito.clase5.callbacks.MailClickListener
import com.loguito.clase5.models.Mail
import com.loguito.clase5.viewholders.MailViewHolder
import io.reactivex.rxjava3.core.Observable
import io.reactivex.rxjava3.subjects.PublishSubject

//TODO 3: Crear el adapter (de tipo MailViewHolder) -> encargado de alimentar el recycler view

class MailAdapter : RecyclerView.Adapter<MailViewHolder>() {

    private val clickListener: PublishSubject<Mail> = PublishSubject.create()

    val onMailClicked: Observable<Mail> = clickListener.hide()

    // TODO 7: Crear atributo para la lista de correos (modelos)
    var mails: List<Mail> = emptyList()
        set(value) {
            field = value
            notifyDataSetChanged()
        }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MailViewHolder {
        // TODO 4: Inflar el layout y asignarlo al viewHolder
        val view = LayoutInflater.from(parent.context)
            .inflate(R.layout.mail_item_view_holder, parent, false)
        return MailViewHolder(view)
    }

    override fun onBindViewHolder(holder: MailViewHolder, position: Int) {
        // TODO 6: Llamar a la funcion para pintar cada celda de correos
        holder.bind(mails[position], clickListener)
    }

    // TODO 8: Retornar la cantidad de elementos en la lista (para que el adapter sepa cuantos tiene que pintar)
    override fun getItemCount() = mails.size
}