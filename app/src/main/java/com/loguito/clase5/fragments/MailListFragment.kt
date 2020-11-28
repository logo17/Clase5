package com.loguito.clase5.fragments

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.DividerItemDecoration.VERTICAL
import com.loguito.clase5.R
import com.loguito.clase5.adapters.MailAdapter
import com.loguito.clase5.callbacks.MailClickListener
import com.loguito.clase5.models.Mail
import io.reactivex.rxjava3.disposables.CompositeDisposable
import kotlinx.android.synthetic.main.fragment_mail_list.*
import java.util.concurrent.TimeUnit

// TODO 9 Creamos el fragmento donde vamos a pintar el recycler view
class MailListFragment : Fragment() {
    private val disposables = CompositeDisposable()
    private val adapter = MailAdapter()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_mail_list, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        disposables.clear()

        // TODO 11 Inicializar adapter del RecyclerView
        mailRecyclerView.adapter = adapter
        mailRecyclerView.addItemDecoration(DividerItemDecoration(requireContext(), VERTICAL))

        // TODO Supongamos que hacemos una llamada al servidor y nos devuelve la lista de correos
        mailRecyclerView.visibility = if (getDummyMailList().isEmpty()) View.GONE else View.VISIBLE
        emptyTextView.visibility = if (getDummyMailList().isEmpty()) View.VISIBLE else View.GONE
        adapter.mails = getDummyMailList()

        disposables.add(adapter.onMailClicked
            .throttleFirst(400, TimeUnit.MILLISECONDS)
            .subscribe {
                findNavController().navigate(R.id.action_mailListFragment_to_detailFragment)
            }
        )

//        manager.getMailList() { mailList ->
//            adapter.mails = mailList
//        }
    }

    override fun onDestroyView() {
        disposables.clear()
        super.onDestroyView()
    }

    private fun getDummyMailList() : List<Mail> {
        return emptyList()
//        return listOf(
//            Mail("Heriberto Urena", "test@test.com", "https://futureindustrycongress.com/wp-content/uploads/2015/04/speaker-3-v2.jpg", false),
//            Mail("Heriberto Urena", "test@test.com", "https://futureindustrycongress.com/wp-content/uploads/2015/04/speaker-3-v2.jpg", false),
//            Mail("Heriberto Urena", "test@test.com", "https://futureindustrycongress.com/wp-content/uploads/2015/04/speaker-3-v2.jpg", false),
//            Mail("Heriberto Urena", "test@test.com", "https://futureindustrycongress.com/wp-content/uploads/2015/04/speaker-3-v2.jpg", false),
//            Mail("Heriberto Urena", "test@test.com", "https://futureindustrycongress.com/wp-content/uploads/2015/04/speaker-3-v2.jpg", true),
//            Mail("Heriberto Urena", "test@test.com", "https://futureindustrycongress.com/wp-content/uploads/2015/04/speaker-3-v2.jpg", false),
//            Mail("Heriberto Urena", "test@test.com", "https://futureindustrycongress.com/wp-content/uploads/2015/04/speaker-3-v2.jpg", false),
//            Mail("Heriberto Urena", "test@test.com", "https://futureindustrycongress.com/wp-content/uploads/2015/04/speaker-3-v2.jpg", false),
//            Mail("Heriberto Urena", "test@test.com", "https://futureindustrycongress.com/wp-content/uploads/2015/04/speaker-3-v2.jpg", true),
//            Mail("Heriberto Urena", "test@test.com", "https://futureindustrycongress.com/wp-content/uploads/2015/04/speaker-3-v2.jpg", false),
//            Mail("Heriberto Urena", "test@test.com", "https://futureindustrycongress.com/wp-content/uploads/2015/04/speaker-3-v2.jpg", false)
//        )
    }
}