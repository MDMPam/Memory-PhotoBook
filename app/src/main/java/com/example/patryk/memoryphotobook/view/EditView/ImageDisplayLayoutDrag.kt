package com.example.patryk.memoryphotobook.view.EditView

import android.content.ClipDescription
import android.graphics.*
import android.support.constraint.solver.widgets.Rectangle
import android.util.Log
import android.view.DragEvent
import android.view.View
import com.example.patryk.memoryphotobook.view.ImageListView.SingleFilterListElement
import com.example.patryk.memoryphotobook.view.ImageListView.SingleFrameListElement
import com.example.patryk.memoryphotobook.view.ImageListView.SinglePhotoListElement
import com.example.patryk.memoryphotobook.view.ImageListView.SingleStickerListElement
import com.example.patryk.memoryphotobook.view.RichImageView
import com.example.patryk.memoryphotobook.view.StickerView
import java.lang.Exception

class  ImageDisplayLayoutDrag(var view: EditBookView): View.OnDragListener
{
    override fun onDrag(v: View, event: DragEvent): Boolean {
        // Defines a variable to store the action type for the incoming event
        val action = event.action
        // Handles each of the expected events
        when (action) {

            DragEvent.ACTION_DRAG_STARTED -> {
                // Determines if this View can accept the dragged data
                return if (event.clipDescription.hasMimeType(ClipDescription.MIMETYPE_TEXT_PLAIN)) {
                    // if you want to apply color when drag started to your view you can uncomment below lines
                    // to give any color tint to the View to indicate that it can accept data.
                    // v.getBackground().setColorFilter(Color.BLUE, PorterDuff.Mode.SRC_IN);
                    // Invalidate the view to force a redraw in the new tint
                    //  v.invalidate();
                    // returns true to indicate that the View can accept the dragged data.
                    true
                } else false
                // Returns false. During the current drag and drop operation, this View will
                // not receive events again until ACTION_DRAG_ENDED is sent.
            }

            DragEvent.ACTION_DRAG_ENTERED -> {
                // Applies a GRAY or any color tint to the View. Return true; the return value is ignored.
                v?.background?.setColorFilter(Color.GRAY, PorterDuff.Mode.SRC_IN)
                // Invalidate the view to force a redraw in the new tint
                v?.invalidate()
                return true
            }

            DragEvent.ACTION_DRAG_LOCATION ->
                // Ignore the event
                return true

            DragEvent.ACTION_DRAG_EXITED -> {
                // Re-sets the color tint to blue. Returns true; the return value is ignored.
                // view.getBackground().setColorFilter(Color.BLUE, PorterDuff.Mode.SRC_IN);
                //It will clear a color filter .
                v?.background?.clearColorFilter()
                // Invalidate the view to force a redraw in the new tint
                v?.invalidate()
                return true
            }

            DragEvent.ACTION_DROP -> {
                // Gets the item containing the dragged data
                val item = event.clipData.getItemAt(0)

                val dragData = item.text.toString()

                v?.background?.clearColorFilter()
                // Invalidates the view to force a redraw
                v?.invalidate()


                //val owner = vw.parent as ViewGroup
                // owner.removeView(vw) //remove the dragged view
                //caste the view into LinearLayout as our drag acceptable layout is LinearLayout
                //val container = v as ConstraintLayout
                try {
                    when(dragData) {
                        SingleFilterListElement.DataDesc->frameSingleFilterCase(event.localState as SingleFilterListElement,event)
                        RichImageView.DataDesc -> richImageViewCase(event.localState as RichImageView,event)
                        StickerView.DataDesc-> stickerViewCase(event.localState as StickerView,event)
                        SingleFrameListElement.DataDesc->frameSingleElementCase(event.localState as SingleFrameListElement,event)
                        SinglePhotoListElement.DataDesc->photoSingleElementCase(event.localState as SinglePhotoListElement,event)
                        SingleStickerListElement.DataDesc->stickerSingleElementCase(event.localState as SingleStickerListElement,event)
                    }

                }catch (e: Exception)
                {
                    var x=0
                }

                // Returns true. DragEvent.getResult() will return true.
                return true
            }

            DragEvent.ACTION_DRAG_ENDED -> {
                // Turns off any color tinting
                v?.background?.clearColorFilter()
                // Invalidates the view to force a redraw
                v?.invalidate()
                // Does a getResult(), and displays what happened.
                if (event.result){}
                //Toast.makeText(, "The drop was handled.", Toast.LENGTH_SHORT).show()
                else{}
                // Toast.makeText(this, "The drop didn't work.", Toast.LENGTH_SHORT).show()
                // returns true; the value is ignored.
                return true
            }
            // An unknown action type was received.
            else -> Log.e("DragDrop Example", "Unknown action type received by OnDragListener.")
        }
        return false
    }

    fun richImageViewCase(richImage:RichImageView, event:DragEvent){
        view.presenter.move(richImage.richImage,
            Point(event.x.toInt() - richImage.richImage.bitmap!!.width/2,event.y.toInt()-richImage.richImage.bitmap!!.height/2))
    }
    fun stickerViewCase(stickerView: StickerView, event:DragEvent){
        view.presenter.move(stickerView.sticker,
            Point(event.x.toInt() - stickerView.sticker.wight/2,event.y.toInt()-stickerView.sticker.height/2))

    }
    fun photoSingleElementCase(photoListElement: SinglePhotoListElement, event:DragEvent){

        view.presenter.move(view.presenter.addRichImage(photoListElement.photo),
            Point(event.x.toInt()-photoListElement.photo.width/2,event.y.toInt()-photoListElement.photo.height/2)
        )
        photoListElement?.visibility = View.VISIBLE//finally set Visibility to VISIBLE
    }
    fun frameSingleFilterCase(filterListElement: SingleFilterListElement, event:DragEvent){

        view.imageList.forEach {
           if(event.x> it.possition.x&& event.x<it.possition.x+it.wight&&event.y>it.possition.y&&event.y<it.possition.y+it.height) {
               view.presenter.setFilter(it, filterListElement.filter)
               return
           }
        }
        view.presenter.move(view.presenter.setFilter(view.presenter.addRichImage(view.defaultImage),filterListElement.filter),
            Point(event.x.toInt()-view.defaultImage.width/2,event.y.toInt()-view.defaultImage.height/2)
        )

    }

    fun frameSingleElementCase(frameListElement: SingleFrameListElement, event:DragEvent){

        view.imageList.forEach {
            if(event.x> it.possition.x&& event.x<it.possition.x+it.wight&&event.y>it.possition.y&&event.y<it.possition.y+it.height) {
                view.presenter.setframe(it, frameListElement.frame)
                return
            }
        }
        view.presenter.move(view.presenter.setframe(view.presenter.addRichImage(view.availableSticker[0]),frameListElement.frame),
            Point(event.x.toInt()-frameListElement.frame.bitMap.width/2,event.y.toInt()-frameListElement.frame.bitMap.height/2)
        )
        frameListElement?.visibility = View.VISIBLE//finally set Visibility to VISIBLE
    }

    fun stickerSingleElementCase(stickerListElement: SingleStickerListElement, event:DragEvent){
    view.presenter.move(view.presenter.addSticker(stickerListElement.sticker),
        Point(event.x.toInt()-stickerListElement.sticker.width/2,event.y.toInt()-stickerListElement.sticker.height/2)
    )
        stickerListElement?.visibility = View.VISIBLE//finally set Visibility to VISIBLE

}
}