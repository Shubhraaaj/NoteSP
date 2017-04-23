package com.example.shubhraj.notesp;

import android.content.Context;
import android.database.Cursor;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CursorAdapter;
import android.widget.SimpleCursorAdapter;
import android.widget.TextView;

import com.example.shubhraj.notesp.data.NoteContract.NoteEntry;

/**
 * {@link NoteCursorAdapter} is an adapter for a list or grid view
 * that uses a {@link Cursor} of pet data as its data source. This adapter knows
 * how to create list items for each row of pet data in the {@link Cursor}.
 */
public class NoteCursorAdapter extends CursorAdapter {

    /**
     * Constructs a new {@link NoteCursorAdapter}.
     *
     * @param context The context
     * @param c       The cursor from which to get the data.
     */
    public NoteCursorAdapter(Context context, Cursor c) {
        super(context, c,0);
    }
    /**
     * Makes a new blank list item view. No data is set (or bound) to the views yet.
     *
     * @param context app context
     * @param cursor  The cursor from which to get the data. The cursor is already
     *                moved to the correct position.
     * @param parent  The parent to which the new view is attached to
     * @return the newly created list item view.
     */
    @Override
    public View newView(Context context, Cursor cursor, ViewGroup parent) {
        // Inflate a list item view using the layout specified in list_item.xml
        LayoutInflater inflater = LayoutInflater.from(context);
        return inflater.inflate(R.layout.list_item, parent, false);
        //return LayoutInflater.from(context).inflate(R.layout.fragment_note_list, parent, false);
    }

    /**
     * This method binds the pet data (in the current row pointed to by cursor) to the given
     * list item layout. For example, the name for the current pet can be set on the name TextView
     * in the list item layout.
     *
     * @param view    Existing view, returned earlier by newView() method
     * @param context app context
     * @param cursor  The cursor from which to get the data. The cursor is already moved to the
     *                correct row.
     */
    @Override
    public void bindView(View view, Context context, Cursor cursor)
    {
        // Find individual views that we want to modify in the list item layout
        TextView nameTextView = (TextView) view.findViewById(R.id.item);

        // Find the columns of pet attributes that we're interested in
        int nameColumnIndex = cursor.getColumnIndex(NoteEntry.COLUMN_NOTE_CONTENT);

        // Read the pet attributes from the Cursor for the current pet
        String noteContent = cursor.getString(nameColumnIndex);

        // Update the TextViews with the attributes for the current pet
        nameTextView.setText(noteContent);
    }
}