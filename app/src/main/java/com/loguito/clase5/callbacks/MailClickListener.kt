package com.loguito.clase5.callbacks

import com.loguito.clase5.models.Mail

interface MailClickListener {
    fun onMailClicked(mail: Mail)
}