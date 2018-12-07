package Model;

import android.content.Context;
import android.preference.ListPreference;
import android.util.AttributeSet;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.ListView;

public class DynamicPreference extends ListPreference {
    public static String[] Languages ;
    public static String[] Values ;
    public DynamicPreference(Context context, AttributeSet attrs) {

        super(context, attrs);
        setEntries(entries());
        setEntryValues(entryValues());
        setValueIndex(initializeIndex());
    }

    public DynamicPreference(Context context) {
        super(context);
        setEntries(entries());
        setEntryValues(entryValues());
        setValueIndex(initializeIndex());
    }

    @Override
    protected View onCreateDialogView() {
        ListView view = new ListView(getContext());
        view.setAdapter(adapter());
        return view;
    }

    private int initializeIndex() {
        return 0;
    }

    private ArrayAdapter adapter() {
        return new ArrayAdapter(getContext(), android.R.layout.select_dialog_singlechoice);
    }

    private CharSequence[] entries() {
        //action to provide entry data in char sequence array for list
        return Languages;
    }

    private CharSequence[] entryValues()
    {
        return Values;
    }
}
