package br.ufrn.imd.campusteca.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.List;

import br.ufrn.imd.campusteca.R;
import br.ufrn.imd.campusteca.model.ListViewItem;

/**
 * Created by Pablo Gabriel on 07/10/2015.
 */
public class ListViewAdapter extends BaseAdapter {
    private LayoutInflater mInflater;
    private List<ListViewItem> itens;

    public ListViewAdapter(Context context, List<ListViewItem> itens) {
        this.mInflater = LayoutInflater.from(context);
        this.itens = itens;
    }

    public int getCount() {
        return itens.size();
    }

    public ListViewItem getItem(int position) {
        return itens.get(position);
    }

    public long getItemId(int position) {
        return position;
    }

    public View getView(int position, View view, ViewGroup parent) {
        ListViewItem item = itens.get(position);

        view = mInflater.inflate(R.layout.item_list_view, null);

        ((TextView) view.findViewById(R.id.bookTitleTextView)).setText(item.getTitle());
        ((ImageView) view.findViewById(R.id.bookImageView)).setImageResource(item.getImage());

        return view;
    }
}
