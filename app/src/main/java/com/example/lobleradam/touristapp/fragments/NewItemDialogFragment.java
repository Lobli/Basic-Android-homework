package com.example.lobleradam.touristapp.fragments;

import android.app.Dialog;
import android.content.DialogInterface;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.DialogFragment;
import android.support.v4.app.FragmentActivity;
import android.support.v7.app.AlertDialog;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.Spinner;

import com.example.lobleradam.touristapp.R;
import com.example.lobleradam.touristapp.data.MonumentItem;

public class NewItemDialogFragment extends DialogFragment {

    private EditText nameEditText;
    private EditText descriptionEditText;
    private EditText countryEditText;
    private EditText cityEditText;
    private Spinner categorySpinner;

    public static final String TAG = "NewItemDialogFragment";

    public interface NewItemDialogListener {
        void onItemCreated(MonumentItem newItem);
    }

    private NewItemDialogListener listener;

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        FragmentActivity activity = getActivity();
        if (activity instanceof NewItemDialogListener) {
            listener = (NewItemDialogListener) activity;
        } else {
            throw new RuntimeException("Can't find the NewItemDialoglistener interface");
        }
    }

    private View getContentView() {
        View contentView = LayoutInflater.from(getContext()).inflate(R.layout.dialog_new_item, null);
        nameEditText = contentView.findViewById(R.id.ItemNameEditText);
        descriptionEditText = contentView.findViewById(R.id.ItemDescriptionEditText);
        countryEditText = contentView.findViewById(R.id.ItemCountryEditText);
        cityEditText = contentView.findViewById(R.id.ItemCityEditText);
        categorySpinner = contentView.findViewById(R.id.ItemCategorySpinner);


        categorySpinner.setAdapter(new ArrayAdapter<>(requireContext(),
                android.R.layout.simple_spinner_dropdown_item,
                getResources().getStringArray(R.array.category_items)));

        return contentView;
    }


    private MonumentItem getItem() {
        MonumentItem Item = new MonumentItem();
        Item.name = nameEditText.getText().toString();
        Item.description = descriptionEditText.getText().toString();
        Item.country = countryEditText.getText().toString();
        Item.city = cityEditText.getText().toString();
        Item.category = MonumentItem.Category.getByOrdinal(categorySpinner.getSelectedItemPosition());
        return Item;
    }

    @NonNull
    @Override
    public Dialog onCreateDialog(Bundle savedInstanceState) {
        return new AlertDialog.Builder(requireContext())
                .setTitle(R.string.new_monument_item)
                .setView(getContentView())
                .setPositiveButton(R.string.add, new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                        if (nameEditText.getText().length() > 0) {
                            listener.onItemCreated(getItem());
                        }
                    }
                })
                .setNegativeButton(R.string.cancel, null)
                .create();
    }
}
