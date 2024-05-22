package com.bb.infec.base.callback

interface AlertDialogCallback {

    fun onPositiveButton(dialogId: Int)

    fun onNegativeButton(dialogId: Int)
}