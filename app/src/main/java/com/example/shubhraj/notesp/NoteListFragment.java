package com.example.shubhraj.notesp;

import android.content.ContentUris;
import android.content.Context;
import android.database.Cursor;
import android.net.Uri;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.v4.app.Fragment;
import android.support.v4.app.LoaderManager;
import android.support.v4.content.Loader;
import android.support.v4.widget.SimpleCursorAdapter;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ListView;

import android.support.v4.content.CursorLoader;

import com.example.shubhraj.notesp.data.NoteContract;
import com.example.shubhraj.notesp.data.NoteContract.NoteEntry;

public class NoteListFragment extends Fragment implements LoaderManager.LoaderCallbacks<Cursor>
{
    private OnNoteListListener mListener;

    private static final int NOTE_LOADER = 0;

    NoteCursorAdapter mCursorAdapter;

    public NoteListFragment() {
        // Required empty public constructor
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_note_list, container, false);
        FloatingActionButton fab = (FloatingActionButton) view.findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view)
            {
                Uri uri = Uri.parse("NEW");
                onButtonPressed(uri);
            }
        });
        ListView petListView = (ListView) view.findViewById(R.id.list);
        View emptyView = view.findViewById(R.id.empty_view);
        petListView.setEmptyView(emptyView);
        mCursorAdapter = new NoteCursorAdapter(getActivity(), null);
        petListView.setAdapter(mCursorAdapter);
        petListView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int position, long id)
            {
                Uri currentNoteUri = ContentUris.withAppendedId(NoteContract.NoteEntry.CONTENT_URI, id);
                onButtonPressed(currentNoteUri);
            }
        });
        getLoaderManager().initLoader(NOTE_LOADER, null, this);
        return view;
    }

    public void onButtonPressed(Uri uri) {
        if (mListener != null) {
            mListener.onNoteListListener(uri);
        }
    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        if (context instanceof OnNoteListListener) {
            mListener = (OnNoteListListener) context;
        } else {
            throw new RuntimeException(context.toString()
                    + " must implement OnLoginButton");
        }
    }

    @Override
    public void onDetach() {
        super.onDetach();
        mListener = null;
    }

    @Override
    public Loader<Cursor> onCreateLoader(int id, Bundle args) {
        // Define a projection that specifies the columns from the table we care about.
        String[] projection = {
                NoteEntry._ID,
                NoteContract.NoteEntry.COLUMN_NOTE_CONTENT};

        // This loader will execute the ContentProvider's query method on a background thread
        return new CursorLoader(getActivity(),   // Parent activity context
                NoteEntry.CONTENT_URI,   // Provider content URI to query
                projection,             // Columns to include in the resulting Cursor
                null,                   // No selection clause
                null,                   // No selection arguments
                null);                  // Default sort order

    }

    @Override
    public void onLoadFinished(Loader<Cursor> loader, Cursor data)
    {
        mCursorAdapter.swapCursor(data);
    }

    @Override
    public void onLoaderReset(Loader<Cursor> loader) {
        mCursorAdapter.swapCursor(null);
    }


    public interface OnNoteListListener
    {
        void onNoteListListener(Uri uri);
    }
}
