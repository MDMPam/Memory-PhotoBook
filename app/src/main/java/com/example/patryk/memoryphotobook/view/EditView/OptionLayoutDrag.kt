package com.example.patryk.memoryphotobook.view.EditView

import android.content.ClipDescription
import android.view.DragEvent
import android.view.View
import android.view.ViewGroup
import com.example.patryk.memoryphotobook.view.ImageListView.SingleFrameListElement
import com.example.patryk.memoryphotobook.view.RichImageView
import com.example.patryk.memoryphotobook.view.StickerView
import com.example.patryk.memoryphotobook.view.TextView
import java.lang.Exception

class OptionLayoutDrag (var view: EditBookView): View.OnDragListener
{
    override fun onDrag(v: View, event: DragEvent): Boolean {
        val action = event.action
        when (action) {

            DragEvent.ACTION_DRAG_STARTED -> {
                return if (event.clipDescription.hasMimeType(ClipDescription.MIMETYPE_TEXT_PLAIN)) {
                    true
                } else false
                // Returns false. During the current drag and drop operation, this View will
                // not receive events again until ACTION_DRAG_ENDED is sent.
            }

            DragEvent.ACTION_DRAG_ENTERED -> {
                v?.invalidate()
                return true
            }

            DragEvent.ACTION_DRAG_LOCATION ->
                // Ignore the event
                return true

            DragEvent.ACTION_DRAG_EXITED -> {
                v?.invalidate()
                return true
            }

            DragEvent.ACTION_DROP -> {
                // Gets the item containing the dragged data
                val item = event.clipData.getItemAt(0)

                val dragData = item.text.toString()

                v?.background?.clearColorFilter()
                v?.invalidate()
                try {
                    when(dragData) {
                        RichImageView.DataDesc -> richImageCase( event.localState as RichImageView,event)
                        TextView.DataDesc->textCase( event.localState as TextView,event)
                        StickerView.DataDesc->stickerCase( event.localState as StickerView,event)
                        SingleFrameListElement.DataDesc->{}
                        else->{}
                    }
                }catch (e: Exception)
                {
                    var x=0}
                return true
            }

            DragEvent.ACTION_DRAG_ENDED -> {
                v?.invalidate()
                return true
            }
        }
        return false
    }
    private fun richImageCase(richImage:RichImageView, event:DragEvent)
    {
        view.presenter.remove(richImage.richImage)
    }
    private fun stickerCase(stickerView: StickerView, event:DragEvent)
    {
        view.presenter.remove(stickerView.sticker)
    }
    private fun textCase(textView: TextView, event:DragEvent)
    {
        view.presenter.remove(textView.text)
    }
}