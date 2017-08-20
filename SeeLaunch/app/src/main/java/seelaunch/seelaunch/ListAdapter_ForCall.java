package seelaunch.seelaunch;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.TextView;

import java.util.ArrayList;

/**
 * Created by Fusion on 02-Aug-16.
 */
public class ListAdapter_ForCall extends ArrayAdapter<String> {
    customButtonListener customListner1;

    public interface customButtonListener {
        public void onButtonClickListner(int position,String value);
    }

    public void setCustomButtonListner(customButtonListener listener) {
        this.customListner1 = listener;
    }

    private Context context;
    private ArrayList<String> NameNumber = new ArrayList<String>();

    public ListAdapter_ForCall(Context context, ArrayList<String> NameNumber) {
        super(context, R.layout.childlistview1, NameNumber);
        this.NameNumber = NameNumber;
        this.context = context;
    }

    @Override
    public View getView(final int position, View convertView, ViewGroup parent) {
        ViewHolder viewHolder;
        if (convertView == null) {
            LayoutInflater inflater = LayoutInflater.from(context);
            convertView = inflater.inflate(R.layout.childlistview1, null);
            viewHolder = new ViewHolder();
            viewHolder.text = (TextView) convertView
                    .findViewById(R.id.childTextView1);
            viewHolder.button = (Button) convertView
                    .findViewById(R.id.childButton1);
            convertView.setTag(viewHolder);
        } else {
            viewHolder = (ViewHolder) convertView.getTag();
        }
        final String temp = getItem(position);
        viewHolder.text.setText(temp);
        viewHolder.button.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                if (customListner1 != null) {
                    customListner1.onButtonClickListner(position,temp);
                }

            }
        });

        return convertView;



    }

    public class ViewHolder {
        TextView text;
        Button button;
    }
}
