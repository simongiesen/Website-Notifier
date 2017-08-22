package com.smutkiewicz.pagenotifier.model;

import android.database.Cursor;
import android.net.Uri;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CompoundButton;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.Switch;
import android.widget.TextView;

import com.smutkiewicz.pagenotifier.R;
import com.smutkiewicz.pagenotifier.database.DbDescription;

public class WebsiteItemAdapter
    extends RecyclerView.Adapter<WebsiteItemAdapter.ViewHolder> {
    // interfejs implementowany przez MainActivityFragment
    // po dotknięciu przez użytkownika elementu wyświetlanego w widoku RecyclerView
    public interface WebsiteItemClickListener {
        void onMoreButtonClick(Uri itemUri);
        void onItemClick(String url);
        void onSwitchClick(Uri itemUri, int newEnableValue);
    }

    // zmienne egzemplarzowe adaptera
    private Cursor cursor = null;
    private final WebsiteItemClickListener clickListener;

    public WebsiteItemAdapter(WebsiteItemClickListener clickListener) {
        this.clickListener = clickListener;
    }

    // zagnieżdżona podklasa RecyclerView.ViewHolder używana do implementacji
    // wzorca ViewHolder w kontekście widoku RecyclerView
    public class ViewHolder extends RecyclerView.ViewHolder {
        private final ImageView pageStateImageView;
        private final TextView pageNameTextView;
        private final Switch isEnabledSwitch;
        private final ImageButton pageMoreImageButton;

        private long rowID;
        private int delayStep;
        private boolean isEnabled;
        private String url;

        // konfiguruje obiekt ViewHolder elementu widoku RecyclerView
        public ViewHolder(View itemView) {
            super(itemView);
            pageNameTextView = (TextView) itemView.findViewById(R.id.pageNameTextView);
            isEnabledSwitch = (Switch) itemView.findViewById(R.id.pageAlertSwitch);
            pageMoreImageButton = (ImageButton) itemView.findViewById(R.id.pageMoreImageButton);
            pageStateImageView = (ImageView) itemView.findViewById(R.id.pageStateImageView);

            setButtonListeners();
            setItemViewListener();

            //TODO Switch
            setIsEnabledSwitchListener();
            //TODO Switch
        }

        private void setButtonListeners() {
            pageMoreImageButton.setOnClickListener(
                    new View.OnClickListener() {
                        @Override
                        public void onClick(View view) {
                            clickListener.onMoreButtonClick(
                                    DbDescription.buildWebsiteItemUri(rowID));
                        }
                    }
            );
        }

        private void setItemViewListener() {
            itemView.setOnClickListener(
                    new View.OnClickListener() {
                        @Override
                        public void onClick(View view) {
                            clickListener.onItemClick(url);
                        }
                    }
            );
        }

        private void setIsEnabledSwitchListener() {
            //TODO Switch
            isEnabledSwitch.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    int newSwitchValue = swapBooleanToInt(isEnabled);
                    clickListener.onSwitchClick(
                            DbDescription.buildWebsiteItemUri(rowID), newSwitchValue);
                }
            });

            /*isEnabledSwitch.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
                public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                    // do something, the isChecked will be
                    // true if the switch is in the On position
                    int pom;
                    if(isChecked)
                        pom = 1;
                    else
                        pom = 0;
                    Log.d("ViewHolder: ", "setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener()");
                    clickListener.onSwitchClick(
                            DbDescription.buildWebsiteItemUri(rowID), pom);
                }
            });*/
        }

        // określ identyfikator rzędu bazy danych strony
        // znajdującego się w tym obiekcie ViewHolder
        public void setRowID(long rowID) {
            this.rowID = rowID;
        }
    }

    // ustala nowy element listy i jego obiekt ViewHolder
    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(
                R.layout.list_item, parent, false);
        return new ViewHolder(view);
    }

    // określa tekst elementu listy w celu wyświetlenia etykiety zapytania
    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        Log.d("ADAPTER: ", "onBindViewHolder(ViewHolder holder, int position)");
        cursor.moveToPosition(position);
        holder.setRowID(cursor.getLong(cursor.getColumnIndex(DbDescription.KEY_ID)));
        holder.pageNameTextView.setText(cursor.getString(cursor.getColumnIndex(
                DbDescription.KEY_NAME)));
        holder.url = cursor.getString(cursor.getColumnIndex(DbDescription.KEY_URL));

        //TODO Switch
        setIsEnabledSwitchState(holder);
        //TODO Switch

        setPageStateImage(holder);
    }

    private void setPageStateImage(ViewHolder holder) {
        int updated = cursor.getInt(cursor.getColumnIndex(DbDescription.KEY_UPDATED));
        View view = holder.pageStateImageView.getRootView();

        if(updated == 1)
            holder.pageStateImageView.setImageDrawable(view.getResources()
                    .getDrawable(R.drawable.ic_updated_black_24dp));
        else //updated == 0
            holder.pageStateImageView.setImageDrawable(view.getResources()
                    .getDrawable(R.drawable.ic_not_updated_black_24dp));
    }

    private void setIsEnabledSwitchState(ViewHolder holder) {
        Log.d("ADAPTER: ", "setIsEnabledSwitchState(ViewHolder holder)");
        int checked = cursor.getInt(cursor.getColumnIndex(DbDescription.KEY_ISENABLED));
        setIsEnabledSwitchCheck(holder, (checked == 1) ? true : false);
    }

    private void setIsEnabledSwitchCheck(ViewHolder holder, boolean checked) {
        Log.d("ADAPTER: ", "setIsEnabledSwitchState(ViewHolder holder)");
        holder.isEnabledSwitch.setChecked(checked);
        holder.isEnabled = checked;
    }

    // zwraca liczbę elementów wiązanych przez adapter
    @Override
    public int getItemCount() {
        return (cursor != null) ? cursor.getCount() : 0;
    }

    // zamień bieżący obiekt Cursor adaptera na nowy
    public void swapCursor(Cursor cursor) {
        this.cursor = cursor;
        notifyDataSetChanged();
    }

    public int swapBooleanToInt(boolean value) {
        if(value)
            return 0;
        else
            return 1;
    }

}
